package com.sdl.dxa.modules.search.provider.implementation;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomainClient;
import com.amazonaws.services.cloudsearchdomain.model.Hit;
import com.amazonaws.services.cloudsearchdomain.model.SearchRequest;
import com.amazonaws.services.cloudsearchdomain.model.SearchResult;
import com.sdl.dxa.modules.search.model.SearchItem;
import com.sdl.dxa.modules.search.model.SearchQuery;
import com.sdl.webapp.common.api.localization.Localization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

@Component
@Primary
@Profile("search.aws")
public class AwsCloudSearchProvider extends AbstractSearchProvider {

    private static final Logger LOG = LoggerFactory.getLogger(AwsCloudSearchProvider.class);

    private static final String HIGHLIGHT_SETTINGS = "{\n" +
            "\t\"title\": {\n" +
            "\t\t\"format\": \"text\",\n" +
            "\t\t\"max_phrases\": 5,\n" +
            "\t\t\"pre_tag\": \"*\",\n" +
            "\t\t\"post_tag\": \"*\"\n" +
            "\t},\n" +
            "\t\"body\": {\n" +
            "\t\t\"format\": \"text\",\n" +
            "\t\t\"max_phrases\": 5,\n" +
            "\t\t\"pre_tag\": \"*\",\n" +
            "\t\t\"post_tag\": \"*\"\n" +
            "\t},\n" +
            "\t\"summary\": {\n" +
            "\t\t\"format\": \"text\",\n" +
            "\t\t\"max_phrases\": 5,\n" +
            "\t\t\"pre_tag\": \"*\",\n" +
            "\t\t\"post_tag\": \"*\"\n" +
            "\t}\n" +
            "}";
    private static final String QUERY_OPTIONS = "{\n" +
            "  \"fields\": [\n" +
            "    \"body\",\n" +
            "    \"summary\",\n" +
            "    \"title\"\n" +
            "  ]\n" +
            "}";

    @Autowired
    private AWSCredentials awsCredentials;

    private static void processResults(SearchQuery searchQuery, SearchResult result) {
        List<Hit> list = result.getHits().getHit();
        List<SearchItem> items = new ArrayList<>(list.size());
        for (Hit hit : list) {
            items.add(convertToSearchItem(hit));
        }

        searchQuery.setResults(items);

        searchQuery.setTotal(result.getHits().getFound());
    }

    private static SearchRequest buildRequest(SearchQuery searchQuery, Localization localization) {
        SearchRequest request = new SearchRequest();
        request.setQuery(searchQuery.getQueryDetails().getQueryText());
        request.setStart((long) (searchQuery.getStart() - 1));
        request.setSize((long) searchQuery.getPageSize());
        request.setFilterQuery("publicationid:'" + localization.getId() + "'");
        request.setQueryOptions(QUERY_OPTIONS);
        request.setHighlight(HIGHLIGHT_SETTINGS);
        return request;
    }

    private static SearchItem convertToSearchItem(Hit hit) {
        SearchItem item = new SearchItem();
        String id = hit.getId();
        Map<String, String> highlights = hit.getHighlights();

        item.setId(id);
        String summary = highlights.get("summary");
        item.setSummary(isEmpty(summary) ? highlights.get("body") : summary);
        item.setTitle(highlights.get("title"));
        item.setUrl(hit.getFields().get("url").get(0));

        return item;
    }

    @Override
    public void executeQuery(SearchQuery searchQuery, Localization localization) {
        SearchRequest request = buildRequest(searchQuery, localization);
        processResults(searchQuery, getClient(localization).search(request));
    }

    private AmazonCloudSearchDomainClient getClient(Localization localization) {
        AmazonCloudSearchDomainClient client = new AmazonCloudSearchDomainClient(awsCredentials);
        client.setEndpoint(getServiceUrl(localization));
        return client;
    }
}
