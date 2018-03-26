package kanban;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.input.DataFormat;

import javax.tools.Tool;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.EventListener;

public class Task extends ListCell<Task>  {
    private String title;
    private Tooltip description;
    private Priority priority;
    private LocalDate expiryDate;

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    Task(String title, Tooltip description, Priority priority, LocalDate expiryDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.expiryDate = expiryDate;
    }

    public String getPriorityString(){
        return priority.toString();
    }
    public Priority getPriority(){
        return priority;
    }
    public Tooltip getDescription(){
        return description;
    }
    @Override
    public String toString() {
        return title;
    }


}
