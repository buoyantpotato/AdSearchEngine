package com.adsearch.model;

import java.io.Serializable;
import java.util.List;

public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long adId;
    private Long campaignId;
    private List<String> keywords;

    private double relevanceScore;
    private double rankScore;
    private double pClick;
    private double bidPrice;
    private double qualityScore;
    private double costPerClick;

    private int position; // 1: top; 2: bottom

    private String title;
    private double price;
    private String thumbnail;
    private String description;
    private String brand;
    private String detailUrl;
    private String query;
    private String category;

    public Ad(Long adId, Long campaignId, List<String> keywords, String title, double price, String thumbnail,
              String description, String brand, String detailUrl, String category, double bidPrice, double pClick) {
        this.adId = adId;
        this.campaignId = campaignId;
        this.keywords = keywords;
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.description = description;
        this.brand = brand;
        this.detailUrl = detailUrl;
        this.category = category;
        this.bidPrice = bidPrice;
        this.pClick = pClick;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public double getRelevanceScore() {
        return relevanceScore;
    }

    public void setRelevanceScore(double relevanceScore) {
        this.relevanceScore = relevanceScore;
    }

    public double getRankScore() {
        return rankScore;
    }

    public void setRankScore(double rankScore) {
        this.rankScore = rankScore;
    }

    public double getpClick() {
        return pClick;
    }

    public void setpClick(double pClick) {
        this.pClick = pClick;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public double getCostPerClick() {
        return costPerClick;
    }

    public void setCostPerClick(double costPerClick) {
        this.costPerClick = costPerClick;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
