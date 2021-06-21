<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.TagLinkList" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<div class="icon-list ${entity.htmlClasses}" ${markup.entity(entity)}>
    <c:forEach var="link" items="${entity.links}" varStatus="status">
        <a href="${link.url}" class="fa-stack fa-lg"
           title="<dxa:resource key="core.visitUsSocialLinkTitle" arg1="${link.tag.displayText}"/>" ${markup.property(entity, "links", status.index)}>
            <i class="fa fa-circle fa-stack-2x"></i>
            <i class="fa fa-${link.tag.key} fa-stack-1x"></i>
        </a>
    </c:forEach>
</div>
