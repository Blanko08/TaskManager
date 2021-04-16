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

public class DeleteTask {
    
    public static void deleteTask(File file, File tempFile, TextField tfTask, String taskToRemove){
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            
            
                
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                if(currentLine.equalsIgnoreCase(taskToRemove)){
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            
            writer.close(); 
            reader.close();
            file.delete();
            tempFile.renameTo(file);
            tfTask.setText("");
        } catch (IOException ex) {
            Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
