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
import java.util.Set;
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
import javafx.scene.Node;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
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

    File selectedFile = null;

    private SequentialTransition animation1, animation2, animation3, animation4, animation5, animation6, animation7, animation8;

    int currentSlider1, currentSlider2, currentSlider3, currentslider4, currentslider5, currentslider6, currentslider7, currentslider8;

    int f, m, n, o, p, q, r, s;
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
    private Button buttonChannel1;
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

    public static String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
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
        Set<Node> nodes = scatterchart.lookupAll(".series1");
        Color d = colorPicker2.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    void changecolor3() {
        Set<Node> nodes = scatterchart.lookupAll(".series2");
        Color d = colorPicker3.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    void changecolor4() {
        Set<Node> nodes = scatterchart.lookupAll(".series3");
        Color d = colorPicker4.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    void changecolor5() {
        Set<Node> nodes = scatterchart.lookupAll(".series4");
        Color d = colorPicker5.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    void changecolor6() {
        Set<Node> nodes = scatterchart.lookupAll(".series5");
        Color d = colorPicker6.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    void changecolor7() {
        Set<Node> nodes = scatterchart.lookupAll(".series6");
        Color d = colorPicker7.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    void changecolor8() {
        Set<Node> nodes = scatterchart.lookupAll(".series7");
        Color d = colorPicker8.getValue();
        String newColor = "-fx-background-color: " + toRGBCode(d);
        for (Node n : nodes) {
            n.setStyle(newColor);
        }
    }

    public void createAnimation1() {
         zoom();
        Timeline timeline1 = new Timeline();
        timeline1.getKeyFrames().add(new KeyFrame(Duration.millis(slider1.getValue()), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                series1.getData().add(new ScatterChart.Data(f, channel1list.get(f)));
                changecolor1();
                f++;
            }
        }));
        System.out.println("slider1:" + slider1.getValue());
        timeline1.setCycleCount(Animation.INDEFINITE);
        animation1 = new SequentialTransition();
        animation1.getChildren().add(timeline1);

    }

    public void createAnimation2() {
          zoom();
        Timeline timeline2 = new Timeline();
        timeline2.getKeyFrames().add(
                new KeyFrame(Duration.millis(slider2.getValue()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series2.getData().add(new ScatterChart.Data(m, channel2list.get(m)));
                        changecolor2();
                        m++;
                    }
                })
        );

        timeline2.setCycleCount(Animation.INDEFINITE);
        animation2 = new SequentialTransition();
        animation2.getChildren().add(timeline2);
    }

    public void createAnimation3() {
          zoom();
        Timeline timeline3 = new Timeline();
        timeline3.getKeyFrames().add(
                new KeyFrame(Duration.millis(slider3.getValue()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series3.getData().add(new ScatterChart.Data(n, channel3list.get(n)));
                        changecolor3();
                        n++;
                    }
                })
        );
        timeline3.setCycleCount(Animation.INDEFINITE);
        animation3 = new SequentialTransition();
        animation3.getChildren().add(timeline3);
    }

    public void createAnimation4() {
          zoom();
        Timeline timeline4 = new Timeline();
        timeline4.getKeyFrames().add(
                new KeyFrame(Duration.millis(slider4.getValue()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series4.getData().add(new ScatterChart.Data(o, channel4list.get(o)));
                        changecolor4();
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

    public void createAnimation5() {
          zoom();
        Timeline timeline5 = new Timeline();
        timeline5.getKeyFrames().add(
                new KeyFrame(Duration.millis(slider5.getValue()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series5.getData().add(new ScatterChart.Data(p, channel5list.get(p)));
                        changecolor5();
                        p++;

                    }
                })
        );

        timeline5.setCycleCount(Animation.INDEFINITE);
        animation5 = new SequentialTransition();
        animation5.getChildren().addAll(timeline5);

    }

    public void createAnimation6() {
          zoom();
        Timeline timeline6 = new Timeline();
        timeline6.getKeyFrames().add(
                new KeyFrame(Duration.millis(slider6.getValue()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series6.getData().add(new ScatterChart.Data(q, channel6list.get(q)));
                        changecolor6();
                        q++;
                    }
                })
        );

        timeline6.setCycleCount(Animation.INDEFINITE);
        animation6 = new SequentialTransition();
        animation6.getChildren().addAll(timeline6);
    }

    public void createAnimation7() {
          zoom();
        Timeline timeline7 = new Timeline();
        timeline7.getKeyFrames().add(
                new KeyFrame(Duration.millis(slider7.getValue()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series7.getData().add(new ScatterChart.Data(r, channel7list.get(r)));
                        changecolor7();
                        r++;
                    }
                })
        );
        timeline7.setCycleCount(Animation.INDEFINITE);
        animation7 = new SequentialTransition();
        animation7.getChildren().add(timeline7);
    }

    public void createAnimation8() {
          zoom();
        Timeline timeline8 = new Timeline();
        timeline8.getKeyFrames().add(
                new KeyFrame(Duration.millis(slider8.getValue()), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        series8.getData().add(new ScatterChart.Data(s, channel8list.get(s)));
                        changecolor8();
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
    private void handelSlider1Action(MouseEvent event) {
        slider1.setMin(1000);
        slider1.setMax(3000);
        slider1.setShowTickLabels(true);
        slider1.setShowTickMarks(true);
        slider1.setMajorTickUnit(50);
        slider1.setMinorTickCount(50);
        slider1.setBlockIncrement(1000);
        Label label1 = new Label();
        Pane thumb1 = (Pane) slider1.lookup(".thumb");
        label1.textProperty().bind(slider1.valueProperty().asString("%.1f"));
        thumb1.getChildren().add(label1);

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

    }

}
