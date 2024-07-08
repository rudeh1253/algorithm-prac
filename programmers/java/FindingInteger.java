package programmers.java;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/181840
 */
public class FindingInteger {
    
    public static void main(String[] args) {
        System.out.println(solution(new int[] { 1, 2, 3, 4, 5 }, 1) == 1);
        System.out.println(solution(new int[] { 1, 2, 3, 4, 5 }, 0) == 0);
    }

    public static int solution(int[] numList, int n) {
        for (int i = 0; i < numList.length; i++) {
            if (numList[i] == n) {
                return 1;
            }
        }
        return 0;
    }
}
