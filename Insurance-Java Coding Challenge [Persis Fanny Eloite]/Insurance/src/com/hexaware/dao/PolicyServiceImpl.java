package com.hexaware.dao;

import java.sql.*;
import java.util.*;

import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException;
import com.hexaware.util.DBConnUtil;

public class PolicyServiceImpl implements IPolicyService {

    private Connection conn;

    public PolicyServiceImpl() {
        try {
            conn = DBConnUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO Policy (policyId, policyName, premium) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, policy.getPolicyId());
            ps.setString(2, policy.getPolicyName());
            ps.setDouble(3, policy.getPremium());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        String query = "SELECT * FROM Policy WHERE policyId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, policyId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("policyName");
                double premium = rs.getDouble("premium");
                return new Policy(policyId, name, premium);
            } else {
                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String query = "SELECT * FROM Policy";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("policyId");
                String name = rs.getString("policyName");
                double premium = rs.getDouble("premium");
                policies.add(new Policy(id, name, premium));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) throws PolicyNotFoundException {
        String query = "UPDATE Policy SET policyName = ?, premium = ? WHERE policyId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, policy.getPolicyName());
            ps.setDouble(2, policy.getPremium());
            ps.setInt(3, policy.getPolicyId());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new PolicyNotFoundException("Cannot update: Policy with ID " + policy.getPolicyId() + " not found.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) throws PolicyNotFoundException {
        String query = "DELETE FROM Policy WHERE policyId = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, policyId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new PolicyNotFoundException("Cannot delete: Policy with ID " + policyId + " not found.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
