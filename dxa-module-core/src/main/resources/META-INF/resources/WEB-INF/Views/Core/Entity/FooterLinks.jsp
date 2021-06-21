<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.LinkList" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<ul class="list-inline text-center ${entity.htmlClasses}" ${markup.entity(entity)}>
    <c:if test="${not empty entity.headline}">
        <li>
            <small ${markup.property(entity, "headline")}>${entity.headline}</small>
        </li>
    </c:if>
    <c:forEach var="link" items="${entity.links}" varStatus="status">
        <li>
            <small ${markup.property(entity, "links", status.index)}>
                <dxa:link link="${link}"/>
            </small>
        </li>
    </c:forEach>
</ul>
