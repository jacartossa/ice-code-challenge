package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tabletop {
    private final Coordinate minDimension;
    private final Coordinate maxDimension;

    public Boolean isInBounds(final Coordinate location) {
        return location.getX() >= minDimension.getX() &&
               location.getX() < maxDimension.getX() &&
               location.getY() >= minDimension.getY() &&
               location.getY() < maxDimension.getY();
    }
}