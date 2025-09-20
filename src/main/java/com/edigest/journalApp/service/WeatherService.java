package com.edigest.journalApp.service;

import com.edigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String APIKEY;

    private static String APIURL = "http://api.weatherstack.com/current?access_key=APIKEY&query=CITY";
    public WeatherResponse getWeather(String city) {
        try {
            String finalUrl = APIURL.replace("CITY", city).replace("APIKEY", APIKEY);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            return body;
        }
        catch (Exception exception)
        {
            return null;
        }
    }
}
