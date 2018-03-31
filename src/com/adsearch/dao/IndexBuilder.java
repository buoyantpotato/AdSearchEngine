package com.adsearch.dao;

import com.adsearch.Constants;
import com.adsearch.model.Ad;
import com.adsearch.model.Campaign;

import java.sql.*;

public class IndexBuilder {

    private String invertedIndexServer;
    private int invertedIndexServerPort;
    private String mysqlDatabaseHost;
    private String mysqlDatabaseName;
    private String mysqlUser;
    private String mysqlPassword;
    private ForwardingIndexDAOImpl forwardingIndexBuilder;
    private MemcachedManager invertedIndexBuilder;

    public IndexBuilder(String invertedIndexServer, int invertedIndexServerPort,
                        String mysqlDatabaseHost, String mysqlDatabaseName, String mysqlUser, String mysqlPassword) {
        this.invertedIndexServer = invertedIndexServer;
        this.invertedIndexServerPort = invertedIndexServerPort;
        this.mysqlDatabaseHost = mysqlDatabaseHost;
        this.mysqlDatabaseName = mysqlDatabaseName;
        this.mysqlUser = mysqlUser;
        this.mysqlPassword = mysqlPassword;

        this.forwardingIndexBuilder = new ForwardingIndexDAOImpl(
                mysqlDatabaseHost, mysqlDatabaseName, mysqlUser, mysqlPassword);
        this.invertedIndexBuilder = new MemcachedManager(invertedIndexServer, invertedIndexServerPort);
    }

    public boolean insertCampaignToForwardIndex(Campaign campaign) {
        return this.forwardingIndexBuilder.insertCampaign(campaign);
    }

    public boolean insertAdToForwardIndex(Ad ad) {
        return this.forwardingIndexBuilder.insertAd(ad);
    }

    public boolean insertAdToInvertedIndex(Ad ad) {
        return false;
    }
}
