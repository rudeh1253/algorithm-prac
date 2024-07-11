#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// https://school.programmers.co.kr/learn/courses/30/lessons/181845
char* solution(int n) {
    int sig = 100000;
    char* result = malloc(1000000);
    char c;
    int i = 0;
    int started = 0;
    while (sig > 0) {
	int num = n / sig;
	if (!started && num == 0) {
	    sig /= 10;
	    continue;
	}
	started = 1;
        c = num + '0';
	n %= sig;
	sig /= 10;
        result[i++] = c;
    }
    result[i] = '\0';
    return result;
}
