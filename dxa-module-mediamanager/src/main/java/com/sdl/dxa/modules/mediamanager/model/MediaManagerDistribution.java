package com.sdl.dxa.modules.mediamanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.semantic.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.MvcData;
import com.sdl.webapp.common.api.model.entity.EclItem;
import com.sdl.webapp.common.api.model.mvcdata.DefaultsMvcData;
import com.sdl.webapp.common.api.model.mvcdata.MvcDataCreator;
import com.sdl.webapp.common.exceptions.DxaException;
import com.sdl.webapp.common.markup.html.HtmlElement;
import com.sdl.webapp.common.markup.html.builders.ImgElementBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Node;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.sdl.webapp.common.api.mapping.semantic.config.SemanticVocabulary.SDL_CORE;
import static com.sdl.webapp.common.markup.html.builders.HtmlBuilders.div;
import static com.sdl.webapp.common.markup.html.builders.HtmlBuilders.img;
import static com.sdl.webapp.common.markup.html.builders.HtmlBuilders.script;
import static com.sdl.webapp.common.util.CollectionUtils.getByCompoundKeyOrAlternative;
import static java.lang.String.format;

@SemanticEntity(entityName = "ExternalContentLibraryStubSchemamm", vocabulary = SDL_CORE, prefix = "s")
@Data
@EqualsAndHashCode(callSuper = true)
public class MediaManagerDistribution extends EclItem {

    protected static final String ENABLED_MARKER = "Enabled";

    static final String CUSTOM_PLAYER_VIEW_PREFIX = "custom-";

    @SemanticProperty("s:playerType")
    private String playerType;

    @SemanticProperty("s:customVideoAutoplay")
    private String customVideoAutoPlay;

    @SemanticProperty("s:customVideoSubtitles")
    private String customVideoSubtitles;

    @SemanticProperty("s:customVideoControls")
    private String customVideoControls;

    public String getGlobalId() {
        final Map<String, Object> externalMetadata = getExternalMetadata();
        if (!CollectionUtils.isEmpty(externalMetadata) && externalMetadata.containsKey("GlobalId")) {
            return Objects.toString(externalMetadata.get("GlobalId"));
        }

        return UriComponentsBuilder.fromHttpUrl(getUrl()).build().getQueryParams().getFirst("o");
    }

    public String getDistributionJsonUrl() {
        return UriComponentsBuilder.fromHttpUrl(getUrl())
                .replacePath("/json/")
                .replaceQuery("")
                .path(getGlobalId())
                .build().encode().toString();
    }

    public String getEmbedScriptUrl() {
        return UriComponentsBuilder.fromHttpUrl(getUrl()).pathSegment("embed").build().encode().toString();
    }

    public String getTitle() {
        String first = getByCompoundKeyOrAlternative("Program/Asset/Title", getExternalMetadata(), null, String.class);
        return first == null ?
                getByCompoundKeyOrAlternative("Program/Title", getExternalMetadata(),
                        super.getFileName(), String.class) : first;
    }

    public String getDescription() {
        return getByCompoundKeyOrAlternative("Program/Asset/Description", getExternalMetadata(), null, String.class);
    }

    @Override
    public String getFileName() {
        return getTitle();
    }

    @Override
    public String getMimeType() {
        return getByCompoundKeyOrAlternative("Program/Asset/MIMEType", getExternalMetadata(), super.getMimeType(), String.class);
    }

    @Override
    public void setUrl(String url) {
        super.setUrl(url != null ? url.replaceAll("/\\?", "?") : null);
    }

    /**
     * Checks if this should be initialized on a with a custom player with JSON data.
     *
     * @return view name with respect to
     */
    @JsonIgnore
    public boolean isCustomView() {
        return "Custom".equalsIgnoreCase(playerType);
    }

    /**
     * Checks if the video is subtitled. Doesn't checks if this is indeed a video and not an image for example. Basically
     * checks a property for video subtitles.
     *
     * @return true if customVideoSubtitles property is set to "enabled", false otherwise
     */
    @JsonIgnore
    public boolean isSubtitled() {
        return ENABLED_MARKER.equalsIgnoreCase(customVideoSubtitles);
    }

    /**
     * Checks if the video is automatically started. Doesn't checks if this is indeed a video and not an image
     * for example. Basically checks a property for video auto play.
     *
     * @return true if customVideoAutoPlay property is set to "enabled", false otherwise
     */
    @JsonIgnore
    public boolean isAutoPlayed() {
        return ENABLED_MARKER.equalsIgnoreCase(customVideoAutoPlay);
    }

    /**
     * Checks if the video controls are shown. Doesn't checks if this is indeed a video and not an image
     * for example. Basically checks a property for video controls.
     *
     * @return true if customVideoControls property is set to "enabled", false otherwise
     */
    @JsonIgnore
    public boolean isShowControls() {
        return ENABLED_MARKER.equalsIgnoreCase(customVideoControls);
    }

    @Override
    public HtmlElement toHtmlElement(String widthFactor, double aspect, String cssClass, int containerSize, String contextPath) throws DxaException {
        switch (getDisplayTypeId()) {
            case "html5dist":
                return getHtml5Dist(cssClass);
            case "imagedist":
                return getImageDist(widthFactor, aspect, cssClass);
            default:
                return super.toHtmlElement(widthFactor, aspect, cssClass, containerSize, contextPath);
        }
    }

    @Override
    public void readFromXhtmlElement(Node xhtmlElement) {
        super.readFromXhtmlElement(xhtmlElement);

        setPlayerType(getNodeAttribute(xhtmlElement, "data-playerType"));
        setCustomVideoAutoPlay(getNodeAttribute(xhtmlElement, "data-customVideoAutoplay"));
        setCustomVideoSubtitles(getNodeAttribute(xhtmlElement, "data-customVideoSubtitles"));
        setCustomVideoControls(getNodeAttribute(xhtmlElement, "data-customVideoControls"));
    }

    @Override
    public MvcData getDefaultMvcData() {
        return MvcDataCreator.creator()
                .fromQualifiedName("MediaManager:" + super.getDisplayTypeId())
                .defaults(DefaultsMvcData.ENTITY)
                .create();
    }

    private HtmlElement getImageDist(String widthFactor, double aspect, String cssClass) {

        final ImgElementBuilder elementBuilder = img().withSrc(getUrl())
                .withClass(cssClass)
                .withAttribute("width", widthFactor);
        if (!Objects.equals(0.0, aspect)) {
            elementBuilder.withAttribute("data-aspect", NumberFormat.getNumberInstance(Locale.getDefault()).format(aspect));
        }

        return elementBuilder.build();
    }

    private HtmlElement getHtml5Dist(String cssClass) {
        final UUID uuid = UUID.randomUUID();
        return div()
                .withClass(cssClass)
                .withNode(
                        div().withId(uuid.toString()).build()
                )
                .withNode(
                        script().withSrc(format("%s&trgt=%s&responsive=true", getEmbedScriptUrl(), uuid)).build()
                ).build();
    }
}
