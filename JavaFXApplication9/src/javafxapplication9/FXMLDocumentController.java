/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

import com.sun.javafx.charts.Legend;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafxapplication9.RWFile.ReadFromFile;
import javafxapplication9.simulator.WriteToFile;
import javax.swing.JOptionPane;

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    private List<ScatterChart.Series<String, Double>> seriesCollection = new ArrayList<>();

    File selectedFile = null;
    ArrayList<Color> colorList = new ArrayList<Color>();

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
    private Button SingelReadButton;

    @FXML
    private Button AutoReadButton;
    private SequentialTransition animation, animation1, animation2, animation3, animation4, animation5, animation6, animation7, animation8;
    private final BooleanProperty zoomAnimated = new SimpleBooleanProperty(true);
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

    Set<Node> nodes2, nodes3, nodes4, nodes5, nodes6, nodes7, nodes8;
    Rectangle selectRect;
    private Timeline zoomAnimation = new Timeline();
    int TotalNbr;
    File dir;
    @FXML
    private Button SingleReadButton;
    ObservableList<DataLogger> channelsdata;
    @FXML
    private Label outputLabel;
    @FXML
    private Button Cleanscreen;
    @FXML
    private RadioButton SampleRadio;
    @FXML
    private RadioButton timeRadio;
    ScatterChart.Series<String, Double> series1, series2, series3, series4, series5, series6, series7, series8;
    @FXML
    private Label fileLabel;

    public int getTotalNbr() {
        return TotalNbr;
    }
    int i = 1, a = 1, b = 1, c = 1, d = 1, e = 1, f = 1, g = 1, h = 1;

    public void setTotalNbr(int TotalNbr) {
        this.TotalNbr = TotalNbr;
    }

    public void initialize(URL url, ResourceBundle rb) {
        checkbox1.setSelected(true);
        checkbox2.setSelected(true);
        checkbox3.setSelected(true);
        checkbox4.setSelected(true);
        checkbox5.setSelected(true);
        checkbox6.setSelected(true);
        checkbox7.setSelected(true);
        checkbox8.setSelected(true);
        colorList.add(Color.rgb(000, 051, 102));
        colorList.add(Color.rgb(255, 051, 000));
        colorList.add(Color.rgb(255, 204, 000));
        colorList.add(Color.rgb(000, 153, 000));
        colorList.add(Color.rgb(102, 000, 000));
        colorList.add(Color.rgb(051, 204, 255));
        colorList.add(Color.rgb(051, 051, 000));
        colorList.add(Color.rgb(204, 204, 000));
        colorPicker1.setValue(colorList.get(0));
        colorPicker2.setValue(colorList.get(1));
        colorPicker3.setValue(colorList.get(2));
        colorPicker4.setValue(colorList.get(3));
        colorPicker5.setValue(colorList.get(4));
        colorPicker6.setValue(colorList.get(5));
        colorPicker7.setValue(colorList.get(6));
        colorPicker8.setValue(colorList.get(7));
        textFieldMs.setText("1000");
        SampleRadio.setSelected(true);

    }
    ReadFromFile rff;

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    @FXML
    private void singlePlot(MouseEvent event) {
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        SingleReadButton.setStyle("-fx-font: 13 arial; -fx-base: #b6e7c9;");
        xAxis.setAnimated(false);
        scatterchart.setLegendVisible(false);
        if (timeRadio.isSelected()) {
            SampleRadio.setSelected(false);
            series1 = new ScatterChart.Series();
            series2 = new ScatterChart.Series();
            series3 = new ScatterChart.Series();
            series4 = new ScatterChart.Series();
            series5 = new ScatterChart.Series();
            series6 = new ScatterChart.Series();
            series7 = new ScatterChart.Series();
            series8 = new ScatterChart.Series();
while(rff.getChannelsdata().iterator().hasNext()){
            series1.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value1));
            series2.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value2));
            series3.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value3));
            series4.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value4));
            series5.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value5));
            series6.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value6));
            series7.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value7));
            series8.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value8));
            System.out.println("rff.iter.date" + rff.getChannelsdata().iterator().next().value1);
            rff.getChannelsdata().remove(0);

}

            if (checkbox1.isSelected()) {
                scatterchart.getData().add(series1);
                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker1.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);

                }
                TotalNbr++;

            } else {
                scatterchart.getData().remove(series1);
            }
            if (checkbox2.isSelected()) {
                scatterchart.getData().add(series2);
                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker2.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series2);
            }

            if (checkbox3.isSelected()) {
                scatterchart.getData().add(series3);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker3.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series3);
            }
            if (checkbox4.isSelected()) {
                scatterchart.getData().add(series4);
                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker4.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series4);
            }
            if (checkbox5.isSelected()) {
                scatterchart.getData().add(series5);
                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker5.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series5);
            }
            if (checkbox6.isSelected()) {
                scatterchart.getData().add(series6);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker6.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series6);
            }
            if (checkbox7.isSelected()) {
                scatterchart.getData().add(series7);
                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker7.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series7);
            }
            if (checkbox8.isSelected()) {
                scatterchart.getData().add(series8);
                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker8.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series8);
            }
         //   rff.getChannelsdata().remove(0);
        } else if (SampleRadio.isSelected()) {
            timeRadio.setSelected(false);
            series1 = new ScatterChart.Series();
            series2 = new ScatterChart.Series();
            series3 = new ScatterChart.Series();
            series4 = new ScatterChart.Series();
            series5 = new ScatterChart.Series();
            series6 = new ScatterChart.Series();
            series7 = new ScatterChart.Series();
            series8 = new ScatterChart.Series();
while(rff.getChannelsdata().iterator().hasNext()){
            series1.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value1));
            series2.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value2));
            series3.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value3));
            series4.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value4));
            series5.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value5));
            series6.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value6));
            series7.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value7));
            series8.getData().add(new ScatterChart.Data<>(String.valueOf(i), rff.getChannelsdata().iterator().next().value8));
            System.out.println("2rff.iter.date" + rff.getChannelsdata().iterator().next().value1);
            rff.getChannelsdata().remove(0);
            i++;
            
}

            if (checkbox1.isSelected()) {
                scatterchart.getData().add(series1);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker1.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);

                }
                TotalNbr++;

            } else {
                scatterchart.getData().remove(series1);
            }
            if (checkbox2.isSelected()) {
                scatterchart.getData().add(series2);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker2.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);

                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series2);
            }

            if (checkbox3.isSelected()) {
                scatterchart.getData().add(series3);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker3.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series3);
            }
            if (checkbox4.isSelected()) {
                scatterchart.getData().add(series4);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker4.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series4);
            }
            if (checkbox5.isSelected()) {
                scatterchart.getData().add(series5);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker5.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series5);
            }
            if (checkbox6.isSelected()) {
                scatterchart.getData().add(series6);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker6.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series6);
            }
            if (checkbox7.isSelected()) {
                scatterchart.getData().add(series7);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker7.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series7);
            }
            if (checkbox8.isSelected()) {
                scatterchart.getData().add(series8);

                Set<Node> nodes = scatterchart.lookupAll(".series" + String.valueOf(TotalNbr));
                Color c = colorPicker8.getValue();
                String newColor = "-fx-background-color: " + toRGBCode(c);
                for (Node n : nodes) {
                    n.setStyle(newColor);
                }
                TotalNbr++;
            } else {
                scatterchart.getData().remove(series8);
            }
         //   rff.getChannelsdata().remove(0);
           

        }
    }

    @FXML
    private void autoPlot(MouseEvent event) throws InterruptedException {
            if(AutoReadButton.getStyle().equals("-fx-font: 13 arial; -fx-base: #b6e7c9;")){
                AutoReadButton.setStyle("");
            }else{
        AutoReadButton.setStyle("-fx-font: 13 arial; -fx-base: #b6e7c9;");}
        scatterchart.getData().clear();
        scatterchart.getData().removeAll();
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        xAxis.setAnimated(false);
        if (textFieldMs.getText() != null) {
            if (timeRadio.isSelected()) {
                SampleRadio.setSelected(false);
                if (checkbox1.isSelected()) {
                    series1 = new ScatterChart.Series();
                    Timeline timeline1 = new Timeline();
                    timeline1.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series1.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value1
                                        ));
                                        System.out.println("auto+rff.iter.value1" + rff.getChannelsdata().iterator().next().value1);
                                    }
                                    String newColorA = "-fx-background-color: " + toRGBCode(colorPicker1.getValue());
                                    Set<Node> nodesA = scatterchart.lookupAll(".series0");
                                    for (Node n : nodesA) {
                                        n.setStyle(newColorA);
                                    }
                                    rff.getChannelsdata().remove(0);
                                }
                            })
                    );

                    timeline1.setCycleCount(Animation.INDEFINITE);
                    animation1 = new SequentialTransition();
                    animation1.getChildren().add(timeline1);
                    scatterchart.getData().add(series1);
                    animation1.play();

                } else {
                    if (animation1 != null) {
                        animation1.stop();
                    }
                }
                if (checkbox2.isSelected()) {
                    series2 = new ScatterChart.Series();
                    Timeline timeline2 = new Timeline();
                    timeline2.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series2.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value2
                                        ));
                                    }
                                    String newColor2 = "-fx-background-color: " + toRGBCode(colorPicker2.getValue());
                                    if (checkbox1.isSelected() == true) {
                                        nodes2 = scatterchart.lookupAll(".series1");
                                    } else {
                                        nodes2 = scatterchart.lookupAll(".series0");
                                    }
                                    for (Node n : nodes2) {
                                        n.setStyle(newColor2);
                                    }
                                    rff.getChannelsdata().remove(0);
                                }
                            })
                    );

                    timeline2.setCycleCount(Animation.INDEFINITE);
                    animation2 = new SequentialTransition();
                    animation2.getChildren().add(timeline2);
                    scatterchart.getData().add(series2);
                    animation2.play();

                } else {
                    if (animation2 != null) {
                        animation2.stop();
                    }
                }
                if (checkbox3.isSelected()) {
                    series3 = new ScatterChart.Series();
                    Timeline timeline3 = new Timeline();
                    timeline3.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series3.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value3
                                        ));
                                    }
                                    String newColor3 = "-fx-background-color: " + toRGBCode(colorPicker3.getValue());
                                    if (checkbox2.isSelected() && checkbox1.isSelected()) {
                                        nodes3 = scatterchart.lookupAll(".series2");

                                    } else if (!checkbox2.isSelected() && checkbox1.isSelected() || checkbox2.isSelected() && !checkbox1.isSelected()) {
                                        nodes3 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox2.isSelected() && !checkbox1.isSelected()) {
                                        nodes3 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes3) {
                                        n.setStyle(newColor3);
                                    }
                                    rff.getChannelsdata().remove(0);

                                }
                            })
                    );
                    timeline3.setCycleCount(Animation.INDEFINITE);
                    animation3 = new SequentialTransition();
                    animation3.getChildren().add(timeline3);
                    scatterchart.getData().add(series3);
                    animation3.play();

                } else {
                    if (animation3 != null) {
                        animation3.stop();
                    }
                }
                if (checkbox4.isSelected()) {
                    series4 = new ScatterChart.Series();
                    Timeline timeline4 = new Timeline();
                    timeline4.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series4.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value4
                                        ));
                                    }
                                    String newColor4 = "-fx-background-color: " + toRGBCode(colorPicker4.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series0");
                                    }
                                    for (Node n : nodes4) {
                                        n.setStyle(newColor4);
                                    }
                                    rff.getChannelsdata().remove(0);

                                }
                            })
                    );

                    timeline4.setCycleCount(Animation.INDEFINITE);
                    animation4 = new SequentialTransition();
                    animation4.getChildren().add(timeline4);
                    scatterchart.getData().add(series4);
                    animation4.play();

                } else {
                    if (animation4 != null) {
                        animation4.stop();
                    }
                }
                if (checkbox5.isSelected()) {
                    series5 = new ScatterChart.Series();
                    Timeline timeline5 = new Timeline();
                    timeline5.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series5.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value5
                                        ));
                                    }
                                    String newColor5 = "-fx-background-color: " + toRGBCode(colorPicker5.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series4");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series3");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series2");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes5) {
                                        n.setStyle(newColor5);
                                    }
                                    rff.getChannelsdata().remove(0);

                                }
                            })
                    );

                    timeline5.setCycleCount(Animation.INDEFINITE);
                    animation5 = new SequentialTransition();
                    animation5.getChildren().add(timeline5);
                    scatterchart.getData().add(series5);
                    animation5.play();

                } else {
                    if (animation5 != null) {
                        animation5.stop();
                    }
                }
                if (checkbox6.isSelected()) {
                    series6 = new ScatterChart.Series();
                    Timeline timeline6 = new Timeline();
                    timeline6.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series6.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value6
                                        ));
                                    }
                                    String newColor6 = "-fx-background-color: " + toRGBCode(colorPicker6.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series5");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series4");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes6) {
                                        n.setStyle(newColor6);
                                    }
                                    rff.getChannelsdata().remove(0);

                                }
                            })
                    );

                    timeline6.setCycleCount(Animation.INDEFINITE);
                    animation6 = new SequentialTransition();
                    animation6.getChildren().add(timeline6);
                    scatterchart.getData().add(series6);
                    animation6.play();

                } else {
                    if (animation6 != null) {
                        animation6.stop();
                    }
                }
                if (checkbox7.isSelected()) {
                    series7 = new ScatterChart.Series();
                    Timeline timeline7 = new Timeline();
                    timeline7.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series7.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value7
                                        ));
                                    }
                                    String newColor7 = "-fx-background-color: " + toRGBCode(colorPicker7.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()
                                            && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series6");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series5");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series4");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()
                                            && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes7) {
                                        n.setStyle(newColor7);
                                        n.requestFocus();
                                    }
                                    rff.getChannelsdata().remove(0);

                                }
                            })
                    );

                    timeline7.setCycleCount(Animation.INDEFINITE);
                    animation7 = new SequentialTransition();
                    animation7.getChildren().add(timeline7);
                    scatterchart.getData().add(series7);
                    animation7.play();

                } else {
                    if (animation7 != null) {
                        animation7.stop();
                    }
                }
                if (checkbox8.isSelected()) {
                    series8 = new ScatterChart.Series();
                    Timeline timeline8 = new Timeline();
                    timeline8.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series8.getData().add(new ScatterChart.Data<String, Double>(
                                                rff.getChannelsdata().iterator().next().date,
                                                rff.getChannelsdata().iterator().next().value8
                                        ));
                                    }
                                    String newColor8 = "-fx-background-color: " + toRGBCode(colorPicker8.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()
                                            && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series7");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series6");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series5");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series4");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()
                                            && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes8) {
                                        n.setStyle(newColor8);
                                    }
                                    rff.getChannelsdata().remove(0);
                                }
                            })
                    );

                    timeline8.setCycleCount(Animation.INDEFINITE);
                    animation8 = new SequentialTransition();
                    animation8.getChildren().add(timeline8);
                    scatterchart.getData().add(series8);
                    animation8.play();

                } else {
                    if (animation8 != null) {
                        animation8.stop();
                    }
                }
            } else if (SampleRadio.isSelected()) {
                timeRadio.setSelected(false);
                if (checkbox1.isSelected()) {
                    series1 = new ScatterChart.Series();
                    Timeline timeline1 = new Timeline();
                    timeline1.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series1.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(a),
                                                rff.getChannelsdata().iterator().next().value1
                                        ));
                                    }
                                    String newColorA = "-fx-background-color: " + toRGBCode(colorPicker1.getValue());
                                    Set<Node> nodesA = scatterchart.lookupAll(".series0");
                                    for (Node n : nodesA) {
                                        n.setStyle(newColorA);
                                    }
                                    rff.getChannelsdata().remove(0);
                                    a++;
                                }
                            })
                    );

                    timeline1.setCycleCount(Animation.INDEFINITE);
                    animation1 = new SequentialTransition();
                    animation1.getChildren().add(timeline1);
                    scatterchart.getData().add(series1);
                    animation1.play();

                } else {
                    if (animation1 != null) {
                        animation1.stop();
                    }
                }
                if (checkbox2.isSelected()) {
                    series2 = new ScatterChart.Series();
                    Timeline timeline2 = new Timeline();
                    timeline2.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series2.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(b),
                                                rff.getChannelsdata().iterator().next().value2
                                        ));
                                    }
                                    String newColor2 = "-fx-background-color: " + toRGBCode(colorPicker2.getValue());
                                    if (checkbox1.isSelected() == true) {
                                        nodes2 = scatterchart.lookupAll(".series1");
                                    } else {
                                        nodes2 = scatterchart.lookupAll(".series0");
                                    }
                                    for (Node n : nodes2) {
                                        n.setStyle(newColor2);
                                    }
                                    rff.getChannelsdata().remove(0);
                                    b++;
                                }
                            })
                    );

                    timeline2.setCycleCount(Animation.INDEFINITE);
                    animation2 = new SequentialTransition();
                    animation2.getChildren().add(timeline2);
                    scatterchart.getData().add(series2);
                    animation2.play();

                } else {
                    if (animation2 != null) {
                        animation2.stop();
                    }
                }
                if (checkbox3.isSelected()) {
                    series3 = new ScatterChart.Series();
                    Timeline timeline3 = new Timeline();
                    timeline3.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series3.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(c),
                                                rff.getChannelsdata().iterator().next().value3
                                        ));
                                    }
                                    String newColor3 = "-fx-background-color: " + toRGBCode(colorPicker3.getValue());
                                    if (checkbox2.isSelected() && checkbox1.isSelected()) {
                                        nodes3 = scatterchart.lookupAll(".series2");

                                    } else if (!checkbox2.isSelected() && checkbox1.isSelected() || checkbox2.isSelected() && !checkbox1.isSelected()) {
                                        nodes3 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox2.isSelected() && !checkbox1.isSelected()) {
                                        nodes3 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes3) {
                                        n.setStyle(newColor3);
                                    }
                                    rff.getChannelsdata().remove(0);
                                    c++;

                                }
                            })
                    );
                    timeline3.setCycleCount(Animation.INDEFINITE);
                    animation3 = new SequentialTransition();
                    animation3.getChildren().add(timeline3);
                    scatterchart.getData().add(series3);
                    animation3.play();

                } else {
                    if (animation3 != null) {
                        animation3.stop();
                    }
                }
                if (checkbox4.isSelected()) {
                    series4 = new ScatterChart.Series();
                    Timeline timeline4 = new Timeline();
                    timeline4.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series4.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(d),
                                                rff.getChannelsdata().iterator().next().value4
                                        ));
                                    }
                                    String newColor4 = "-fx-background-color: " + toRGBCode(colorPicker4.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()) {
                                        nodes4 = scatterchart.lookupAll(".series0");
                                    }
                                    for (Node n : nodes4) {
                                        n.setStyle(newColor4);
                                    }
                                    rff.getChannelsdata().remove(0);
                                    d++;
                                    System.out.println("d" + d);

                                }
                            })
                    );

                    timeline4.setCycleCount(Animation.INDEFINITE);
                    animation4 = new SequentialTransition();
                    animation4.getChildren().add(timeline4);
                    scatterchart.getData().add(series4);
                    animation4.play();

                } else {
                    if (animation4 != null) {
                        animation4.stop();
                    }
                }
                if (checkbox5.isSelected()) {
                    series5 = new ScatterChart.Series();
                    Timeline timeline5 = new Timeline();
                    timeline5.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series5.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(e),
                                                rff.getChannelsdata().iterator().next().value5
                                        ));
                                    }
                                    String newColor5 = "-fx-background-color: " + toRGBCode(colorPicker5.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series4");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series3");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series2");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected()) {
                                        nodes5 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes5) {
                                        n.setStyle(newColor5);
                                    }
                                    rff.getChannelsdata().remove(0);
                                    e++;

                                }
                            })
                    );

                    timeline5.setCycleCount(Animation.INDEFINITE);
                    animation5 = new SequentialTransition();
                    animation5.getChildren().add(timeline5);
                    scatterchart.getData().add(series5);
                    animation5.play();

                } else {
                    if (animation5 != null) {
                        animation5.stop();
                    }
                }
                if (checkbox6.isSelected()) {
                    series6 = new ScatterChart.Series();
                    Timeline timeline6 = new Timeline();
                    timeline6.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series6.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(f),
                                                rff.getChannelsdata().iterator().next().value6
                                        ));
                                    }
                                    String newColor6 = "-fx-background-color: " + toRGBCode(colorPicker6.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series5");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series4");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected()) {
                                        nodes6 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes6) {
                                        n.setStyle(newColor6);
                                    }
                                    rff.getChannelsdata().remove(0);
                                    f++;

                                }
                            })
                    );

                    timeline6.setCycleCount(Animation.INDEFINITE);
                    animation6 = new SequentialTransition();
                    animation6.getChildren().add(timeline6);
                    scatterchart.getData().add(series6);
                    animation6.play();

                } else {
                    if (animation6 != null) {
                        animation6.stop();
                    }
                }
                if (checkbox7.isSelected()) {
                    series7 = new ScatterChart.Series();
                    Timeline timeline7 = new Timeline();
                    timeline7.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series7.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(g),
                                                rff.getChannelsdata().iterator().next().value7
                                        ));
                                    }
                                    String newColor7 = "-fx-background-color: " + toRGBCode(colorPicker7.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()
                                            && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series6");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series5");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series4");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()
                                            && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected()) {
                                        nodes7 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes7) {
                                        n.setStyle(newColor7);
                                        n.requestFocus();
                                    }
                                    rff.getChannelsdata().remove(0);
                                    g++;
                                }
                            })
                    );

                    timeline7.setCycleCount(Animation.INDEFINITE);
                    animation7 = new SequentialTransition();
                    animation7.getChildren().add(timeline7);
                    scatterchart.getData().add(series7);
                    animation7.play();

                } else {
                    if (animation7 != null) {
                        animation7.stop();
                    }
                }
                if (checkbox8.isSelected()) {
                    series8 = new ScatterChart.Series();
                    Timeline timeline8 = new Timeline();
                    timeline8.getKeyFrames().add(
                            new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent actionEvent) {
                                    if (rff.getChannelsdata().iterator().hasNext()) {
                                        series8.getData().add(new ScatterChart.Data<String, Double>(
                                                String.valueOf(h),
                                                rff.getChannelsdata().iterator().next().value8
                                        ));
                                    }
                                    String newColor8 = "-fx-background-color: " + toRGBCode(colorPicker8.getValue());
                                    if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected()
                                            && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series7");

                                    } else if (!checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series6");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series5");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series4");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series3");

                                    } else if (checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series2");

                                    } else if (checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && checkbox6.isSelected() && !checkbox7.isSelected()
                                            || !checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected() && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series1");

                                    } else if (!checkbox1.isSelected() && !checkbox2.isSelected() && !checkbox3.isSelected()
                                            && !checkbox4.isSelected() && !checkbox5.isSelected() && !checkbox6.isSelected() && !checkbox7.isSelected()) {
                                        nodes8 = scatterchart.lookupAll(".series0");

                                    }
                                    for (Node n : nodes8) {
                                        n.setStyle(newColor8);
                                    }
                                    rff.getChannelsdata().remove(0);
                                    h++;
                                }
                            })
                    );

                    timeline8.setCycleCount(Animation.INDEFINITE);
                    animation8 = new SequentialTransition();
                    animation8.getChildren().add(timeline8);
                    scatterchart.getData().add(series8);
                    animation8.play();

                } else {
                    if (animation8 != null) {
                        animation8.stop();
                    }
                }

            }
            scatterchart.setLegendVisible(false);

        }
        
    }

    @FXML
    private void Cleanscreen(ActionEvent event) {
        scatterchart.getData().clear();
        scatterchart.getData().removeAll();
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        if (animation != null) {
            animation.stop();
        }
        rff.setChannelsdata(null);

    }

    @FXML
    private void handleWriteAction(ActionEvent event) {
        // FileChooser fileChooser = new FileChooser();
        // selectedFile = fileChooser.showOpenDialog(null);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        //System.getProperty returns absolute path
        File f = new File(System.getProperty("user.dir") + "/folder/file.txt");
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        //Remove if clause if you want to overwrite file
        //if (!f.exists()) {
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //}

        dir = new File(f.getParentFile(), f.getName());
        System.out.println("dir" + dir);
        // WriteToFile wtf=new WriteToFile(selectedFile);
        WriteToFile wtf = new WriteToFile(dir);
        wtf.start();
    }

    @FXML
    private void handleReadAction(ActionEvent event) throws InterruptedException, ParseException, FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        // if (dir != null) {
        rff = new ReadFromFile(selectedFile);
        rff.read();
        fileLabel.setText(selectedFile.getPath());
        // rff = new ReadFromFile(dir);
        // rff.read();
        // }

    }

    @FXML
    private void sampleHandleAction(ActionEvent event) {
        timeRadio.setSelected(false);
    }

    @FXML
    private void timeHandleAction(ActionEvent event) {
        SampleRadio.setSelected(false);
    }

  

  
}
