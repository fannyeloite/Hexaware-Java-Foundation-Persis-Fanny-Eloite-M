
package com.hexaware.dao;

import java.util.List;
import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException;

public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws PolicyNotFoundException;
    List<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy) throws PolicyNotFoundException;
    boolean deletePolicy(int policyId) throws PolicyNotFoundException;
}
