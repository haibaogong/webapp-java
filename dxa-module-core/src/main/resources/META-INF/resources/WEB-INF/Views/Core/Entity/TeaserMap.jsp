<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.Teaser" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<div class="${entity.htmlClasses}" ${markup.entity(entity)}>
    <h3 ${markup.property(entity, "headline")}>
        <c:choose>
            <c:when test="${not empty entity.link.url}">
                <a href="${entity.link.url}">${entity.headline}</a>
            </c:when>
            <c:otherwise>
                ${entity.headline}
            </c:otherwise>
        </c:choose>
    </h3>
    <c:if test="${not empty entity.location}">
        <dxa:googlestaticmap latitude="${entity.location.latitude}" longitude="${entity.location.longitude}"
                             markerName="${entity.headline}" mapWidth="311" mapHeight="160"/>
    </c:if>
</div>
