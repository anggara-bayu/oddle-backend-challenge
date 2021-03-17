package com.oddle.app.weather.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oddle.app.weather.database.entity.CityEntity;
import com.oddle.app.weather.database.entity.WeatherConditionEntity;
import com.oddle.app.weather.database.entity.WeatherConditionEntityPK;
import com.oddle.app.weather.database.repo.CityRepo;
import com.oddle.app.weather.database.repo.WeatherConditionRepo;
import com.oddle.app.weather.exception.DataAlreadyExistsException;
import com.oddle.app.weather.exception.DataNotFoundException;
import com.oddle.app.weather.parser.WeatherConditionParser;
import com.oddle.app.weather.pojo.request.WeatherRequest;
import com.oddle.app.weather.pojo.response.WeatherHistory;
import com.oddle.app.weather.pojo.response.WeatherHistoryResponse;
import com.oddle.app.weather.pojo.response.weather.Coordinate;

@Service
public class WeatherService {
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private WeatherConditionRepo weatherConditionRepo;
	
    public void saveWeatherData(WeatherRequest request) throws Exception {         
		Optional<CityEntity> cityOpt = cityRepo.findById(request.getCityId());
		
		if(!cityOpt.isPresent()) {
			doSaveOrUpdateData(request);
		} else {
			WeatherConditionEntityPK weatherConditionPK = new WeatherConditionEntityPK();
	    	weatherConditionPK.setCityId(request.getCityId());
	    	weatherConditionPK.setDatetime(request.getCalculationDateTime());
			Optional<WeatherConditionEntity> weatherConditionOpt = weatherConditionRepo.findById(weatherConditionPK);
			
			if(!weatherConditionOpt.isPresent()) {
				doSaveOrUpdateData(request);
			} else {
				throw new DataAlreadyExistsException();
			}
		}
		
    }
    
    public void updateWeatherData(WeatherRequest request) throws Exception {         
		Optional<CityEntity> cityOpt = cityRepo.findById(request.getCityId());
		
		if(!cityOpt.isPresent()) {
			throw new DataNotFoundException();
		} else {
			WeatherConditionEntityPK weatherConditionPK = new WeatherConditionEntityPK();
	    	weatherConditionPK.setCityId(request.getCityId());
	    	weatherConditionPK.setDatetime(request.getCalculationDateTime());
			Optional<WeatherConditionEntity> weatherConditionOpt = weatherConditionRepo.findById(weatherConditionPK);
			
			if(!weatherConditionOpt.isPresent()) {
				throw new DataNotFoundException();
			} else {
				doSaveOrUpdateData(request);
			}
		}	
    }
	
    @Transactional(rollbackFor = Exception.class)
	private void doSaveOrUpdateData(WeatherRequest request) throws Exception {
		CityEntity city = new CityEntity();
    	city.setId(request.getCityId());
    	city.setName(request.getCityName());
    	city.setCod(request.getCod());
    	city.setTimezone(request.getTimeZone());
    	city.setCoordLon(request.getCoordinate().getLongitude());
    	city.setCoordLat(request.getCoordinate().getLatitude());
    	city.setBase(request.getBase());
    	city.setSysId(request.getSystemData().getId());
    	city.setSysType(request.getSystemData().getType());
    	city.setSysCountry(request.getSystemData().getCountryCode());
    	cityRepo.save(city);
    	
    	WeatherConditionParser parser = new WeatherConditionParser();
    	String json = parser.objectToJson(request);
    	
    	WeatherConditionEntity weatherCondition = new WeatherConditionEntity();
    	WeatherConditionEntityPK weatherConditionPK = new WeatherConditionEntityPK();
    	weatherConditionPK.setCityId(request.getCityId());
    	weatherConditionPK.setDatetime(request.getCalculationDateTime());
    	weatherCondition.setPrimaryKey(weatherConditionPK);
    	weatherCondition.setWeatherJson(json);
    	weatherConditionRepo.save(weatherCondition);
	}
    
    @Transactional(rollbackFor = Exception.class)
    public void deleteWeatherData(int cityId, long calculationDateTime) throws Exception {         
		Optional<CityEntity> cityOpt = cityRepo.findById(cityId);
		
		if(!cityOpt.isPresent()) {
			throw new DataNotFoundException();
		} else {
			WeatherConditionEntityPK weatherConditionPK = new WeatherConditionEntityPK();
	    	weatherConditionPK.setCityId(cityId);
	    	weatherConditionPK.setDatetime(calculationDateTime);
			Optional<WeatherConditionEntity> weatherConditionOpt = weatherConditionRepo.findById(weatherConditionPK);
			
			if(!weatherConditionOpt.isPresent()) {
				throw new DataNotFoundException();
			} else {
		    	weatherConditionRepo.deleteById(weatherConditionPK);
			}
		}
		
    }
	
    //currentPage start from 1
    public WeatherHistoryResponse getHistoricalWeatherData(String cityName, int currentPage, int pageSize) {         
    	
    	if(pageSize < 1) {
    		pageSize = 5;
    	}
    	
    	if(currentPage < 1) {
    		currentPage = 1;
    	}
    	
    	currentPage -= 1;
    	Pageable paging = PageRequest.of(currentPage, pageSize);
    	
    	List<WeatherConditionEntity> weatherConditions = weatherConditionRepo.findWeatherByCityName(cityName, paging);
    	
    	if(!weatherConditions.isEmpty()) {
    		WeatherHistoryResponse response = new WeatherHistoryResponse();
    		
    		CityEntity city = weatherConditions.get(0).getCity();
    		response.setCityId(city.getId());
    		response.setCityName(city.getName());
    		response.setCod(city.getCod());
    		response.setTimeZone(city.getTimezone());
    		
    		Coordinate coordinate = new Coordinate();
    		coordinate.setLatitude(city.getCoordLat());
    		coordinate.setLongitude(city.getCoordLon());
    		response.setCoordinate(coordinate);
    		
    		response.setBase(city.getBase());
    		response.setSysId(city.getSysId());
    		response.setSysCountryCode(city.getSysCountry());
    		response.setSysType(city.getSysType());
    		
    		LinkedList<WeatherHistory> history = new LinkedList<WeatherHistory>();
    		WeatherConditionParser parser = new WeatherConditionParser();
    		for(WeatherConditionEntity condition : weatherConditions) {
        		WeatherHistory data = new WeatherHistory();
        		data.setCalculationDateTime(condition.getPrimaryKey().getDatetime());
        		
        		try {
        			parser.jsonToObject(condition.getWeatherJson(), data);
        		} catch(Exception e) {}
        		
        		history.add(data);
        	}
    		
    		response.setHistory(history);
    		return response;
    		
    	}
    	
    	return null;
    }
}
