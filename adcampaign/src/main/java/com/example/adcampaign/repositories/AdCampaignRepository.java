package com.example.adcampaign.repositories;

import com.example.adcampaign.models.AdCampaign;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;


public interface AdCampaignRepository extends CrudRepository<AdCampaign, Long> {
	
	@Query("SELECT t FROM AdCampaign t where t.partner_id = :partner_id")
	public AdCampaign findByPartner_id(@Param("partner_id") String partner_id); 
}
