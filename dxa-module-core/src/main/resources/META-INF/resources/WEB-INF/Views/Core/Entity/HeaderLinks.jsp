<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.LinkList" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<c:if test="${not empty entity.links}">
    <ul class="nav navbar-nav utility-nav ${entity.htmlClasses}" ${markup.entity(entity)}>
        <c:forEach var="link" items="${entity.links}" varStatus="status">
            <li ${markup.property(entity, "links", status.index)}>
                <dxa:link link="${link}"/>
            </li>
        </c:forEach>
    </ul>
</c:if>
