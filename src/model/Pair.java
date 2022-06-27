package model;

public class Pair implements Comparable<Pair> {
    public String name;
    public int points;

    public Pair(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public int compareTo(Pair o1) {
        return name.compareTo(o1.name);
    }

    public int getPoints() {
        return this.points;
    }

    public void updatePoints(int p) {
        this.points += p;
    }
}
