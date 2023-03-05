package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatPic;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCatPicService implements CatPicService {
	String API_URL = "https://cat-data.netlify.app/api/pictures/random";
	RestTemplate restTemplate = new RestTemplate();
	@Override
	public CatPic getPic() {
		// TODO Auto-generated method stub
		CatPic catPic = restTemplate.getForObject(API_URL, CatPic.class);
		return catPic;
	}

}	
