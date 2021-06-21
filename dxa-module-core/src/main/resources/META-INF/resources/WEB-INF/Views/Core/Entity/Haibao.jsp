<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.Haibao" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>
<jsp:useBean id="screenWidth" type="com.sdl.webapp.common.api.ScreenWidth" scope="request"/>
<%=entity%>

<Haibao class="rich-text ${entity.htmlClasses}" ${markup.entity(entity)}>

    <div id="myButtons2" class="bs-example">
        <button type="button" class="btn btn-primary"
                data-loading-text="Loading..."> Haibao
        </button>
    </div>

    <c:if test="${not empty entity.name}">
        <div ${markup.property(entity, "name")} >
                ${entity.name}
        </div>
    </c:if>

</Haibao>
