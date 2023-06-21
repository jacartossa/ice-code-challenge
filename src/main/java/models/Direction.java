package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
public enum Direction {
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    @Getter
    private final int degrees;

    public static Optional<Direction> fromDegrees(final int degrees) {
        final Optional<Direction> result = Arrays.stream(values())
                .filter(direction -> direction.degrees == degrees)
                .findFirst();
        return result;
    }
}
