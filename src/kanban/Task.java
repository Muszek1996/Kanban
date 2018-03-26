package kanban;

import java.util.Date;

public class Task {
    String title,description;
    Priority priority;
    Date expiryDate;

    public Task(String title, String description, Priority priority, Date expiryDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.expiryDate = expiryDate;
    }
    public Task(int i) {
        this.title = "titledebug"+String.valueOf(i);
        this.description = "description"+String.valueOf(i);
        this.priority = Priority.TurboImportant;
        this.expiryDate = new Date(i,12,15);
    }

    @Override
    public String toString() {
        return title;
    }
}
