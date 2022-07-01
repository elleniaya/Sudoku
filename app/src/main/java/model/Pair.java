package model;

public class Pair {
    public String name;
    public int points;

    public Pair(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public void updatePoints(int p) {
        this.points += p;
    }
}
