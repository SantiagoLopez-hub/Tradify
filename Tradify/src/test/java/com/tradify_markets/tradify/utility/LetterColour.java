package com.tradify_markets.tradify.utility;

public enum LetterColour {
    GREEN("\u001B[32m"),
    RESET("\u001B[0m");

    private final String colour;

    LetterColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
