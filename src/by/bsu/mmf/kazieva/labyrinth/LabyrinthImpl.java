package by.bsu.mmf.kazieva.labyrinth;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabyrinthImpl implements Labyrinth {
    private static String inputFileName = "src/input/INPUT.txt";
    private static final Logger logger = Logger.getLogger(LabyrinthImpl.class);

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
    public int findMinimumTime(String fileName ) {
        inputFileName="src/input/"+ fileName+".txt";
        setLabyrinth();
        findPrince();
        findPrincess();
        findMinWay();
        return minWay * TIME;
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
                CharSequence lineChar = lines.get(0);
                for (int k = 0; k < length; k++) {
                    labyrinth[i][j][k] = convertLabyrinthCell(lineChar.charAt(k));
                }
                lines.remove(FIRST_LINE);
            }
        }

    }

    private int convertLabyrinthCell(char symbol) {
        if (symbol == '.') return FREE_SPACE;
        if (symbol == 'o') return COLUMN;
        if (symbol == '1') return PRINCE;
        if (symbol == '2') return PRINCESS;
        return Integer.MAX_VALUE;
    }

    private void setHeightWidthLength(String str) {
        String firstLine[] = str.split(" ");
        this.height = Integer.parseInt(firstLine[POSITION_OF_HEIGHT]);
        this.width = Integer.parseInt(firstLine[POSITION_OF_WIDTH]);
        this.length = Integer.parseInt(firstLine[POSITION_OF_LENGTH]);
        this.labyrinth = new int[height][width][length];
        this.labeledLabyrinth = new int[height][width][length];
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

    private void checkNeighbors(int startZ, int startY, int startX, int iteration) {

        if (startY > 0) checkCell(startZ, startY - 1, startX, iteration);
        if (startY < width - 1) checkCell(startZ, startY + 1, startX, iteration);
        if (startX > 0) checkCell(startZ, startY, startX - 1, iteration);
        if (startX < length - 1) checkCell(startZ, startY, startX + 1, iteration);
        if (startZ < height - 1) checkCell(startZ + 1, startY, startX, iteration);

    }

    private int checkCell(int z, int y, int x, int iteration) {
        if (labeledLabyrinth[z][y][x] == PRINCESS) {
            if (iteration < minWay) {
                minWay = iteration;
            }
            return labeledLabyrinth[z][y][x] = PRINCESS;
        } else {
            if (labyrinth[z][y][x] == 0 && labeledLabyrinth[z][y][x] == 0) {
                return labeledLabyrinth[z][y][x] = iteration;
            } else {
                if (labyrinth[z][y][x] == -1 && labeledLabyrinth[z][y][x] == 0) {
                    return labeledLabyrinth[z][y][x] = -1;
                } else {
                    if (labeledLabyrinth[z][y][x] > iteration && labeledLabyrinth[z][y][x] == 0) {
                        return labeledLabyrinth[z][y][x] = iteration;
                    }
                }
            }
        }
        return Integer.MIN_VALUE;
    }

    public void printLabyrinth(int[][][] labyrinth) {

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
