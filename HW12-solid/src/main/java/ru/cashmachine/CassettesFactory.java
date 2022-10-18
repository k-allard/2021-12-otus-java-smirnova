package ru.cashmachine;

import java.util.LinkedList;
import java.util.List;

public class CassettesFactory {
    public List<Cassette> createCassettes() {
        List<Cassette> cassettes = new LinkedList<>();
        for (Denomination denomination : Denomination.values()) {
            cassettes.add(new Cassette(denomination));
        }
        return cassettes;
    }
}
