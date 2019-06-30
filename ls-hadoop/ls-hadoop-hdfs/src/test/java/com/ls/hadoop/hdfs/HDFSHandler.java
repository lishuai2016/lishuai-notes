package com.ls.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.net.URI;

/**
 * @program: lishuai-notes
 * @author: lishuai
 * @create: 2019-03-13 13:25
 */
public class HDFSHandler {

    @Test
    public void downLoad() throws Exception{
        FileSystem fs = FileSystem.get(
                new URI("hdfs://127.0.0.1:9000"),
                new Configuration());
        fs.copyToLocalFile(new Path(""), new Path(""));
    }


    @Test
    public void test(){
        System.out.println("sasdasd");
    }
    public static void main(String[] args) {


    }
    @Test
    public void StringBuild(){
        StringBuilder sb = new StringBuilder();
        sb.append("a");
        String result = sb.toString();
        System.out.println(result);
    }

}
