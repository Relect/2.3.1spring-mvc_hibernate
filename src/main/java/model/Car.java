package model;

public class Car {
    private String name;
    private String mark;
    private int year;

    public Car(String name, String mark, int year) {
        this.name = name;
        this.mark = mark;
        this.year = year;
    }

    public String getName() { return name; }

    public String getMark() { return mark; }

    public int getYear() { return year; }
}
