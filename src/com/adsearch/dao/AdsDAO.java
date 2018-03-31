package com.adsearch.dao;

import com.adsearch.model.Ad;

public interface AdsDAO {
    String TABLE_NAME_AD = "ad";
    String COLUMN_NAME_AD_CAMPAIGN_ID = "campaign_id";
    String COLUMN_NAME_AD_ID = "ad_id";
    boolean insertAd(Ad ad);
    Ad getAd(Long id);
}
