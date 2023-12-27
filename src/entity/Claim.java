package entity;

public class Claim {
    private int claimId;
    private String claimNumber;
    private String dateFiled;
    private double claimAmount;
    private String status;
    private int policyId;
    private int clientId;
	public Claim(int claimId, String claimNumber, String dateFiled, double claimAmount, String status, int policyId,
			int clientId) {
		super();
		this.claimId = claimId;
		this.claimNumber = claimNumber;
		this.dateFiled = dateFiled;
		this.claimAmount = claimAmount;
		this.status = status;
		this.policyId = policyId;
		this.clientId = clientId;
	}
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public String getClaimNumber() {
		return claimNumber;
	}
	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}
	public String getDateFiled() {
		return dateFiled;
	}
	public void setDateFiled(String dateFiled) {
		this.dateFiled = dateFiled;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", claimNumber=" + claimNumber + ", dateFiled=" + dateFiled
				+ ", claimAmount=" + claimAmount + ", status=" + status + ", policyId=" + policyId + ", clientId="
				+ clientId + "]";
	}
    
    

 
}


