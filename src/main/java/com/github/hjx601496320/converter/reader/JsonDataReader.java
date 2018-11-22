package com.github.hjx601496320.converter.reader;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * json格式的报文解析
 */
public class JsonDataReader extends AbstractDataReader implements DataReader {

    /**
     * 如果报文类型是数组形式,放在freeMarker中的根节点为list
     */
    static final String ROOT_NAME = "list";

    /**
     * 解析json格式的报文
     *
     * @param stringData
     * @return
     */
    public Map<String, Object> read(String stringData) throws Exception {
        this.beConvertData = stringData;
        for (char c : stringData.toCharArray()) {
            if (c == '\u0000') {
                continue;
            }
            if (c == '[') {
                ArrayList list = JSONObject.parseObject(stringData, ArrayList.class);
                this.requestData = new HashMap(1);
                this.requestData.put(ROOT_NAME, list);
                return this.requestData;
            }
            if (c == '{') {
                this.requestData = JSONObject.parseObject(stringData, HashMap.class);
                return this.requestData;
            }
        }
        throw new RuntimeException("解析json异常:\n" + stringData);
    }

}
