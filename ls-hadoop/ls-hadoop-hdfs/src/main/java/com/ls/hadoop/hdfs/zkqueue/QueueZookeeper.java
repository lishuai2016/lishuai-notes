package com.ls.hadoop.hdfs.zkqueue;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
/**
 * 分布式队列zookeeper调度
 * @author Jon_China
 *
 */
public class QueueZookeeper {
    //设置队列目录树
    final public static String QUEUE = "/queue";
    final public static String PROFIT = "/queue/profit";
    final public static String PURCHASE = "/queue/purchase";
    final public static String SELL = "/queue/sell";
    final public static String OTHER = "/queue/other";

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Please start a task:");
        } else {
            doAction(Integer.parseInt(args[0]));
        }
    }
    public static void doAction(int client) throws Exception {
        //zookeeper地址
        String host1 = "192.168.8.104:2181";
        String host2 = "192.168.8.105:2181";
        String host3 = "192.168.8.106:2181";

        ZooKeeper zk = null;
        switch (client) {//1,2,3分别将不同任务加入队列
            case 1:
                zk = connection(host1);
                initQueue(zk);
                doPurchase(zk);
                break;
            case 2:
                zk = connection(host2);
                initQueue(zk);
                doSell(zk);
                break;
            case 3:
                zk = connection(host3);
                initQueue(zk);
                doOther(zk);
                break;
        }
    }

    // 创建一个与服务器的连接
    public static ZooKeeper connection(String host) throws IOException {
        ZooKeeper zk = new ZooKeeper(host, 60000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeCreated && event.getPath().equals(PROFIT)) {
                    System.out.println("Queue has Completed!!!");
                }
            }
        });
        return zk;
    }
    /**
     * 初始化队列
     * @param zk
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void initQueue(ZooKeeper zk) throws KeeperException, InterruptedException {
        System.out.println("WATCH => " + PROFIT);
        zk.exists(PROFIT, true);

        if (zk.exists(QUEUE, false) == null) {
            System.out.println("create " + QUEUE);
            zk.create(QUEUE, QUEUE.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            System.out.println(QUEUE + " is exist!");
        }
    }
    /**
     * 采购任务
     * @param zk
     * @throws Exception
     */
    public static void doPurchase(ZooKeeper zk) throws Exception {
        if (zk.exists(PURCHASE, false) == null) {

            Purchase.run(Purchase.path());

            System.out.println("create " + PURCHASE);
            zk.create(PURCHASE, PURCHASE.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            System.out.println(PURCHASE + " is exist!");
        }
        isCompleted(zk);
    }
    /**
     * 销售任务
     * @param zk
     * @throws Exception
     */
    public static void doSell(ZooKeeper zk) throws Exception {
        if (zk.exists(SELL, false) == null) {

            Sell.run(Sell.path());

            System.out.println("create " + SELL);
            zk.create(SELL, SELL.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            System.out.println(SELL + " is exist!");
        }
        isCompleted(zk);
    }
    /**
     * 其他计算任务
     * @param zk
     * @throws Exception
     */
    public static void doOther(ZooKeeper zk) throws Exception {
        if (zk.exists(OTHER, false) == null) {

            Other.calcOther(Other.file);

            System.out.println("create " + OTHER);
            zk.create(OTHER, OTHER.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            System.out.println(OTHER + " is exist!");
        }
        isCompleted(zk);
    }
    /**
     * 检测完成情况
     * @param zk
     * @throws Exception
     */
    public static void isCompleted(ZooKeeper zk) throws Exception {
        int size = 3;
        List<String> children = zk.getChildren(QUEUE, true);
        int length = children.size();

        System.out.println("Queue Complete:" + length + "/" + size);
        if (length >= size) {
            System.out.println("create " + PROFIT);
            Profit.profit();
            zk.create(PROFIT, PROFIT.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            for (String child : children) {// 清空节点
                zk.delete(QUEUE + "/" + child, -1);
            }

        }
    }
}
