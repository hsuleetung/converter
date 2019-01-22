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