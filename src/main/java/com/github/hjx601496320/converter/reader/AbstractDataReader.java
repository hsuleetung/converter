package com.github.hjx601496320.converter.reader;

import com.github.hjx601496320.converter.DataEnum;
import com.github.hjx601496320.converter.FreeMarkerFtl;
import com.github.hjx601496320.converter.FreeMarkerUtils;

import java.io.InputStream;
import java.util.*;

/**
 * 基础的数据读取类
 */
public abstract class AbstractDataReader implements DataReader {

    /**
     * 报文转换后的参数
     */
    protected Map<String, Object> requestData;

    /**
     * 要转换的原始数据
     */
    protected String beConvertData;

    /**
     * 数据转换枚举
     */
    protected DataEnum dataEnum;

    /**
     * 读取流中的内容
     *
     * @param inputStream
     * @return
     * @throws Exception
     */
    public void read(InputStream inputStream) throws Exception {
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String data = new String(bytes);
        read(data);
    }

    /**
     * * 根据请求参数，填充要转换后的数据格式
     * * 使其一一对应
     *
     * @param ftl 要转换的报文freeMarke模板
     * @return
     */
    public String converterFormat(FreeMarkerFtl ftl) throws Exception {
        if (this.requestData == null) {
            throw new RuntimeException("转换参数异常, 原始数据: \n" + this.beConvertData);
        }
        Map<String, Object> map = new HashMap<String, Object>(this.requestData);
        foreachForChangeData(map);
        return FreeMarkerUtils.format(map, ftl);
    }

    /**
     * 获取请求的数据结构
     *
     * @return
     */
    public Set<String> getDataNodeNames() {
        Set<String> stringSet = new HashSet<String>();
        foreach(requestData, stringSet);
        return stringSet;
    }

    /**
     * 设置节点数据转换枚举
     *
     * @param dataEnum
     */
    public void setDataEnum(DataEnum dataEnum) {
        this.dataEnum = dataEnum;
    }

    /**
     * 递归转换原始数据
     *
     * @param data
     */
    void foreachForChangeData(Object data) {
        if (data instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) data;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                //freemarker 中 Boolean  将其修改为 字符串类型
                if (value instanceof Boolean) {
                    entry.setValue(String.valueOf(value));
                }
                boolean notListOrMap = value instanceof List || value instanceof Map;
                if (!notListOrMap && dataEnum != null) {
                    Map<String, Object> dataMapping = dataEnum.getDataMapping(key);
                    if (dataMapping != null) {
                        Object changedValue = dataMapping.get(String.valueOf(value));
                        entry.setValue(changedValue);
                    }
                } else {
                    foreachForChangeData(value);
                }
            }
        }
        if (data instanceof List) {
            for (Object obj : (List) data) {
                foreachForChangeData(obj);
            }
        }
    }

    void foreach(Object data, Set<String> nodePath) {
        if (data instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) data;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                if (nodePath.contains(key)) {
                    continue;
                }
                Object value = entry.getValue();
                nodePath.add(key);
                foreach(value, nodePath);
            }
        }
        if (data instanceof List) {
            for (Object obj : (List) data) {
                foreach(obj, nodePath);
            }
        }
    }
}

