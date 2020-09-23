package pl.boniaszczuk.weatherinfo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.boniaszczuk.weatherinfo.hibernate.WeatherData;
import pl.boniaszczuk.weatherinfo.hibernate.WeatherRepo;
import pl.boniaszczuk.weatherinfo.model.CurrentWeather;

import java.util.Collections;
@EnableScheduling
@Service
public class WeatherInfoController {
    @Value("${baseUrl}")
    private String baseUrl;

    @Value("${apiId}")
    private String apiKey;

    WeatherRepo weatherRepo;

    public WeatherInfoController(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    public CurrentWeather getCurrentWeather() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CurrentWeather> currentWeatherResponseEntity = restTemplate.exchange(baseUrl + apiKey,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                CurrentWeather.class);

        return currentWeatherResponseEntity.getBody();
    }
    public WeatherData mapCurrentWeatherOnModel(CurrentWeather currentWeather) {
        WeatherData weatherData = new WeatherData();

        weatherData.setName(currentWeather.getName());
        weatherData.setDescription(currentWeather.getWeather().get(0).getDescription());
        weatherData.setTemperature(currentWeather.getMain().getTemp()-273.15);
        weatherData.setPressure(currentWeather.getMain().getPressure());
        weatherData.setHumidity(currentWeather.getMain().getHumidity());


        return weatherData;
    }
    @Scheduled(cron = "0 */15 * * * *")
    @EventListener(ApplicationReadyEvent.class)
    private void saveCurrentWeatherInDb(){
        weatherRepo.save(mapCurrentWeatherOnModel(getCurrentWeather()));

    }
}
