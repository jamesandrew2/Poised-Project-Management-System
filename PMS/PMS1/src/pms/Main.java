package pms;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * The main class for the Poise Project Management System application.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProjectDAO projectDAO = new ProjectDAO();

        while (true) {
            System.out.println("Project Management System");
            System.out.println("1. Add new project");
            System.out.println("2. Update existing project");
            System.out.println("3. Finalize project");
            System.out.println("4. List incomplete projects");
            System.out.println("5. List overdue projects");
            System.out.println("6. Find project by number or name");
            System.out.println("7. Delete project");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    try {
                        addNewProject(scanner, projectDAO);
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        updateProject(scanner, projectDAO);
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        finalizeProject(scanner, projectDAO);
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        listIncompleteProjects(projectDAO);
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        listOverdueProjects(projectDAO);
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        findProject(scanner, projectDAO);
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        deleteProject(scanner, projectDAO);
                    } catch (SQLException e) {
                        System.out.println("SQL Error: " + e.getMessage());
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Adds a new project to the database.
     * 
     * @param scanner the Scanner object for user input
     * @param projectDAO the ProjectDAO object for database operations
     * @throws SQLException if a database access error occurs
     */
    private static void addNewProject(Scanner scanner, ProjectDAO projectDAO) throws SQLException {
        System.out.println("Adding a new project...");

        System.out.print("Enter project name (leave blank to auto-generate): ");
        String projectName = scanner.nextLine();

        System.out.print("Enter building type: ");
        String buildingType = scanner.nextLine();

        System.out.print("Enter project address: ");
        String address = scanner.nextLine();

        System.out.print("Enter ERF number: ");
        String erfNumber = scanner.nextLine();

        System.out.print("Enter total fee: ");
        double totalFee = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter total amount paid: ");
        double totalPaid = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        System.out.print("Enter project deadline (YYYY-MM-DD): ");
        String deadline = scanner.nextLine();

        System.out.print("Enter architect ID: ");
        int architectID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter contractor ID: ");
        int contractorID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter customer ID: ");
        int customerID = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Generate project name if not provided
        if (projectName.isEmpty()) {
            projectName = buildingType + " " + projectDAO.getCustomerSurnameByID(customerID);
        }

        Project project = new Project(0, projectName, buildingType, address, erfNumber, totalFee, totalPaid, deadline, false, null, architectID, contractorID, customerID);
        projectDAO.addProject(project);
        System.out.println("Project added successfully!");
    }

    /**
     * Updates an existing project in the database.
     * 
     * @param scanner the Scanner object for user input
     * @param projectDAO the ProjectDAO object for database operations
     * @throws SQLException if a database access error occurs
     */
    private static void updateProject(Scanner scanner, ProjectDAO projectDAO) throws SQLException {
        System.out.println("Updating an existing project...");

        System.out.print("Enter project number to update: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Project project = projectDAO.findProjectByNumber(projectNumber);
        if (project != null) {
            System.out.println("Current project details: " + project);

            System.out.print("Enter new project name (or press Enter to keep current): ");
            String projectName = scanner.nextLine();
            if (!projectName.isEmpty()) {
                project.setName(projectName);
            }

            System.out.print("Enter new building type (or press Enter to keep current): ");
            String buildingType = scanner.nextLine();
            if (!buildingType.isEmpty()) {
                project.setType(buildingType);
            }

            System.out.print("Enter new project address (or press Enter to keep current): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                project.setAddress(address);
            }

            System.out.print("Enter new ERF number (or press Enter to keep current): ");
            String erfNumber = scanner.nextLine();
            if (!erfNumber.isEmpty()) {
                project.setErfNo(erfNumber);
            }

            System.out.print("Enter new total fee (or press Enter to keep current): ");
            String totalFeeInput = scanner.nextLine();
            if (!totalFeeInput.isEmpty()) {
                project.setTotalFee(Double.parseDouble(totalFeeInput));
            }

            System.out.print("Enter new total amount paid (or press Enter to keep current): ");
            String totalPaidInput = scanner.nextLine();
            if (!totalPaidInput.isEmpty()) {
                project.setAmountPaid(Double.parseDouble(totalPaidInput));
            }

            System.out.print("Enter new project deadline (or press Enter to keep current): ");
            String deadline = scanner.nextLine();
            if (!deadline.isEmpty()) {
                project.setDeadline(deadline);
            }

            projectDAO.updateProject(project);
            System.out.println("Project updated successfully!");
        } else {
            System.out.println("Project not found!");
        }
    }

    /**
     * Finalizes a project in the database.
     * 
     * @param scanner the Scanner object for user input
     * @param projectDAO the ProjectDAO object for database operations
     * @throws SQLException if a database access error occurs
     */
    private static void finalizeProject(Scanner scanner, ProjectDAO projectDAO) throws SQLException {
        System.out.println("Finalizing a project...");

        System.out.print("Enter project number to finalize: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter completion date (YYYY-MM-DD): ");
        String completionDate = scanner.nextLine();

        projectDAO.finalizeProject(projectNumber, completionDate);
        System.out.println("Project finalized successfully!");
    }

    /**
     * Lists all incomplete projects.
     * 
     * @param projectDAO the ProjectDAO object for database operations
     * @throws SQLException if a database access error occurs
     */
    private static void listIncompleteProjects(ProjectDAO projectDAO) throws SQLException {
        System.out.println("Listing incomplete projects...");
        for (Project project : projectDAO.getIncompleteProjects()) {
            System.out.println(project);
        }
    }

    /**
     * Lists all overdue projects.
     * 
     * @param projectDAO the ProjectDAO object for database operations
     * @throws SQLException if a database access error occurs
     */
    private static void listOverdueProjects(ProjectDAO projectDAO) throws SQLException {
        System.out.println("Listing overdue projects...");
        for (Project project : projectDAO.getOverdueProjects()) {
            System.out.println(project);
        }
    }

    /**
     * Finds a project by its number or name.
     * 
     * @param scanner the Scanner object for user input
     * @param projectDAO the ProjectDAO object for database operations
     * @throws SQLException if a database access error occurs
     */
    private static void findProject(Scanner scanner, ProjectDAO projectDAO) throws SQLException {
        System.out.println("Finding a project by number or name...");

        System.out.print("Enter project number or name: ");
        String input = scanner.nextLine();

        try {
            int projectNumber = Integer.parseInt(input);
            Project project = projectDAO.findProjectByNumber(projectNumber);
            if (project != null) {
                System.out.println(project);
            } else {
                System.out.println("Project not found!");
            }
        } catch (NumberFormatException e) {
            Project project = projectDAO.findProjectByName(input);
            if (project != null) {
                System.out.println(project);
            } else {
                System.out.println("Project not found!");
            }
        }
    }

    /**
     * Deletes a project from the database.
     * 
     * @param scanner the Scanner object for user input
     * @param projectDAO the ProjectDAO object for database operations
     * @throws SQLException if a database access error occurs
     */
    private static void deleteProject(Scanner scanner, ProjectDAO projectDAO) throws SQLException {
        System.out.println("Deleting a project...");

        System.out.print("Enter project number to delete: ");
        int projectNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        projectDAO.deleteProject(projectNumber);
        System.out.println("Project deleted successfully!");
    }
}