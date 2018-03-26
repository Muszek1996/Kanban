package kanban;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import java.net.URL;
import java.util.*;

public class mainController implements Initializable {

    @FXML private Menu about;
    @FXML private Label todolabel;
    @FXML private ListView todo;
    @FXML private ListView inProgress;
    @FXML private ListView done;
    @FXML private BorderPane borderPane;
    private final ObjectProperty<ListCell<Task>> dragSource = new SimpleObjectProperty<>();
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
        System.out.println(source.getItems().toString());
        delete.setOnAction(event -> {

            source.getItems().removeAll(source.getSelectionModel().getSelectedItems());
        });
        menu.getItems().addAll(delete,new MenuItem("Edit"));
        menu.setAutoHide(true);
        menu.setHideOnEscape(true);
        source.setContextMenu(menu);
        //menu.show(source,e.getScreenX(), e.getScreenY());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){


        ObservableList<Task> items1 = FXCollections.observableArrayList(
                new Task("Low",new Tooltip("zrobimy w przyszłym roku"),Priority.Low,new Date(9,9,3)),
                new Task("Medium",new Tooltip("srednio wazne zadanie to nie jest robota na dziś"),Priority.Medium,new Date(9,9,3)),
                new Task("High",new Tooltip("zadanie o wysokim priorytecie"),Priority.High,new Date(9,9,3)),
                new Task("TurboImportant",new Tooltip("mega wazne zadanie"),Priority.TurboImportant,new Date(9,9,3))
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
        ObservableList<ListView> lists = FXCollections.observableArrayList(
                todo,inProgress,done
        );

    for(ListView l:lists){
        l.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {

            @Override
            public ListCell<Task> call(ListView<Task> param) {
                ListCell<Task> cell = new ListCell<Task>() {

                    @Override
                    protected void updateItem(Task item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                            setTooltip(item.getDescription());
                            ImageView priorityImage = new ImageView(new Image(this.getClass().getResource("img/"+item.getPriority()+".png").toString()));
                            priorityImage.setFitHeight(32);
                            priorityImage.setFitWidth(32);
                            setGraphic(priorityImage);
                        }


                    }
                };

                cell.setOnDragDetected(event -> {
                    if (! cell.isEmpty()) {
                        Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent cc = new ClipboardContent();
                        cc.putString(cell.getItem().toString());
                        db.setContent(cc);
                        dragSource.set(cell);
                    }
                });

                cell.setOnDragOver(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                });

                cell.setOnDragDone(event -> ((ListCell)event.getSource()).getListView().getItems().remove(cell.getItem()));


                cell.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString() && dragSource.get() != null) {
                        // in this example you could just do
                        // listView.getItems().add(db.getString());
                        // but more generally:

                        ListCell<Task> dragSourceCell = dragSource.get();
                        ((ListCell)event.getSource()).getListView().getItems().add(dragSourceCell.getItem());
                        event.setDropCompleted(true);
                        dragSource.set(null);
                    } else {
                        event.setDropCompleted(false);
                    }
                });


                return cell;
            }
        });
    }




        todo.setItems(items1);
        inProgress.setItems(items2);
        done.setItems(items3);

        System.out.println("inicjalizuje");


    }
}