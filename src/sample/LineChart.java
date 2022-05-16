package sample;

import javafx.animation.Timeline;
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
import java.util.*;
import java.util.stream.Collectors;

public class LineChart extends Chart {

    ArrayList<Line> point=new ArrayList<>();
    private javafx.scene.chart.LineChart<String ,Number> lineChart;
    public LineChart(String title, String xAxisLabel) {
        super(title, xAxisLabel);
    }

    @Override
    public void setCaption(String caption) {
        super.setCaption(caption);
        this.lineChart.setTitle(caption);
    }

    public javafx.scene.chart.LineChart<String, Number> getLineChart() {
        return lineChart;
    }

    public void setLineChart(javafx.scene.chart.LineChart<String, Number> lineChart) {
        this.lineChart = lineChart;
    }

    public Map<String, List<Line>> loadData() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(Controller.url);

        if(getFileExtension(file).equals("xml")){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(Controller.url));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("record");
            //NodeList nList2 = document.getElementsByTagName("title");
            //NodeList nList3 = document.getElementsByTagName("xlabel");
            //String title=nList2.item(0).getTextContent();
            //String lableName=nList3.item(0).getTextContent();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String category=eElement.getElementsByTagName("field").item(1).getTextContent();
                    String x=eElement.getElementsByTagName("field").item(2).getTextContent();
                    String y= eElement.getElementsByTagName("field").item(3).getTextContent();
                    point.add(new Line(x,category,Integer.parseInt(y)));
                }
            }
            Map<String, List<Line>> studlistGrouped =
                    point.stream().collect(Collectors.groupingBy(w -> w.getCategory()));
            return studlistGrouped;
        }else {
            FileReader input = new FileReader(Controller.url);
            BufferedReader bufRead = new BufferedReader(input);
            String myLine = null;

            while ( (myLine = bufRead.readLine()) != null)
            {
                String[] array1 = myLine.split(",");
                // check to make sure you have valid data
                if(Arrays.stream(array1).count()>1){
                String category=array1[1];
                String x=array1[0];
                String y= array1[3];

                point.add(new Line(x,category,Integer.parseInt(y)));

                }
            }
            Map<String, List<Line>> studlistGrouped =
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
        this.lineChart.getData().clear();
    }

}
