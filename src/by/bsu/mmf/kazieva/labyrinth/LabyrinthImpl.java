package by.bsu.mmf.kazieva.labyrinth;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabyrinthImpl implements Labyrinth {
    private static final Logger logger = Logger.getLogger(LabyrinthImpl.class);

    private String inputFileName = "src/input/INPUT.txt";
    private int labyrinth[][][];
    private int labeledLabyrinth[][][];
    private int height;
    private int width;
    private int length;
    private int princeX;
    private int princeY;
    private int minWay = Integer.MAX_VALUE;


    public int findMinimumTime() {
        setLabyrinth();
        findPrince();
        findPrincess();
        findMinWay();
        return minWay * TIME;
    }

    public int findMinimumTime(String fileName) {
        inputFileName = "src/input/" + fileName + ".txt";
        return findMinimumTime();
    }


    private void setLabyrinth() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String line;
            List<String> rowsOfLabyrinth = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.length() != 0) {
                    rowsOfLabyrinth.add(line);
                }
            }
            reader.close();
            setHeightWidthLength(rowsOfLabyrinth.get(FIRST_LINE));
            rowsOfLabyrinth.remove(FIRST_LINE);
            setLabyrinthCells(rowsOfLabyrinth);

        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private void setLabyrinthCells(List<String> lines) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                CharSequence lineChar = lines.get(FIRST_LINE);
                for (int k = 0; k < length; k++) {
                    labyrinth[i][j][k] = convertLabyrinthCell(lineChar.charAt(k));
                }
                lines.remove(FIRST_LINE);
            }
        }

    }

    private void setHeightWidthLength(String str) {
        String firstLine[] = str.split(" ");
        this.height = Integer.parseInt(firstLine[POSITION_OF_HEIGHT]);
        this.width = Integer.parseInt(firstLine[POSITION_OF_WIDTH]);
        this.length = Integer.parseInt(firstLine[POSITION_OF_LENGTH]);
        this.labyrinth = new int[height][width][length];
        this.labeledLabyrinth = new int[height][width][length];
    }

    private int convertLabyrinthCell(char symbol) {
        if (symbol == '.') return FREE_SPACE;
        if (symbol == 'o') return COLUMN;
        if (symbol == '1') return PRINCE;
        if (symbol == '2') return PRINCESS;
        return Integer.MAX_VALUE;
    }

    private void findPrince() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (labyrinth[0][i][j] == PRINCE) {
                    labeledLabyrinth[0][i][j] = PRINCE;
                    princeX = j;
                    princeY = i;
                    return;
                }
            }
        }

    }

    private void findPrincess() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (labyrinth[height - 1][i][j] == PRINCESS) {
                    labeledLabyrinth[height - 1][i][j] = PRINCESS;
                    return;
                }
            }
        }
    }

    private void findMinWay() {
        checkNeighbors(0, princeY, princeX, 1);
        for (int iteration = 1; minWay == Integer.MAX_VALUE; iteration++) {
            findCell(iteration);
        }

    }

    private void findCell(int iteration) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < length; k++) {
                    if (labeledLabyrinth[i][j][k] == iteration) {
                        checkNeighbors(i, j, k, iteration + 1);
                    }
                }
            }
        }
    }

    private void checkNeighbors(int Z, int Y, int X, int iteration) {

        if (Y > 0) checkCell(Z, Y - 1, X, iteration);
        if (Y < width - 1) checkCell(Z, Y + 1, X, iteration);
        if (X > 0) checkCell(Z, Y, X - 1, iteration);
        if (X < length - 1) checkCell(Z, Y, X + 1, iteration);
        if (Z < height - 1) checkCell(Z + 1, Y, X, iteration);

    }

    private int checkCell(int z, int y, int x, int iteration) {
        if (isPrincess(z, y, x)) {
            if (iteration < minWay) {
                minWay = iteration;
            }
            return labeledLabyrinth[z][y][x] = PRINCESS;
        } else {

            if (isFreeSpace(z, y, x)) {
                return labeledLabyrinth[z][y][x] = iteration;
            } else {

                if (isColumn(z, y, x)) {
                    return labeledLabyrinth[z][y][x] = -1;
                } else {

                    if (isShorterWay(z, y, x, iteration)) {
                        return labeledLabyrinth[z][y][x] = iteration;
                    }
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    private boolean isPrincess(int z, int y, int x) {
        return labeledLabyrinth[z][y][x] == PRINCESS;
    }

    private boolean isFreeSpace(int z, int y, int x) {
        return (labyrinth[z][y][x] == 0 && labeledLabyrinth[z][y][x] == 0);
    }

    private boolean isColumn(int z, int y, int x) {
        return (labyrinth[z][y][x] == -1 && labeledLabyrinth[z][y][x] == 0);
    }

    private boolean isShorterWay(int z, int y, int x, int iteration) {
        return (labeledLabyrinth[z][y][x] > iteration && labeledLabyrinth[z][y][x] == 0);
    }


    private void printLabyrinth(int[][][] labyrinth) {

        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.println("");
                for (int k = 0; k < length; k++) {
                    System.out.print(labyrinth[i][j][k] + " ");
                }
            }
        }
        System.out.println();
    }
}
