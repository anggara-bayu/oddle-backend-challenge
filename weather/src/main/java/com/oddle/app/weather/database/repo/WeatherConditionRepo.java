package com.oddle.app.weather.database.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.oddle.app.weather.database.entity.WeatherConditionEntity;
import com.oddle.app.weather.database.entity.WeatherConditionEntityPK;

public interface WeatherConditionRepo extends CrudRepository<WeatherConditionEntity, WeatherConditionEntityPK> {
	
	@Query(
		value = "SELECT * FROM weather_condition w "
		  		+ "LEFT JOIN city c ON w.city_id=c.id "
		  		+ "WHERE c.name = :city_name",
  		countQuery = "SELECT count(*) FROM city c "
		  		+ "LEFT JOIN weather_condition w ON c.id=w.city_id "
		  		+ "WHERE c.name = :city_name",
  		nativeQuery = true)
	List<WeatherConditionEntity> findWeatherByCityName(
			@Param("city_name") String cityName,
			Pageable pageable);
	
}
