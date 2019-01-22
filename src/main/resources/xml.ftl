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

