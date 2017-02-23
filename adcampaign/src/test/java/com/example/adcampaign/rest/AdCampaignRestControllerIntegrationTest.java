package com.example.adcampaign.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.AdcampaignApplication;
import com.example.adcampaign.models.AdCampaign;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=AdcampaignApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
public class AdCampaignRestControllerIntegrationTest {
	
	@Value("${local.server.port}")
	private int serverPort;
	
	@Before
	public void setup(){
		RestAssured.port = serverPort;
		
	}
	
	@Test
	public void postAdAndRetrieval_Success() throws Exception {
		AdCampaign adCampaign = new AdCampaign("partner_1",30,"ad_1");
		
		//Successfull ad campaign post
		Response postResponse = given().
		       contentType("application/json; charset=UTF-16").
		       body(adCampaign).
		when().
		      post("/ad");
		
		assertThat(getAdCampaignFromResponse(postResponse).getId()).isNotNull();
		
		
		//Retrieve ad campaign which is posted earlier
		
		Response getResponse = given().
			       contentType("application/json; charset=UTF-16").
			when().
			      get("/ad/"+adCampaign.getPartner_id());
		
		assertThat(getAdCampaignFromResponse(getResponse).getDuration()).isEqualTo(30);
		
		
		//Trying to add ad campaign for partner which is existed already, expected failure message
		Response rePostResponse = given().
			       contentType("application/json; charset=UTF-16").
			       body(adCampaign).
			when().
			      post("/ad");
			
			assertThat(rePostResponse.asString()).isEqualTo("Campagin already exists for partnerid:"+adCampaign.getPartner_id());
			
		//Retrive the ad campaing which is "current time is greater than a campaign's creation time + duration"
			
			//post new campaign for partner_2 which is 5 seconds duration
			adCampaign.setDuration(5); //post 5 second durations
			adCampaign.setAd_content("Ad 2");
			adCampaign.setPartner_id("partner_2");
			Response postNewResponse = given().
			       contentType("application/json; charset=UTF-16").
			       body(adCampaign).
			when().
			      post("/ad");
			
			//Sleep for 6 seconds and try to retrive ad, which is already expired
			Thread.sleep(6000);
			
			Response getNewResponse = given().
				       contentType("application/json; charset=UTF-16").
				when().
				      get("/ad/"+adCampaign.getPartner_id());
			
			assertThat(getNewResponse.asString()).isEqualTo("AdCampaign is Expired for partner_id:"+adCampaign.getPartner_id());
		
		
	}
	
	private AdCampaign getAdCampaignFromResponse(Response response) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		AdCampaign campaignFromResponse = mapper.readValue(response.asString().getBytes(), AdCampaign.class);
		return campaignFromResponse;
	}
}
