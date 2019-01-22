package com.github.hjx601496320.converter.reader;

import com.github.hjx601496320.converter.FreeMarkerFtl;
import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class JsonDataReaderTest {

    static String getData(String name) {
        StringBuilder sb = new StringBuilder();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        Scanner scanner = new Scanner(resourceAsStream);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        return sb.toString();
    }

    @Test
    public void readJson2Xml() throws Exception {
        DataReader dataReader = new JsonDataReader();
        //读取数据
        dataReader.read(getData("test.json"));
        //设置模板
        FreeMarkerFtl freeMarkerFtl = new FreeMarkerFtl();
        freeMarkerFtl.setTemplateName("xml.ftl");
        freeMarkerFtl.setTemplateText(getData("xml.ftl"));
        //转换结果
        String res = dataReader.converterFormat(freeMarkerFtl);
        System.out.println(res);
    }

    @Test
    public void readJson2Json() throws Exception {
        DataReader dataReader = new JsonDataReader();
        //读取数据
        dataReader.read(getData("test.json"));
        //设置模板
        FreeMarkerFtl freeMarkerFtl = new FreeMarkerFtl();
        freeMarkerFtl.setTemplateName("json.ftl");
        freeMarkerFtl.setTemplateText(getData("json.ftl"));
        //转换结果
        String res = dataReader.converterFormat(freeMarkerFtl);
        System.out.println(res);
    }

}