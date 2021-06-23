package com.sdl.dxa.modules.core.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.entity.AbstractEntityModel;
import com.sdl.webapp.common.api.model.entity.Link;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.sdl.webapp.common.api.mapping.semantic.config.SemanticVocabulary.SCHEMA_ORG;

@SemanticEntity(entityName = "SolutionsContent", vocabulary = SCHEMA_ORG, prefix = "s", public_ = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionsContent extends AbstractEntityModel {
    @JsonProperty("Headline")
    @SemanticProperty("s:headline")
    private String headline;

    @JsonProperty("Content")
    @SemanticProperty("s:content")
    private String content;

    @JsonProperty("Download")
    @SemanticProperty("s:download")
    private Link download;

    @JsonProperty("Image")
    @SemanticProperty("s:image")
    private Image image;
}
