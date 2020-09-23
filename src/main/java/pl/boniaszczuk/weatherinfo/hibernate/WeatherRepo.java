package pl.boniaszczuk.weatherinfo.hibernate;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepo extends JpaRepository<WeatherData, Long> {



}
