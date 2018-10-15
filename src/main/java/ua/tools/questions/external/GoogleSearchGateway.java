package ua.tools.questions.external;

import com.google.api.services.customsearch.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * https://developers.google.com/custom-search/json-api/v1/reference/cse/list
 */
@Slf4j
@Component
public class GoogleSearchGateway {

    private static String CX_PARAM = "cx";
    private static String KEY_PARAM = "key";
    private static String SEARCH_QUERY_PARAM = "q";

    @Value("${google.url}")
    private String baseUrl;

//    @Value("${google.search-engine}")
    private String se = "+001242515460665111999%3Abmikj0bh0go";

    @Value("${google.key}")
    private String key;

    private RestTemplate restTemplate;

    GoogleSearchGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<Result> performSearch(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam(SEARCH_QUERY_PARAM, query)
                .queryParam(CX_PARAM, se)
                .queryParam(KEY_PARAM, key);



        String url = baseUrl + SEARCH_QUERY_PARAM + "=" + query +
                "&" + CX_PARAM + "=" +  se + "&" + KEY_PARAM + "="+ key;

        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(uri);

        ResponseEntity<Result> res = null;
        try {
           res = restTemplate.getForEntity(
                   uri,
                   Result.class);
        } catch ( Exception e) {
            System.err.println(e.getMessage());
        }
        if (res.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(res.getBody());
        } else {
            System.err.println(res);
            return Optional.empty();
        }
    }
}
