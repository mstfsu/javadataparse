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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.animation.Animation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class drawChart implements Initializable {
    @FXML
    private javafx.scene.chart.LineChart<String,Number> chartLine;
    ArrayList<XYChart.Series> a=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            LoadData();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    private void LoadData() throws ParserConfigurationException, IOException, SAXException {
        chartLine.getData().clear();
        chartLine.setCreateSymbols(false);


        LineChart l=new LineChart("Line Chart","Ülkeler");
        chartLine.setTitle(l.getTitle());
        l.setLineChart(chartLine);
        l.setCaption("Ülkeler");
        Map<String, List<Line>> a=l.loadData();
        ArrayList<String> keySet=new ArrayList<>();
        a.keySet()
                .iterator()
                .forEachRemaining(key ->keySet.add(key));
        for (int i=0;i<keySet.size();i++){
            XYChart.Series<String,Number> series = new XYChart.Series<String, Number>();
            for (int j=0 ; j< a.get(keySet.get(i)).size();j++){
                //a.get(keySet.get(i)).get(j).nextValue(series);
                series.getData().add(new XYChart.Data<String, Number>(a.get(keySet.get(i)).get(j).getName(),a.get(keySet.get(i)).get(j).getValue()));
            }
            series.setName(keySet.get(i));
            chartLine.getData().addAll(series);
        }

    }
    public void goMain(ActionEvent actionEvent) throws Exception {
        Parent tableview = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene=new Scene(tableview,800,400);

        Stage window=(Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
}
