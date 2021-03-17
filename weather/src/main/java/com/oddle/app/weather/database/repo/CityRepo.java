package com.oddle.app.weather.database.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.oddle.app.weather.database.entity.CityEntity;

public interface CityRepo extends CrudRepository<CityEntity, Integer> {
	
	@Query(
		  value = "SELECT * FROM city c "
		  		+ "LEFT JOIN weather_condition w ON c.id=w.city_id "
		  		+ "WHERE c.name = :city_name",
  		countQuery = "SELECT count(*) FROM city c "
		  		+ "LEFT JOIN weather_condition w ON c.id=w.city_id "
		  		+ "WHERE c.name = :city_name",
		  nativeQuery = true)
	Page<CityEntity> findCityByName(
			@Param("city_name") String cityName,
			Pageable pageable);
	
}
