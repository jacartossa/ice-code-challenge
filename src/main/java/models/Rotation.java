package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Rotation {
    RIGHT(90),
    LEFT(270);

    @Getter
    private final int degrees;
}
