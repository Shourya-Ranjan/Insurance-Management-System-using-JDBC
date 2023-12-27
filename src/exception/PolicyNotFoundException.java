package exception;

public class PolicyNotFoundException extends Exception{
	public PolicyNotFoundException(int policyId) {
	super("Policy not found with ID: "+policyId);
}
}