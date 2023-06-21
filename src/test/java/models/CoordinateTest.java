package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CoordinateTest {
    @Test
    public void when_coordinatesSameValues_then_equals() {
        final Coordinate firstCoordinate = new Coordinate(0,0);
        final Coordinate secondCoordinate = new Coordinate(0,0);
        assertEquals(firstCoordinate, secondCoordinate);
    }

    @Test
    public void when_coordinatesDifferentValues_then_notEquals() {
        final Coordinate firstCoordinate = new Coordinate(0,0);
        final Coordinate secondCoordinate = new Coordinate(1,0);
        assertNotEquals(firstCoordinate, secondCoordinate);
    }

    @Test
    public void when_addTwoCoordinates_then_validateResult() {
        final Coordinate firstCoordinate = new Coordinate(1,0);
        final Coordinate secondCoordinate = new Coordinate(2,1);
        final Coordinate expectedCoordinate = new Coordinate(3,1);

        final Coordinate result = Coordinate.add(firstCoordinate, secondCoordinate);
        assertEquals(expectedCoordinate, result);
    }
}
