package application;

import errors.RoverDoesNotExistException;
import errors.RoverOutOfBoundsException;
import models.Coordinate;
import models.Direction;
import models.Rotation;
import models.Rover;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoonRoverApplicationTest {
    private MoonRoverApplication application;
    private Coordinate roverLocation;

    @BeforeEach
    public void setup() {
        application = new MoonRoverApplication();
        roverLocation = new Coordinate(0,3);
    }

    @Test
    public void when_placeRoverOnTabletop_then_roverExists() {
        application.placeRover(roverLocation.getX(), roverLocation.getY(), Direction.EAST);

        final Rover resultRover = application.getRover();
        assertEquals(Direction.EAST, resultRover.getDirection());
        assertEquals(roverLocation, resultRover.getLocation());
    }

    @Test
    public void given_rover_when_turned_then_directionUpdated() {
        application.placeRover(roverLocation.getX(), roverLocation.getY(), Direction.EAST);
        application.turnRover(Rotation.RIGHT);

        final Rover resultRover = application.getRover();
        assertEquals(Direction.SOUTH, resultRover.getDirection());
        assertEquals(roverLocation, resultRover.getLocation());
    }

    @Test
    public void given_rover_when_moved_then_locationUpdated() {
        application.placeRover(roverLocation.getX(), roverLocation.getY(), Direction.EAST);
        application.moveRover();

        final Rover resultRover = application.getRover();
        final Coordinate expectedCoordinate = new Coordinate(1,3);
        assertEquals(Direction.EAST, resultRover.getDirection());
        assertEquals(expectedCoordinate, resultRover.getLocation());
    }

    @Test
    public void when_placeRoverOffTabletop_then_roverOutOfBoundsException() {
        Assertions.assertThrows(RoverOutOfBoundsException.class, () -> {
            application.placeRover(-1, roverLocation.getY(), Direction.EAST);
        });
    }

    @Test
    public void given_noRover_when_moved_then_roverDoesNotExistException() {
        Assertions.assertThrows(RoverDoesNotExistException.class, () -> {
            application.moveRover();
        });
    }

    @Test
    public void given_noRover_when_retrieved_then_roverDoesNotExistException() {
        Assertions.assertThrows(RoverDoesNotExistException.class, () -> {
            application.getRover();
        });
    }

    @Test
    public void given_noRover_when_turned_then_roverDoesNotExistException() {
        Assertions.assertThrows(RoverDoesNotExistException.class, () -> {
            application.turnRover(Rotation.RIGHT);
        });
    }
}
