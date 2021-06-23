<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.BackgroundPicture" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>
<jsp:useBean id="screenWidth" type="com.sdl.webapp.common.api.ScreenWidth" scope="request"/>

<BackgroundPicture class="rich-text ${entity.htmlClasses}" ${markup.entity(entity)}>

    <div class="mc_banner john_div">
        <div class="mc_banner_bg mc_lbjq">
            <div class="john_div1" ${markup.property(entity, "image")}>
                <dxa:media media="${entity.image}" aspect="3.3" />
            </div>
        </div>
        <div class="mc_banner_cotainer g_content yxedr_active">
            <h4 class="mc_banner_titbox" style="white-space: normal;">

                    <c:if test="${not empty entity.headline}">
                       <span ${markup.property(entity, "headline")} class="mc_banner_title mc_banner_title_b2 mc_block">
                           ${entity.headline}
                       </span>
                   </c:if>

            </h4><p>
            <span class="mc_banner_title mc_banner_title_b2 mc_block"><br/></span></p>
            <h4 class="mc_banner_titbox">

                    <c:if test="${not empty entity.content}">
                        <span ${markup.property(entity, "content")} class="mc_banner_subtitle mc_banner_subtitle_b2 mc_block">
                            ${entity.content}
                        </span>
                    </c:if>
                <br/>
            </h4>
        </div>

   </div>
</BackgroundPicture>
