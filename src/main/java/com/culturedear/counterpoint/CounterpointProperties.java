package com.culturedear.counterpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@EnableAutoConfiguration
public class CounterpointProperties {

    @Value("${chordanalyzerservice.route.analyze}")
    public String ROUTE_ANALYZE;

    @Value("${chordanalyzerservice.host}")
    public String CHORD_ANALYZER_SERVICE_HOST;

    public static CounterpointProperties counterpointProperties;

    public CounterpointProperties() {
    }

    /**
     * Provide a route to a service method
     * @param route a relative URI to the service endpoint for the analyzer service
     * @return a fully qualified URI to the desired service route
     */
    public static String getAnalyzerServiceEndpoint(String route) {
        return getAnalyzerServiceEndpoint(route, null);
    }

    /**
     * Provide a route to a service method
     * @param route a relative URI to the service endpoint for the analyzer service
     * @param param parameters for the route's query string
     * @return a fully qualified URI to the desired service route
     */
    public static String getAnalyzerServiceEndpoint(String route, String... param) {
        return String.format("%s%s", CounterpointProperties.counterpointProperties.CHORD_ANALYZER_SERVICE_HOST,
                param != null ? String.format(route, param) : route);
    }
}
