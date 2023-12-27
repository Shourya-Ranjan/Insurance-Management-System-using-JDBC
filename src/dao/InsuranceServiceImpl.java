package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import entity.*;
import exception.PolicyNotFoundException;
import util.DBConnection;

import java.sql.Connection;

public class InsuranceServiceImpl implements IPolicyService {
    static Scanner scanner = new Scanner(System.in);
    Connection conn = DBConnection.getConnection();

    @Override
    public boolean createPolicy(Policy policy) { //Create PolicyFunction
        Connection conn = DBConnection.getConnection();

        boolean status = false;
        try {

            String query = "INSERT INTO policy (policyId, policyName, policyType, coverageAmount)\r\n"
                    + "VALUES (?, ?, ?, ?);";
            // INSERT INTO Policy (policyId, policyName, policyType, coverageAmount)
            // VALUES (1, 'Life Insurance', 'Term Life', 50000.00);
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, policy.getPolicyId());
            pstmt.setString(2, policy.getPolicyName());
            pstmt.setString(3, policy.getPolicyType());
            pstmt.setDouble(4, policy.getCoverageAmount());

            status = pstmt.execute();
            return !status;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    @Override
    public Policy getPolicy(int policyId) {
        Policy result = null;
        try {
            // Retrieve policy from the database
            String sql = "SELECT * FROM policy WHERE policyId = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            {
                statement.setInt(1, policyId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    result = new Policy(resultSet.getInt("policyId"), resultSet.getString("policyName"),
                            resultSet.getString("policyType"), resultSet.getDouble("coverageAmount"));
                } else {
                    throw new PolicyNotFoundException(policyId);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result; // Policy not found
    }

    @Override
    public Collection<Policy> getAllPolicies() {
        Connection conn = DBConnection.getConnection();
		ArrayList<Policy> result=new ArrayList<Policy>();
		try {
			
			String query = "Select * from policy ";
//			INSERT INTO Policy (policyId, policyName, policyType, coverageAmount)
//			VALUES (1, 'Life Insurance', 'Term Life', 50000.00);
			PreparedStatement pstmt=conn.prepareStatement(query);
			ResultSet res = pstmt.executeQuery();
			
			while(res.next())
			result.add(new Policy(res.getInt("policyId"),res.getString("policyName"),res.getString("policyType"),res.getDouble("coverageAmount")));
			return result;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
        
    }

    @Override
    public boolean updatePolicy(Policy policy) {
		Connection conn = DBConnection.getConnection();

		boolean status=false;
		try {
			
			String query = "UPDATE Policy SET policyName=?,policyType=?,coverageAmount=? WHERE policyId = ? ";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, policy.getPolicyName());
			pstmt.setString(2, policy.getPolicyType());
			pstmt.setDouble(3, policy.getCoverageAmount());
			pstmt.setInt(4, policy.getPolicyId());
			
			status = pstmt.execute();
            // System.out.println(status);
            if(status)
            {
                throw new PolicyNotFoundException(0);
            }
			// return !status;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		return status;
	}

    @Override
    public boolean deletePolicy(int policyId) {
		Connection conn = DBConnection.getConnection();

		boolean status=false;
		try {
			
			String query = "DELETE FROM Policy WHERE policyId = ?";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, policyId);
			
			status = pstmt.execute();
            // System.out.println(status);
            if(status==false)
            {
                throw new PolicyNotFoundException(policyId);
            }
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	
		return status;
	}

}
