package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import entity.*;
import exception.PolicyNotFoundException;
import util.DBConnection;

import java.sql.Connection;

public class InsuranceServiceImpl implements IPolicyService {
    static Scanner scanner = new Scanner(System.in);
    static Connection conn = DBConnection.getConnection();

    @Override
    public boolean createPolicy(Policy policy) { // Create PolicyFunction
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
        return result; 
    }

    @Override
    public Collection<Policy> getAllPolicies() {
        Map<Integer, Policy> policyMap = new HashMap<>();
        try {
            String query = "SELECT * FROM policy";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet res = pstmt.executeQuery();

            while (res.next()) {
                int policyId = res.getInt("policyId");
                String policyName = res.getString("policyName");
                String policyType = res.getString("policyType");
                double coverageAmount = res.getDouble("coverageAmount");

                Policy policy = new Policy(policyId, policyName, policyType, coverageAmount);
                policyMap.put(policyId, policy);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return policyMap.values(); // Return the Collection of Policy objects
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        boolean status = false;
        try {

            String query = "UPDATE Policy SET policyName=?,policyType=?,coverageAmount=? WHERE policyId = ? ";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, policy.getPolicyName());
            pstmt.setString(2, policy.getPolicyType());
            pstmt.setDouble(3, policy.getCoverageAmount());
            pstmt.setInt(4, policy.getPolicyId());

            status = pstmt.execute();
            // System.out.println(status);
            if (status) {
                throw new PolicyNotFoundException(0);
            }
            // return !status;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return status;
    }

    @Override
    public boolean deletePolicy(int policyId) {

        boolean status = false;
        try {

            String query = "DELETE FROM Policy WHERE policyId = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, policyId);

            status = pstmt.executeUpdate() > 0;
            // System.out.println(status);
            if (status == false) {
                throw new PolicyNotFoundException(policyId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return status;
    }

}
