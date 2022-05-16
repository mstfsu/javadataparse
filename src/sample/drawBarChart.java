package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class drawBarChart implements Initializable {
    @FXML
    private javafx.scene.chart.BarChart<Number, String> barChart;
    ArrayList<XYChart.Series> serie = new ArrayList<>();
    Timeline tl;
    BarChart l;
    int i = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(300),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            barChart.getData().clear();
                            barChart.layout();
                            LoadData(i);
                            i++;
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        }
                    }
                }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.setAutoReverse(true);
        tl.play();


    }

    private void LoadData(int i) throws ParserConfigurationException, IOException, SAXException {
        barChart.getData().clear();
        barChart.layout();
        barChart.setBarGap(10.0);
        barChart.setAnimated(false);
        barChart.getXAxis().setTickLabelRotation(90);
         l = new BarChart("Bar Chart", "Ülkeler");
        // l.setCaption(l.getTitle());
        barChart.setTitle(l.getTitle());

        l.setBarChart(barChart);
        Map<String, List<Bar>> a = l.loadData();
        ArrayList<String> keySet = new ArrayList<>();
        a.keySet()
                .iterator()
                .forEachRemaining(key -> keySet.add(key));
        serie.clear();
        for (int b = 0; b < keySet.size(); b++) {
            if (a.get(keySet.get(b)).size() > i) {
                XYChart.Series series = new XYChart.Series();
                series.setName(a.get(keySet.get(b)).get(i).getCategory());
                a.get(keySet.get(b)).get(i).compareTo(a.get(keySet.get(b)).get(i));
                series.getData().add(new XYChart.Data(a.get(keySet.get(b)).get(i).getValue(), a.get(keySet.get(b)).get(i).getCategory()));
                serie.add(series);
                barChart.setTitle(a.get(keySet.get(b)).get(i).getName());
            }
        }
        for (int c = 0; c < serie.size(); c++) {
            barChart.getData().addAll(serie.get(c));
        }
        barChart.getData().sort(Comparator.comparingInt(d -> d.getData().get(0).getXValue().intValue()));
    }

    public void goMain(ActionEvent actionEvent) throws Exception {
        tl.stop();
        Parent tableview = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(tableview, 800, 400);

        Stage window = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    public void stop(ActionEvent actionEvent) throws Exception {
        tl.stop();
    }

    public void start(ActionEvent actionEvent) throws Exception {
        tl.play();
    }

    public void restart(ActionEvent actionEvent) throws Exception {
        l.reset();
        tl.stop();
        i = 0;
        tl.play();
    }
}
