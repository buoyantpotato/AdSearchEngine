package com.adsearch;

import com.adsearch.dao.IndexBuilder;
import com.adsearch.model.Ad;
import com.adsearch.model.Campaign;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdEngine {

    private String adsDataFilePath;
    private String campaignDataFilePath;
    private IndexBuilder indexBuilder;

    public AdEngine(String adsDataFilePath, String campaignDataFilePath,
                    String invertedIndexServer, int invertedIndexServerPort,
                    String mysqlDatabaseHost, String mysqlDatabaseName, String mysqlUser, String mysqlPassword) {
        this.adsDataFilePath = adsDataFilePath;
        this.campaignDataFilePath = campaignDataFilePath;
        this.indexBuilder = new IndexBuilder(invertedIndexServer, invertedIndexServerPort,
                mysqlDatabaseHost, mysqlDatabaseName, mysqlUser, mysqlPassword);

    }

    public void init() {
        loadCampaignData();
        loadAdsData();
    }

    private void loadAdsData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.adsDataFilePath));
            String line = null;
            while ((line = reader.readLine()) != null) {
                JSONObject item = new JSONObject(line);

                List<String> kewords = new ArrayList<>();
                if (item.isNull(Constants.JSON_AD_KEYWORDS)) {
                    JSONArray keywordArray = item.getJSONArray(Constants.JSON_AD_KEYWORDS);
                    for (int i = 0; i < keywordArray.length(); i++) {
                        kewords.add(keywordArray.getString(i));
                    }
                }

                Ad ad = new Ad(
                        item.getLong(Constants.JSON_AD_ID),
                        item.getLong(Constants.JSON_CAMPAIGN_ID),
                        kewords,
                        item.isNull(Constants.JSON_AD_TITLE) ? "" : item.getString(Constants.JSON_AD_TITLE),
                        item.isNull(Constants.JSON_AD_PRICE) ? 0.0 : item.getDouble(Constants.JSON_AD_PRICE),
                        item.isNull(Constants.JSON_AD_THUMBNAIL) ? "" : item.getString(Constants.JSON_AD_THUMBNAIL),
                        item.isNull(Constants.JSON_AD_DESCRIPTION) ? "" : item.getString(Constants.JSON_AD_DESCRIPTION),
                        item.isNull(Constants.JSON_AD_BRAND) ? "" : item.getString(Constants.JSON_AD_BRAND),
                        item.isNull(Constants.JSON_AD_DETAIL_URL) ? "" : item.getString(Constants.JSON_AD_DETAIL_URL),
                        item.isNull(Constants.JSON_AD_CATEGORY) ? "" : item.getString(Constants.JSON_AD_CATEGORY),
                        item.isNull(Constants.JSON_AD_BID_PRICE) ? 0.0 : item.getDouble(Constants.JSON_AD_BID_PRICE),
                        item.isNull(Constants.JSON_AD_PCLICK) ? 0.0 : item.getDouble(Constants.JSON_AD_PCLICK)
                );

                if (indexBuilder.insertAdToForwardIndex(ad)) {
                    System.out.println("Insert ad[" + ad.getAdId() + "]");
                }
                indexBuilder.insertAdToInvertedIndex(ad);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: Cannot find the ads data file " + this.adsDataFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCampaignData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.campaignDataFilePath));
            String line = null;
            while ((line = reader.readLine()) != null) {
                JSONObject item = new JSONObject(line);
                Campaign campaign = new Campaign(
                        item.getLong(Constants.JSON_CAMPAIGN_ID),
                        item.getDouble(Constants.JSON_CAMPAIGN_BUDGET)
                );

                if (indexBuilder.insertCampaignToForwardIndex(campaign)) {
                    System.out.println("Insert campaign[" + campaign.getCampaignId() + "]");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERROR: Cannot find the ads data file " + this.campaignDataFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
