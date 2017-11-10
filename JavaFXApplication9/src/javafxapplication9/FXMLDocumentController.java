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
import java.text.ParseException;
import java.util.ArrayList;
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

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    private List<ScatterChart.Series<String, Double>> seriesCollection = new ArrayList<>();
    private final ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();
    File selectedFile = null;
    ArrayList<Color> colorList = new ArrayList<Color>();

    @FXML
    private Label outputLabel;

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
    @FXML
    private Button Cleanscreen;
    private final SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty rectY = new SimpleDoubleProperty();
    private SimpleBooleanProperty selected = new SimpleBooleanProperty(false);

    Set<Node> nodes2, nodes3, nodes4, nodes5, nodes6, nodes7, nodes8;
    Rectangle selectRect;
    private Timeline zoomAnimation = new Timeline();
    int TotalNbr;
    @FXML
    private Button SingleReadButton;

    public int getTotalNbr() {
        return TotalNbr;
    }

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
        colorList.add(Color.BLUE);
        colorList.add(Color.RED);
        colorList.add(Color.GREEN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.BLACK);
        colorList.add(Color.CYAN);
        colorList.add(Color.DARKBLUE);
        colorList.add(Color.GREY);
        colorPicker1.setValue(colorList.get(0));
        colorPicker2.setValue(colorList.get(1));
        colorPicker3.setValue(colorList.get(2));
        colorPicker4.setValue(colorList.get(3));
        colorPicker5.setValue(colorList.get(4));
        colorPicker6.setValue(colorList.get(5));
        colorPicker7.setValue(colorList.get(6));
        colorPicker8.setValue(colorList.get(7));

        scatterchart.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double yStart = scatterchart.getLayoutY();
                double xStart = scatterchart.getLayoutX();
                double axisYRelativeMousePosition = mouseEvent.getY() - yStart;
                outputLabel.setText(String.format(
                        "%d, %d (%d, %d); %d - %d",
                        (int) mouseEvent.getSceneX(), (int) mouseEvent.getSceneY(),
                        (int) mouseEvent.getX(), (int) mouseEvent.getY(),
                        (int) xStart,
                        (int) scatterchart.getYAxis().getValueForDisplay(axisYRelativeMousePosition).intValue()
                ));
            }
        });

    }

    @FXML
    private void handleReadAction(ActionEvent event) throws InterruptedException, ParseException {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        readFromFile();
        setupZooming(scatterchart, DEFAULT_FILTER);

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
    private void singlePlot(MouseEvent event) {
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        SingleReadButton.setStyle("-fx-font: 13 arial; -fx-base: #b6e7c9;");

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

    }

    @FXML
    private void autoPlot(MouseEvent event) throws InterruptedException {
        scatterchart.getData().clear();
        scatterchart.getData().removeAll();
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        if (textFieldMs.getText() != null) {
            AutoReadButton.setDisable(false);
            if (checkbox1.isSelected()) {
                ScatterChart.Series<String, Double> seriesA1 = new ScatterChart.Series();
                Timeline timeline1 = new Timeline();
                timeline1.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA1.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value1
                                    ));
                                }
                                String newColorA = "-fx-background-color: " + toRGBCode(colorPicker1.getValue());
                                Set<Node> nodesA = scatterchart.lookupAll(".series0");
                                for (Node n : nodesA) {
                                    n.setStyle(newColorA);
                                }
                                getChannelsdata().remove(0);
                            }
                        })
                );

                timeline1.setCycleCount(1000);
                animation1 = new SequentialTransition();
                animation1.getChildren().add(timeline1);
                scatterchart.getData().add(seriesA1);
                animation1.play();

            } else {
                if (animation1 != null) {
                    animation1.stop();
                }
            }
            if (checkbox2.isSelected()) {
                ScatterChart.Series<String, Double> seriesA2 = new ScatterChart.Series();
                Timeline timeline2 = new Timeline();
                timeline2.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA2.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value2
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
                                getChannelsdata().remove(0);
                            }
                        })
                );

                timeline2.setCycleCount(1000);
                animation2 = new SequentialTransition();
                animation2.getChildren().add(timeline2);
                scatterchart.getData().add(seriesA2);
                animation2.play();

            } else {
                if (animation2 != null) {
                    animation2.stop();
                }
            }
            if (checkbox3.isSelected()) {
                ScatterChart.Series<String, Double> seriesA3 = new ScatterChart.Series();
                Timeline timeline3 = new Timeline();
                timeline3.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA3.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value3
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
                                getChannelsdata().remove(0);

                            }
                        })
                );
                timeline3.setCycleCount(1000);
                animation3 = new SequentialTransition();
                animation3.getChildren().add(timeline3);
                scatterchart.getData().add(seriesA3);
                animation3.play();

            } else {
                if (animation3 != null) {
                    animation3.stop();
                }
            }
            if (checkbox4.isSelected()) {
                ScatterChart.Series<String, Double> seriesA4 = new ScatterChart.Series();
                Timeline timeline4 = new Timeline();
                timeline4.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA4.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value4
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
                                getChannelsdata().remove(0);

                            }
                        })
                );

                timeline4.setCycleCount(1000);
                animation4 = new SequentialTransition();
                animation4.getChildren().add(timeline4);
                scatterchart.getData().add(seriesA4);
                animation4.play();

            } else {
                if (animation4 != null) {
                    animation4.stop();
                }
            }
            if (checkbox5.isSelected()) {
                ScatterChart.Series<String, Double> seriesA5 = new ScatterChart.Series();
                Timeline timeline5 = new Timeline();
                timeline5.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA5.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value5
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
                                getChannelsdata().remove(0);

                            }
                        })
                );

                timeline5.setCycleCount(1000);
                animation5 = new SequentialTransition();
                animation5.getChildren().add(timeline5);
                scatterchart.getData().add(seriesA5);
                animation5.play();

            } else {
                if (animation5 != null) {
                    animation5.stop();
                }
            }
            if (checkbox6.isSelected()) {
                ScatterChart.Series<String, Double> seriesA6 = new ScatterChart.Series();
                Timeline timeline6 = new Timeline();
                timeline6.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA6.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value6
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
                                getChannelsdata().remove(0);

                            }
                        })
                );

                timeline6.setCycleCount(Animation.INDEFINITE);
                animation6 = new SequentialTransition();
                animation6.getChildren().add(timeline6);
                scatterchart.getData().add(seriesA6);
                animation6.play();

            } else {
                if (animation6 != null) {
                    animation6.stop();
                }
            }
            if (checkbox7.isSelected()) {
                ScatterChart.Series<String, Double> seriesA7 = new ScatterChart.Series();
                Timeline timeline7 = new Timeline();
                timeline7.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA7.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value7
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
                                getChannelsdata().remove(0);

                            }
                        })
                );

                timeline7.setCycleCount(Animation.INDEFINITE);
                animation7 = new SequentialTransition();
                animation7.getChildren().add(timeline7);
                scatterchart.getData().add(seriesA7);
                animation7.play();

            } else {
                if (animation7 != null) {
                    animation7.stop();
                }
            }
            if (checkbox8.isSelected()) {

                ScatterChart.Series<String, Double> seriesA8 = new ScatterChart.Series();
                Timeline timeline8 = new Timeline();
                timeline8.getKeyFrames().add(
                        new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (getChannelsdata().iterator().hasNext()) {
                                    seriesA8.getData().add(new ScatterChart.Data<String, Double>(
                                            getChannelsdata().iterator().next().date,
                                            getChannelsdata().iterator().next().value8
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
                                getChannelsdata().remove(0);
                            }
                        })
                );

                timeline8.setCycleCount(1000);
                animation8 = new SequentialTransition();
                animation8.getChildren().add(timeline8);
                scatterchart.getData().add(seriesA8);
                animation8.play();

            } else {
                if (animation8 != null) {
                    animation8.stop();
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
    }

    /**
     * Find the X /Ycoordinate in ancestor's coordinate system that corresponds
     * to the X=0 axis in descendant's coordinate system.
     *
     */
    private static double getXShift(Node node) {
        double shift = 0;
        do {
            shift += node.getLayoutX();
            node = node.getParent();
        } while (node != null);
        return shift;
    }

    private static double getYShift(Node node) {
        double shift = 0;
        do {
            shift += node.getLayoutY();
            node = node.getParent();
        } while (node != null);
        return shift;
    }

    /**
     * Make a best attempt to replace the original component with the
     * replacement, and keep the same position and layout constraints in the
     * container.
     * <p>
     * Currently this method is probably not perfect. It uses three strategies:
     * <ol>
     * <li>If the original has any properties, move all of them to the
     * replacement</li>
     * <li>If the parent of the original is a {@link BorderPane}, preserve the
     * position</li>
     * <li>Preserve the order of the children in the parent's list</li>
     * </ol>
     * <p>
     * This method does not transfer any handlers (mouse handlers for example).
     *
     * @param original non-null Node whose parent is a {@link Pane}.
     * @param replacement non-null Replacement Node
     */
    public void replaceComponent(Node original, Node replacement) {
        Pane parent = (Pane) original.getParent();
        //transfer any properties (usually constraints)
        replacement.getProperties().putAll(original.getProperties());
        original.getProperties().clear();

        ObservableList<Node> children = parent.getChildren();
        int originalIndex = children.indexOf(original);
        if (parent instanceof BorderPane) {
            BorderPane borderPane = (BorderPane) parent;
            if (borderPane.getTop() == original) {
                children.remove(original);
                borderPane.setTop(replacement);

            } else if (borderPane.getLeft() == original) {
                children.remove(original);
                borderPane.setLeft(replacement);

            } else if (borderPane.getCenter() == original) {
                children.remove(original);
                borderPane.setCenter(replacement);

            } else if (borderPane.getRight() == original) {
                children.remove(original);
                borderPane.setRight(replacement);

            } else if (borderPane.getBottom() == original) {
                children.remove(original);
                borderPane.setBottom(replacement);
            }
        } else {
            //Hope that preserving the properties and position in the list is sufficient
            children.set(originalIndex, replacement);
        }
    }

    public Region setupZooming(XYChart<?, ?> chart, EventHandler<? super MouseEvent> mouseFilter) {
        anchorpane = new AnchorPane();
        if (chart.getParent() != null) {
            replaceComponent(chart, anchorpane);
        }

        selectRect = new Rectangle(0, 0, 0, 0);
        selectRect.setFill(Color.DODGERBLUE);
        selectRect.setMouseTransparent(false);
        selectRect.setOpacity(0.3);
        selectRect.setStroke(Color.rgb(0, 0x29, 0x66));
        selectRect.setStrokeType(StrokeType.INSIDE);
        selectRect.setStrokeWidth(3.0);
        //anchorpane..setAlignment(selectRect, Pos.TOP_LEFT);

        anchorpane.getChildren().addAll(chart, selectRect);
        scatterchart.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selected.set(true);
                double x = mouseEvent.getX();
                double y = mouseEvent.getY();
                selectRect.setTranslateX(x);
                selectRect.setTranslateY(y);
                rectX.set(x);
                rectY.set(y);
                //animated rectangle:
                selectRect.widthProperty().bind(rectX.subtract(x));
                selectRect.heightProperty().bind(rectY.subtract(y));
                selectRect.visibleProperty().bind(selected);
                Rectangle2D plotArea = getPlotArea();
                selectRect.setTranslateX(x);
                selectRect.setTranslateY(plotArea.getMinY());
                rectX.set(x);
                rectY.set(plotArea.getMaxY());
            }
        });

        scatterchart.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!selected.get()) {
                    return;
                }
                Rectangle2D plotArea = getPlotArea();

                double x = mouseEvent.getX();
                //Clamp to the selection start
                x = Math.max(x, selectRect.getTranslateX());

                //Clamp to plot area
                //  x = Math.min(x, plotArea.getMaxX());
                rectX.set(x);

                double y = mouseEvent.getY();
                //Clamp to the selection start

                y = Math.max(y, selectRect.getTranslateY());
                //Clamp to plot area
                //  y = Math.min(y, plotArea.getMaxY());
                rectY.set(y);

            }
        });

        scatterchart.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (!selected.get()) {
                    return;
                }

                Rectangle2D zoomWindow = getDataCoordinates2(
                        selectRect.getTranslateX(), selectRect.getTranslateY(),
                        rectX.get(), rectY.get()
                );

                xAxis.setAutoRanging(false);
                yAxis.setAutoRanging(false);

                if (zoomAnimated.get()) {
                    zoomAnimation.stop();
                    zoomAnimation.getKeyFrames().setAll(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(yAxis.lowerBoundProperty(), yAxis.getLowerBound()),
                                    new KeyValue(yAxis.upperBoundProperty(), yAxis.getUpperBound())
                            ),
                            new KeyFrame(Duration.millis(1000),
                                    new KeyValue(yAxis.lowerBoundProperty(), zoomWindow.getMinY()),
                                    new KeyValue(yAxis.upperBoundProperty(), zoomWindow.getMaxY())
                            )
                    );
                    zoomAnimation.play();
                } else {
                    zoomAnimation.stop();
                    yAxis.setLowerBound(zoomWindow.getMinY());
                    yAxis.setUpperBound(zoomWindow.getMaxY());
                }
                selected.set(false);
            }
        });

        return anchorpane;
    }
    public EventHandler<MouseEvent> DEFAULT_FILTER = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            //The ChartPanManager uses this reference, so if behavior changes, copy to users first.
            if (mouseEvent.getButton() != MouseButton.PRIMARY) {
                mouseEvent.consume();
            }
        }
    };

    /**
     * Returns the plot area in the reference's coordinate space.
     */
    public Rectangle2D getPlotArea() {
        Axis<?> xAxis = scatterchart.getXAxis();
        Axis<?> yAxis = scatterchart.getYAxis();

        double xStart = getXShift(xAxis);
        double yStart = getYShift(yAxis);

        //If the direct method to get the width (which is based on its Node dimensions) is not found to
        //be appropriate, an alternative method is commented.
        //double width = xAxis.getDisplayPosition( xAxis.toRealValue( xAxis.getLowerBound() )  );
        double width = xAxis.getWidth();
        //double height = yAxis.getDisplayPosition( yAxis.toRealValue( yAxis.getLowerBound() ) );
        double height = yAxis.getHeight();

        return new Rectangle2D(xStart, yStart, width, height);
    }

    /**
     * Given graphical coordinates in the reference's coordinate system, returns
     * x and y axis value as a point via the
     * {@link Axis#getValueForDisplay(double)} and
     * {@link Axis#toNumericValue(Object)} methods.
     *
     * @param minX lower X value (upper left point)
     * @param minY lower Y value (upper left point)
     * @param maxX upper X value (bottom right point)
     * @param maxY upper Y value (bottom right point)
     */
    @SuppressWarnings("unchecked")
    public Rectangle2D getDataCoordinates2(double minX, double minY, double maxX, double maxY) {
        if (minX > maxX || minY > maxY) {
            throw new IllegalArgumentException("min > max for X and/or Y");
        }

        Axis xAxis = scatterchart.getXAxis();
        Axis yAxis = scatterchart.getYAxis();

        double xStart = getXShift(xAxis);
        double yStart = getYShift(yAxis);

        double minDataX = xAxis.toNumericValue(xAxis.getValueForDisplay(minX - xStart));
        double maxDataX = xAxis.toNumericValue(xAxis.getValueForDisplay(maxX - xStart));

        //The "low" Y data value is actually at the maxY graphical location as Y graphical axis gets
        //larger as you go down on the screen.
        double minDataY = yAxis.toNumericValue(yAxis.getValueForDisplay(maxY - yStart));
        double maxDataY = yAxis.toNumericValue(yAxis.getValueForDisplay(minY - yStart));

        return new Rectangle2D(minDataX,
                minDataY,
                maxDataX - minDataX,
                maxDataY - minDataY);
    }

}
