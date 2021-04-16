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

public class CreateTask {
    
    public static void createTask(File file, TextField tfTask){
        
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String task = tfTask.getText();

            String currentLine;
            boolean flag = false;
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equalsIgnoreCase(task)){
                    alertWindow("La tarea ya existe");
                    flag = true;
                }
            }

            if (flag == false) {
                bufferedWriter.write(task);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                tfTask.setText("");
            }
            
            bufferedWriter.close();
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(VentanaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
