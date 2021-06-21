<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.Heading" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>
<jsp:useBean id="screenWidth" type="com.sdl.webapp.common.api.ScreenWidth" scope="request"/>
<%=entity%>

<Heading class="rich-text ${entity.htmlClasses}" ${markup.entity(entity)}>

    <div id="myButtons2" class="bs-example">
        <button type="button" class="btn btn-primary"
                data-loading-text="Loading...">原始
        </button>
    </div>

<%--    <c:choose>--%>
<%--        <c:when test="${not empty entity.image and screenWidth != 'EXTRA_SMALL'}">--%>
<%--            <div class="hero" ${markup.property(entity, "image")}>--%>
<%--                <dxa:media media="${entity.image}" aspect="3.3"/>--%>
<%--                <div class="overlay overlay-tl ribbon">--%>
<%--                    <h1 ${markup.property(entity, "headline")}>${entity.headline}</h1>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <h1 ${markup.property(entity, "headline")}>${entity.headline}</h1>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>
    <c:if test="${not empty entity.headline}">
        <div ${markup.property(entity, "headline")} >
                ${entity.headline}
        </div>
    </c:if>
    <c:if test="${not empty entity.date}">
        <div class="meta" ${markup.property(entity, "date")}>
                ${markup.formatDate(entity.date)}
        </div>
    </c:if>
</Heading>
