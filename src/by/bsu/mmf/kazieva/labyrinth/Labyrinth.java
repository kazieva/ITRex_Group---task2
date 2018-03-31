package by.bsu.mmf.kazieva.labyrinth;

public interface Labyrinth {
    int TIME = 5;
    int FIRST_LINE = 0;
    int POSITION_OF_HEIGHT = 0;
    int POSITION_OF_WIDTH = 1;
    int POSITION_OF_LENGTH = 2;
    int PRINCE = -10;
    int PRINCESS = -100;
    int COLUMN = -1;
    int FREE_SPACE = 0;

    int findMinimumTime();
    int findMinimumTime(String fileName);
}
