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
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 *
 * @author Jenny_2
 */
public class FXMLDocumentController implements Initializable {

    private ArrayList<ScatterChart.Series> scatterchartseries = new ArrayList<>();

    private ArrayList<Double> channel1list = new ArrayList<>();
    private ArrayList<Double> channel2list = new ArrayList<>();
    private ArrayList<Double> channel3list = new ArrayList<>();
    private ArrayList<Double> channel4list = new ArrayList<>();
    private ArrayList<Double> channel5list = new ArrayList<>();
    private ArrayList<Double> channel6list = new ArrayList<>();
    private ArrayList<Double> channel7list = new ArrayList<>();
    private ArrayList<Double> channel8list = new ArrayList<>();

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

    private SequentialTransition animation1, animation2, animation3, animation4, animation5, animation6, animation7, animation8;

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

    public void createChart() {

        getChannelsdata().stream().forEach((c) -> {
            channel1list.add(c.getValue1());
            channel2list.add(c.getValue2());
            channel3list.add(c.getValue3());
            channel4list.add(c.getValue4());
            channel5list.add(c.getValue5());
            channel6list.add(c.getValue6());
            channel7list.add(c.getValue7());
            channel8list.add(c.getValue8());

        });

    }
    int f, m, n, o, p, q, r, s;

    public void createAnimation1() {
        if (slider1.getValue() == 0.0) {
            slider1.setValue(1000);
        }
            Timeline timeline1 = new Timeline();
            timeline1.getKeyFrames().add(
                    new KeyFrame(Duration.millis(slider1.getValue()), new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            series1.getData().add(new ScatterChart.Data(f, channel1list.get(f)));
                            System.out.println("chartvalue:" + channel1list.get(f));

                            f++;
                            if (series1.getData().size() > 50) {
                                series1.getData().remove(0);
                            }
                            System.out.println("f" + f);

                        }
                    })
            );
            timeline1.setCycleCount(Animation.INDEFINITE);
            animation1 = new SequentialTransition();
            animation1.getChildren().addAll(timeline1);
        
    }

    public void createAnimation2() {
        Timeline timeline2 = new Timeline();
        timeline2.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series2.getData().add(new ScatterChart.Data(m, channel2list.get(m)));
                        System.out.println("chartvalue:" + channel2list.get(m));

                        m++;
                        if (series2.getData().size() > 50) {
                            series2.getData().remove(0);
                        }
                        System.out.println("m" + m);

                    }
                })
        );
        timeline2.setCycleCount(Animation.INDEFINITE);
        animation2 = new SequentialTransition();
        animation2.getChildren().add(timeline2);
    }

    public void createAnimation3() {
        Timeline timeline3 = new Timeline();
        timeline3.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series3.getData().add(new ScatterChart.Data(n, channel3list.get(n)));
                        System.out.println("chartvalue:" + channel3list.get(n));
                        n++;
                        if (series3.getData().size() > 50) {
                            series3.getData().remove(0);
                        }
                        System.out.println("n" + n);
                    }
                })
        );
        timeline3.setCycleCount(Animation.INDEFINITE);
        animation3 = new SequentialTransition();
        animation3.getChildren().add(timeline3);
    }

    public void createAnimation4() {
        Timeline timeline4 = new Timeline();
        timeline4.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series4.getData().add(new ScatterChart.Data(o, channel4list.get(o)));
                        System.out.println("chartvalue:" + channel4list.get(o));
                        o++;
                        if (series4.getData().size() > 50) {
                            series4.getData().remove(0);
                        }
                        System.out.println("o" + o);
                    }
                })
        );
        timeline4.setCycleCount(Animation.INDEFINITE);
        animation4 = new SequentialTransition();
        animation4.getChildren().add(timeline4);
    }

    @FXML
    private void handleButton1Action(ActionEvent event) {

        if (buttonChannel1.getText().equalsIgnoreCase("Channel 1")) {
            scatterchart.getData().add(series1);
            createAnimation1();
            animation1.play();
            series1.setName("Channel1");
            buttonChannel1.setText("StopChannel1");
        } else {

            scatterchart.getData().remove(series1);
            buttonChannel1.setText("Channel 1");
            animation1.stop();
        }

    }

    @FXML
    private void handleButton2Action(ActionEvent event) {
        if (buttonChannel2.getText().equalsIgnoreCase("Channel 2")) {
            scatterchart.getData().addAll(series2);
            createAnimation2();
            animation2.play();
            series2.setName("Channel2");
            buttonChannel2.setText("StopChannel2");
        } else {
            scatterchart.getData().remove(series2);
            buttonChannel2.setText("Channel 2");
            animation2.stop();
        }

    }

    @FXML
    private void handleButton3Action(ActionEvent event) {
        if (buttonChannel3.getText().equalsIgnoreCase("Channel 3")) {
            scatterchart.getData().addAll(series3);
            createAnimation3();
            animation3.play();
            series3.setName("Channel3");
            buttonChannel3.setText("StopChannel3");
        } else {
            scatterchart.getData().remove(series3);
            buttonChannel3.setText("Channel 3");
            animation3.stop();
        }
    }

    @FXML
    private void handleButton4Action(ActionEvent event) {
        if (buttonChannel4.getText().equalsIgnoreCase("Channel 4")) {
            scatterchart.getData().addAll(series4);
            createAnimation4();
            animation4.play();
            series4.setName("Channel4");
            buttonChannel4.setText("StopChannel4");
        } else {
            scatterchart.getData().remove(series4);
            buttonChannel4.setText("Channel 4");
            animation4.stop();
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

        slider1.setMin(1000);
        slider1.setMax(2000);
        slider1.setShowTickLabels(true);
        slider1.setShowTickMarks(true);
        slider1.setMajorTickUnit(50);
        slider1.setMinorTickCount(5);
        slider1.setBlockIncrement(100);
        Label label = new Label();

        Pane thumb = (Pane) slider1.lookup(".thumb");
        label.textProperty().bind(slider1.valueProperty().asString("%.1f"));
        //  label.textProperty().setValue(String.valueOf((int) slider1.getValue()));
        thumb.getChildren().add(label);
        System.out.println("slider1:" + slider1.getValue());
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
