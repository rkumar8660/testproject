package com.example.adcampaign.service.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.adcampaign.models.AdCampaign;
import com.example.adcampaign.repositories.AdCampaignRepository;
import com.example.adcampaign.service.AdCampaignService;

@Component
public class AdCampaignServiceImpl implements AdCampaignService {
	
    @Autowired
    private AdCampaignRepository repository;


	@Override
	public Collection<AdCampaign> getAllCampaign() {
		return (Collection<AdCampaign>) repository.findAll();
	}

	@Override
	public AdCampaign getAdCampaignWithPartnerId(String partner_id) {
		return repository.findByPartner_id(partner_id);
	}

	@Override
	public AdCampaign addAdCampaign(AdCampaign adCampagin) {
		return repository.save(adCampagin);
	}

	@Override
	public boolean isAdCampaignExpired(AdCampaign adCampaign) {
		long creatadTime = adCampaign.getCreationDateTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(creatadTime));
		cal.add(Calendar.SECOND, adCampaign.getDuration());
		
		return new Date().getTime() > cal.getTimeInMillis();
		
		
	}

}
