package com.hexaware.main;

import java.util.List;
import java.util.Scanner;

import com.hexaware.dao.IPolicyService;
import com.hexaware.dao.PolicyServiceImpl;
import com.hexaware.entity.Policy;
import com.hexaware.exception.PolicyNotFoundException;


public class mainmod {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        IPolicyService policyService = new PolicyServiceImpl();

        int choice;
        do {
            System.out.println("\n====== INSURANCE POLICY MANAGEMENT ======");
            System.out.println("1. Create Policy");
            System.out.println("2. View Policy by ID");
            System.out.println("3. View All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Policy ID:");
                    int pid = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.println("Enter Policy Name:");
                    String pname = sc.nextLine();

                    System.out.println("Enter Premium:");
                    double premium = sc.nextDouble();

                    Policy newPolicy = new Policy(pid, pname, premium);
                    boolean created = policyService.createPolicy(newPolicy);
                    System.out.println(created ? "Policy Created!" : "Failed to Create Policy.");
                    break;

                case 2:
                    System.out.println("Enter Policy ID to View:");
                    int viewId = sc.nextInt();
                    Policy found1 = null;
                    try {
                        found1 = policyService.getPolicy(viewId);
                        System.out.println("Policy Details: " + found1);
                    } catch (PolicyNotFoundException e) {
                        System.out.println("Policy Not Found! " + e.getMessage());
                    }
                    break;


                case 3:
                    List<Policy> policies = policyService.getAllPolicies();
                    if (!policies.isEmpty()) {
                        System.out.println("All Policies:");
                        for (Policy p : policies) {
                            System.out.println(p);
                        }
                    } else {
                        System.out.println("No policies found.");
                    }
                    break;

                case 4:
                    System.out.println("Enter Policy ID to Update:");
                    int upId = sc.nextInt();
                    sc.nextLine(); // consume newline

                    System.out.println("Enter New Policy Name:");
                    String newName = sc.nextLine();

                    System.out.println("Enter New Premium:");
                    double newPremium = sc.nextDouble();

                    Policy updatedPolicy = new Policy(upId, newName, newPremium);
                    try {
                        boolean updated = policyService.updatePolicy(updatedPolicy);
                        System.out.println(updated ? "Policy Updated!" : "Update Failed.");
                    } catch (PolicyNotFoundException e) {
                        System.out.println("Policy Not Found! " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Enter Policy ID to Delete:");
                    int delId = sc.nextInt();
                    try {
                        boolean deleted = policyService.deletePolicy(delId);
                        System.out.println(deleted ? "Policy Deleted!" : "Delete Failed.");
                    } catch (PolicyNotFoundException e) {
                        System.out.println("Policy Not Found! " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
		

	}


