package model;

public class Fecha {
    public int day;
    public int month;
    public int year;

    public Fecha(int day, int month, int age) {
        this.day = day;
        this.month = month;
        this.year = age;
    }

    @Override
    public String toString() {
        return day + "-" + month + "-" + year;
    }

    public String toStringHuman() {
        return day + " de " + utils.Utils.nombreMes(month) + " del " + year;
    }
}
