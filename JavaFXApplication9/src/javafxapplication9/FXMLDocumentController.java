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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    private ArrayList<ScatterChart.Series> scatterchartseries = new ArrayList<>();

    private final ListView<Double> channel1 = new ListView<Double>();

    private final ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();

    ScatterChart.Series series1 = new ScatterChart.Series();
    ScatterChart.Series series2 = new ScatterChart.Series();
    ScatterChart.Series series3 = new ScatterChart.Series();
    ScatterChart.Series series4 = new ScatterChart.Series();
    ScatterChart.Series series5 = new ScatterChart.Series();
    ScatterChart.Series series6 = new ScatterChart.Series();
    ScatterChart.Series series7 = new ScatterChart.Series();
    ScatterChart.Series series8 = new ScatterChart.Series();
    @FXML
    private Button buttonChannel1;
    @FXML
    private ColorPicker colorPicker1;

    File selectedFile = null;
    int f = 0;
    JavaFXApplication9 aThis;
    @FXML
    private ScatterChart<Integer, Double> scatterchart;
    @FXML
    private ColorPicker colorPicker4;
    @FXML
    private ColorPicker colorPicker3;
    @FXML
    private ColorPicker colorPicker2;
    @FXML
    private ColorPicker colorPicker5;
    @FXML
    private ColorPicker colorPicker6;
    @FXML
    private ColorPicker colorPicker7;
    @FXML
    private ColorPicker colorPicker8;
    @FXML
    private Button buttonChannel2;
    @FXML
    private Button buttonChannel3;
    @FXML
    private Button buttonChannel4;
    @FXML
    private Button buttonChannel5;
    @FXML
    private Button buttonChannel6;
    @FXML
    private Button buttonChannel7;
    @FXML
    private Button buttonChannel8;
    @FXML
    private Slider slider1;
    @FXML
    private Slider slider2;
    @FXML
    private Slider slider3;
    @FXML
    private Slider slider4;
    @FXML
    private Slider slider5;
    @FXML
    private Slider slider6;
    @FXML
    private Slider slider7;
    @FXML
    private Slider slider8;

    @FXML
    private void handleReadAction(ActionEvent event) throws InterruptedException, ParseException {

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        addSenorValueFromFile();
        createChart();

    }

    public ObservableList<DataLogger> getChannelsdata() {
        return channelsdata;
    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    public ObservableList<DataLogger> addSenorValueFromFile() throws ParseException {

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

                    channelsdata.add(new DataLogger(parts[i],
                            Double.parseDouble(parts[i + 1]),
                            Double.parseDouble(parts[i + 2]),
                            Double.parseDouble(parts[i + 3]),
                            Double.parseDouble(parts[i + 4]),
                            Double.parseDouble(parts[i + 5]),
                            Double.parseDouble(parts[i + 6]),
                            Double.parseDouble(parts[i + 7]),
                            Double.parseDouble(parts[i + 8])));
                    i += 9;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelsdata;
    }

    public void createChart() throws ParseException {

        getChannelsdata().stream().forEach((c) -> {

            series1.getData().add(new ScatterChart.Data(f, c.getValue1()));
            series2.getData().add(new ScatterChart.Data(f, c.getValue2()));
            series3.getData().add(new ScatterChart.Data(f, c.getValue3()));
            series4.getData().add(new ScatterChart.Data(f, c.getValue4()));
            series5.getData().add(new ScatterChart.Data(f, c.getValue5()));
            series6.getData().add(new ScatterChart.Data(f, c.getValue6()));
            series7.getData().add(new ScatterChart.Data(f, c.getValue7()));
            series8.getData().add(new ScatterChart.Data(f, c.getValue8()));

            f++;

        });
        scatterchartseries.addAll(Arrays.asList(series1, series2, series3, series4, series5, series6, series7, series8));
        //scatterchart.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8);

    }

    @FXML
    private void handleButton1Action(ActionEvent event) {

        if (buttonChannel1.getText().equalsIgnoreCase("Channel 1")) {

            scatterchart.getData().add(series1);
            series1.setName("Channel1");
            buttonChannel1.setText("StopChannel1");
        } else {
            scatterchart.getData().remove(series1);
            buttonChannel1.setText("Channel 1");
        }

    }

    @FXML
    private void handleButton2Action(ActionEvent event) {
        if (buttonChannel2.getText().equalsIgnoreCase("Channel 2")) {
            scatterchart.getData().addAll(series2);
            series2.setName("Channel2");
            buttonChannel2.setText("StopChannel2");
        } else {
            scatterchart.getData().remove(series2);
            buttonChannel2.setText("Channel 2");
        }

    }

    @FXML
    private void handleButton3Action(ActionEvent event) {
        if (buttonChannel3.getText().equalsIgnoreCase("Channel 3")) {
            scatterchart.getData().addAll(series3);
            series3.setName("Channel3");
            buttonChannel3.setText("StopChannel3");
        } else {
            scatterchart.getData().remove(series3);
            buttonChannel3.setText("Channel 3");
        }
    }

    @FXML
    private void handleButton4Action(ActionEvent event) {
        if (buttonChannel4.getText().equalsIgnoreCase("Channel 4")) {
            scatterchart.getData().addAll(series4);
            series4.setName("Channel4");
            buttonChannel4.setText("StopChannel4");
        } else {
            scatterchart.getData().remove(series4);
            buttonChannel4.setText("Channel 4");
        }
    }

    @FXML
    private void handleColor4Action(ActionEvent event) {
    }

    @FXML
    private void handleColor3Action(ActionEvent event) {
    }

    @FXML
    private void handleColor2Action(ActionEvent event) {
    }

    @FXML
    private void handleColor1Action(ActionEvent event) {
    }

    @FXML
    private void handleButton5Action(ActionEvent event) {
        if (buttonChannel5.getText().equalsIgnoreCase("Channel 5")) {
            scatterchart.getData().addAll(series5);
            series5.setName("Channel5");
            buttonChannel5.setText("StopChannel5");
        } else {
            scatterchart.getData().remove(series5);
            buttonChannel5.setText("Channel 5");
        }
    }

    @FXML
    private void handleButton6Action(ActionEvent event) {
        if (buttonChannel6.getText().equalsIgnoreCase("Channel 6")) {
            scatterchart.getData().addAll(series6);
            series6.setName("Channel6");
            buttonChannel6.setText("StopChannel6");
        } else {
            scatterchart.getData().remove(series6);
            buttonChannel6.setText("Channel 6");
        }
    }

    @FXML
    private void handleButton7Action(ActionEvent event) {
        if (buttonChannel7.getText().equalsIgnoreCase("Channel 7")) {
            scatterchart.getData().addAll(series7);
            series7.setName("Channel7");
            buttonChannel7.setText("StopChannel7");
        } else {
            scatterchart.getData().remove(series7);
            buttonChannel7.setText("Channel 7");
        }
    }

    @FXML
    private void handleButton8Action(ActionEvent event) {
        if (buttonChannel8.getText().equalsIgnoreCase("Channel 8")) {
            scatterchart.getData().addAll(series8);
            series8.setName("Channel8");
            buttonChannel8.setText("StopChannel8");
        } else {
            scatterchart.getData().remove(series8);
            buttonChannel8.setText("Channel 8");
        }
    }

    @FXML
    private void handleColor5Action(ActionEvent event) {
    }

    @FXML
    private void handleColor6Action(ActionEvent event) {
    }

    @FXML
    private void handleColor7Action(ActionEvent event) {
    }

    @FXML
    private void handleColor8Action(ActionEvent event) {
    }

    @FXML
    private void handelSlider1Action(MouseEvent event) {

        slider1.setShowTickMarks(true);
        slider1.setShowTickLabels(true);
        slider1.setMajorTickUnit(10f);
        slider1.setBlockIncrement(10f);

        System.out.println("slider1" + slider1.getValue());
    }

    @FXML
    private void handelSlider2Action(MouseEvent event) {
    }

    @FXML
    private void handelSlider3Action(MouseEvent event) {
    }

    @FXML
    private void handelSlider4Action(MouseEvent event) {
    }

    @FXML
    private void handelSlider5Action(MouseEvent event) {
    }

    @FXML
    private void handelSlider6Action(MouseEvent event) {
    }

    @FXML
    private void handelSlider7Action(MouseEvent event) {
    }

    @FXML
    private void handelSlider8Action(MouseEvent event) {
    }

}
