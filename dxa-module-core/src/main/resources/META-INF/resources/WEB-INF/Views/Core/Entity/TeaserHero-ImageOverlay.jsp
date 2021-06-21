<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.Teaser" scope="request"/>

<div class="hero ${entity.htmlClasses}">
    <c:set var="item" value="${entity}" scope="request"/>
    <c:import url="/WEB-INF/Views/Core/Entity/Partials/Teaser-ImageOverlay.jsp"/>
</div>
