package com.github.hjx601496320.converter.reader;

import org.junit.Test;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class XmlDataReaderTest {

    static String getXmlData() {
        StringBuilder sb = new StringBuilder();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml");
        Scanner scanner = new Scanner(resourceAsStream);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        return sb.toString();
    }

    @Test
    public void read() throws Exception {
        DataReader dataReader = new XmlDataReader();
        String xmlData = getXmlData();
        Map<String, Object> read = dataReader.read(xmlData);
        System.out.println(read);
    }

    @Test
    public void read1() throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xml");
        DataReader dataReader = new XmlDataReader();
        Map<String, Object> map = dataReader.read(resourceAsStream);
        System.out.println(map);
    }
}