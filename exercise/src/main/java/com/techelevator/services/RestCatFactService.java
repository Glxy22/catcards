package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatFactService implements CatFactService {
	String API_URL = "https://cat-data.netlify.app/api/facts/random";
	RestTemplate restTemplate = new RestTemplate();
	@Override
	public CatFact getFact() {
		// TODO Auto-generated method stub
		CatFact catFact = restTemplate.getForObject(API_URL, CatFact.class);
		return catFact;
	}

}
