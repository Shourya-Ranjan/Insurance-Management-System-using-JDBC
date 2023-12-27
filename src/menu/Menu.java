package menu;

import dao.InsuranceServiceImpl;
import entity.Policy;
import java.util.Scanner;

public class Menu {

    private static final InsuranceServiceImpl insuranceService = new InsuranceServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createyourPolicy();
                    break;
                case 2:
                    getyourPolicy();
                    break;
                case 3:
                    getyourAllPolicies();
                    break;
                case 4:
                    getyourAllPolicies();
                    updateyourPolicy();
                    break;
                case 5:
                    getyourAllPolicies();
                    deleteyourPolicy();
                    break;
                case 6:
                    System.out.println("Thank you for using Insurance Management System!");
                    break;
                default:
                    System.out.println("Invalid choice!!");
            }

        } while (choice != 6);
    }

    private static void displayMenu() {
        System.out.println("===== Insurance Management System =====");
        System.out.println("1. Create a Policy!");
        System.out.println("2. Get your Policy!");
        System.out.println("3. Retrieve all available policies!");
        System.out.println("4. Update an existing Policy!");
        System.out.println("5. Delete an existing Policy!");
        System.out.println("6. To Exit!");
        
    }

    private static void createyourPolicy() {
        System.out.println("Creating a new policy...");
        System.out.print("Enter policy Id: ");
        int policyId = scanner.nextInt();
        // Example: Getting input from the user
        System.out.print("Enter policy name: ");
        String policyName = scanner.next();
        System.out.print("Enter policy type: ");
        String policyType = scanner.next();
        System.out.print("Enter coverage amount: ");
        double coverageAmount = scanner.nextDouble();

        Policy newPolicy = new Policy(policyId, policyName, policyType, coverageAmount);

        // Example: Calling the createPolicy method from the service
        boolean success = insuranceService.createPolicy(newPolicy);

        if (success) {
            System.out.println("Policy created successfully.");
        } else {
            System.out.println("Failed to create policy. Please try again.");
        }
    }

    private static void getyourPolicy() {
        System.out.print("Enter policy ID: ");
        int policyId = scanner.nextInt();
        
        Policy policy = insuranceService.getPolicy(policyId);
        if(policy!=null){
        System.out.println("\nGetting your policy...");
        System.out.println("Policy details:\n" + policy);}
    }

    private static void getyourAllPolicies() {
        System.out.println("\nGetting all policies...");
        System.out.println("=========================");   
        insuranceService.getAllPolicies().forEach(policy->System.out.println(policy));
        System.out.println("===================================\n");
    }


    private static void updateyourPolicy() {
        System.out.println("Updating a policy...");

        // Example: Getting input from the user
        System.out.print("Enter policy ID to update: ");
        int policyId = scanner.nextInt();
        Policy result=insuranceService.getPolicy(policyId);
        if(result!=null){
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter new policy name: ");
        String newPolicyName = scanner.next();
        System.out.print("Enter new policy type: ");
        String newPolicyType = scanner.next();
        System.out.print("Enter new coverage amount: ");
        double newCoverageAmount = scanner.nextDouble();

        Policy updatedPolicy = new Policy(policyId, newPolicyName, newPolicyType, newCoverageAmount);

        // Example: Calling the updatePolicy method from the service
        boolean success = insuranceService.updatePolicy(updatedPolicy);

        if (success) {
            System.out.println("Policy updated successfully.");
        }
    }
    }


    private static void deleteyourPolicy() {
        System.out.println("Deleting a policy...");

        // Example: Getting input from the user
        System.out.print("Enter policy ID to delete: ");
        int policyId = scanner.nextInt();

        // Example: Calling the deletePolicy method from the service
        boolean success = insuranceService.deletePolicy(policyId);

        if (success) {
            System.out.println("Policy deleted successfully.");
        }
    }
}
