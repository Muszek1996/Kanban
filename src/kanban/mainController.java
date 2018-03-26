package kanban;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class mainController implements Initializable {
    public static ListView destination;
    public static ListView todoStatic;
    @FXML public  ListView todo;
    @FXML private ListView inProgress;
    @FXML private ListView done;
    private final ObjectProperty<ListCell<Task>> dragSource = new SimpleObjectProperty<>();


    @FXML private Stage buttonAction() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("newTask.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Main.primaryStageMain);
        stage.setTitle("Add new task");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        destination=todo;
        taskController.stageStatic = stage;
        return stage;
    }

    @FXML private void showAboutDialog() {
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
        MenuItem edit = new MenuItem("Edit");
        ListView source =(ListView)e.getSource();
        delete.setOnAction(event -> {

            source.getItems().removeAll(source.getSelectionModel().getSelectedItems());
        });
        edit.setOnAction(event ->{
            try {

                buttonAction();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            destination = source;
            if((Task)source.getSelectionModel().getSelectedItem()!=null)
            taskController.editTask((Task)source.getSelectionModel().getSelectedItem(),(ListView)e.getSource());
        });

        menu.getItems().addAll(delete,edit);
        menu.setAutoHide(true);
        menu.setHideOnEscape(true);
        source.setContextMenu(menu);
        //menu.show(source,e.getScreenX(), e.getScreenY());
    }
    void setSource(ListView<Task> source){
        destination=source;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<ListView> lists = FXCollections.observableArrayList(
                todo,inProgress,done
        );
        todoStatic = todo;


        ObservableList<Task> items1 = FXCollections.observableArrayList(
                new Task("Zrobić program (Kanban)",new Tooltip("mega wazne zadanie "),Priority.TurboImportant, LocalDate.now()),
                new Task("Wyniesc smieci",new Tooltip("isc i wyniesc smieci ;D"),Priority.Medium, LocalDate.now()),
                new Task("Wyproawdzic psa",new Tooltip("Iść z azorem na spacer"),Priority.High, LocalDate.now()),
                new Task("Iść na siłownie",new Tooltip("Klata,plecy,barki"),Priority.Low, LocalDate.now())
        );

        todo.setItems(items1);





    for(ListView l:lists){

        l.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {

            @Override
            public ListCell<Task> call(ListView<Task> param) {
                ListCell<Task> cell = new ListCell<Task>() {

                    @Override
                    protected void updateItem(Task item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getPriority() ==null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.toString());
                            setTooltip(item.getDescription());
                            ImageView priorityImage = new ImageView(new Image(this.getClass().getResource("img/"+item.getPriorityString()+".png").toString()));
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






                cell.setOnDragDone(event -> {
                    destination.getItems().add(cell.getItem());
                    ((ListCell)event.getSource()).getListView().getItems().remove(cell.getItem());
                });





                return cell;
            }
        });
    }

        for(ListView i:lists){
            i.setOnDragOver(event -> {
                setSource((ListView)event.getSource());
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            });
        }



    }
}