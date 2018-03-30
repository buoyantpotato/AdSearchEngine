package com.adsearch.model;

import java.io.Serializable;

public class Campaign implements Serializable{

    private Long campaignId;
    private double budget;

    public Campaign(Long campaignId, double budget) {
        this.campaignId = campaignId;
        this.budget = budget;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
