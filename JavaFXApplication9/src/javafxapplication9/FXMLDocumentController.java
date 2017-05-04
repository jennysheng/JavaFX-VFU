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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
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
    private ArrayList<ScatterChart.Series> scatterchartseries = new ArrayList<>();

    private final ListView<Double> channel1 = new ListView<Double>();

    private final ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();

    ScatterChart.Series series1, series2, series3, series4, series5, series6, series7, series8;

    @FXML
    private Button buttonChannel1;
    @FXML
    private ColorPicker colorPicker1;

    File selectedFile = null;
    int f = 0;
    JavaFXApplication9 aThis;

    @FXML
    private void handleReadAction(ActionEvent event) throws InterruptedException {

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
     

        createChart();

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

                String line = reader.readLine();
                int j = 0;

                while ((line) != null) {
                    line = reader.readLine();
                    filecontent.append(line + "\t").trimToSize();
                    j++;
                }
                String content = filecontent.toString();
                String[] parts = content.split("\t");

                System.out.println(j);
                int i = 0;

                while (i < ((j - 1) * 9)) {

                    channelsdata.add(new DataLogger(parts[i], parts[i + 1], parts[i + 2], parts[i + 3], parts[i + 4], parts[i + 5], parts[i + 6], parts[i + 7], parts[i + 8]));
                    i += 9;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelsdata;
    }

    public void createChart() {

        series1 = new ScatterChart.Series();
        series2 = new ScatterChart.Series();
        series3 = new ScatterChart.Series();
        series4 = new ScatterChart.Series();
        series5 = new ScatterChart.Series();
        series6 = new ScatterChart.Series();
        series7 = new ScatterChart.Series();
        series8 = new ScatterChart.Series();

        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd:HH:mm:ss,SSS");

        this.addSenorValueFromFile().stream().forEach((c) -> {

            series1.getData().add(new ScatterChart.Data(f, Double.parseDouble(c.getValue1())));
            series2.getData().add(new ScatterChart.Data(f, Double.parseDouble(c.getValue2())));
            series3.getData().add(new ScatterChart.Data(f, Double.parseDouble(c.getValue3())));
            series4.getData().add(new XYChart.Data(f, Double.parseDouble(c.getValue4())));
            series5.getData().add(new XYChart.Data(f, Double.parseDouble(c.getValue5())));
            series6.getData().add(new XYChart.Data(f, Double.parseDouble(c.getValue6())));
            series7.getData().add(new XYChart.Data(f, Double.parseDouble(c.getValue7())));
            series8.getData().add(new XYChart.Data(f, Double.parseDouble(c.getValue8())));
            f++;

        });
        scatterchartseries.addAll(Arrays.asList(series1, series2, series3, series4, series5, series6, series7, series8));

    }

    

}
