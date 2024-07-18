<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row-fluid">
    <div class="span12">
        <div class="link">
            <div class="linkHeader">友情链接</div>
            <div class="datas">
                <ul>
                    <c:forEach var="link" items="${linkList}">
                        <li><a href="${link.linkUrl}" target="_blank">${link.linkName}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>