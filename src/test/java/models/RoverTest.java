package models;

import errors.InvalidRotationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoverTest {

    private Rover testRover;
    @Mock
    private Rotation rotation;
    @Mock
    private Tabletop tabletop;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        final Coordinate startCoordinate = new Coordinate(0,0);
        testRover = new Rover(startCoordinate, Direction.NORTH, tabletop);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    public void given_rover_when_rotatedRight_then_updateDirection() {
        testRover.rotate(Rotation.RIGHT);
        assertEquals(Direction.EAST, testRover.getDirection());
    }

    @Test
    public void given_rover_when_rotatedLeft_then_updateDirection() {
        testRover.rotate(Rotation.LEFT);
        assertEquals(Direction.WEST, testRover.getDirection());
    }

    @Test
    public void given_rover_when_move_then_updateCoordinate() {
        final Coordinate expectedCoordinate = new Coordinate(0, 1);
        when(tabletop.isInBounds(expectedCoordinate)).thenReturn(true);
        testRover.move();

        assertEquals(expectedCoordinate, testRover.getLocation());
    }

    @Test
    public void given_rover_when_movedOffTabletop_then_noMovement() {
        final Coordinate expectedCoordinate = new Coordinate(0, 1);
        final Coordinate outOfBoundsCoordinate = new Coordinate(0, 2);
        when(tabletop.isInBounds(expectedCoordinate)).thenReturn(true);
        when(tabletop.isInBounds(outOfBoundsCoordinate)).thenReturn(false);

        testRover.move();
        assertEquals(expectedCoordinate, testRover.getLocation());
        testRover.move();
        assertEquals(expectedCoordinate, testRover.getLocation());
    }

    @Test
    public void given_rover_when_badRotationValue_then_invalidRotationException() {
        when(rotation.getDegrees()).thenReturn(91);
        Assertions.assertThrows(InvalidRotationException.class, () -> {
            testRover.rotate(rotation);
        });
    }
}
