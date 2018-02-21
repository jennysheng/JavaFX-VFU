/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication9;

import com.sun.javafx.charts.Legend;
import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotFactory;
import java.awt.Robot;
import java.awt.event.InputEvent;
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
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
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
import javafx.scene.input.KeyEvent;
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
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    private final SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    private final SimpleDoubleProperty rectY = new SimpleDoubleProperty();
    private SimpleBooleanProperty selected = new SimpleBooleanProperty(false);
    private final BooleanProperty zoomAnimated = new SimpleBooleanProperty(true);

    Rectangle selectRect;
    File selectedFile = null;
    ArrayList<Color> colorList = new ArrayList<Color>();
    private Timeline zoomAnimation = new Timeline();
    @FXML
    public ScatterChart<String, Double> scatterchart;

    @FXML
    public ColorPicker colorPicker1;
    @FXML
    public ColorPicker colorPicker4;
    @FXML
    public ColorPicker colorPicker3;
    @FXML
    public ColorPicker colorPicker2;
    @FXML
    public ColorPicker colorPicker5;
    @FXML
    public ColorPicker colorPicker6;
    @FXML
    public ColorPicker colorPicker7;
    @FXML
    public ColorPicker colorPicker8;

    @FXML
    public AnchorPane anchorpane;
    @FXML
    public NumberAxis yAxis;
    @FXML
    public CategoryAxis xAxis;

    @FXML
    public CheckBox checkbox1;
    public Button SingelReadButton;
    int m = 0;
    int n = 0;

    @FXML
    public Button AutoReadButton;
    public SequentialTransition animation4;

    @FXML
    public TextField textFieldMs;
    @FXML
    public CheckBox checkbox2;
    @FXML
    public CheckBox checkbox3;
    @FXML
    public CheckBox checkbox4;
    @FXML
    public CheckBox checkbox5;
    @FXML
    public CheckBox checkbox6;
    @FXML
    public CheckBox checkbox7;
    @FXML
    public CheckBox checkbox8;

    Set<Node> nodes2, nodes3, nodes4, nodes5, nodes6, nodes7, nodes8;

    ReadFromFile rff;

    int TotalNbr;
    File dir;

    @FXML
    public Button SingleReadButton;

    @FXML
    public Label outputLabel;

    @FXML
    public RadioButton SampleRadio;
    @FXML
    public RadioButton timeRadio;
    ScatterChart.Series<String, Double> series1;
    ScatterChart.Series<String, Double> series2;
    ScatterChart.Series<String, Double> series3;
    ScatterChart.Series<String, Double> series4;
    ScatterChart.Series<String, Double> series5;
    ScatterChart.Series<String, Double> series6;
    ScatterChart.Series<String, Double> series7;
    ScatterChart.Series<String, Double> series8;
    @FXML
    public Label fileLabel;

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
        SingleReadButton.setVisible(false);
        AutoReadButton.setVisible(false);

    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    void jennyAutoplot() {
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        xAxis.setAnimated(false);
        scatterchart.setLegendVisible(false);

        if (timeRadio.isSelected()) {
            timeAutoPlot();
        } else if (SampleRadio.isSelected()) {
            sampleAutoPlot();
        }

    }

    void jennySingleplot() {
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
        xAxis.setAnimated(false);
        scatterchart.setLegendVisible(false);

        if (timeRadio.isSelected()) {
            timeSinglePlot();
        } else if (SampleRadio.isSelected()) {
            sampleSinglePlot();
        }
    }

    void timeAutoPlot() {
        SampleRadio.setSelected(false);
        series1 = new ScatterChart.Series();
        series2 = new ScatterChart.Series();
        series3 = new ScatterChart.Series();
        series4 = new ScatterChart.Series();
        series5 = new ScatterChart.Series();
        series6 = new ScatterChart.Series();
        series7 = new ScatterChart.Series();
        series8 = new ScatterChart.Series();
        while (rff.getChannelsdata().iterator().hasNext()) {
            series1.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value1));
            series2.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value2));
            series3.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value3));
            series4.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value4));
            series5.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value5));
            series6.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value6));
            series7.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value7));
            series8.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().iterator().next().date, rff.getChannelsdata().iterator().next().value8));
            System.out.println("timeplot" + rff.getChannelsdata().iterator().next().value1);
            rff.getChannelsdata().remove(0);

        }

        if (checkbox1.isSelected()) {
            scatterchart.getData().add(series1);
            changecolor1();

        } else {
            scatterchart.getData().remove(series1);
        }
        if (checkbox2.isSelected()) {
            scatterchart.getData().add(series2);
            changecolor2();
        } else {
            scatterchart.getData().remove(series2);
        }

        if (checkbox3.isSelected()) {
            scatterchart.getData().add(series3);
            changecolor3();
        } else {
            scatterchart.getData().remove(series3);
        }
        if (checkbox4.isSelected()) {
            scatterchart.getData().add(series4);
            changecolor4();
        } else {
            scatterchart.getData().remove(series4);
        }
        if (checkbox5.isSelected()) {
            scatterchart.getData().add(series5);
            changecolor5();
        } else {
            scatterchart.getData().remove(series5);
        }
        if (checkbox6.isSelected()) {
            scatterchart.getData().add(series6);
            changecolor6();
        } else {
            scatterchart.getData().remove(series6);
        }
        if (checkbox7.isSelected()) {
            scatterchart.getData().add(series7);
            changecolor7();
        } else {
            scatterchart.getData().remove(series7);
        }
        if (checkbox8.isSelected()) {
            scatterchart.getData().add(series8);
            changecolor8();
        } else {
            scatterchart.getData().remove(series8);
        }

    }

    void sampleAutoPlot() {

        timeRadio.setSelected(false);
        series1 = new ScatterChart.Series();
        series2 = new ScatterChart.Series();
        series3 = new ScatterChart.Series();
        series4 = new ScatterChart.Series();
        series5 = new ScatterChart.Series();
        series6 = new ScatterChart.Series();
        series7 = new ScatterChart.Series();
        series8 = new ScatterChart.Series();

        while (rff.getChannelsdata().iterator().hasNext()) {
            series1.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value1));
            series2.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value2));
            series3.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value3));
            series4.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value4));
            series5.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value5));
            series6.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value6));
            series7.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value7));
            series8.getData().add(new ScatterChart.Data<>(String.valueOf(n), rff.getChannelsdata().iterator().next().value8));
            System.out.println("sampleplot" + rff.getChannelsdata().iterator().next().value1);
            rff.getChannelsdata().remove(0);
            n++;

        }

        if (checkbox1.isSelected()) {
            scatterchart.getData().add(series1);
            changecolor1();

        } else {
            scatterchart.getData().remove(series1);
        }
        if (checkbox2.isSelected()) {
            scatterchart.getData().add(series2);
            changecolor2();
        } else {
            scatterchart.getData().remove(series2);
        }

        if (checkbox3.isSelected()) {
            scatterchart.getData().add(series3);
            changecolor3();
        } else {
            scatterchart.getData().remove(series3);
        }
        if (checkbox4.isSelected()) {
            scatterchart.getData().add(series4);
            changecolor4();
        } else {
            scatterchart.getData().remove(series4);
        }
        if (checkbox5.isSelected()) {
            scatterchart.getData().add(series5);
            changecolor5();
        } else {
            scatterchart.getData().remove(series5);
        }
        if (checkbox6.isSelected()) {
            scatterchart.getData().add(series6);
            changecolor6();
        } else {
            scatterchart.getData().remove(series6);
        }
        if (checkbox7.isSelected()) {
            scatterchart.getData().add(series7);
            changecolor7();
        } else {
            scatterchart.getData().remove(series7);
        }
        if (checkbox8.isSelected()) {
            scatterchart.getData().add(series8);
            changecolor8();
        } else {
            scatterchart.getData().remove(series8);
        }

    }

    void timeSinglePlot() {
        SampleRadio.setSelected(false);
        series1 = new ScatterChart.Series();
        series2 = new ScatterChart.Series();
        series3 = new ScatterChart.Series();
        series4 = new ScatterChart.Series();
        series5 = new ScatterChart.Series();
        series6 = new ScatterChart.Series();
        series7 = new ScatterChart.Series();
        series8 = new ScatterChart.Series();
        for (int j = 0; j < rff.getJ(); j++) {
            series1.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue1()));
            series2.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue2()));
            series3.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue3()));
            series4.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue4()));
            series5.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue5()));
            series6.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue6()));
            series7.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue7()));
            series8.getData().add(new ScatterChart.Data<>(rff.getChannelsdata().get(j).getDate(), rff.getChannelsdata().get(j).getValue8()));

        }

        if (checkbox1.isSelected()) {
            scatterchart.getData().add(series1);
            changecolor1();
        } else {
            scatterchart.getData().remove(series1);
        }
        if (checkbox2.isSelected()) {
            scatterchart.getData().add(series2);
            changecolor2();
        } else {
            scatterchart.getData().remove(series2);
        }

        if (checkbox3.isSelected()) {
            scatterchart.getData().add(series3);
            changecolor3();
        } else {
            scatterchart.getData().remove(series3);
        }
        if (checkbox4.isSelected()) {
            scatterchart.getData().add(series4);
            changecolor4();
        } else {
            scatterchart.getData().remove(series4);
        }
        if (checkbox5.isSelected()) {
            scatterchart.getData().add(series5);
            changecolor5();
        } else {
            scatterchart.getData().remove(series5);
        }
        if (checkbox6.isSelected()) {
            scatterchart.getData().add(series6);
            changecolor6();
        } else {
            scatterchart.getData().remove(series6);
        }
        if (checkbox7.isSelected()) {
            scatterchart.getData().add(series7);
            changecolor7();
        } else {
            scatterchart.getData().remove(series7);
        }
        if (checkbox8.isSelected()) {
            scatterchart.getData().add(series8);
            changecolor8();

        } else {
            scatterchart.getData().remove(series8);
        }
    }

    void sampleSinglePlot() {

        timeRadio.setSelected(false);
        series1 = new ScatterChart.Series();
        series2 = new ScatterChart.Series();
        series3 = new ScatterChart.Series();
        series4 = new ScatterChart.Series();
        series5 = new ScatterChart.Series();
        series6 = new ScatterChart.Series();
        series7 = new ScatterChart.Series();
        series8 = new ScatterChart.Series();

        for (int j = 0; j < rff.getJ(); j++) {
            series1.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue1()));
            series2.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue2()));
            series3.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue3()));
            series4.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue4()));
            series5.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue5()));
            series6.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue6()));
            series7.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue7()));
            series8.getData().add(new ScatterChart.Data<>(String.valueOf(j), rff.getChannelsdata().get(j).getValue8()));

        }

        if (checkbox1.isSelected()) {
            scatterchart.getData().add(series1);
            changecolor1();

        } else {
            scatterchart.getData().remove(series1);
        }
        if (checkbox2.isSelected()) {
            scatterchart.getData().add(series2);
            changecolor2();
        } else {
            scatterchart.getData().remove(series2);
        }

        if (checkbox3.isSelected()) {
            scatterchart.getData().add(series3);
            changecolor3();
        } else {
            scatterchart.getData().remove(series3);
        }
        if (checkbox4.isSelected()) {
            scatterchart.getData().add(series4);
            changecolor4();
        } else {
            scatterchart.getData().remove(series4);
        }
        if (checkbox5.isSelected()) {
            scatterchart.getData().add(series5);
            changecolor5();
        } else {
            scatterchart.getData().remove(series5);
        }
        if (checkbox6.isSelected()) {
            scatterchart.getData().add(series6);
            changecolor6();
        } else {
            scatterchart.getData().remove(series6);
        }
        if (checkbox7.isSelected()) {
            scatterchart.getData().add(series7);
            changecolor7();
        } else {
            scatterchart.getData().remove(series7);
        }
        if (checkbox8.isSelected()) {
            scatterchart.getData().add(series8);
            changecolor8();
        } else {
            scatterchart.getData().remove(series8);
        }

    }

    @FXML
    private void singlePlot(MouseEvent event) {

        if (SingleReadButton.getText().equals("SinglePlot")) {
            //on------------------------------------------

            SingleReadButton.setText("ClearScreen");
            scatterchart.getData().clear();
            jennySingleplot();

        } else {
            //off-----------------------------------
            SingleReadButton.setText("SinglePlot");
            scatterchart.getData().clear();
            SingleReadButton.setStyle("");
            scatterchart.setVisible(true);
            rff = new ReadFromFile(selectedFile);
            rff.start();
            setupZooming(scatterchart, DEFAULT_FILTER);

        }

    }

    Timeline timeline;

    @FXML
    private void autoPlot(MouseEvent event) throws InterruptedException {
        SingleReadButton.setDisable(false);
        scatterchart.getData().clear();
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(false);
        xAxis.setAnimated(false);
        yAxis.setAnimated(false);
        scatterchart.setLegendVisible(false);
        scatterchart.setVisible(true);
        if (AutoReadButton.getText().equalsIgnoreCase("Stop")) {
            AutoReadButton.setText("AutoPlot");
            animation4.stop();
            rff.interrupt();

        } else if (AutoReadButton.getText().equalsIgnoreCase("AutoPlot")) {

            AutoReadButton.setText("Stop");

            timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.millis(Integer.parseInt(textFieldMs.getText())), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            rff = new ReadFromFile(selectedFile);
                            rff.start();

                            scatterchart.getData().clear();

                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            jennyAutoplot();

                        }
                    })
            );

            if (scatterchart.getData() != null) {
                timeline.setCycleCount(Animation.INDEFINITE);
                animation4 = new SequentialTransition();
                animation4.getChildren().add(timeline);
                animation4.play();
                animation4.getChildren().clear();
                scatterchart.getData().clear();
                if (scatterchart.getData() == null) {
                    animation4.stop();
                }
            }

        }

    }

    void clear() {
        scatterchart.getData().clear();
        scatterchart.getData().removeAll();
        rff.setChannelsdata(null);
        xAxis.setAutoRanging(true);
        yAxis.setAutoRanging(true);
    }

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
        scatterchart.getData().clear();
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        //if (dir != null) {

        fileLabel.setText(selectedFile.getPath());
        // fileLabel.setText(dir.toString());
        // }
        SingleReadButton.setVisible(true);
        AutoReadButton.setVisible(true);

    }

    @FXML
    private void sampleHandleAction(ActionEvent event) {
        timeRadio.setSelected(false);

    }

    @FXML
    private void timeHandleAction(ActionEvent event) {
        SampleRadio.setSelected(false);

    }

    void changecolor1() {
        Set<Node> nodes = scatterchart.lookupAll(".series0");
        Color c = colorPicker1.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(c);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    void changecolor2() {
        Color c = colorPicker2.getValue();
        String newColor2 = "-fx-background-color: " + toRGBCode(c);
        if (checkbox1.isSelected() == true) {
            nodes2 = scatterchart.lookupAll(".series1");
            
        } else {
            nodes2 = scatterchart.lookupAll(".series0");
       
        }
        for (Node n : nodes2) {
            n.setStyle(newColor2);
        }

    }

    void changecolor3() {
        Color c = colorPicker3.getValue();
        String newColor3 = "-fx-background-color: " + toRGBCode(c);
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

    }

    void changecolor4() {
        Color c = colorPicker4.getValue();
        String newColor4 = "-fx-background-color: " + toRGBCode(c);
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

    }

    void changecolor5() {
        Color c = colorPicker5.getValue();
        String newColor5 = "-fx-background-color: " + toRGBCode(c);
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

    }

    void changecolor6() {
        Color c = colorPicker6.getValue();
        String newColor6 = "-fx-background-color: " + toRGBCode(c);
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

    }

    void changecolor7() {
        Color c = colorPicker7.getValue();
        String newColor7 = "-fx-background-color: " + toRGBCode(c);
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
        }

    }

    void changecolor8() {
        Color c = colorPicker8.getValue();
        String newColor8 = "-fx-background-color: " + toRGBCode(c);
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
    }

    @FXML
    private void handleExitAction(ActionEvent event) {
        System.exit(0);
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
        xAxis.setTickLabelRotation(90);

        //The "low" Y data value is actually at the maxY graphical location as Y graphical axis gets
        //larger as you go down on the screen.
        double minDataY = yAxis.toNumericValue(yAxis.getValueForDisplay(maxY - yStart));
        double maxDataY = yAxis.toNumericValue(yAxis.getValueForDisplay(minY - yStart));

        return new Rectangle2D(minDataX,
                minDataY,
                maxDataX - minDataX,
                maxDataY - minDataY);
    }

    @FXML
    private void handlecheckbox1(ActionEvent event) {
        if (checkbox1.isSelected()) {
            scatterchart.getData().add(series1);
            changecolor1();

        } else {
            scatterchart.getData().remove(series1);
        }
    }

    @FXML
    private void handlecheckbox2(ActionEvent event) {
         if (checkbox2.isSelected()) {
            scatterchart.getData().add(series2);
            changecolor2();

        } else {
            scatterchart.getData().remove(series2);
        }
    }

    @FXML
    private void handlecheckbox3(ActionEvent event) {
         if (checkbox3.isSelected()) {
            scatterchart.getData().add(series3);
            changecolor3();

        } else {
            scatterchart.getData().remove(series3);
        }
    }

    @FXML
    private void handlecheckbox4(ActionEvent event) {
         if (checkbox4.isSelected()) {
            scatterchart.getData().add(series4);
            changecolor4();

        } else {
            scatterchart.getData().remove(series4);
        }
    }

    @FXML
    private void handlecheckbox5(ActionEvent event) {
         if (checkbox5.isSelected()) {
            scatterchart.getData().add(series5);
            changecolor5();

        } else {
            scatterchart.getData().remove(series5);
        }
    }

    @FXML
    private void handlecheckbox6(ActionEvent event) {
         if (checkbox6.isSelected()) {
            scatterchart.getData().add(series6);
            changecolor6();

        } else {
            scatterchart.getData().remove(series6);
        }
    }

    @FXML
    private void handlecheckbox7(ActionEvent event) {
         if (checkbox7.isSelected()) {
            scatterchart.getData().add(series7);
            changecolor7();

        } else {
            scatterchart.getData().remove(series7);
        }
    }

    @FXML
    private void handlecheckbox8(ActionEvent event) {
         if (checkbox8.isSelected()) {
            scatterchart.getData().add(series8);
            changecolor8();

        } else {
            scatterchart.getData().remove(series8);
        }
    }

}
