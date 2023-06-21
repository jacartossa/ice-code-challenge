package models;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DirectionTest {
    @Test
    public void when_directionFromDegreesExists_then_getEnum() {
        final Optional<Direction> testDirection = Direction.fromDegrees(90);
        assertFalse(testDirection.isEmpty());
        assertEquals(Direction.EAST, testDirection.get());
    }

    @Test
    public void when_directionFromDegreesNotExists_then_getEmptyOptional() {
        final Optional<Direction> testDirection = Direction.fromDegrees(91);
        assertTrue(testDirection.isEmpty());
    }
}
