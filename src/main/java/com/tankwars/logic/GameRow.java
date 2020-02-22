package com.tankwars.logic;

import java.util.ArrayList;
import java.util.List;

public class GameRow {
    private List<GameElement> cols = new ArrayList<>();

    public GameRow() {
        for (int i = 0; i < 49; i++) {
            cols.add(new NoneElement());
        }
    }

    public List<GameElement> getCols() {
        return cols;
    }
}
