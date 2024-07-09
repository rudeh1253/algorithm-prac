#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// https://school.programmers.co.kr/learn/courses/30/lessons/181843?language=c
int solution(const char* my_string, const char* target) {
    int my_string_len = strlen(my_string);
    int target_len = strlen(target);

    for (int i = 0; i < my_string_len - target_len + 1; i++) {
        for (int j = 0; j < target_len; j++) {
            if (my_string[i + j] != target[j]) {
                break;
            }
            if (j + 1 == target_len) {
                return 1;
            }
        }
    }
    return 0;
}