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
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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

    private List<ScatterChart.Series<Date, Double>> seriesCollection = new ArrayList<>();

    Map<String, Double> channel1HashMap = new HashMap<>();
    Map<String, Double> channel2HashMap = new HashMap<>();
    Map<String, Double> channel3HashMap = new HashMap<>();
    Map<String, Double> channel4HashMap = new HashMap<>();
    Map<String, Double> channel5HashMap = new HashMap<>();
    Map<String, Double> channel6HashMap = new HashMap<>();
    Map<String, Double> channel7HashMap = new HashMap<>();
    Map<String, Double> channel8HashMap = new HashMap<>();

    private final ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();
    XYChart.Series<String, Double> series1 = new ScatterChart.Series();
    ScatterChart.Series<String, Double> series2 = new ScatterChart.Series();
    ScatterChart.Series<String, Double> series3 = new ScatterChart.Series();
    ScatterChart.Series<String, Double> series4 = new ScatterChart.Series();
    ScatterChart.Series<String, Double> series5 = new ScatterChart.Series();
    ScatterChart.Series<String, Double> series6 = new ScatterChart.Series();
    ScatterChart.Series<String, Double> series7 = new ScatterChart.Series();
    ScatterChart.Series<String, Double> series8 = new ScatterChart.Series();

    File selectedFile = null;

    int f = 0, m = 0, n = 0, o = 0, q = 0, r = 0, s = 0;
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
    private DatePicker Datepicker1;
    @FXML
    private DatePicker datePicker2;

    @FXML
    private ScrollBar scollBar1;
    @FXML
    private ScrollBar scrollBar2;
    @FXML
    private ScrollBar scrollBar3;

    private final String pattern = "yyyy-MM-dd";
    @FXML
    private CheckBox checkbox1;

    @FXML
    private void handleReadAction(ActionEvent event) throws InterruptedException, ParseException {

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        readFromFile();
        createALLChannelDataSeries();

    }

    public ObservableList<DataLogger> getChannelsdata() {
        return channelsdata;
    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    Date StringToDate(String datetimeStr) {

        Date parseDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");
        try {
            parseDate = format.parse(datetimeStr);

        } catch (ParseException e) {

        }
        return parseDate;
    }

    String DateToString(Date datetime) {

        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");
        String s = format.format(datetime);
        return s;
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelsdata;
    }
    int i = 0;

    public void createALLChannelDataSeries() {

        getChannelsdata().stream().forEach((c) -> {
            channel1HashMap.put(c.getDate(), c.getValue1());
            channel2HashMap.put(c.getDate(), c.getValue2());
            channel3HashMap.put(c.getDate(), c.getValue3());
            channel4HashMap.put(c.getDate(), c.getValue4());
            channel5HashMap.put(c.getDate(), c.getValue5());
            channel6HashMap.put(c.getDate(), c.getValue6());
            channel7HashMap.put(c.getDate(), c.getValue7());
            channel8HashMap.put(c.getDate(), c.getValue8());

        });
        channel1HashMap.forEach((t, u) -> {
            series1.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel2HashMap.forEach((t, u) -> {
            series2.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel3HashMap.forEach((t, u) -> {
            series3.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel4HashMap.forEach((t, u) -> {
            series4.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel5HashMap.forEach((t, u) -> {
            series5.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel6HashMap.forEach((t, u) -> {
            series6.getData().add(new ScatterChart.Data<>(t, u));

        });
        channel7HashMap.forEach((t, u) -> {
            series7.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel8HashMap.forEach((t, u) -> {
            series8.getData().add(new ScatterChart.Data<>(t, u));
        });

        // seriesCollection.addAll(Arrays.asList(series1, series2, series3, series4, series5, series6, series7, series8));
    }

    @FXML
    private void selectCheckBox1(ActionEvent event) {
        if (event.getSource() instanceof CheckBox) {
            CheckBox cp = (CheckBox) event.getSource();
            if (cp.isSelected() && cp.getId().equalsIgnoreCase("checkbox1")) {
                scatterchart.getData().add(series1);
            } else {
                scatterchart.getData().remove(series1);
            }

        }
    }
       @FXML
    private void autoPlot(ScrollEvent event) {
        
        
        
        
    }

    @FXML
    private void singelPlot(MouseEvent event) {
        
    }

    @FXML
    private void pickStartDate(ActionEvent event) {
    }

    @FXML
    private void pickEndDate(ActionEvent event) {
    }


    @FXML
    private void freq2(MouseEvent event) {
    }

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    @FXML
    private void changeColor1(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series1");
        Color d = colorPicker1.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    @FXML
    private void changeColor2(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series2");
        Color d = colorPicker2.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    @FXML
    private void changeColor3(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series3");
        Color d = colorPicker3.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    @FXML
    private void changeColor4(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series4");
        Color d = colorPicker4.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    @FXML
    private void changeColor5(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series5");
        Color d = colorPicker5.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    @FXML
    private void changeColor6(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series6");
        Color d = colorPicker6.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    @FXML
    private void changeColor7(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series7");
        Color d = colorPicker7.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    @FXML
    private void changeColor8(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series7");
        Color d = colorPicker8.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
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

 

}
