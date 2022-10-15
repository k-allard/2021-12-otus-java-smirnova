package ru.cashmachine;

import java.util.LinkedList;

public class CassettesFactory {
    public LinkedList<Cassette> createCassettes() {
        LinkedList<Cassette> cassettes = new LinkedList<>();
        for (Denomination denomination : Denomination.values()) {
            cassettes.add(new Cassette(denomination));
        }
        return cassettes;
    }
}
