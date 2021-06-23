package com.sdl.dxa.modules.core;

import com.sdl.dxa.modules.core.model.entity.*;
import com.sdl.dxa.modules.core.model.region.MultiColumnRegion;
import com.sdl.webapp.common.api.mapping.views.AbstractModuleInitializer;
import com.sdl.webapp.common.api.mapping.views.ModuleInfo;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModel;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModels;
import com.sdl.webapp.common.api.model.entity.Configuration;
import com.sdl.webapp.common.api.model.entity.NavigationLinks;
import com.sdl.webapp.common.api.model.entity.SitemapItem;
import com.sdl.webapp.common.api.model.page.DefaultPageModel;
import com.sdl.webapp.common.api.model.region.RegionModelImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
@ComponentScan("com.sdl.dxa.modules.core")
public class CoreInitializer {

    @RegisteredViewModels({
            //start haibao
            @RegisteredViewModel(viewName = "BackgroundPicture", modelClass = BackgroundPicture.class),
            @RegisteredViewModel(viewName = "SolutionsContent", modelClass = SolutionsContent.class),
            @RegisteredViewModel(viewName = "SolutionsContentList", modelClass = SolutionsContentList.class),
            //end haibao
            @RegisteredViewModel(viewName = "Article", modelClass = Article.class),
            @RegisteredViewModel(viewName = "Accordion", modelClass = ItemList.class),
            @RegisteredViewModel(viewName = "Breadcrumb", modelClass = NavigationLinks.class, controllerName = "Navigation"),
            @RegisteredViewModel(viewName = "Carousel", modelClass = ItemList.class),
            @RegisteredViewModel(viewName = "CookieNotificationBar", modelClass = Notification.class),
            @RegisteredViewModel(viewName = "Download", modelClass = Download.class),
            @RegisteredViewModel(viewName = "FooterLinkGroup", modelClass = LinkList.class),
            @RegisteredViewModel(viewName = "FooterLinks", modelClass = LinkList.class),
            @RegisteredViewModel(viewName = "HeaderLinks", modelClass = LinkList.class),
            @RegisteredViewModel(viewName = "HeaderLogo", modelClass = Teaser.class),
            @RegisteredViewModel(viewName = "Image", modelClass = Image.class),
            @RegisteredViewModel(viewName = "LeftNavigation", modelClass = NavigationLinks.class, controllerName = "Navigation"),
            @RegisteredViewModel(viewName = "LanguageSelector", modelClass = Configuration.class),
            @RegisteredViewModel(viewName = "List", modelClass = ContentList.class, controllerName = "List"),
            @RegisteredViewModel(viewName = "OldBrowserNotificationBar", modelClass = Notification.class),
            @RegisteredViewModel(viewName = "PagedList", modelClass = ContentList.class, controllerName = "List"),
            @RegisteredViewModel(viewName = "Place", modelClass = Place.class),
            @RegisteredViewModel(viewName = "SiteMap", modelClass = SitemapItem.class, controllerName = "Navigation"),
            @RegisteredViewModel(viewName = "SiteMapXml", modelClass = SitemapItem.class, controllerName = "Navigation"),
            @RegisteredViewModel(viewName = "SocialLinks", modelClass = TagLinkList.class),
            @RegisteredViewModel(viewName = "SocialSharing", modelClass = TagLinkList.class),
            @RegisteredViewModel(viewName = "Tab", modelClass = ItemList.class),
            @RegisteredViewModel(viewName = "Teaser-ImageOverlay", modelClass = Teaser.class),
            @RegisteredViewModel(viewName = "Teaser", modelClass = Teaser.class),
            @RegisteredViewModel(viewName = "TeaserColored", modelClass = Teaser.class),
            @RegisteredViewModel(viewName = "TeaserHero-ImageOverlay", modelClass = Teaser.class),
            @RegisteredViewModel(viewName = "TeaserMap", modelClass = Teaser.class),
            @RegisteredViewModel(viewName = "ThumbnailList", modelClass = ContentList.class, controllerName = "List"),
            @RegisteredViewModel(viewName = "TopNavigation", modelClass = NavigationLinks.class, controllerName = "Navigation"),
            @RegisteredViewModel(viewName = "YouTubeVideo", modelClass = YouTubeVideo.class),
            @RegisteredViewModel(viewName = "GeneralPage", modelClass = DefaultPageModel.class),
            @RegisteredViewModel(viewName = "IncludePage", modelClass = DefaultPageModel.class),
            @RegisteredViewModel(viewName = "RedirectPage", modelClass = DefaultPageModel.class),
            @RegisteredViewModel(viewName = "2-Column", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "3-Column", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "4-Column", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Multi-Column", modelClass = MultiColumnRegion.class),
            @RegisteredViewModel(viewName = "Additional", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Article", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Content", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Main Section", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Hero", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Info", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Left", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Links", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Logo", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Main", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Nav", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Tools", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Header", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Footer", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Left Navigation", modelClass = RegionModelImpl.class),
            @RegisteredViewModel(viewName = "Content Tools", modelClass = RegionModelImpl.class)
    })
    @Component
    @ModuleInfo(name = "Core module", areaName = "Core", description = "Core DXA module which contains basic views")
    public static class CoreModuleInitializer extends AbstractModuleInitializer {
        @Override
        protected String getAreaName() {
            return "Core";
        }
    }
}

