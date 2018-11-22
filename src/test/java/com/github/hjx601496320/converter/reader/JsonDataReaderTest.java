package com.github.hjx601496320.converter.reader;

import org.junit.Test;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class JsonDataReaderTest {

    static String getJsonData() {
        StringBuilder sb = new StringBuilder();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("json.json");
        Scanner scanner = new Scanner(resourceAsStream);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        return sb.toString();
    }

    @Test
    public void read() throws Exception {
        DataReader dataReader = new JsonDataReader();
        Map<String, Object> read = dataReader.read(getJsonData());
        System.out.println(read);
    }

    @Test
    public void read1() throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("json.json");
        DataReader dataReader = new JsonDataReader();
        Map<String, Object> read = dataReader.read(resourceAsStream);
        System.out.println(read);
    }
}