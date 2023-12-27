// Policy.java
package entity;

public class Policy {
    public int policyId;
    public String policyName;
    public String policyType;
    public double coverageAmount;


    public Policy(int policyId, String policyName, String policyType, double coverageAmount) {
        this.policyId = policyId;
        this.policyName = policyName;
        this.policyType = policyType;
        this.coverageAmount = coverageAmount;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }


    @Override
    public String toString() {
        return "Policy{" +
                "policyId=" + policyId +
                ", policyName='" + policyName + '\'' +
                ", policyType='" + policyType + '\'' +
                ", coverageAmount=" + coverageAmount +
                '}';
    }
}
