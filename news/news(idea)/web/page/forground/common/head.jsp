<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
    <div class="span12">
        <img src="${pageContext.request.contextPath}/images/logo.png">
    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <div class="navbar">
            <div class="navbar-inner">
                <a class="brand" href="goIndex">首页</a>
                <ul class="nav">
                    <%--新闻类别--%>
                    <c:forEach var="newsType" items="${newsTypeList}">
                        <li><a href="news?action=list&typeId=${newsType.newsTypeId}">${newsType.typeName}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>
