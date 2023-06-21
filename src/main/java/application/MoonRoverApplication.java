package application;

import errors.RoverDoesNotExistException;
import errors.RoverOutOfBoundsException;
import lombok.extern.log4j.Log4j2;
import models.Coordinate;
import models.Direction;
import models.Rotation;
import models.Rover;
import models.Tabletop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Log4j2
public class MoonRoverApplication {
    private Rover rover;
    private final Coordinate minTabletop = new Coordinate(0,0);
    private final Coordinate maxTabletop = new Coordinate(5,5);
    private final Tabletop tabletop = new Tabletop(minTabletop, maxTabletop);

    public static void main(String[] args) {
        SpringApplication.run(MoonRoverApplication.class, args);
    }

    @PutMapping("/place")
    public void placeRover(@RequestParam final int x,
                           @RequestParam final int y,
                           @RequestParam final Direction direction) {
        log.info("Place rover call started with details x:{}, y:{}, direction:{}", x, y, direction);
        final Coordinate location = new Coordinate(x,y);

        if(!tabletop.isInBounds(location)) {
            final String errorMessage = String.format("Rover was placed off tabletop in location:%s", location);
            log.error(errorMessage);
            throw new RoverOutOfBoundsException(errorMessage);
        }

        rover = new Rover(location, direction, tabletop);
        log.info("Successful place rover call with location {}, direction:{}", location, direction);
    }

    @PostMapping("/turn")
    public void turnRover(@RequestParam final Rotation rotation) {
        log.info("Turn rover call started with rotation:{}", rotation);
        if(rover == null) {
            final String errorMessage = "No rover available to turn";
            log.error(errorMessage);
            throw new RoverDoesNotExistException(errorMessage);
        }
        rover.rotate(rotation);
        log.info("Successful turn rover call with rotation:{}", rotation);
    }

    @PostMapping("/move")
    public void moveRover() {
        log.info("Move rover call started");
        if(rover == null) {
            final String errorMessage = "No rover available to move";
            log.error(errorMessage);
            throw new RoverDoesNotExistException(errorMessage);
        }
        rover.move();
        log.info("Successful move rover call");
    }

    @GetMapping("/report")
    @ResponseBody
    public Rover getRover() {
        log.info("Get rover call started");
        if(rover == null) {
            final String errorMessage = "No rover available to report";
            log.error(errorMessage);
            throw new RoverDoesNotExistException(errorMessage);
        }
        log.info("Successful get rover call");
        return rover;
    }

    //for testing
    @DeleteMapping("/delete")
    public void deleteRover() {
        log.info("Delete rover call started");
        rover = null;
        log.info("Successful delete rover call");
    }
}
