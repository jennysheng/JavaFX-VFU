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
    int currentSlider1, currentSlider2, currentSlider3, currentslider4, currentslider5, currentslider6, currentslider7, currentslider8;

    public int getCurrentSlider2() {
        return currentSlider2;
    }

    public void setCurrentSlider2(int currentSlider2) {
        this.currentSlider2 = currentSlider2;
    }

    public int getCurrentSlider3() {
        return currentSlider3;
    }

    public void setCurrentSlider3(int currentslider3) {
        this.currentSlider3 = currentslider3;
    }

    public int getCurrentslider4() {
        return currentslider4;
    }

    public void setCurrentslider4(int currentslider4) {
        this.currentslider4 = currentslider4;
    }

    public int getCurrentslider5() {
        return currentslider5;
    }

    public void setCurrentslider5(int currentslider5) {
        this.currentslider5 = currentslider5;
    }

    public int getCurrentslider6() {
        return currentslider6;
    }

    public void setCurrentslider6(int currentslider6) {
        this.currentslider6 = currentslider6;
    }

    public int getCurrentslider7() {
        return currentslider7;
    }

    public void setCurrentslider7(int currentslider7) {
        this.currentslider7 = currentslider7;
    }

    public int getCurrentslider8() {
        return currentslider8;
    }

    public void setCurrentslider8(int currentslider8) {
        this.currentslider8 = currentslider8;
    }

    public int getCurrentSlider1() {
        return currentSlider1;
    }

    public void setCurrentSlider1(int currentSlider1) {
        this.currentSlider1 = currentSlider1;
    }

    public void createAnimation1() {
        Timeline timeline1 = new Timeline();
        timeline1.getKeyFrames().add(
                new KeyFrame(Duration.millis(getCurrentSlider1()), new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series1.getData().add(new ScatterChart.Data(f, channel1list.get(f)));
                       // series1.getNode().setStyle(colorPicker1.getValue().toString());               

                        System.out.println("chartvalue:" + channel1list.get(f));
                        f++;

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
                new KeyFrame(Duration.millis(getCurrentSlider2()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series2.getData().add(new ScatterChart.Data(m, channel2list.get(m)));
                        m++;
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
                new KeyFrame(Duration.millis(getCurrentSlider3()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series3.getData().add(new ScatterChart.Data(n, channel3list.get(n)));
                        n++;
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
                new KeyFrame(Duration.millis(getCurrentslider4()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series4.getData().add(new ScatterChart.Data(o, channel4list.get(o)));
                        o++;
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

    public void createAnimation5() {
        Timeline timeline5 = new Timeline();
        timeline5.getKeyFrames().add(
                new KeyFrame(Duration.millis(getCurrentslider5()), new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series5.getData().add(new ScatterChart.Data(p, channel5list.get(p)));
                        p++;

                    }
                })
        );

        timeline5.setCycleCount(Animation.INDEFINITE);
        animation5 = new SequentialTransition();
        animation5.getChildren().addAll(timeline5);

    }

    public void createAnimation6() {
        Timeline timeline6 = new Timeline();
        timeline6.getKeyFrames().add(
                new KeyFrame(Duration.millis(getCurrentslider6()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series6.getData().add(new ScatterChart.Data(q, channel6list.get(q)));
                        q++;
                    }
                })
        );

        timeline6.setCycleCount(Animation.INDEFINITE);
        animation6 = new SequentialTransition();
        animation6.getChildren().addAll(timeline6);
    }

    public void createAnimation7() {
        Timeline timeline7 = new Timeline();
        timeline7.getKeyFrames().add(
                new KeyFrame(Duration.millis(getCurrentslider7()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series7.getData().add(new ScatterChart.Data(r, channel7list.get(r)));
                        r++;
                    }
                })
        );
        timeline7.setCycleCount(Animation.INDEFINITE);
        animation7 = new SequentialTransition();
        animation7.getChildren().add(timeline7);
    }

    public void createAnimation8() {
        Timeline timeline8 = new Timeline();
        timeline8.getKeyFrames().add(
                new KeyFrame(Duration.millis(getCurrentslider8()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series8.getData().add(new ScatterChart.Data(s, channel8list.get(s)));
                        s++;
                    }
                })
        );
        timeline8.setCycleCount(Animation.INDEFINITE);
        animation8 = new SequentialTransition();
        animation8.getChildren().add(timeline8);
    }

    @FXML
    private void handleButton5Action(ActionEvent event) {
        if (buttonChannel5.getText().equalsIgnoreCase("Channel 5")) {
            scatterchart.getData().addAll(series5);
            createAnimation5();
            animation5.play();
            series5.setName("Channel5");
            buttonChannel5.setText("StopChannel5");
        } else {
            scatterchart.getData().remove(series5);
            buttonChannel5.setText("Channel 5");
            animation5.stop();
        }
    }

    @FXML
    private void handleButton6Action(ActionEvent event) {
        if (buttonChannel6.getText().equalsIgnoreCase("Channel 6")) {
            scatterchart.getData().addAll(series6);
            createAnimation6();
            animation6.play();
            series6.setName("Channel6");
            buttonChannel6.setText("StopChannel6");
        } else {
            scatterchart.getData().remove(series6);
            buttonChannel6.setText("Channel 6");
            animation6.stop();
        }
    }

    @FXML
    private void handleButton7Action(ActionEvent event) {
        if (buttonChannel7.getText().equalsIgnoreCase("Channel 7")) {
            scatterchart.getData().addAll(series7);
            createAnimation7();
            animation7.play();
            series7.setName("Channel7");
            buttonChannel7.setText("StopChannel7");
        } else {
            scatterchart.getData().remove(series7);
            buttonChannel7.setText("Channel 7");
            animation7.stop();
        }
    }

    @FXML
    private void handleButton8Action(ActionEvent event) {
        if (buttonChannel8.getText().equalsIgnoreCase("Channel 8")) {
            scatterchart.getData().addAll(series8);
            createAnimation8();
            animation8.play();
            series8.setName("Channel8");
            buttonChannel8.setText("StopChannel8");
        } else {
            scatterchart.getData().remove(series8);
            buttonChannel8.setText("Channel 8");
            animation8.stop();
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
        slider1.setMax(2500);
        slider1.setShowTickLabels(true);
        slider1.setShowTickMarks(true);
        slider1.setMajorTickUnit(50);
        slider1.setMinorTickCount(50);
        slider1.setBlockIncrement(100);
        Label label = new Label();
        Pane thumb = (Pane) slider1.lookup(".thumb");
        label.textProperty().bind(slider1.valueProperty().asString("%.1f"));
        thumb.getChildren().add(label);
        setCurrentSlider1((int) slider1.getValue());
    }

    @FXML
    private void handelSlider2Action(MouseEvent event) {
        slider2.setMin(1000);
        slider2.setMax(2500);
        slider2.setShowTickLabels(true);
        slider2.setShowTickMarks(true);
        slider2.setMajorTickUnit(50);
        slider2.setMinorTickCount(50);
        slider2.setBlockIncrement(100);
        Label label2 = new Label();
        Pane thumb2 = (Pane) slider2.lookup(".thumb");
        label2.textProperty().bind(slider2.valueProperty().asString("%.1f"));
        thumb2.getChildren().add(label2);
        setCurrentSlider2((int) slider2.getValue());
    }

    @FXML
    private void handelSlider3Action(MouseEvent event) {
        slider3.setMin(1000);
        slider3.setMax(2500);
        slider3.setShowTickLabels(true);
        slider3.setShowTickMarks(true);
        slider3.setMajorTickUnit(50);
        slider3.setMinorTickCount(50);
        slider3.setBlockIncrement(100);
        Label label2 = new Label();
        Pane thumb2 = (Pane) slider3.lookup(".thumb");
        label2.textProperty().bind(slider3.valueProperty().asString("%.1f"));
        thumb2.getChildren().add(label2);
        setCurrentSlider3((int) slider3.getValue());
    }

    @FXML
    private void handelSlider4Action(MouseEvent event) {
        slider4.setMin(1000);
        slider4.setMax(2500);
        slider4.setShowTickLabels(true);
        slider4.setShowTickMarks(true);
        slider4.setMajorTickUnit(50);
        slider4.setMinorTickCount(50);
        slider4.setBlockIncrement(100);
        Label label2 = new Label();
        Pane thumb2 = (Pane) slider4.lookup(".thumb");
        label2.textProperty().bind(slider4.valueProperty().asString("%.1f"));
        thumb2.getChildren().add(label2);
        setCurrentslider4((int) slider4.getValue());
    }

    @FXML
    private void handelSlider5Action(MouseEvent event) {
        slider5.setMin(1000);
        slider5.setMax(2500);
        slider5.setShowTickLabels(true);
        slider5.setShowTickMarks(true);
        slider5.setMajorTickUnit(50);
        slider5.setMinorTickCount(50);
        slider5.setBlockIncrement(100);
        Label label = new Label();
        Pane thumb = (Pane) slider5.lookup(".thumb");
        label.textProperty().bind(slider5.valueProperty().asString("%.1f"));
        thumb.getChildren().add(label);
        setCurrentslider5((int) slider5.getValue());
    }

    @FXML
    private void handelSlider6Action(MouseEvent event) {
        slider6.setMin(1000);
        slider6.setMax(2500);
        slider6.setShowTickLabels(true);
        slider6.setShowTickMarks(true);
        slider6.setMajorTickUnit(50);
        slider6.setMinorTickCount(50);
        slider6.setBlockIncrement(100);
        Label label = new Label();
        Pane thumb = (Pane) slider6.lookup(".thumb");
        label.textProperty().bind(slider6.valueProperty().asString("%.1f"));
        thumb.getChildren().add(label);
        setCurrentslider6((int) slider6.getValue());
    }

    @FXML
    private void handelSlider7Action(MouseEvent event) {
        slider7.setMin(1000);
        slider7.setMax(2500);
        slider7.setShowTickLabels(true);
        slider7.setShowTickMarks(true);
        slider7.setMajorTickUnit(50);
        slider7.setMinorTickCount(50);
        slider7.setBlockIncrement(100);
        Label label = new Label();
        Pane thumb = (Pane) slider7.lookup(".thumb");
        label.textProperty().bind(slider7.valueProperty().asString("%.1f"));
        thumb.getChildren().add(label);
        setCurrentslider7((int) slider7.getValue());
    }

    @FXML
    private void handelSlider8Action(MouseEvent event) {
        slider8.setMin(1000);
        slider8.setMax(2500);
        slider8.setShowTickLabels(true);
        slider8.setShowTickMarks(true);
        slider8.setMajorTickUnit(50);
        slider8.setMinorTickCount(50);
        slider8.setBlockIncrement(100);
        Label label = new Label();
        Pane thumb = (Pane) slider8.lookup(".thumb");
        label.textProperty().bind(slider8.valueProperty().asString("%.1f"));
        thumb.getChildren().add(label);
        setCurrentslider8((int) slider8.getValue());
    }

}
