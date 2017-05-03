/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private void handleReadAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(selectedFile.getPath())))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("pressed");
       

    }
       

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
