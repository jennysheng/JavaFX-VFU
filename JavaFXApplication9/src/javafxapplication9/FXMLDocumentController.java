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

    int i = 0, b = 0, d = 0, e = 0, f = 0, g = 0, h = 0, j = 0;
    int TotalNbr;

    public int getTotalNbr() {
        return TotalNbr;
    }

    public void setTotalNbr(int TotalNbr) {
        this.TotalNbr = TotalNbr;
    }
    @FXML
    private ScatterChart<String, Double> scatterchart;

    @FXML
    private ColorPicker colorPicker1;
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
    private SequentialTransition animation, animation1, animation2, animation3, animation4, animation5, animation6, animation7, animation8;

    @FXML
    private TextField textFieldMs;
    @FXML
    private CheckBox checkbox2;
    @FXML
    private CheckBox checkbox3;
    @FXML
    private CheckBox checkbox4;
    @FXML
    private CheckBox checkbox5;
    @FXML
    private CheckBox checkbox6;
    @FXML
    private CheckBox checkbox7;
    @FXML
    private CheckBox checkbox8;
    @FXML
    private Button Cleanscreen;

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

            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker1.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;

        } else {
            scatterchart.getData().remove(seriesS1);
        }
        if (checkbox2.isSelected()) {
            scatterchart.getData().add(seriesS2);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker2.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;
        } else {
            scatterchart.getData().remove(seriesS2);
        }

        if (checkbox3.isSelected()) {
            scatterchart.getData().add(seriesS3);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker3.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;
        } else {
            scatterchart.getData().remove(seriesS3);
        }
        if (checkbox4.isSelected()) {
            scatterchart.getData().add(seriesS4);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker4.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;
        } else {
            scatterchart.getData().remove(seriesS4);
        }
        if (checkbox5.isSelected()) {
            scatterchart.getData().add(seriesS5);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker5.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;
        } else {
            scatterchart.getData().remove(seriesS5);
        }
        if (checkbox6.isSelected()) {
            scatterchart.getData().add(seriesS6);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker6.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;
        } else {
            scatterchart.getData().remove(seriesS6);
        }
        if (checkbox7.isSelected()) {
            scatterchart.getData().add(seriesS7);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker7.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;
        } else {
            scatterchart.getData().remove(seriesS7);
        }
        if (checkbox8.isSelected()) {
            scatterchart.getData().add(seriesS8);
            xAxis.setAnimated(true);
            scatterchart.setLegendVisible(false);
            Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
            Color c = colorPicker8.getValue();
            String newColor = "-fx-background-color: " + toRGBCode(c);
            for (Node n : nodes) {
                n.setStyle(newColor);
            }
            TotalNbr++;
        } else {
            scatterchart.getData().remove(seriesS8);
        }
        getChannelsdata().remove(0);
        zoom();

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
                        Color c1 = colorPicker1.getValue();
                        String newColor1 = "-fx-background-color: " + toRGBCode(c1);
                        Set<Node> nodes1 = scatterchart.lookupAll(".series0");
                        for (Node n : nodes1) {
                            n.setStyle(newColor1);
                        }
                        getChannelsdata().remove(0);

                    }
                })
        );

        timeline1.setCycleCount(1000);
        animation = new SequentialTransition();
        animation.getChildren().addAll(timeline1);
        scatterchart.getData().add(seriesA1);
        scatterchart.setLegendVisible(false);
    }

    private void animation2() {
        ScatterChart.Series<String, Double> seriesA2 = new ScatterChart.Series();
        Timeline timeline2 = new Timeline();
        timeline2.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA2.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value2
                        ));
                        Color c2 = colorPicker2.getValue();
                        String newColor2 = "-fx-background-color: " + toRGBCode(c2);
                        Set<Node> nodes2 = scatterchart.lookupAll(".series1");
                        for (Node n : nodes2) {
                            n.setStyle(newColor2);
                        }
                        getChannelsdata().remove(0);

                    }
                })
        );

        timeline2.setCycleCount(1000);
        animation2 = new SequentialTransition();
        animation2.getChildren().addAll(timeline2);
        scatterchart.getData().add(seriesA2);
        scatterchart.setLegendVisible(false);
    }

    private void animation3() {
        ScatterChart.Series<String, Double> seriesA3 = new ScatterChart.Series();
        Timeline timeline3 = new Timeline();
        timeline3.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA3.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value3
                        ));
                        Color c1 = colorPicker3.getValue();
                        String newColor1 = "-fx-background-color: " + toRGBCode(c1);
                        Set<Node> nodes1 = scatterchart.lookupAll(".series12");
                        for (Node n : nodes1) {
                            n.setStyle(newColor1);

                        }
                        getChannelsdata().remove(0);

                    }
                })
        );

        timeline3.setCycleCount(1000);
        animation3 = new SequentialTransition();
        animation3.getChildren().addAll(timeline3);
        scatterchart.getData().add(seriesA3);
        scatterchart.setLegendVisible(false);
    }

    private void animation4() {
        ScatterChart.Series<String, Double> seriesA4 = new ScatterChart.Series();
        Timeline timeline4 = new Timeline();
        timeline4.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA4.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value4
                        ));
                        Color c1 = colorPicker4.getValue();
                        String newColor1 = "-fx-background-color: " + toRGBCode(c1);
                        Set<Node> nodes1 = scatterchart.lookupAll(".series13");
                        for (Node n : nodes1) {
                            n.setStyle(newColor1);
                        }
                        getChannelsdata().remove(0);

                    }
                })
        );

        timeline4.setCycleCount(1000);
        animation4 = new SequentialTransition();
        animation4.getChildren().addAll(timeline4);
        scatterchart.getData().add(seriesA4);
        scatterchart.setLegendVisible(false);
    }

    private void animation5() {
        ScatterChart.Series<String, Double> seriesA5 = new ScatterChart.Series();
        Timeline timeline5 = new Timeline();
        timeline5.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA5.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value5
                        ));
                        Color c1 = colorPicker5.getValue();
                        String newColor1 = "-fx-background-color: " + toRGBCode(c1);
                        Set<Node> nodes1 = scatterchart.lookupAll(".series14");
                        for (Node n : nodes1) {
                            n.setStyle(newColor1);
                        }

                        getChannelsdata().remove(0);

                    }
                })
        );

        timeline5.setCycleCount(1000);
        animation5 = new SequentialTransition();
        animation5.getChildren().addAll(timeline5);
        scatterchart.getData().add(seriesA5);
        scatterchart.setLegendVisible(false);
    }

    private void animation6() {
        ScatterChart.Series<String, Double> seriesA6 = new ScatterChart.Series();
        Timeline timeline6 = new Timeline();
        timeline6.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA6.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value6
                        ));
                        Color c6 = colorPicker6.getValue();
                        String newColor6 = "-fx-background-color: " + toRGBCode(c6);
                        Set<Node> nodes6 = scatterchart.lookupAll(".series15");
                        for (Node n : nodes6) {
                            n.setStyle(newColor6);
                        }

                        getChannelsdata().remove(0);

                    }
                })
        );

        timeline6.setCycleCount(1000);
        animation6 = new SequentialTransition();
        animation6.getChildren().addAll(timeline6);
        scatterchart.getData().add(seriesA6);
        scatterchart.setLegendVisible(false);
        seriesA6.setName("series5");
    }

    private void animation7() {
        ScatterChart.Series<String, Double> seriesA7 = new ScatterChart.Series();
        Timeline timeline7 = new Timeline();
        timeline7.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA7.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value7
                        ));
                        Color c1 = colorPicker7.getValue();
                        String newColor7 = "-fx-background-color: " + toRGBCode(c1);
                        Set<Node> nodes7 = scatterchart.lookupAll(".series16");
                        for (Node n : nodes7) {
                            n.setStyle(newColor7);
                        }

                        getChannelsdata().remove(0);

                    }
                })
        );

        timeline7.setCycleCount(1000);
        animation7 = new SequentialTransition();
        animation7.getChildren().addAll(timeline7);
        scatterchart.getData().add(seriesA7);
        scatterchart.setLegendVisible(false);
    }

    private void animation8() {
        ScatterChart.Series<String, Double> seriesA8 = new ScatterChart.Series();
        Timeline timeline8 = new Timeline();
        timeline8.getKeyFrames().add(
                new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {

                        seriesA8.getData().add(new ScatterChart.Data<String, Double>(
                                getChannelsdata().iterator().next().date,
                                getChannelsdata().iterator().next().value8
                        ));

                        Color c1 = colorPicker8.getValue();
                        String newColor1 = "-fx-background-color: " + toRGBCode(c1);
                        Set<Node> nodes1 = scatterchart.lookupAll(".series8");
                        for (Node n : nodes1) {
                            n.setStyle(newColor1);
                        }
                        getChannelsdata().remove(0);
                    }
                })
        );

        timeline8.setCycleCount(1000);
        animation8 = new SequentialTransition();
        animation8.getChildren().addAll(timeline8);
        scatterchart.getData().add(seriesA8);
        scatterchart.setLegendVisible(false);

    }

    @FXML
    private void autoPlot(MouseEvent event) throws InterruptedException {
        if (textFieldMs.getText() != null) {
            AutoReadButton.setDisable(false);
            if (checkbox1.isSelected()) {

                animation();
                animation.play();
            }
            if (checkbox2.isSelected()) {

                animation2();
                animation2.play();
            }
            if (checkbox3.isSelected()) {

                animation3();
                animation3.play();
            }
            if (checkbox4.isSelected()) {

                animation4();
                animation4.play();
            }
            if (checkbox5.isSelected()) {
                animation5();
                animation5.play();
            }
            if (checkbox6.isSelected()) {
                animation6();
                animation6.play();
            }
            if (checkbox7.isSelected()) {
                animation7();
                animation7.play();
            }
            if (checkbox8.isSelected()) {
                animation8();
                animation8.play();
            }

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

    @FXML
    private void Cleanscreen(ActionEvent event) {
        scatterchart.getData().clear();
        for (int i = getTotalNbr(); i >= 0; i--) {
            scatterchart.getData().remove("series" + String.valueOf(i));
        }
        for (int j = 0; j < 8; j++) {
            scatterchart.getData().remove("series" + String.valueOf(j));
        }
        if (animation != null) {
            animation.stop();
        }
        if (animation2 != null) {
            animation2.stop();
        }
        if (animation3 != null) {
            animation3.stop();
        }
        if (animation4 != null) {
            animation4.stop();
        }
        if (animation5 != null) {
            animation5.stop();
        }
        if (animation6 != null) {
            animation6.stop();
        }
        if (animation7 != null) {
            animation7.stop();
        }
        if (animation8 != null) {
            animation8.stop();
        }

    }

}
