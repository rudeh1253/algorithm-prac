/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/172928ls
 */
public class Lv1_WalkingPark {

    public int[] solution(String[] park, String[] routes) {
        int[] currentPos = null;

        String[][] parkMatrix = new String[park.length][];

        for (int i = 0; i < park.length; i++) {
            String[] split = park[i].split("");
            parkMatrix[i] = split;
            for (int j = 0; j < parkMatrix[i].length; j++) {
                if (parkMatrix[i][j].equals("S")) {
                    currentPos = new int[] { i, j };
                }
            }
        }

        if (currentPos == null) {
            currentPos = new int[] { 0, 0 };
        }

        for (String routeStr : routes) {
            String[] parsed = routeStr.split(" ");
            String direction = parsed[0];
            int dist = Integer.parseInt(parsed[1]);
            if (canGo(direction, dist, currentPos, parkMatrix)) {
                switch (direction) {
                    case "E":
                        currentPos[1] += dist;
                        break;
                    case "W":
                        currentPos[1] -= dist;
                        break;
                    case "S":
                        currentPos[0] += dist;
                        break;
                    case "N":
                        currentPos[0] -= dist;
                        break;
                    default:
                        break;
                }
            }
        }
        return currentPos;
    }

    private boolean canGo(String direction, int dist, int[] currentPos, String[][] parkMatrix) {
        switch (direction) {
            case "E":
                if (currentPos[1] + dist >= parkMatrix[0].length) {
                    return false;
                }
                for (int i = currentPos[1]; i <= currentPos[1] + dist; i++) {
                    if (parkMatrix[currentPos[0]][i].equals("X")) {
                        return false;
                    }
                }
                return true;
            case "W":
                if (currentPos[1] - dist < 0) {
                    return false;
                }
                for (int i = currentPos[1]; i >= currentPos[1] - dist; i--) {
                    if (parkMatrix[currentPos[0]][i].equals("X")) {
                        return false;
                    }
                }
                return true;
            case "S":
                if (currentPos[0] + dist >= parkMatrix.length) {
                    return false;
                }
                for (int i = currentPos[0]; i <= currentPos[0] + dist; i++) {
                    if (parkMatrix[i][currentPos[1]].equals("X")) {
                        return false;
                    }
                }
                return true;
            case "N":
                if (currentPos[0] - dist < 0) {
                    return false;
                }
                for (int i = currentPos[0]; i >= currentPos[0] - dist; i--) {
                    if (parkMatrix[i][currentPos[1]].equals("X")) {
                        return false;
                    }
                }
                return true;
            default:
                return false;
        }
    }
}