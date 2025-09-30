package com.example.ezen.rest;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {
	private final SqlSession template;

	@Autowired
	public WeatherRepository(SqlSession template) {
		this.template = template;
	}
	
	public int insertWeather(List<Item> item) {
		return template.insert("weatherMapper.insertWeather", item);
	}
}
