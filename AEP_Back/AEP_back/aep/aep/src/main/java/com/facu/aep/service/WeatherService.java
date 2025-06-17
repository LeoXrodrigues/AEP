package com.facu.aep.service;

import com.facu.aep.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherDTO getWeatherByCity(String city) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .queryParam("lang", "pt_br")
                .toUriString();

        WeatherDTO weather = fetchWeather(url);

        Map<String, String> address = getAddressFromCoordinates(weather.getLatitude(), weather.getLongitude());
        if (address != null) {
            weather.setRua(address.getOrDefault("road", ""));
            weather.setBairro(address.getOrDefault("suburb", ""));
        }

        return weather;
    }

    public WeatherDTO getWeatherByCoordinates(Double lat, Double lon) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .queryParam("lang", "pt_br")
                .toUriString();

        WeatherDTO weather = fetchWeather(url);

        Map<String, String> address = getAddressFromCoordinates(lat, lon);
        if (address != null) {
            weather.setRua(address.getOrDefault("road", ""));
            weather.setBairro(address.getOrDefault("suburb", ""));
        }

        return weather;
    }

    private WeatherDTO fetchWeather(String url) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || response.isEmpty()) {
            throw new RuntimeException("Resposta vazia do serviço de clima.");
        }

        String nome = (String) response.get("name");

        Map<String, Object> main = (Map<String, Object>) response.get("main");
        double temp = ((Number) main.get("temp")).doubleValue();

        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
        String descricao = (String) weatherList.get(0).get("description");

        Map<String, Object> coord = (Map<String, Object>) response.get("coord");
        double latitude = ((Number) coord.get("lat")).doubleValue();
        double longitude = ((Number) coord.get("lon")).doubleValue();

        WeatherDTO dto = new WeatherDTO();
        dto.setCidade(nome);
        dto.setTemperatura(temp);
        dto.setDescricao(descricao);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setRua("");    // será preenchido depois
        dto.setBairro(""); // será preenchido depois

        return dto;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getAddressFromCoordinates(Double lat, Double lon) {
        try {
            String url = "https://nominatim.openstreetmap.org/reverse?format=json" +
                    "&lat=" + URLEncoder.encode(lat.toString(), StandardCharsets.UTF_8) +
                    "&lon=" + URLEncoder.encode(lon.toString(), StandardCharsets.UTF_8) +
                    "&addressdetails=1";

            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);

            if (response == null) return null;

            Object addressObj = response.get("address");
            if (addressObj instanceof Map) {
                return (Map<String, String>) addressObj;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
