package ua.tools.questions.resource;

import com.google.api.services.customsearch.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.tools.questions.external.GoogleSearchGateway;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    GoogleSearchGateway gateway;

    @RequestMapping("/test")
    public Result getGoogleRes(@RequestParam(value="q", defaultValue="Love") String q) {
        return gateway.performSearch(q).get();
    }
}
