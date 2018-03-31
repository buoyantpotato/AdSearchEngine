package com.adsearch.dao;

import com.adsearch.model.Campaign;

public interface CampaignDAO {
    String TABLE_NAME_CAMPAIGN = "campaign";
    String COLUMN_NAME_CAMPAIGN_ID = "campaign_id";
    boolean insertCampaign(Campaign campaign);
    Campaign getCampaign(Long id);
}
