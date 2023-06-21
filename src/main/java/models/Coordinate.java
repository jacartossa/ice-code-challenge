package models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Coordinate {
    @Getter
    private final int x;
    @Getter
    private final int y;

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Coordinate)) {
            return false;
        }

        final Coordinate c = (Coordinate) o;
        return c.x == this.x && c.y == this.y;
    }

    @Override
    public String toString() {
        return String.format("x:%s, y:%s", x, y);
    }

    public static Coordinate add(Coordinate first, Coordinate second) {
        int newX = first.getX() + second.getX();
        int newY = first.getY() + second.getY();
        return new Coordinate(newX, newY);
    }
}
