// https://school.programmers.co.kr/learn/courses/30/lessons/159993#

import java.util.Queue;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[] maps) {
        int[][] matrix = new int[maps.length][];
        
        int[] start = null;
        int[] lever = null;
        int[] exit = null;
        int rowIdx = 0;
        for (String line : maps) {
            String[] lineArr = line.split("");
            matrix[rowIdx] = new int[lineArr.length];
            for (int colIdx = 0; colIdx < lineArr.length; colIdx++) {
                switch (lineArr[colIdx]) {
                    case "S":
                        start = new int[] { rowIdx, colIdx };
                        matrix[rowIdx][colIdx] = 1;
                        break;
                    case "E":
                        exit = new int[] { rowIdx, colIdx };
                        matrix[rowIdx][colIdx] = 1;
                        break;
                    case "L":
                        lever = new int[] { rowIdx, colIdx };
                        matrix[rowIdx][colIdx] = 1;
                        break;
                    case "O":
                        matrix[rowIdx][colIdx] = 1;
                        break;
                    case "X":
                        matrix[rowIdx][colIdx] = 0;
                        break;
                    default:
                        throw new RuntimeException("Coding error");
                }
            }
            rowIdx++;
        }
        
        return search(start, exit, lever, matrix);
    }
    
    int search(int[] start, int[] exit, int[] lever, int[][] matrix) {
        Coord startCoord = new Coord(start[0], start[1]);
        Coord leverCoord = new Coord(lever[0], lever[1]);
        Coord exitCoord = new Coord(exit[0], exit[1]);
        
        Map<Coord, Integer> ssspFromStart = sssp(startCoord, matrix);
        Map<Coord, Integer> ssspFromLever = sssp(leverCoord, matrix);
        
        if (ssspFromStart.containsKey(leverCoord)) {
            if (ssspFromLever.containsKey(exitCoord)) {
                return ssspFromStart.get(leverCoord) + ssspFromLever.get(exitCoord);
            }
        }
        return -1;
    }
    
    Map<Coord, Integer> sssp(Coord from, int[][] matrix) {
        Map<Coord, Integer> distMap = new HashMap<>();
        Queue<Coord> visitQueue = new LinkedList<>();
        
        visitQueue.offer(from);
        distMap.put(from, 0);
        
        while (!visitQueue.isEmpty()) {
            Coord now = visitQueue.poll();
            int nowDist = distMap.get(now);
            for (int i = 0; i < 4; i++) {
                int targetRow = now.row + (i - 1) % 2;
                int targetCol = now.col + (i - 2) % 2;
                Coord target = new Coord(targetRow, targetCol);
                if (targetRow >= 0 && targetRow < matrix.length
                        && targetCol >= 0 && targetCol < matrix[0].length
                        && matrix[targetRow][targetCol] == 1
                        && !distMap.containsKey(target)) {
                    visitQueue.offer(target);
                    distMap.put(target, nowDist + 1);
                }
            }
        }
        return distMap;
    }
    
    static class Coord {
        final int row;
        final int col;
        
        Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            
            if (!(obj instanceof Coord)) {
                return false;
            }
            
            Coord target = (Coord)obj;
            return this.row == target.row
                && this.col == target.col;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
    }
}