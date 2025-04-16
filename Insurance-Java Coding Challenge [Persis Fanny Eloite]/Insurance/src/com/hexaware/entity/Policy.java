package com.hexaware.entity;

public class Policy {
	 private int policyId;
	    private String policyName;
	    private double premium;

	    public Policy() {}

	    public Policy(int policyId, String policyName, double premium) {
	        this.policyId = policyId;
	        this.policyName = policyName;
	        this.premium = premium;
	    }

	    // Getters, setters, toString()

	    public int getPolicyId() { return policyId; }
	    public void setPolicyId(int policyId) { this.policyId = policyId; }

	    public String getPolicyName() { return policyName; }
	    public void setPolicyName(String policyName) { this.policyName = policyName; }

	    public double getPremium() { return premium; }
	    public void setPremium(double premium) { this.premium = premium; }

	    @Override
	    public String toString() {
	        return "Policy [policyId=" + policyId + ", policyName=" + policyName + ", premium=" + premium + "]";
	    }

}
