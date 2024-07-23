package pms;

/**
 * Represents a project in the Poise Project Management System.
 */
public class Project {
    private int projectNo;
    private String name;
    private String type;
    private String address;
    private String erfNo;
    private double totalFee;
    private double amountPaid;
    private String deadline;
    private boolean finalised;
    private String completionDate;
    private int architectID;
    private int contractorID;
    private int customerID;

    /**
     * Constructor to match the parameters used when creating a new Project instance.
     * 
     * @param projectNo the project number
     * @param name the project name
     * @param type the project type
     * @param address the project address
     * @param erfNo the ERF number
     * @param totalFee the total fee
     * @param amountPaid the amount paid
     * @param deadline the project deadline
     * @param finalised whether the project is finalised
     * @param completionDate the completion date
     * @param architectID the architect ID
     * @param contractorID the contractor ID
     * @param customerID the customer ID
     */
    public Project(int projectNo, String name, String type, String address, String erfNo, double totalFee, double amountPaid, String deadline, boolean finalised, String completionDate, int architectID, int contractorID, int customerID) {
        this.projectNo = projectNo;
        this.name = name;
        this.type = type;
        this.address = address;
        this.erfNo = erfNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
        this.deadline = deadline;
        this.finalised = finalised;
        this.completionDate = completionDate;
        this.architectID = architectID;
        this.contractorID = contractorID;
        this.customerID = customerID;
    }

    // Getters and setters with Javadoc comments

    /**
     * Gets the project number.
     * 
     * @return the project number
     */
    public int getProjectNo() {
        return projectNo;
    }

    /**
     * Sets the project number.
     * 
     * @param projectNo the project number to set
     */
    public void setProjectNo(int projectNo) {
        this.projectNo = projectNo;
    }

    /**
     * Gets the project name.
     * 
     * @return the project name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the project name.
     * 
     * @param name the project name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the project type.
     * 
     * @return the project type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the project type.
     * 
     * @param type the project type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the project address.
     * 
     * @return the project address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the project address.
     * 
     * @param address the project address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the project ERF number.
     * 
     * @return the project ERF number
     */
    public String getErfNo() {
        return erfNo;
    }

    /**
     * Sets the project ERF number.
     * 
     * @param erfNo the project ERF number to set
     */
    public void setErfNo(String erfNo) {
        this.erfNo = erfNo;
    }

    /**
     * Gets the total fee of the project.
     * 
     * @return the total fee
     */
    public double getTotalFee() {
        return totalFee;
    }

    /**
     * Sets the total fee of the project.
     * 
     * @param totalFee the total fee to set
     */
    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * Gets the amount paid for the project.
     * 
     * @return the amount paid
     */
    public double getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the amount paid for the project.
     * 
     * @param amountPaid the amount paid to set
     */
    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Gets the project deadline.
     * 
     * @return the project deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Sets the project deadline.
     * 
     * @param deadline the project deadline to set
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * Checks if the project is finalised.
     * 
     * @return true if the project is finalised, false otherwise
     */
    public boolean isFinalised() {
        return finalised;
    }

    /**
     * Sets the project finalisation status.
     * 
     * @param finalised the finalisation status to set
     */
    public void setFinalised(boolean finalised) {
        this.finalised = finalised;
    }

    /**
     * Gets the project completion date.
     * 
     * @return the completion date
     */
    public String getCompletionDate() {
        return completionDate;
    }

    /**
     * Sets the project completion date.
     * 
     * @param completionDate the completion date to set
     */
    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Gets the architect ID associated with the project.
     * 
     * @return the architect ID
     */
    public int getArchitectID() {
        return architectID;
    }

    /**
     * Sets the architect ID associated with the project.
     * 
     * @param architectID the architect ID to set
     */
    public void setArchitectID(int architectID) {
        this.architectID = architectID;
    }

    /**
     * Gets the contractor ID associated with the project.
     * 
     * @return the contractor ID
     */
    public int getContractorID() {
        return contractorID;
    }

    /**
     * Sets the contractor ID associated with the project.
     * 
     * @param contractorID the contractor ID to set
     */
    public void setContractorID(int contractorID) {
        this.contractorID = contractorID;
    }

    /**
     * Gets the customer ID associated with the project.
     * 
     * @return the customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Sets the customer ID associated with the project.
     * 
     * @param customerID the customer ID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectNo=" + projectNo +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                ", erfNo='" + erfNo + '\'' +
                ", totalFee=" + totalFee +
                ", amountPaid=" + amountPaid +
                ", deadline='" + deadline + '\'' +
                ", finalised=" + finalised +
                ", completionDate='" + completionDate + '\'' +
                ", architectID=" + architectID +
                ", contractorID=" + contractorID +
                ", customerID=" + customerID +
                '}';
    }
}

