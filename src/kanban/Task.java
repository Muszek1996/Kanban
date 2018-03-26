package kanban;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;

import javax.tools.Tool;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.EventListener;

public class Task extends ListCell<Task> {
    String title;
    Tooltip description;
    Priority priority;
    Date expiryDate;


    public Task(String title, Tooltip description, Priority priority, Date expiryDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.expiryDate = expiryDate;
    }
    public Task(int i) {
        this.title = "titledebug"+String.valueOf(i);
        this.description = new Tooltip("Desctiption"+String.valueOf(i));
        this.priority = Priority.TurboImportant;
        this.expiryDate = new Date(i,12,15);

    }
    public String getPriority(){
        return priority.toString();
    }

    public Tooltip getDescription(){
        return description;
    }
    @Override
    public String toString() {
        return title;
    }




}
