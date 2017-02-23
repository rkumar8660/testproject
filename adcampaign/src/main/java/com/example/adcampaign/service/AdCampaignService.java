package com.example.adcampaign.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.example.adcampaign.models.AdCampaign;

public interface AdCampaignService {
	 public Collection<AdCampaign> getAllCampaign();
	 public AdCampaign getAdCampaignWithPartnerId(String partner_id);
	 public AdCampaign addAdCampaign(AdCampaign adCampagin);
	 public boolean isAdCampaignExpired(AdCampaign adCampaign);
}
