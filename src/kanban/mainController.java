package kanban;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import java.net.URL;
import java.util.*;

public class mainController implements Initializable {

    @FXML private Menu about;
    Label todolabel;
    ListView toodo;
    ListView<String> todoo;
    BorderPane borderPane;
    @FXML public ListView<Task> todo;
    @FXML private ListView<Task> inProgress;
    @FXML private ListView<Task> done;

    @FXML private void buttonAction() throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("newTask.fxml"));
        Stage stage = new Stage();
        stage.setTitle("My New Stage Title");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        // Hide this current window (if this is what you want)


        ObservableList<String> items = FXCollections.observableArrayList(
                "Singles", "Double", "Suite", "Family App");

       // todo.setItems(items);

    }

    @FXML private void showAboutDialog() {
        System.out.println("DUPA");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About author");
        alert.setHeaderText("Informations about author:");
        alert.setContentText("Personal details: Jakub Mucha\nEmail:rroott33@gmail.com");
        alert.showAndWait();
    }

    @FXML private void closeAction() {
        Platform.exit();
        System.exit(0);
    }


    @FXML private void contextMenuAction(ContextMenuEvent e){
        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        ListView source =(ListView)e.getSource();

        delete.setOnAction(event -> {
            source.getItems().removeAll(source.getSelectionModel().getSelectedItems());
        });
        menu.getItems().addAll(delete,new MenuItem("Edit"));
        menu.show(source,e.getScreenX(), e.getScreenY());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){


        ObservableList<Task> items1 = FXCollections.observableArrayList(
                new Task(1),
                new Task(2),
                new Task(3),
                new Task(7)
        );
        ObservableList<Task> items2 = FXCollections.observableArrayList(
                new Task(4),
                new Task(5),
                new Task(6),
                new Task(8)
        );
        ObservableList<Task> items3 = FXCollections.observableArrayList(
                new Task(11),
                new Task(12),
                new Task(13),
                new Task(14)
        );

        System.out.println("inicjalizuje");
        todo.setItems(items1);       //debug
        inProgress.setItems(items2); //debug
        done.setItems(items3);       //debug
    }
}