package com.sdl.dxa.modules.ugc.controllers;

import com.sdl.dxa.modules.ugc.UgcService;
import com.sdl.dxa.modules.ugc.data.Comment;
import com.sdl.dxa.modules.ugc.model.entity.UgcComment;
import com.sdl.dxa.modules.ugc.model.entity.UgcComments;
import com.sdl.dxa.modules.ugc.model.entity.UgcPostCommentForm;
import com.sdl.webapp.common.api.content.ContentProviderException;
import com.sdl.webapp.common.api.model.EntityModel;
import com.sdl.webapp.common.api.model.ViewModel;
import com.sdl.webapp.common.controller.EntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Ugc entity controller that handles include requests to <code>/system/mvc/Framework/Ugc/{entityId}</code>.</p>
 */
@Controller
@RequestMapping({"/system/mvc/Framework/Ugc"})
public class UgcController extends EntityController {

    @Autowired
    private UgcService ugcService;

    private static List<UgcComment> createEntities(List<Comment> comments) {

        final List<UgcComment> ugcComments = new ArrayList<>();
        comments.forEach((Comment comment) -> ugcComments.add(createEntity(comment)));
        return ugcComments;
    }

    private static UgcComment createEntity(Comment comment) {
        final UgcComment ugcComment = new UgcComment();

        ugcComment.setComments(createEntities(comment.getChildren()));
        ugcComment.setCommentData(comment);
        return ugcComment;
    }

    @Override
    protected ViewModel enrichModel(ViewModel model, HttpServletRequest request) throws Exception {

        if (model instanceof UgcComments) {
            final ViewModel enrichedModel = super.enrichModel(model, request);
            final UgcComments ugcComments = (UgcComments) (enrichedModel instanceof EntityModel ? enrichedModel : model);
            final List<Comment> comments = ugcService.getComments(ugcComments.getTarget().getPublicationId(),
                    ugcComments.getTarget().getItemId(), false, new Integer[0], 0, 0);
            ugcComments.setComments(createEntities(comments));
            return ugcComments;
        }

        if (model instanceof UgcPostCommentForm) {
            final ViewModel enrichedModel = super.enrichModel(model, request);
            final UgcPostCommentForm postForm = (UgcPostCommentForm) (enrichedModel instanceof EntityModel ? enrichedModel : model);

            postForm.setFormUrl(context.getPage().getUrl());
        }
        return model;
    }

    /**
     * Handles a request for an entity.
     *
     * @param request  The request.
     * @param entityId The entity id.
     * @return The name of the entity view that should be rendered for this request.
     * @throws ContentProviderException If an error occurs so that the entity cannot not be retrieved.
     * @throws java.lang.Exception      if any.
     */
    @RequestMapping({"Entity/{entityId}"})
    public String handleGetEntity(HttpServletRequest request, @PathVariable String entityId) throws Exception {
        return this.handleEntityRequest(request, entityId);
    }

}
