package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BarChart extends Chart{
    ArrayList<Bar> point=new ArrayList<>();
    private javafx.scene.chart.BarChart<Number,String > barChart;

    public BarChart(String title, String xAxisLabel) {
        super(title, xAxisLabel);
    }

    @Override
    public void setCaption(String caption) {
        this.barChart.setTitle(caption);
    }

    public ArrayList<Bar> getPoint() {
        return point;
    }

    public void setPoint(ArrayList<Bar> point) {
        this.point = point;
    }

    public javafx.scene.chart.BarChart<Number, String> getBarChart() {
        return barChart;
    }

    public void setBarChart(javafx.scene.chart.BarChart<Number, String> barChart) {
        this.barChart = barChart;
    }

    public Map<String, List<Bar>> loadData() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(Controller.url);

        if(getFileExtension(file).equals("xml")){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(Controller.url));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("record");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String category=eElement.getElementsByTagName("field").item(1).getTextContent();
                    String x=eElement.getElementsByTagName("field").item(2).getTextContent();
                    String y= eElement.getElementsByTagName("field").item(3).getTextContent();
                    point.add(new Bar(x,category,Integer.parseInt(y)));
                }
            }
            Map<String, List<Bar>> studlistGrouped =
                    point.stream().collect(Collectors.groupingBy(w -> w.getCategory()));
            return studlistGrouped;
        }else{
            FileReader input = new FileReader(Controller.url);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine = null;

            while ( (myLine = bufRead.readLine()) != null)
            {
                String[] array1 = myLine.split(",");
                // check to make sure you have valid data
                if(array1.length>2){
                    String category=array1[1];
                    String x=array1[0];
                    String y= array1[3];
                    point.add(new Bar(x,category,Integer.parseInt(y)));

                }
            }
            Map<String, List<Bar>> studlistGrouped =
                    point.stream().collect(Collectors.groupingBy(w -> w.getCategory()));
            return studlistGrouped;
        }


    }
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    @Override
    public void reset() {
        super.reset();
        this.barChart.getData().clear();
    }
}
