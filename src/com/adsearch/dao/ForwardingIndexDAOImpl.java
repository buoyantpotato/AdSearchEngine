package com.adsearch.dao;

import com.adsearch.Utility;
import com.adsearch.model.Ad;
import com.adsearch.model.Campaign;

import java.sql.*;

public class ForwardingIndexDAOImpl implements AdsDAO, CampaignDAO {

    private String mysqlDatabaseHost;
    private String mysqlDatabaseName;
    private String mysqlUser;
    private String mysqlPassword;

    public ForwardingIndexDAOImpl(String mysqlDatabaseHost, String mysqlDatabaseName,
                                  String mysqlUser, String mysqlPassword) {
        this.mysqlDatabaseHost = mysqlDatabaseHost;
        this.mysqlDatabaseName = mysqlDatabaseName;
        this.mysqlUser = mysqlUser;
        this.mysqlPassword = mysqlPassword;
    }

    @Override
    public boolean insertAd(Ad ad) {
        String query = "INSERT INTO " + TABLE_NAME_AD + " VALUE(?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getDBConnection();

            if (conn == null) {
                throw new SQLException("ERROR: IndexBuilder cannot connect to the MySQL DB. Fail to insert campaign.");
            }

            if (isEntryExisted(conn, TABLE_NAME_AD, COLUMN_NAME_AD_ID, ad.getAdId())) {
                throw new SQLException("INFO: The ad[" + ad.getAdId() + "] exists already");
            }

            statement = conn.prepareStatement(query);

            statement.setLong(1, ad.getAdId());
            statement.setLong(2, ad.getCampaignId());
            statement.setString(3, Utility.joinStrings(ad.getKeywords(), ","));
            statement.setString(4, ad.getTitle());
            statement.setDouble(5, ad.getPrice());
            statement.setString(6, ad.getThumbnail());
            statement.setString(7, ad.getDescription());
            statement.setString(8, ad.getBrand());
            statement.setString(9, ad.getDetailUrl());
            statement.setString(10, ad.getCategory());
            statement.setDouble(11, ad.getBidPrice());
            statement.setNull(12, Types.NULL);

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            closeDBConnection(conn, statement);
        }

        return true;
    }

    @Override
    public Ad getAd(Long id) {
        return null;
    }

    @Override
    public boolean insertCampaign(Campaign campaign) {
        String query = "INSERT INTO " + TABLE_NAME_CAMPAIGN + " VALUE(?,?)";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = getDBConnection();

            if (conn == null) {
                throw new SQLException("ERROR: IndexBuilder cannot connect to the MySQL DB. Fail to insert campaign.");
            }

            if (isEntryExisted(conn, TABLE_NAME_CAMPAIGN, COLUMN_NAME_CAMPAIGN_ID, campaign.getCampaignId())) {
                throw new SQLException("INFO: The campaign[" + campaign.getCampaignId() + "] exists already");
            }

            statement = conn.prepareStatement(query);
            statement.setLong(1, campaign.getCampaignId());
            statement.setDouble(2, campaign.getBudget());
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            closeDBConnection(conn, statement);
        }

        return true;
    }

    @Override
    public Campaign getCampaign(Long id) {
        return null;
    }

    private boolean isEntryExisted(Connection conn, String tableName, String columnName, Long value) throws SQLException {
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE " + columnName + "=" + value;

        boolean isExisted = false;
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            isExisted = true;
        }
        statement.close();

        return isExisted;
    }

    private Connection getDBConnection() {
        return MySQLManager.getConnection(
                this.mysqlDatabaseHost, this.mysqlDatabaseName, this.mysqlUser, this.mysqlPassword);
    }

    private void closeDBConnection(Connection conn, PreparedStatement statement) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
