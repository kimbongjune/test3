package com.example.ezen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.ezen.rest.ApiResponse;
import com.example.ezen.rest.Item;
import com.example.ezen.rest.WeatherRepository;

@RestController
public class ApiTestController {
	
	private final RestTemplate restTemplate;
	private final WeatherRepository repository;
	
	@Autowired
	public ApiTestController(RestTemplate restTemplate, WeatherRepository repository) {
		this.restTemplate = restTemplate;
		this.repository = repository;
	}

	@GetMapping("/rest/api")
	public String api() {
		
		URI uri = UriComponentsBuilder
				.fromHttpUrl("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst")
				.queryParam("serviceKey", "6cf83c3e95952c00fe8dee49cf3b06bb488d161aee97526570b9803d9154a2b9")
				.queryParam("pageNo", "1")
				.queryParam("numOfRows", "1")
				.queryParam("dataType", "json")
				.queryParam("base_date", "20250904")
				.queryParam("base_time", "0600")
				.queryParam("nx", "55")
				.queryParam("ny", "127")
				.build(false)
				.toUri();
				//&nx=55&ny=127
		
		ApiResponse data 
			= restTemplate.getForObject(uri, ApiResponse.class);
		System.out.println(data);
		
		List<Item> item = data.getResponse().getBody().getItems().getItem();
		
		repository.insertWeather(item);
		
		for(int i = 0; i < item.size(); i ++) {
			System.out.println(item.get(i));
		}
		
//		System.out.println(
//				data.getResponse().
//				getBody().
//				getItems().
//				getItem().
//				get(0).
//				getCategory()
//			);
        
		return "success";
	}
}
