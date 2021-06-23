<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="dxa" uri="http://www.sdl.com/tridion-dxa" %>
<%@ taglib prefix="xpm" uri="http://www.sdl.com/tridion-xpm" %>

<jsp:useBean id="entity" type="com.sdl.dxa.modules.core.model.entity.SolutionsContentList" scope="request"/>
<jsp:useBean id="markup" type="com.sdl.webapp.common.markup.Markup" scope="request"/>
<jsp:useBean id="screenWidth" type="com.sdl.webapp.common.api.ScreenWidth" scope="request"/>

<SolutionsContent class="rich-text ${entity.htmlClasses}" ${markup.entity(entity)}>

    <div>
        <c:forEach var="para" items="${entity.content}" varStatus="status">
            <div class="">
                <ul class="mo_pro_list">
                <c:if test="${status.count % 2 eq 1}">
                    <!--左文右图-->
                    <li class="mo_pro_li mo_zwyt">
                        <div class="g_content">
                            <div class="mo_pro_lisbox clearfix">
                                <div class="mo_pro_word fr">
                                    <div class="mo_pro_txtbox">
                                        <div class="mo_pro_txt yxedr_active">
                                            <h3 class="mo_pro_title">
                                                <c:if test="${not empty para.headline}">
                                                   <div ${markup.property(para, "headline")}>
                                                       ${para.headline}
                                                   </div>
                                               </c:if>
                                            </h3>
                                            <c:if test="${not empty para.content}">
                                                 <p ${markup.property(para, "content")} >
                                                       ${para.content}
                                                 </p>
                                            </c:if>
                                            <c:if test="${not empty para.download.url}">
                                                <div class="mo_pro_btnbox mo_pro_btns">
                                                     <a href="${para.download.url}" target="_blank">
                                                        ${para.download.linkText}
                                                     </a>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="mo_pro_pic fl">
                                    <a href="javascript:void(0)">
                                        <div class="mo_pro_imgbox mc_list_imgbox">
                                        <%//<img src="/Public/Cn/images/c2img.png" alt="" class="mc_list_png"> %>
                                            ${para.image}
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:if>
                <c:if test="${status.count % 2 eq 0}">
                    <!--左图右文-->
                    <li class="mo_pro_li mo_ztyw">
                        <div class="g_content">
                            <div class="mo_pro_lisbox clearfix">
                                <div class="mo_pro_word fr">
                                    <div class="mo_pro_txtbox">
                                        <div class="mo_pro_txt yxedr_active">
                                            <h3 class="mo_pro_title">
                                                <c:if test="${not empty para.headline}">
                                                    <div ${markup.property(para, "headline")}>
                                                        ${para.headline}
                                                    </div>
                                                </c:if>
                                            </h3>
                                             <c:if test="${not empty para.content}">
                                                <p ${markup.property(para, "content")}>
                                                        ${para.content}
                                                 </p>
                                              </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="mo_pro_pic fl">
                                    <a href="javascript:void(0)">
                                        <div class="mo_pro_imgbox mc_list_imgbox">
                                         <%//<img src="/Public/Cn/images/c2img.png" alt="" class="mc_list_png"> %>
                                            ${para.image}
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </li>
                </c:if>
            </div>
        </c:forEach>
    </div>
</SolutionsContent>
