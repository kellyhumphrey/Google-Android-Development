package khumphrey.com.final_taskmanager.ui.main;

public class Task {

    private String taskTitle = "";
    private String shortDescription = "";
    private String additionalInformation ="";
    private String dueDate = "";

    public String getTaskTitle() {return(taskTitle);}
    public void setTaskTitle(String taskTitle) {this.taskTitle=taskTitle;}

    public String getDueDate() {return(dueDate);}
    public void setDueDate(String dueDate) {this.dueDate=dueDate;}

    public String getShortDescription() {
        if (shortDescription != null) {return(shortDescription);}
        else {return "No short description was provided";}
    }
    public void setShortDescription(String shortDescription) {this.shortDescription=shortDescription;}

    public String getAdditionalInformation() {
        if(additionalInformation != null) {return(additionalInformation);}
        else {return "No additional information was provided";}
    }
    public void setAdditionalInformation(String additionalInformation) {this.additionalInformation=additionalInformation;}

    protected long id = 0;
    public long getId() {return(id);}
    public void setId(long id) {this.id=id;}

    @Override
    public String toString() {
        return "Task{taskTitle='" + taskTitle + '\'' + ", shortDescription='" + shortDescription + '\'' + ", additionalInformation='" + additionalInformation + '\'' +'}';
    }

}
