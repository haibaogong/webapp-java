package com.sdl.dxa.modules.model.TSI811;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.KeywordModel;
import com.sdl.webapp.common.api.model.entity.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import java.util.List;

import static com.sdl.webapp.common.api.mapping.semantic.config.SemanticVocabulary.SDL_CORE;

@Data
@EqualsAndHashCode(callSuper = true)
@SemanticEntity(entityName = "TSI811KeywordMetadataSchema", vocabulary = SDL_CORE)
public class Tsi811TestKeyword extends KeywordModel {

    @JsonProperty("TextField")
    private String textField;

    @JsonProperty("NumberProperty")
    @SemanticProperty("numberField")
    private double numberProperty;

    @JsonProperty("DateField")
    private DateTime dateField;

    @JsonProperty("KeywordField")
    private List<Tag> keywordField;
}
