package pl.bykowski.homework3.model;

public class Car {

    private long id;
    private String name;
    private String model;
    private String color;

    public Car() {
    }

    public Car(long id, String name, String model, String color) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
