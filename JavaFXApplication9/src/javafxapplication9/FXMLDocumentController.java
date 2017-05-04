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
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ScatterChart<?, ?> scatterchart;

    private ListView<DataLogger> datalist = new ListView<DataLogger>();
    ArrayList<Date> datetime = new ArrayList<Date>();
    ArrayList<Double> channel1 = new ArrayList<Double>();

    private final ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();
    @FXML
    private Button buttonChannel1;
    @FXML
    private ColorPicker colorPicker1;

    File selectedFile = null;

    @FXML
    private void handleReadAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        addSenorValueFromFile();

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public ObservableList<DataLogger> addSenorValueFromFile() {
        StringBuilder filecontent = new StringBuilder();

        if (selectedFile != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(selectedFile.getPath())))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    filecontent.append(line).trimToSize();
                }
                String content = filecontent.toString();

                System.out.println(filecontent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
