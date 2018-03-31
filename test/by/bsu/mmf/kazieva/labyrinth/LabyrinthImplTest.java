package by.bsu.mmf.kazieva.labyrinth;

import org.junit.Test;

import static org.junit.Assert.*;


public class LabyrinthImplTest {
    @Test
    public void findMinimumTime() throws Exception {
        Labyrinth labyrinth = new LabyrinthImpl();
        assertEquals(60, labyrinth.findMinimumTime());
    }
    @Test
    public void findMinimumTime1() throws Exception {
        Labyrinth labyrinth = new LabyrinthImpl();
        assertEquals(35, labyrinth.findMinimumTime("input-1"));
    }
}