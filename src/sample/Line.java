package sample;


import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Line {
    private String name;
    private String category;
    private int value;

    public String getName() {
        return name;
    }

    public Line(String name, String category, int value) {
        this.name = name;
        this.category = category;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getValue() {
        return value;
    }
    public int nextValue(XYChart.Series<String,Number> series){
     return  Integer.valueOf(series.getData().get(this.value+1).getXValue());
    }

    public void setValue(int value) {
        this.value = value;
    }
}
