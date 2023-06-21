package application;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Coordinate;
import models.Direction;
import models.Rover;
import models.Tabletop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MoonRoverApplicationIntegrationTest {
    @Autowired
    private MockMvc mvc;
    private ObjectMapper mapper = new ObjectMapper();
    private final Tabletop tabletop = new Tabletop(new Coordinate(0,0), new Coordinate(5,5));

    @BeforeEach
    public void setup() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/delete"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_roverMovementOffTable_then_roverStays() throws Exception {
        final Coordinate expectedCoordinate = new Coordinate(0,3);
        final Rover expectedRover = new Rover(expectedCoordinate, Direction.WEST, tabletop);

        mvc.perform(MockMvcRequestBuilders.put("/place")
                .param("x", "0")
                .param("y", "3")
                .param("direction", "WEST"))
            .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/report"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(expectedRover))));

        mvc.perform(MockMvcRequestBuilders.post("/move"))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/report"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(expectedRover))));
    }

    @Test
    public void when_roverMakesMovementWithRotation_then_roverUpdatesLocation() throws Exception {
        final Coordinate expectedCoordinate = new Coordinate(0,2);
        final Rover expectedRover = new Rover(expectedCoordinate, Direction.SOUTH, tabletop);

        mvc.perform(MockMvcRequestBuilders.put("/place")
                .param("x", "0")
                .param("y", "3")
                .param("direction", "WEST"))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/turn")
                .param("rotation", "LEFT"))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/move"))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders.get("/report"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(mapper.writeValueAsString(expectedRover))));
    }

    @Test
    public void when_roverPlacedOffTable_then_expectationFailed() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/place")
                .param("x", "-1")
                .param("y", "0")
                .param("direction", "WEST"))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void when_roverMovementBeforePlace_then_notFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/move"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void when_roverGetBeforePlace_then_notFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/report"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void when_roverTurnBeforePlace_then_notFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/turn")
                .param("rotation", "LEFT"))
                .andExpect(status().isNotFound());
    }
}
