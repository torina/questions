package ua.tools.questions.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleSearchGateway {

    @Value("${google.base-url}")
    private String baseUrl;

    public Object performSearch() throws NoSuchMethodException {
        throw new NoSuchMethodException();
    }

}
