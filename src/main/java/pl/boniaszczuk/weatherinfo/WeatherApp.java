package pl.boniaszczuk.weatherinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.boniaszczuk.weatherinfo.hibernate.WeatherData;
import pl.boniaszczuk.weatherinfo.hibernate.WeatherRepo;
import pl.boniaszczuk.weatherinfo.model.CurrentWeather;
import pl.boniaszczuk.weatherinfo.model.Main;
import pl.boniaszczuk.weatherinfo.model.Weather;

@SpringBootApplication
public class WeatherApp {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApp.class, args);
    }



}
