package com.sdl.dxa.modules.search;

import com.amazonaws.auth.BasicAWSCredentials;
import com.sdl.dxa.modules.search.model.SearchBox;
import com.sdl.dxa.modules.search.model.SearchItem;
import com.sdl.dxa.modules.search.model.SearchQuery;
import com.sdl.webapp.common.api.mapping.views.AbstractModuleInitializer;
import com.sdl.webapp.common.api.mapping.views.ModuleInfo;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModel;
import com.sdl.webapp.common.api.mapping.views.RegisteredViewModels;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.sdl.dxa.modules.search")
public class SearchModuleInitializer {

    @RegisteredViewModels({
            @RegisteredViewModel(viewName = "SearchBox", modelClass = SearchBox.class),
            @RegisteredViewModel(viewName = "SearchItem", modelClass = SearchItem.class),
            @RegisteredViewModel(viewName = "SearchResults", modelClass = SearchQuery.class, controllerName = "Search")
    })
    @ModuleInfo(name = "Search Module", areaName = "Search", description = "Support for SOLR and AWS searches, " +
            "Spring profiles for activation should be set explicitly: search.solr, search.aws")
    @Component
    public static class SearchViewsInitializer extends AbstractModuleInitializer {
        @Override
        protected String getAreaName() {
            return "Search";
        }
    }

    @Configuration
    @Profile("search.aws")
    public static class AwsSpringConfiguration {
        @Value("#{systemEnvironment['AWS_ACCESS_KEY_ID']}")
        private String accessKeyId;

        @Value("#{systemEnvironment['AWS_SECRET_ACCESS_KEY']}")
        private String secretAccessKey;

        @Bean
        public BasicAWSCredentials awsCredentials() {
            return new BasicAWSCredentials(accessKeyId, secretAccessKey);
        }
    }

}
