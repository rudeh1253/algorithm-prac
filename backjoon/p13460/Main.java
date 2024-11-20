import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] countInput = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = countInput[0];

        String[][] matrix = new String[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = br.readLine().split("");
        }

        int answer = solution(matrix);
        System.out.println(answer);

        br.close();
    }

    static int VERT = 0;
    static int HORIZON = 1;

    static int solution(String[][] matrix) {
        int rVert = -1;
        int rHorizon = -1;
        int bVert = -1;
        int bHorizon = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                String here = matrix[i][j];
                if (here.equals(RED_BALL)) {
                    rVert = i;
                    rHorizon = j;
                    matrix[rVert][rHorizon] = SPACE;
                }
                if (here.equals(BLUE_BALL)) {
                    bVert = i;
                    bHorizon = j;
                    matrix[bVert][bHorizon] = SPACE;
                }
            }
        }


        return recur(
                matrix,
                1,
                new int[] { rVert, rHorizon },
                new int[] { bVert, bHorizon });
    }

    static int recur(String[][] matrix, int count, int[] rCoord, int[] bCoord) {
        if (count > 10) {
            return -1;
        }

        int upNext;
        try {
            int[][] upCoord = roll(matrix, rCoord, bCoord, UP);
            upNext = recur(matrix, count + 1, upCoord[0], upCoord[1]);
        } catch (Exception e) {
            String sign = e.getMessage();
            if (sign.equals(RED_HALL_IN)) {
                upNext = count;
            } else {
                upNext = -1;
            }
        }

        int leftNext;
        try {
            int[][] leftCoord = roll(matrix, rCoord, bCoord, LEFT);
            leftNext = recur(matrix, count + 1, leftCoord[0], leftCoord[1]);
        } catch (Exception e) {
            String sign = e.getMessage();
            if (sign.equals(RED_HALL_IN)) {
                leftNext = count;
            } else {
                leftNext = -1;
            }
        }

        int downNext;
        try {
            int[][] downCoord = roll(matrix, rCoord, bCoord, DOWN);
            downNext = recur(matrix, count + 1, downCoord[0], downCoord[1]);
        } catch (Exception e) {
            String sign = e.getMessage();
            if (sign.equals(RED_HALL_IN)) {
                downNext = count;
            } else {
                downNext = -1;
            }
        }

        int rightNext;
        try {
            int[][] rightCoord = roll(matrix, rCoord, bCoord, RIGHT);
            rightNext = recur(matrix, count + 1, rightCoord[0], rightCoord[1]);
        } catch (Exception e) {
            String sign = e.getMessage();
            if (sign.equals(RED_HALL_IN)) {
                rightNext = count;
            } else {
                rightNext = -1;
            }
        }

        OptionalInt opInt = Arrays.stream(new int[] { upNext, leftNext, downNext, rightNext })
                .filter((n) -> n != -1)
                .min();
        if (opInt.isPresent()) {
            return opInt.getAsInt();
        } else {
            return -1;
        }
    }

    static final String HALL = "O";
    static final String OBSTACLE = "#";
    static final String RED_BALL = "R";
    static final String BLUE_BALL = "B";
    static final String SPACE = ".";

    static final String RED_HALL_IN = "RED_HALL_IN";
    static final String BLUE_HALL_IN = "BLUE_HALL_IN";

    static final int UP = 0;
    static final int LEFT = 1;
    static final int DOWN = 2;
    static final int RIGHT = 3;

    static int[][] roll(String[][] matrix, int[] rInitCoord, int[] bInitcoord, int direction) throws Exception {
        int dv = (direction - 1) % 2;
        int dh = (direction - 2) % 2;
        boolean rNoMore = false;
        boolean bNoMore = false;

        int[] rCurCoord = { rInitCoord[VERT], rInitCoord[HORIZON] };
        int[] bCurCoord = { bInitcoord[VERT], bInitcoord[HORIZON] };

        boolean redHallIn = false;
        boolean blueHallIn = false;
        while (!bNoMore || !rNoMore) {
            if (!rNoMore && canNext(matrix, rCurCoord, bCurCoord, dv, dh, blueHallIn)) {
                rCurCoord[VERT] = rCurCoord[VERT] + dv;
                rCurCoord[HORIZON] = rCurCoord[HORIZON] + dh;
                if (!redHallIn) {
                    redHallIn = matrix[rCurCoord[VERT]][rCurCoord[HORIZON]].equals(HALL);
                }
            } else {
                rNoMore = true;
            }
            if (!bNoMore && canNext(matrix, bCurCoord, rCurCoord, dv, dh, redHallIn)) {
                bCurCoord[VERT] = bCurCoord[VERT] + dv;
                bCurCoord[HORIZON] = bCurCoord[HORIZON] + dh;
                if (!blueHallIn) {
                    blueHallIn = matrix[bCurCoord[VERT]][bCurCoord[HORIZON]].equals(HALL);
                }
            } else {
                bNoMore = true;
            }
        }

        if (blueHallIn) {
            throw new Exception(BLUE_HALL_IN);
        }
        if (redHallIn) {
            throw new Exception(RED_HALL_IN);
        }

        return new int[][] {
                { rCurCoord[VERT], rCurCoord[HORIZON] },
                { bCurCoord[VERT], bCurCoord[HORIZON] }
        };
    }

    static boolean canNext(String[][] matrix, int[] coord, int[] oppoCoord, int dv, int dh, boolean oppoHallIn) {
        String next = getNext(matrix, coord, dv, dh);
        if (next.equals("#")) {
            return false;
        }
        if (!oppoHallIn && coord[VERT] + dv == oppoCoord[VERT] && coord[HORIZON] + dh == oppoCoord[HORIZON]) {
            return canNext(matrix, oppoCoord, coord, dv, dh, oppoHallIn);
        }
        return true;
    }

    static String getNext(String[][] matrix, int[] coord, int dv, int dh) {
        return matrix[coord[VERT] + dv][coord[HORIZON] + dh];
    }
}