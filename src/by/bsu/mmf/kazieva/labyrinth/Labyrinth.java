package by.bsu.mmf.kazieva.labyrinth;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    private final static int TIME = 5;
    private static String inputFileName = "src/input/INPUT.txt";
    private static final Logger logger = Logger.getLogger(Labyrinth.class);
    private static final int FIRST_LINE = 0;
    private static final int POSITION_OF_HEIGHT = 0;
    private static final int POSITION_OF_WIDTH = 1;
    private static final int POSITION_OF_LENGTH = 2;
    private static final char PRINCE = '1';
    private static final char PRINCESS = '2';


    private char[][][] labyrinth;
    private int height;
    private int width;
    private int length;
    private int princessX;
    private int princessY;
    private int princeX;
    private int princeY;

    public void setLabyrinth() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.length() != 0) {
                    lines.add(line);
                }
            }
            reader.close();
            setHeightWidthLength(lines.get(FIRST_LINE));
            lines.remove(FIRST_LINE);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    CharSequence lineChar = lines.get(0);
                    for (int k = 0; k < length; k++) {
                        labyrinth[i][j][k] = lineChar.charAt(k);
                    }
                    lines.remove(FIRST_LINE);
                }
            }
            printLabyrinth();

        } catch (IOException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    private void setHeightWidthLength(String str) {
        String firstLine[] = str.split(" ");
        this.height = Integer.parseInt(firstLine[POSITION_OF_HEIGHT]);
        this.width = Integer.parseInt(firstLine[POSITION_OF_WIDTH]);
        this.length = Integer.parseInt(firstLine[POSITION_OF_LENGTH]);
        this.labyrinth = new char[height][width][length];
    }

    public char[][][] getLabyrinth() {
        return labyrinth;
    }


    public void printLabyrinth() {
        System.out.println("[height][width][length] " + height + " " + width + " " + length);

        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.println("");
                for (int k = 0; k < length; k++) {
                    System.out.print(labyrinth[i][j][k]);
                }
            }
        }
    }

    public void findPrincess() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < length; x++) {
                if (labyrinth[height - 1][y][x] == PRINCESS) {
                    princessX = x;
                    princessY = y;
                }
            }
        }
        System.out.println("\n" + "princessX= " + princessX + "   princessY = " + princessY);
    }

    public void findPrince() {
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < length; x++) {
                if (labyrinth[0][y][x] == PRINCE) {
                    princeX = x;
                    princeY = y;
                }
            }
        }
        System.out.println("\n" + "princeX= " + princeX + "   princeY = " + princeY);
    }

    public void algoritm() {
        setLabyrinth();
        findPrincess();
        findPrince();
    }
}
