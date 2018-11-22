# 报文格式转换工具：converter
```$xslt
通过将xml/json 形式的报文解析成为map，然后通过自定义的freemarker模板转换成为需要的格式。
完成 请求报文的自定义转换
```
### xml to map
```$xslt
String xml = "<xml>\n" +
                "    <uuid>123123asdasdasdasd</uuid>\n" +
                "    <people>\n" +
                "        <sex>男</sex>\n" +
                "        <age>20</age>\n" +
                "        <name>王麻子</name>\n" +
                "        <people>\n" +
                "            <sex>女</sex>\n" +
                "            <age>201</age>\n" +
                "            <name>李傻子</name>\n" +
                "        </people>\n" +
                "        <people>\n" +
                "            <sex>男</sex>\n" +
                "            <age>201</age>\n" +
                "            <name>王大锤</name>\n" +
                "        </people>\n" +
                "    </people>\n" +
                "</xml>";
        DataReader dataReader = new XmlDataReader();
        Map<String, Object> map = dataReader.read(xml);
```
### json to map
```$xslt
        String json = "[\n" +
                "  {\n" +
                "    \"dataInt\": 1,\n" +
                "    \"dataStr\": \"D1-C\",\n" +
                "    \"databoo\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"dataInt\": 0,\n" +
                "    \"dataStr\": \"D1-C\",\n" +
                "    \"databoo\": false\n" +
                "  }\n" +
                "]";
        DataReader dataReader = new JsonDataReader();
        Map<String, Object> map = dataReader.read(json);
```
### xml 根据模板转换格式
```$xslt
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
        
        //加载freemarker模板
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
        //转换格式
        String data = dataReader.converterFormat(freeMarkerFtl);
        System.out.println(data);
        /*
         转换结果
        <xml>
            <uuid>123123asdasdasdasd</uuid>
            <sex>男</sex>
            <name>王麻子</name>
                <ps>
                    <name>李傻子</name>
                    <age>201</age>
                    <p.sex>女</p.sex>
                </ps>
                <ps>
                    <name>王大锤</name>
                    <age>201</age>
                    <p.sex>男</p.sex>
                </ps>
        </xml>
        */
```
