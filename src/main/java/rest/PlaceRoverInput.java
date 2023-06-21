package rest;

import lombok.Data;
import models.Coordinate;
import models.Direction;

@Data
public class PlaceRoverInput {
    private Coordinate placeLocation;
    private Direction placeDirection;
}
