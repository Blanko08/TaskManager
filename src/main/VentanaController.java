package main;

import methods.ModifyTask;
import methods.DeleteTask;
import methods.CreateTask;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Blanko
 */
public class VentanaController implements Initializable {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnModify;
    @FXML
    private TextField tfTask;
    @FXML
    private ListView<String> lvTasks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File file = new File("C:\\Users\\Blanko\\Documents\\Tareas.txt");
        
        try {
            if(file.exists() != true){
                file.createNewFile();  
                System.out.println("\nArchivo creado en " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("Error creating the file: " + e.getMessage());
        }
        
        showTask(file);
        
    }

    /**
     * En éste método lo que hago es leer linea por linea el archivo y las voy añadiendo al ListView.
     * 
     * @param file Esta variable contiene la ruta del archivo.
     */
    public void showTask(File file){
        
        ObservableList tasks = FXCollections.observableArrayList();
        
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                tasks.add(line);
            }
            
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lvTasks.setItems(tasks);
        
    }

    @FXML
    /**
     * Al pulsar el botón "Crear" recoge el texto que haya en el TextField y lo introduce en el archivo. Solo lo introduce si la tarea no está repetida.
     * Acto seguido limpio el TextField y llamo al método showTask para que actualice el ListView con la nueva tarea introducida.
     */
    private void createTask(ActionEvent event) {
        
        File file = new File("C:\\Users\\Blanko\\Documents\\Tareas.txt");
        
        CreateTask.createTask(file, tfTask);
        showTask(file);
        
    }

    @FXML
    /**
     * Selecciono una tarea con el ratón en la ListView y al seleccionarla se refleja en el TextField. Solo la refleja si la linea que se selecciona contiene algo.
     */
    private void select(MouseEvent event) {
        
        String taskSelected = lvTasks.getSelectionModel().getSelectedItem();
        
        if (taskSelected != null) {
            tfTask.setText(taskSelected);
        }
        
    }

    @FXML
    /**
     * Recojo la tarea que quiero borrar seleccionandola con el ratón en la lista, acto seguido copio el contenido del archivo en un archivo temporal, en el archivo temporal
     * borro la tarea que he seleccionado, después borro el archivo principal y cambio el nombre del archivo temporal por el nombre del archivo anterior.
     */
    private void deleteTask(ActionEvent event) {
        
        File file = new File("C:\\Users\\Blanko\\Documents\\Tareas.txt");
        File tempFile = new File("C:\\Users\\Blanko\\Documents\\TareasTemp.txt");
        
        String taskToRemove = lvTasks.getSelectionModel().getSelectedItem();
        
        DeleteTask.deleteTask(file, tempFile, tfTask, taskToRemove);
        showTask(file);
        
    }

    @FXML
    /**
     * Recojo la tarea seleccionandola con el ratón en la lista, acto seguido copio el contenido del archivo en un archivo temporal, compruebo el archivo temporal linea por linea
     * para ver si el nuevo nombre de la tarea que quiero modificar coincide con una ya existente, si no es así, modifico la tarea en el archivo temporal, elimino el primer archivo y
     * cambio el nombre del archivo temporal por el del anterior archivo.
     */
    private void modifyTask(ActionEvent event) {
        
        File file = new File("C:\\Users\\Blanko\\Documents\\Tareas.txt");
        File tempFile = new File("C:\\Users\\Blanko\\Documents\\TareasTemp.txt");
        
        String taskToModify = lvTasks.getSelectionModel().getSelectedItem();
        
        ModifyTask.modifyTask(file, tempFile, tfTask, taskToModify);
        showTask(file);
        
    }
    
    /**
     * Ventana que muestra un mensaje de error.
     * @param alertMessage String donde se le pasa el mensaje de error.
     */
    public static void alertWindow(String alertMessage){
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(alertMessage);
        alert.showAndWait();
        
    }
    
}
