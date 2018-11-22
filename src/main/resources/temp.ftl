<?xml version="1.0" encoding="UTF-8" ?>
<xml>
    <uuid>${uuid}</uuid>
    <sex>${people.sex}</sex>
    <name>${people.name}</name>
        <#list people.manList.people as obj>
        <ps>
            <name>${obj.name}</name>
            <age>${obj.age}</age>
            <p.sex>${obj.sex}</p.sex>
        </ps>
        </#list>
</xml>

