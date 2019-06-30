package com.ls;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: lishuai-notes
 * @author: lishuai
 * @create: 2019-04-03 16:59
 */
public class CsvTest {

    public static void main(String[] args) throws IOException {
        String[] headers = new String[]{"姓名","年龄"};
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"张三","11"});
        data.add(new String[]{"李四","22"});
        data.add(new String[]{"王五","33"});
        String filePath  = "d:/testcsv.csv";
        //writeCsv(headers,data,filePath);
        writeCsvNotHeaders(headers,data,filePath);
    }


    //CSV文件分隔符
    private final static String NEW_LINE_SEPARATOR="\n";


    /**写入csv文件
     * @param headers 列头
     * @param data 数据内容
     * @param filePath 创建的csv文件路径
     * @throws IOException **/
    public static void writeCsv(String[] headers, List<String[]> data, String filePath) throws IOException {
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter=new FileWriter(filePath);
        //创建CSVPrinter对象
        CSVPrinter printer=new CSVPrinter(fileWriter,formator);
        //写入列头数据
        printer.printRecord(headers);
        if(null!=data){
            //循环写入数据
            for(String[] lineData:data){
                printer.printRecord(lineData);
            }
        }
        printer.close();
        System.out.println("CSV文件创建成功,文件路径:"+filePath);
    }


    public static void writeCsvNotHeaders(String[] headers, List<String[]> data, String filePath) throws IOException {
        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        //创建FileWriter对象
        FileWriter fileWriter=new FileWriter(filePath);
        //创建CSVPrinter对象
        CSVPrinter printer=new CSVPrinter(fileWriter,formator);
        if(null!=data){
            //循环写入数据
            for(String[] lineData:data){

                printer.printRecord(lineData);

            }
        }
        printer.close();
        System.out.println("CSV文件创建成功,文件路径:"+filePath);

    }
}
