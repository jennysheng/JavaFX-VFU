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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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

    private ArrayList<ScatterChart.Series> scatterchartseries = new ArrayList<>();

    Map<Date, Double> channel1HashMap = new HashMap<>();
    Map<Date, Double> channel2HashMap = new HashMap<>();
    Map<Date, Double> channel3HashMap = new HashMap<>();
    Map<Date, Double> channel4HashMap = new HashMap<>();
    Map<Date, Double> channel5HashMap = new HashMap<>();
    Map<Date, Double> channel6HashMap = new HashMap<>();
    Map<Date, Double> channel7HashMap = new HashMap<>();
    Map<Date, Double> channel8HashMap = new HashMap<>();

    private final ObservableList<DataLogger> channelsdata = FXCollections.observableArrayList();

    ScatterChart.Series series1 = new ScatterChart.Series();
    ScatterChart.Series series2 = new ScatterChart.Series();
    ScatterChart.Series series3 = new ScatterChart.Series();
    ScatterChart.Series series4 = new ScatterChart.Series();
    ScatterChart.Series series5 = new ScatterChart.Series();
    ScatterChart.Series series6 = new ScatterChart.Series();
    ScatterChart.Series series7 = new ScatterChart.Series();
    ScatterChart.Series series8 = new ScatterChart.Series();

    File selectedFile = null;

    int f=0, m=0, n=0, o=0, q=0, r=0, s=0;
    @FXML
    private ScatterChart<Integer, Double> scatterchart;
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
    private NumberAxis xAxis;

    @FXML
    private DatePicker Datepicker1;
    @FXML
    private DatePicker datePicker2;
   
    @FXML
    private Button Button1;
    @FXML
    private Button Button2;
    @FXML
    private Button Button3;
    @FXML
    private Button Button5;
    @FXML
    private Button Button6;
    @FXML
    private Button Button7;
    @FXML
    private ScrollBar scollBar1;
    @FXML
    private ScrollBar scrollBar2;
    @FXML
    private Button Button8;
    @FXML
    private Label Displa;
    @FXML
    private ScrollBar scrollBar3;
    @FXML
    private Button Button4;
      
    private final String pattern = "yyyy-MM-dd";

    Date dateconverter(String datetimeStr) {

        Date parseDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S");
        try {
            parseDate = format.parse(datetimeStr);

        } catch (ParseException e) {

        }
        return parseDate;
    }

    @FXML
    private void handleReadAction(ActionEvent event) throws InterruptedException, ParseException {

        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        readFromFile();
        create8ChannelDataHashMap();

    }

    public ObservableList<DataLogger> getChannelsdata() {
        return channelsdata;
    }

    public void initialize(URL url, ResourceBundle rb) {

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

    public void create8ChannelDataHashMap() {

        getChannelsdata().stream().forEach((c) -> {
            channel1HashMap.put(dateconverter(c.getDate()), c.getValue1());
            channel2HashMap.put(dateconverter(c.getDate()), c.getValue1());
            channel3HashMap.put(dateconverter(c.getDate()), c.getValue1());
            channel4HashMap.put(dateconverter(c.getDate()), c.getValue1());
            channel5HashMap.put(dateconverter(c.getDate()), c.getValue1());
            channel6HashMap.put(dateconverter(c.getDate()), c.getValue1());
            channel7HashMap.put(dateconverter(c.getDate()), c.getValue1());
            channel8HashMap.put(dateconverter(c.getDate()), c.getValue1());

        });
       

    }
    public void pickDateTime(){
        
    }
   
        @FXML
    private void handelButton1Action(ActionEvent event) {
      
        for(int i=0; i<100; i++){
        series1.getData().add(new ScatterChart.Data(i, channel1HashMap.get(Datepicker1.getDate())));
        scatterchart.getData().add(series1);
        
        }
  
    }

    @FXML
    private void handelButton2Action(ActionEvent event) {
        scatterchart.getData().add(series2);
    }

    @FXML
    private void handelButton3Action(ActionEvent event) {
        scatterchart.getData().add(series3);
    }
      @FXML
    private void handelButton4Action(ActionEvent event) {
        scatterchart.getData().add(series4);
    }
   
    
     @FXML
    private void handelButton5Action(ActionEvent event) {
        scatterchart.getData().add(series5);
    }
    
    @FXML
    private void handelButton6Action(ActionEvent event) {
        scatterchart.getData().add(series6);
    }
      @FXML
    private void handelButton7Action(ActionEvent event) {
        scatterchart.getData().add(series7);
    }
     @FXML
    private void handelButton8Action(ActionEvent event) {
        scatterchart.getData().add(series8);
    }

  

  
   

    @FXML
    private void pickStartDate(ActionEvent event) {
    }

    @FXML
    private void pickEndDate(ActionEvent event) {
    }
    
    @FXML
    private void freq1(MouseEvent event) {
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


  

  

   

  


}
