<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<jsp:useBean id="entity" type="com.sdl.dxa.modules.docs.mashup.models.products.Bicycle" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<div class="rich-text ${entity.htmlClasses}" ${markup.entity(entity)}>

    <div ${markup.property(entity, "title")}>
        <h1><%= entity.getTitle() %></h1>
    </div>

    <div class="content">
        <div ${markup.property(entity, "body")}>
            <dxa:richtext content="${entity.getBody()}" />
        </div>

        <div ${markup.property(entity, "image")}>
            <dxa:media media="${entity.getImage()}" />
        </div>
    </div>
</div>
