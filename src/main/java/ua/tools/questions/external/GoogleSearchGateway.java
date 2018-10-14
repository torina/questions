package ua.tools.questions.external;

import com.google.api.services.customsearch.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * https://developers.google.com/custom-search/json-api/v1/reference/cse/list
 */
@Component
public class GoogleSearchGateway {

    private static String CX_PARAM = "cx";
    private static String KEY_PARAM = "key";
    private static String SEARCH_QUERY_PARAM = "key";

    @Value("${google.base-url}")
    private String baseUrl;

    @Value("${google.search-engine}")
    private String se;

    @Value("${google.key}")
    private String key;

    private
    @Autowired
    RestTemplate restTemplate;

    //TODO UnitTest
    public ResponseEntity<Result> performSearch(String query) throws NoSuchMethodException {
        Map<String,String> params = new HashMap<>();
        params.put(CX_PARAM, se);
        params.put(KEY_PARAM, key);
        params.put(SEARCH_QUERY_PARAM, query);
        return restTemplate.getForEntity(baseUrl, Result.class, params);
    }

}
