package com.github.hjx601496320.converter;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据转换枚举
 */
public class DataEnum {

    /**
     * 节点-值对应转换映射
     */
    private Map<String, Map<String, Object>> nodeDataMapping = new HashMap<String, Map<String, Object>>();

    /**
     * 根据节点获取值对应转换map
     *
     * @param nodeName
     * @return
     */
    public Map<String, Object> getDataMapping(String nodeName) {
        if (nodeDataMapping.containsKey(nodeName)) {
            return nodeDataMapping.get(nodeName);
        }
        return null;
    }

    /**
     * 添加节点获取值对应转换map
     *
     * @param nodeName
     * @param objectMap
     */
    public void addDataMapping(String nodeName, Map<String, Object> objectMap) {
        if (nodeDataMapping.containsKey(nodeName)) {
            Map<String, Object> map = nodeDataMapping.get(nodeName);
            map.putAll(objectMap);
        } else {
            nodeDataMapping.put(nodeName, objectMap);
        }
    }
}
