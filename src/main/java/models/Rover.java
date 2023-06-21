package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import errors.InvalidRotationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = { "tabletop" })
public class Rover {
    @NonNull
    @JsonProperty
    private Coordinate location;
    @NonNull
    @JsonProperty
    private Direction direction;
    @NonNull
    private Tabletop tabletop;

    public void rotate(final Rotation addedRotation) {
        direction = calculateNewDirection(addedRotation);
    }

    public void move() {
        final Coordinate newLocation = getNewLocation();
        if(tabletop.isInBounds(newLocation)) {
            location = newLocation;
            log.info("Moving rover to newLocation: {}", newLocation.toString());
        } else {
            log.warn("Movement to newLocation: {} is invalid", newLocation.toString());
        }
    }

    protected Direction calculateNewDirection(final Rotation addedRotation) {
        final int newRotation = (direction.getDegrees() + addedRotation.getDegrees()) % 360;
        final Optional<Direction> newDirection = Direction.fromDegrees(newRotation);
        if(newDirection.isEmpty()) {
            throw new InvalidRotationException("Not supported rotation");
        }
        log.info("New direction: {} calculated from rotation: {}", newDirection.get(), addedRotation);
        return newDirection.get();
    }

    private Coordinate getNewLocation() {
        final Coordinate roverMovement = new Coordinate(
                (int) Math.sin(Math.toRadians(direction.getDegrees())),
                (int) Math.cos(Math.toRadians(direction.getDegrees())));
        return Coordinate.add(location, roverMovement);
    }
}
