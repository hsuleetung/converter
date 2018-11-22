package com.github.hjx601496320.converter.reader;

import com.github.hjx601496320.converter.FreeMarkerFtl;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractDataReaderTest {

    @Test
    public void converterFormat() throws Exception {
        DataReader dataReader = new XmlDataReader();
        dataReader.read(
                "<xml>\n" +
                        "    <uuid>123123asdasdasdasd</uuid>\n" +
                        "    <people>\n" +
                        "        <sex>男</sex>\n" +
                        "        <age>20</age>\n" +
                        "        <name>王麻子</name>\n" +
                        "        <child>\n" +
                        "            <sex>女</sex>\n" +
                        "            <age>201</age>\n" +
                        "            <name>李傻子</name>\n" +
                        "        </child>\n" +
                        "        <child>\n" +
                        "            <sex>男</sex>\n" +
                        "            <age>201</age>\n" +
                        "            <name>王大锤</name>\n" +
                        "        </child>\n" +
                        "    </people>\n" +
                        "</xml>"
        );
        FreeMarkerFtl freeMarkerFtl = new FreeMarkerFtl(
                "<xml>\n" +
                        "    <uuid>${uuid}</uuid>\n" +
                        "    <sex>${people.sex}</sex>\n" +
                        "    <name>${people.name}</name>\n" +
                        "        <#list people.child as obj>\n" +
                        "        <ps>\n" +
                        "            <name>${obj.name}</name>\n" +
                        "            <age>${obj.age}</age>\n" +
                        "            <p.sex>${obj.sex}</p.sex>\n" +
                        "        </ps>\n" +
                        "        </#list>\n" +
                        "</xml>",
                "text"
        );
        String data = dataReader.converterFormat(freeMarkerFtl);
        System.out.println(data);
    }
}