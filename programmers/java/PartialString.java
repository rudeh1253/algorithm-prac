package programmers.java;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/181842
 */
public class PartialString {

    public int solution(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        for (int i = 0; i < chars2.length - chars1.length + 1; i++) {
            int j = 0;
            while (true) {
                if (j == chars1.length) {
                    return 1;
                }
                if (chars1[j] != chars2[i + j]) {
                    break;
                }
                j++;
            }
        }
        return 0;
    }
}
