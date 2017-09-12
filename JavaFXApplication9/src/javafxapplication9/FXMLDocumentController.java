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
    Map<String, Double> channel1DateHashMap = new HashMap<>();
    Map<String, Double> channel2DateHashMap = new HashMap<>();
    Map<String, Double> channel3DateHashMap = new HashMap<>();
    Map<String, Double> channel4DateHashMap = new HashMap<>();
    Map<String, Double> channel5DateHashMap = new HashMap<>();
    Map<String, Double> channel6DateHashMap = new HashMap<>();
    Map<String, Double> channel7DateHashMap = new HashMap<>();
    Map<String, Double> channel8DateHashMap = new HashMap<>();
    
    ScatterChart.Series<String, Double> series1 = new ScatterChart.Series();
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
    private DatePicker Datepicker1;
    @FXML
    private DatePicker datePicker2;
    
    private final String pattern = "yyyy-MM-dd";
    @FXML
    private CheckBox checkbox1;
    @FXML
    private Button SingelReadButton;
    @FXML
    private ScrollBar scrollbar1;
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
    private SplitMenuButton starttime;
    
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
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return channelsdata;
    }
    
    public void createALLChannelDataSeries() {
        
        getChannelsdata().stream().forEach((c) -> {
            channel1DateHashMap.put(c.getDate(), c.getValue1());
            channel2DateHashMap.put(c.getDate(), c.getValue2());
            channel3DateHashMap.put(c.getDate(), c.getValue3());
            channel4DateHashMap.put(c.getDate(), c.getValue4());
            channel5DateHashMap.put(c.getDate(), c.getValue5());
            channel6DateHashMap.put(c.getDate(), c.getValue6());
            channel7DateHashMap.put(c.getDate(), c.getValue7());
            channel8DateHashMap.put(c.getDate(), c.getValue8());
            
        });
        channel1DateHashMap.forEach((t, u) -> {
            series1.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel2DateHashMap.forEach((t, u) -> {
            series2.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel3DateHashMap.forEach((t, u) -> {
            series3.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel4DateHashMap.forEach((t, u) -> {
            series4.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel5DateHashMap.forEach((t, u) -> {
            series5.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel6DateHashMap.forEach((t, u) -> {
            series6.getData().add(new ScatterChart.Data<>(t, u));
            
        });
        channel7DateHashMap.forEach((t, u) -> {
            series7.getData().add(new ScatterChart.Data<>(t, u));
        });
        channel8DateHashMap.forEach((t, u) -> {
            series8.getData().add(new ScatterChart.Data<>(t, u));
        });
        
        seriesCollection.addAll(Arrays.asList(series1, series2, series3, series4, series5, series6, series7, series8));
        
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
    
    private void changeColor2(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series2");
        Color d = colorPicker2.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }
    
    private void changeColor3(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series3");
        Color d = colorPicker3.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }
    
    private void changeColor4(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series4");
        Color d = colorPicker4.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }
    
    private void changeColor5(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series5");
        Color d = colorPicker5.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }
    
    private void changeColor6(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series6");
        Color d = colorPicker6.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }
    
    private void changeColor7(ActionEvent event) {
        Set<Node> nodes = scatterchart.lookupAll(".series7");
        Color d = colorPicker7.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }
    
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
    
    @FXML
    private void frekvensSetup(MouseDragEvent event) {
    }
    
    @FXML
    private void pickStartDate(ActionEvent event) {
    }
    
    @FXML
    private void pickEndDate(ActionEvent event) {
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
        
        scatterchart.getData().add(seriesS1);
        scatterchart.getData().add(seriesS2);
        scatterchart.getData().add(seriesS3);
        scatterchart.getData().add(seriesS4);
        scatterchart.getData().add(seriesS5);
        scatterchart.getData().add(seriesS6);
        scatterchart.getData().add(seriesS7);
        scatterchart.getData().add(seriesS8);
        
        scatterchart.setLegendVisible(false);        
        getChannelsdata().remove(0);
        zoom();
        
    }
    
    @FXML
    private void autoPlot(MouseEvent event) throws InterruptedException {
        
        AutoReadButton.setStyle("-fx-font: 13 arial; -fx-base: #b6e7b9;");
       
        
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
    
}
