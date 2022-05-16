package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javafx.fxml.FXML;


public class Controller {
    @FXML
    TextField urlText;
    ArrayList<Line> point= new ArrayList<>();
    static Stage window;
    static Scene scene;
    static String url;
    public void goLineChart(ActionEvent actionEvent) throws Exception {
        url = urlText.getText();
        Parent tableview = FXMLLoader.load(getClass().getResource("lineChart.fxml"));
        Scene scene=new Scene(tableview,1500,1000);

        Stage window=(Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
    public void goBarChart(ActionEvent actionEvent) throws Exception {
        url = urlText.getText();
        Parent tableview = FXMLLoader.load(getClass().getResource("barChart.fxml"));
        Scene scene=new Scene(tableview,1500,1000);

        Stage window=(Stage)((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }
}
