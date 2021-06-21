<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.sdl.dxa.modules.docs.mashup.models.widgets.Topic" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>
<jsp:useBean id="entity" type="com.sdl.dxa.modules.docs.mashup.models.widgets.DynamicWidget" scope="request"/>
<jsp:useBean id="localization" type="com.sdl.webapp.common.api.localization.Localization" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>

<div class="rich-text ${entity.htmlClasses}" ${markup.entity(entity)}>

    <%
	    List<Topic> topics =  entity.getTopics();

        if(topics!= null && topics.size() > 0)
        {
	        String lastId = topics.get(topics.size() - 1).getId();

            for (Topic topic : topics)
            {
                if(entity.getDisplayContentAs().toLowerCase().equals("embedded content"))
                {
    %>
                    <dxa:entity entity="<%=topic%>"/>
    <%
                 }
                 else
                 {
    %>
                    <div ${markup.property(entity, "topics")}>
                      <a href="<%= topic.getLink() %>" target="_blank"><%= topic.getTitle()%></a>
                    </div>
    <%
                 }

		 if (!topic.getId().equals(lastId))
		 {
    %>
		   <br />
    <%
		 }

             }
         }
	else
	 {
    %>
            <xpm:if-enabled>
                <span>&nbsp;</span>
            </xpm:if-enabled>
    <%
	 }
    %>

</div>
