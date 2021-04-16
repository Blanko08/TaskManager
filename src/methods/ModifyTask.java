package methods;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import main.VentanaController;
import static main.VentanaController.alertWindow;

public class ModifyTask {
    
    public static void modifyTask(File file, File tempFile, TextField tfTask, String taskToModify){
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedReader reader2 = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            String currentLine;
            boolean flag = false;
            while((currentLine = reader.readLine()) != null) {
                if(currentLine.equalsIgnoreCase(taskToModify)){
                    String newTask = tfTask.getText();
                    
                    while((currentLine = reader2.readLine()) != null) {
                        if(currentLine.equalsIgnoreCase(newTask)){
                            alertWindow("La tarea ya existe");
                            flag = true;
                        }
                    }
                    
                    if (flag == false) {
                        writer.write(newTask + System.getProperty("line.separator"));
                    }else{
                        writer.write(taskToModify + System.getProperty("line.separator"));
                    }
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            
            writer.close(); 
            reader.close();
            reader2.close();
            file.delete();
            tempFile.renameTo(file);
            tfTask.setText("");
        } catch (IOException ex) {
            Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
