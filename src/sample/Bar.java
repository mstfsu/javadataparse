package sample;

public class Bar implements Comparable<Bar>{
    private String name;
    private String category;
    private int value;

    public String getName() {
        return name;
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

    public Bar(String name, String category, int value) {
        this.name = name;
        this.category = category;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Bar o) {
        if(this.value<o.value){
            return o.value;
        }else{
            return 0;
        }
    }


}
