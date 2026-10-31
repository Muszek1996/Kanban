# Kanban

JavaFX Kanban board with TO DO, IN PROGRESS and DONE columns. Each task has a title, priority (Low, Medium, High, TurboImportant), expiry date and description. You can move tasks between columns by drag and drop, and edit or delete them from a context menu.

- `Main.java`: entry point
- `kanban.fxml`, `mainController.java`: board, drag and drop, context menu
- `newTask.fxml`, `taskController.java`: add/edit task dialog
- `Task.java`, `Priority.java`: model

## Run
Requires a JDK with JavaFX (Java 8, or OpenJFX added to newer JDKs). Open the IntelliJ project (`Kanban.iml`) and run `kanban.Main`.
