package pms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for managing project data in the database.
 */
public class ProjectDAO {

    /**
     * Adds a new project to the database.
     * 
     * @param project the Project object to add
     * @throws SQLException if a database access error occurs
     */
    public void addProject(Project project) throws SQLException {
        String query = "INSERT INTO Project (Name, Type, Address, ERFNo, TotalFee, AmountPaid, Deadline, Finalised, CompletionDate, ArchitectID, ContractorID, CustomerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getType());
            stmt.setString(3, project.getAddress());
            stmt.setString(4, project.getErfNo());
            stmt.setDouble(5, project.getTotalFee());
            stmt.setDouble(6, project.getAmountPaid());
            stmt.setString(7, project.getDeadline());
            stmt.setBoolean(8, project.isFinalised());
            stmt.setString(9, project.getCompletionDate());
            stmt.setInt(10, project.getArchitectID());
            stmt.setInt(11, project.getContractorID());
            stmt.setInt(12, project.getCustomerID());
            stmt.executeUpdate();
        }
    }

    /**
     * Finds a project by its number.
     * 
     * @param projectNumber the project number
     * @return the Project object, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Project findProjectByNumber(int projectNumber) throws SQLException {
        String query = "SELECT p.*, c.firstName AS customerFirstName, c.lastName AS customerLastName, " +
                       "a.firstName AS architectFirstName, a.lastName AS architectLastName, " +
                       "ct.firstName AS contractorFirstName, ct.lastName AS contractorLastName " +
                       "FROM Project p " +
                       "JOIN Customer c ON p.CustomerID = c.CustomerID " +
                       "JOIN Architect a ON p.ArchitectID = a.ArchitectID " +
                       "JOIN Contractor ct ON p.ContractorID = ct.ContractorID " +
                       "WHERE p.ProjectNo = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, projectNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(
                    rs.getInt("ProjectNo"), 
                    rs.getString("Name"), 
                    rs.getString("Type"), 
                    rs.getString("Address"), 
                    rs.getString("ERFNo"), 
                    rs.getDouble("TotalFee"), 
                    rs.getDouble("AmountPaid"), 
                    rs.getString("Deadline"), 
                    rs.getBoolean("Finalised"), 
                    rs.getString("CompletionDate"), 
                    rs.getInt("ArchitectID"), 
                    rs.getInt("ContractorID"), 
                    rs.getInt("CustomerID")
                );
            } else {
                return null;
            }
        }
    }

    /**
     * Updates an existing project in the database.
     * 
     * @param project the Project object with updated details
     * @throws SQLException if a database access error occurs
     */
    public void updateProject(Project project) throws SQLException {
        String query = "UPDATE Project SET Name = ?, Type = ?, Address = ?, ERFNo = ?, TotalFee = ?, AmountPaid = ?, Deadline = ?, Finalised = ?, CompletionDate = ?, ArchitectID = ?, ContractorID = ?, CustomerID = ? WHERE ProjectNo = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getType());
            stmt.setString(3, project.getAddress());
            stmt.setString(4, project.getErfNo());
            stmt.setDouble(5, project.getTotalFee());
            stmt.setDouble(6, project.getAmountPaid());
            stmt.setString(7, project.getDeadline());
            stmt.setBoolean(8, project.isFinalised());
            stmt.setString(9, project.getCompletionDate());
            stmt.setInt(10, project.getArchitectID());
            stmt.setInt(11, project.getContractorID());
            stmt.setInt(12, project.getCustomerID());
            stmt.setInt(13, project.getProjectNo());
            stmt.executeUpdate();
        }
    }

    /**
     * Finalizes a project by updating its status and adding a completion date in the database.
     * 
     * @param projectNumber the project number to finalize
     * @param completionDate the completion date to set
     * @throws SQLException if a database access error occurs
     */
    public void finalizeProject(int projectNumber, String completionDate) throws SQLException {
        String query = "UPDATE Project SET Finalised = true, CompletionDate = ? WHERE ProjectNo = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, completionDate);
            stmt.setInt(2, projectNumber);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves all projects that are not yet finalized.
     * 
     * @return a list of incomplete Project objects
     * @throws SQLException if a database access error occurs
     */
    public List<Project> getIncompleteProjects() throws SQLException {
        String query = "SELECT p.*, c.firstName AS customerFirstName, c.lastName AS customerLastName, " +
                       "a.firstName AS architectFirstName, a.lastName AS architectLastName, " +
                       "ct.firstName AS contractorFirstName, ct.lastName AS contractorLastName " +
                       "FROM Project p " +
                       "JOIN Customer c ON p.CustomerID = c.CustomerID " +
                       "JOIN Architect a ON p.ArchitectID = a.ArchitectID " +
                       "JOIN Contractor ct ON p.ContractorID = ct.ContractorID " +
                       "WHERE p.Finalised = false";
        List<Project> projects = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                projects.add(new Project(
                    rs.getInt("ProjectNo"), 
                    rs.getString("Name"), 
                    rs.getString("Type"), 
                    rs.getString("Address"), 
                    rs.getString("ERFNo"), 
                    rs.getDouble("TotalFee"), 
                    rs.getDouble("AmountPaid"), 
                    rs.getString("Deadline"), 
                    rs.getBoolean("Finalised"), 
                    rs.getString("CompletionDate"), 
                    rs.getInt("ArchitectID"), 
                    rs.getInt("ContractorID"), 
                    rs.getInt("CustomerID")
                ));
            }
        }
        return projects;
    }

    /**
     * Retrieves all projects that are overdue based on their deadline.
     * 
     * @return a list of overdue Project objects
     * @throws SQLException if a database access error occurs
     */
    public List<Project> getOverdueProjects() throws SQLException {
        String query = "SELECT p.*, c.firstName AS customerFirstName, c.lastName AS customerLastName, " +
                       "a.firstName AS architectFirstName, a.lastName AS architectLastName, " +
                       "ct.firstName AS contractorFirstName, ct.lastName AS contractorLastName " +
                       "FROM Project p " +
                       "JOIN Customer c ON p.CustomerID = c.CustomerID " +
                       "JOIN Architect a ON p.ArchitectID = a.ArchitectID " +
                       "JOIN Contractor ct ON p.ContractorID = ct.ContractorID " +
                       "WHERE p.Deadline < CURDATE() AND p.Finalised = false";
        List<Project> projects = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                projects.add(new Project(
                    rs.getInt("ProjectNo"), 
                    rs.getString("Name"), 
                    rs.getString("Type"), 
                    rs.getString("Address"), 
                    rs.getString("ERFNo"), 
                    rs.getDouble("TotalFee"), 
                    rs.getDouble("AmountPaid"), 
                    rs.getString("Deadline"), 
                    rs.getBoolean("Finalised"), 
                    rs.getString("CompletionDate"), 
                    rs.getInt("ArchitectID"), 
                    rs.getInt("ContractorID"), 
                    rs.getInt("CustomerID")
                ));
            }
        }
        return projects;
    }

    /**
     * Finds a project by its name.
     * 
     * @param projectName the project name
     * @return the Project object, or null if not found
     * @throws SQLException if a database access error occurs
     */
    public Project findProjectByName(String projectName) throws SQLException {
        String query = "SELECT p.*, c.firstName AS customerFirstName, c.lastName AS customerLastName, " +
                       "a.firstName AS architectFirstName, a.lastName AS architectLastName, " +
                       "ct.firstName AS contractorFirstName, ct.lastName AS contractorLastName " +
                       "FROM Project p " +
                       "JOIN Customer c ON p.CustomerID = c.CustomerID " +
                       "JOIN Architect a ON p.ArchitectID = a.ArchitectID " +
                       "JOIN Contractor ct ON p.ContractorID = ct.ContractorID " +
                       "WHERE p.Name = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, projectName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(
                    rs.getInt("ProjectNo"), 
                    rs.getString("Name"), 
                    rs.getString("Type"), 
                    rs.getString("Address"), 
                    rs.getString("ERFNo"), 
                    rs.getDouble("TotalFee"), 
                    rs.getDouble("AmountPaid"), 
                    rs.getString("Deadline"), 
                    rs.getBoolean("Finalised"), 
                    rs.getString("CompletionDate"), 
                    rs.getInt("ArchitectID"), 
                    rs.getInt("ContractorID"), 
                    rs.getInt("CustomerID")
                );
            } else {
                return null;
            }
        }
    }

    /**
     * Deletes a project from the database.
     * 
     * @param projectNumber the project number to delete
     * @throws SQLException if a database access error occurs
     */
    public void deleteProject(int projectNumber) throws SQLException {
        String query = "DELETE FROM Project WHERE ProjectNo = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, projectNumber);
            stmt.executeUpdate();
        }
    }

    /**
     * Retrieves the customer's surname by their ID.
     * 
     * @param customerID the customer ID
     * @return the customer's surname
     * @throws SQLException if a database access error occurs
     */
    public String getCustomerSurnameByID(int customerID) throws SQLException {
        String query = "SELECT lastName FROM Customer WHERE CustomerID = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("lastName");
            } else {
                throw new SQLException("Customer not found");
            }
        }
    }
}
