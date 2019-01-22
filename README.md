# 报文格式转换工具：converter
## 功能描述：

可以把**xml**/**json**形式的数据根据自定义的模板来转换成为你想要的任何格式。

需要用到 **freemarker** 方面的知识。

## 示例

### josn to json

1.  转换代码

    ```java
    DataReader dataReader = new JsonDataReader();
    //读取数据
    dataReader.read("json 格式数据");
    //设置模板
    FreeMarkerFtl freeMarkerFtl = new FreeMarkerFtl();
    freeMarkerFtl.setTemplateName("模板名称");
    freeMarkerFtl.setTemplateText("模板数据");
    //转换结果
    String res = dataReader.converterFormat(freeMarkerFtl);
    ```

2.  模板

    ```xml
    {
      "uuid": "${uuid}",
      "p-sex": "${people.sex}",
      "p-name": "${people.name}",
      "list": [
    <#list people.people as obj>
        {
          "c-name": "${obj.name}",
          "c-age": "${obj.age}",
          "c-sex": "${obj.sex}"
        }<#if obj_has_next>,</#if>
    </#list>
      ]
    }
    ```

3.  数据 

    ```json
    {
      "uuid": "123456789032sdfgh",
      "people": {
        "sex": "男",
        "age": "20",
        "name": "王麻子",
        "people": [
          {
            "sex": "女",
            "age": "18",
            "name": "李傻子"
          },
          {
            "sex": "男",
            "age": "21",
            "name": "王大锤"
          }
        ]
      }
    }
    ```

4.   结果

     ```json
     {
         "uuid":"123456789032sdfgh",
         "p-sex":"男",
         "p-name":"王麻子",
         "list":[
             {
                 "c-name":"李傻子",
                 "c-age":"18",
                 "c-sex":"女"
             },
             {
                 "c-name":"王大锤",
                 "c-age":"21",
                 "c-sex":"男"
             }
         ]
     }
     ```


     ​    

###  json to xml

1.  只需要修改模板即可

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <xml>
        <uuid>${uuid}</uuid>
        <p-sex>${people.sex}</p-sex>
        <p-name>${people.name}</p-name>
    <#list people.people as obj>
            <list>
                <c-name>${obj.name}</c-name>
                <c-age>${obj.age}</c-age>
                <c-sex>${obj.sex}</c-sex>
            </list>
    </#list>
    </xml>
    
    ```

    

### xml to xml

1.  转换代码

    ```java
    DataReader dataReader = new XmlDataReader();
    //读取数据
    dataReader.read("xml 格式数据");
    //设置模板
    FreeMarkerFtl freeMarkerFtl = new FreeMarkerFtl();
    freeMarkerFtl.setTemplateName("模板名称");
    freeMarkerFtl.setTemplateText("模板数据");
    //转换结果
    String res = dataReader.converterFormat(freeMarkerFtl);
    ```

2.  模板

    ```xml
    <xml>
        <uuid>${uuid}</uuid>
        <p-sex>${people.sex}</p-sex>
        <p-name>${people.name}</p-name>
    <#list people.people as obj>
            <list>
                <c-name>${obj.name}</c-name>
                <c-age>${obj.age}</c-age>
                <c-sex>${obj.sex}</c-sex>
            </list>
    </#list>
    </xml>
    ```

3. 数据

   ```xml
   <xml>
       <uuid>123456789032sdfgh</uuid>
       <people>
           <sex>男</sex>
           <age>20</age>
           <name>王麻子</name>
           <people>
               <sex>女</sex>
               <age>18</age>
               <name>李傻子</name>
           </people>
           <people>
               <sex>男</sex>
               <age>20</age>
               <name>王大锤</name>
           </people>
       </people>
   </xml>
   ```

4.  结果

    ```xml
    <?xml version="1.0" encoding="utf-8"?>
    
    <xml> 
      <uuid>123456789032sdfgh</uuid>  
      <p-sex>男</p-sex>  
      <p-name>王麻子</p-name>  
      <list> 
        <c-name>李傻子</c-name>  
        <c-age>18</c-age>  
        <c-sex>女</c-sex> 
      </list>  
      <list> 
        <c-name>王大锤</c-name>  
        <c-age>20</c-age>  
        <c-sex>男</c-sex> 
      </list>
    </xml>
    
    ```

### xml to json

1.  修改模板即可

    ```xml
    {
      "uuid": "${uuid}",
      "p-sex": "${people.sex}",
      "p-name": "${people.name}",
      "list": [
    <#list people.people as obj>
        {
          "c-name": "${obj.name}",
          "c-age": "${obj.age}",
          "c-sex": "${obj.sex}"
        }<#if obj_has_next>,</#if>
    </#list>
      ]
    }
    ```

    


​           