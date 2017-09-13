/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

import com.sun.javafx.charts.Legend;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Consumer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    private List<ScatterChart.Series<String, Double>> seriesCollection = new ArrayList<>();

    private final ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();

    File selectedFile = null;

    int f = 0, m = 0, n = 0, o = 0, q = 0, r = 0, s = 0;
    @FXML
    private ScatterChart<String, Double> scatterchart;

    @FXML
    private ColorPicker colorPicker1;
    private ColorPicker colorPicker4;
    private ColorPicker colorPicker3;
    private ColorPicker colorPicker2;
    private ColorPicker colorPicker5;
    private ColorPicker colorPicker6;
    private ColorPicker colorPicker7;
    private ColorPicker colorPicker8;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;

    @FXML
    private CheckBox checkbox1;
    @FXML
    private Button SingelReadButton;

    @FXML
    private Button AutoReadButton;
    @FXML
    private Slider SliderScaleX;
    @FXML
    private Slider SliderScaleY;
    @FXML
    private RadioButton SamplesRadio;
    @FXML
    private RadioButton TimeRadio;
    private SequentialTransition animation, animation1, animation2, animation3, animation4, animation5, animation6, animation7, animation8;

    @FXML
    private TextField textFieldMs;

    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleReadAction(ActionEvent event) throws InterruptedException, ParseException {

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        readFromFile();

    }

    public ObservableList<DataLogger> getChannelsdata() {
        return channelsdata;
    }

    public ObservableList<DataLogger> readFromFile() throws ParseException {

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
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelsdata;
    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    @FXML
    private void autoScaleX(MouseDragEvent event) {

    }

    @FXML
    private void autoScaleY(MouseDragEvent event) {
    }

    @FXML
    private void SamplesOnX(ActionEvent event) {
    }

    @FXML
    private void TimeOnX(ActionEvent event) {
    }

    int i = 0;
    int b = 0;

    @FXML
    private void singelPlot(MouseEvent event) {

        SingelReadButton.setStyle("-fx-font: 13 arial; -fx-base: #b6e7c9;");

        ScatterChart.Series<String, Double> seriesS1 = new ScatterChart.Series();
        ScatterChart.Series<String, Double> seriesS2 = new ScatterChart.Series();
        ScatterChart.Series<String, Double> seriesS3 = new ScatterChart.Series();
        ScatterChart.Series<String, Double> seriesS4 = new ScatterChart.Series();
        ScatterChart.Series<String, Double> seriesS5 = new ScatterChart.Series();
        ScatterChart.Series<String, Double> seriesS6 = new ScatterChart.Series();
        ScatterChart.Series<String, Double> seriesS7 = new ScatterChart.Series();
        ScatterChart.Series<String, Double> seriesS8 = new ScatterChart.Series();

        seriesS1.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value1));
        seriesS2.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value2));
        seriesS3.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value3));
        seriesS4.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value4));
        seriesS5.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value5));
        seriesS6.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value6));
        seriesS7.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value7));
        seriesS8.getData().add(new ScatterChart.Data<>(getChannelsdata().iterator().next().date, getChannelsdata().iterator().next().value8));
        if (checkbox1.isSelected()) {
            scatterchart.getData().add(seriesS1);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(b));
            Color c = colorPicker1.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
              
            }
            b++;
            getChannelsdata().remove(0);
            zoom();

        }
    }

    private void animation() {

        ScatterChart.Series<String, Double> seriesA1 = new ScatterChart.Series();
        Timeline timeline1 = new Timeline();
        timeline1.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA1.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value1
                        ));

                        getChannelsdata().remove(0);
                        changeAutoColor1();
                        zoom();

                    }
                })
        );

        timeline1.setCycleCount(1000);
        animation = new SequentialTransition();
        animation.getChildren().addAll(timeline1);
        scatterchart.getData().add(seriesA1);
        scatterchart.setLegendVisible(false);
    }

    @FXML
    private void autoPlot(MouseEvent event) throws InterruptedException {

        if (checkbox1.isSelected() && textFieldMs.getText() != null) {
            AutoReadButton.setStyle("-fx-font: 13 arial; -fx-base: #b6e7b9;");
            animation();
            animation.play();
        } else {
            System.out.println("please fillin the time interval:");
            animation.stop();
            scatterchart.getData().clear();

        }

    }

    void zoom() {
        final double SCALE_DELTA = 1.1;

        scatterchart.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                event.consume();

                if (event.getDeltaY() == 0) {
                    return;
                }

                double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

                scatterchart.setScaleX(scatterchart.getScaleX() * scaleFactor);
                scatterchart.setScaleY(scatterchart.getScaleY() * scaleFactor);
            }
        });
    }

    private void changeAutoColor1() {
        Set<Node> nodes = scatterchart.lookupAll(".series0");
        Color c = colorPicker1.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(c);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

}
