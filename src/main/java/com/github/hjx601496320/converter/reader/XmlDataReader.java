package com.github.hjx601496320.converter.reader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml格式的报文解析
 */
public class XmlDataReader extends AbstractDataReader implements DataReader {

    /**
     * 解析xml格式的报文
     *
     * @param stringData
     * @return
     */
    public Map<String, Object> read(String stringData) {
        this.beConvertData = stringData;
        try {
            InputStream inputStream = new ByteArrayInputStream(stringData.getBytes("utf-8"));
            Element rootElement = getRootElement(inputStream);
            this.requestData = foreachElement(rootElement);
            return this.requestData;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("解析xml异常:\n" + stringData);
    }

    /**
     * 递归转换xml
     *
     * @param xmlElement
     * @return
     */
    private Map foreachElement(Element xmlElement) {
        List childrenList = xmlElement.getChildren();
        Map map = new HashMap();
        for (int i = 0; i < childrenList.size(); i++) {
            List list = new ArrayList();
            Element element = (Element) childrenList.get(i);
            String name = element.getName();
            if (element.getChildren().size() > 0) {
                if (xmlElement.getChildren(name).size() > 1) {
                    if (map.containsKey(name)) {
                        list = (List) map.get(name);
                    }
                    list.add(foreachElement(element));
                    map.put(name, list);
                } else {
                    map.put(name, foreachElement(element));
                }
            } else {
                if (xmlElement.getChildren(name).size() > 1) {
                    if (map.containsKey(name)) {
                        list = (List) map.get(name);
                    }
                    list.add(element.getTextTrim());
                    map.put(name, list);
                } else {
                    map.put(name, element.getTextTrim());
                }
            }
        }
        return map;
    }

    /**
     * 获取根节点
     * 添加防止XXE 漏洞代码
     *
     * @param xmlInputStream
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    Element getRootElement(InputStream xmlInputStream) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        //下面是添加的代码
        String feature = "http://apache.org/xml/features/disallow-doctype-decl";
        builder.setFeature(feature, true);
        feature = "http://xml.org/sax/features/external-general-entities";
        builder.setFeature(feature, false);
        feature = "http://xml.org/sax/features/external-parameter-entities";
        builder.setFeature(feature, false);
        feature = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
        builder.setFeature(feature, false);
        Document document = builder.build(xmlInputStream);
        Element rootElement = document.getRootElement();
        return rootElement;
    }
}
