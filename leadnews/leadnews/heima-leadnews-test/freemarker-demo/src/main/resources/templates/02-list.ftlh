<!DOCTYPE html>
<html lang="ch">
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>

<#-- list 数据的展示 -->
<b>展示list中的stu数据:</b>
<br>
<br>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#if stus??>
        集合大小：${stus?size}
        <#list stus as stu>
            <tr>
                <td>${stu_index + 1}</td>
                <td>${stu.name!'无名氏'}</td>
                <td>${stu.age}</td>
                <td>${stu.money}</td>
            </tr>
        </#list>
    <#else >
        <tr>
            <td colspan="4">暂无数据</td>
        </tr>
    </#if>
</table>
<hr>

<#-- Map 数据的展示 -->
<b>map数据的展示：</b>
<br/><br/>
<a href="#">方式一：通过map['key_name'].property</a><br/>
输出stu1的学生信息：<br/>
姓名：${stuMap.stu1.name}<br/>
年龄：${stuMap.stu1.age}<br/>
<br/>
<a href="#">方式二：通过map.key_name.property</a><br/>
输出stu2的学生信息：<br/>
姓名：${stuMap.stu2.name}<br/>
年龄：${stuMap.stu2.age}<br/>

<br/>
<a href="#">遍历map中两个学生信息：</a><br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stuMap?keys as key>
        <#if stuMap[key].name?? && stuMap[key].name == "小红"><#-- 在freemarker中， `==` 和 `=` 是等价的 -->
            <tr style="color: red">
        <#else>
            <tr>
        </#if>
        <td>${key_index + 1}</td>
        <td>${stuMap[key].name!'无名氏'}</td>
        <td>${stuMap[key].age}</td>
        <td>${stuMap[key].money}</td>
        </tr>
    </#list>
</table>
<hr>
<p>
    显示年月日：${today?date}<br>
    显示时分秒：${today?time}<br>
    显示年月日时分秒：${today?datetime}<br>
    自定义格式化：${today?string("yyyy-MM-dd HH:mm:ss")}
</p>
<p>
    数字的展示方式默认是这样的：${point}<br>
    ，你会发现它每隔三位就有一个逗号。<br>
    但是我们可以通过内建函数c来将其转为字符串输出：${point?c}
</p>
</body>
</html>