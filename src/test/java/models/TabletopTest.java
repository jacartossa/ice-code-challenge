package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TabletopTest {
    private Tabletop testTabletop;

    @BeforeEach
    public void setup() {
        final Coordinate minCoordinate = new Coordinate(0,0);
        final Coordinate maxCoordinate = new Coordinate(2,2);
        testTabletop = new Tabletop(minCoordinate, maxCoordinate);
    }

    @Test
    public void given_tabletop_when_locationInBounds_then_returnTrue() {
        final Coordinate inBoundsCoordinate = new Coordinate(1,1);
        assertTrue(testTabletop.isInBounds(inBoundsCoordinate));
    }

    @Test
    public void given_tabletop_when_locationXTooBig_then_returnFalse() {
        final Coordinate inBoundsCoordinate = new Coordinate(3,1);
        assertFalse(testTabletop.isInBounds(inBoundsCoordinate));
    }

    @Test
    public void given_tabletop_when_locationXTooSmall_then_returnFalse() {
        final Coordinate inBoundsCoordinate = new Coordinate(-1,1);
        assertFalse(testTabletop.isInBounds(inBoundsCoordinate));
    }

    @Test
    public void given_tabletop_when_locationYTooBig_then_returnFalse() {
        final Coordinate inBoundsCoordinate = new Coordinate(1,3);
        assertFalse(testTabletop.isInBounds(inBoundsCoordinate));
    }

    @Test
    public void given_tabletop_when_locationYTooSmall_then_returnFalse() {
        final Coordinate inBoundsCoordinate = new Coordinate(1,-1);
        assertFalse(testTabletop.isInBounds(inBoundsCoordinate));
    }
}
