<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.ContentList" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<div class="${entity.htmlClasses}" ${markup.entity(entity)}>
    <c:if test="${not empty entity.headline}">
        <h3 ${markup.property(entity, "headline")}>${entity.headline}</h3>
    </c:if>
    <ul>
        <c:forEach var="item" items="${entity.itemListElements}">
            <li>
                <c:choose>
                    <c:when test="${not empty item.link.url}">
                        <a href="${item.link.url}" title="${item.link.alternateText}">
                            <c:choose>
                                <c:when test="${not empty item.headline}">${item.headline}</c:when>
                                <c:otherwise>${item.link.url}</c:otherwise>
                            </c:choose>
                        </a>
                    </c:when>
                    <c:otherwise>
                        ${item.headline}
                    </c:otherwise>
                </c:choose>
                <c:if test="${not empty item.date}">
                    <time class="meta small">[${markup.formatDateDiff(item.date)}]</time>
                </c:if>
            </li>
        </c:forEach>
    </ul>
    <c:if test="${not empty entity.link.url}">
        <p ${markup.property(entity.link, "url")}>
                <%-- Explicit XPM property tag as the link text is editable not the link URL --%>
            <xpm:property entity="${entity.link}" property="linkText"/>
            <a href="${entity.link.url}" title="${entity.link.alternateText}">
                <c:choose>
                    <c:when test="${not empty entity.link.linkText}">${entity.link.linkText}</c:when>
                    <c:otherwise>
                        <dxa:resource key="core.readMoreLinkText"/>
                    </c:otherwise>
                </c:choose>
                <i class="fa fa-chevron-right"></i>
            </a>
        </p>
    </c:if>
</div>
