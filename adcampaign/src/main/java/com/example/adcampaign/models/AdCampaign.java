package com.example.adcampaign.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class AdCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String partner_id;
    private Integer duration;
    private String ad_content;
    private Long creationDateTime;

    public AdCampaign(String partner_id,Integer duration, String ad_content) {
        this.partner_id = partner_id;
        this.duration = duration;
        this.ad_content = ad_content;
    }

    //for JPA
    public AdCampaign() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getAd_content() {
		return ad_content;
	}

	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public Long getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Long creationDateTime) {
		this.creationDateTime = creationDateTime;
	}
	
	

    
}
