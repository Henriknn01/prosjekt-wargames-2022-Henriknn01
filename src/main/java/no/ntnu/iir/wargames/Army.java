package no.ntnu.iir.wargames;


import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Army {

    private String name;
    private List<Unit> units;

    public Army(String name, List<Unit> units) {
        this.name = name;
        this.units = units;
    }

    public Army(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Unit unit) {
        this.units.add(unit);
    }

    public void addAll(List<Unit> units) {
        this.units.addAll(units);
    }

    public void remove(Unit unit) {
        this.units.remove(unit);
    }

    public boolean hasUnits() {
        return this.units.size() > 0;
    }

    public List<Unit> getAllUnits() {
        return this.units;
    }

    public Unit getRandom() {
        Random randomNumb = new Random();
        return this.units.get(
                randomNumb.nextInt(this.units.size())
        );
    }

    @Override
    public String toString() {
        return "Army{" +
                "name='" + name + '\'' +
                ", units=" + units +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return name.equals(army.name) && Objects.equals(units, army.units);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}

