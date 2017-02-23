package com.example.adcampaign.rest;

import com.example.adcampaign.models.AdCampaign;
import com.example.adcampaign.repositories.AdCampaignRepository;
import com.example.adcampaign.service.AdCampaignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/ad")
public class AdCampaignRestController {

        @Autowired
        private AdCampaignService adCampaignService;

        //URL to return all campaign as JSON
        //http://localhost:6060/ad
        @RequestMapping(method = RequestMethod.GET, value="/list")
        public ResponseEntity<Collection<AdCampaign>> getAllCampaign(){
            return new ResponseEntity<>(adCampaignService.getAllCampaign(), HttpStatus.OK);
        }
        
        //http://localhost:6060/ad/partner_1
        @RequestMapping(method = RequestMethod.GET, value = "/{partner_id}")
        public ResponseEntity<?> getAdCampaignWithPartnerId(@PathVariable("partner_id") String partner_id) {
        	AdCampaign adCampaign = adCampaignService.getAdCampaignWithPartnerId(partner_id);
        	if(adCampaign == null){
        		return new ResponseEntity<String>("AdCampaign not found for Partner id:"+partner_id,HttpStatus.OK);
        	}
        	if(adCampaignService.isAdCampaignExpired(adCampaign)){
        		return new ResponseEntity<String>("AdCampaign is Expired for partner_id:"+partner_id,HttpStatus.OK);
        	}
            return new ResponseEntity<>(adCampaign,HttpStatus.OK);
        }

        //http://localhost:6060/ad
        //{ "partner_id":"partner_2", "duration":60, "ad_content":"ad_2" };
        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> addAdCampaign(@RequestBody AdCampaign adCampaign) {
         	AdCampaign campagin = adCampaignService.getAdCampaignWithPartnerId(adCampaign.getPartner_id());
        	if(campagin == null){
        		adCampaign.setCreationDateTime(new Date().getTime());
        		return new ResponseEntity<>(adCampaignService.addAdCampaign(adCampaign), HttpStatus.CREATED);
        	}
        	else
        		return new ResponseEntity<String>("Campagin already exists for partnerid:"+adCampaign.getPartner_id(),HttpStatus.BAD_REQUEST);
        }
}
