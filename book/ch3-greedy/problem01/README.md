# 큰 수의 법칙
다양한 수로 이루어진 배열이 있을 때 주어진 수를 M번 더하여 가장 큰 수를 만든느 법칙이다. 단, 배열의 특정한 인덱스(번호)에 해당하는 수가 연속해서 K번을 초과하여 더해질 수 없는 것이 이 법칙의 특징이다.
예를 들어 순서대로 2, 4, 5, 4, 6으로 이루어진 배열이 있을 때 M이 8이고, K가 3이라고 가정하자. 이 경우 특정한 인덱스의 수가 연속해서 세 번까지만 더해질 수 있으므로 클 수의 법칙에 따른 결과는 6 + 6 + 5 + 6 + 6 + 5 = 46이 도니다.
단, 서로 다른 인덱스에 해당하는 수가 같은 경우에도 서로 다른 것으로 간주한다. 예를 들어 순서대로 3, 4, 3, 4, 3으로 이루어진 배열이 있을 때, M이 7이고 K가 2라고 가정하자. 이 경우 두 번째 원소에 해당하는 4와 네 번째 원소에 해당하는 4를 번갈아 두 번씩 더하는 것이 가능하다. 결과적으로 4 + 4 + 4 + 4 + 4 + 4 = 28이 도출된다.
배열의 크기 N, 숫자가 더해지는 횟수 M, 그리고 K가 주어질 때 동빈이의 큰 수의 법에 따른 결과를 출력하시오.

# 풀이

## 알고리즘
1. 배열에서 가장 큰 수를 선택한다.
2. 가장 큰 수를 K번 반복해 더한다.
3. 그후 배열에서 두 번째로 큰 수를 선택한다.
4. 그 수를 한 번 더한다.
5. 1번으로 돌아가 반복한다.

## 증명
크기가 n인 배열 A에 대해서<br>
```
A = { a_1, a_2, ..., a_i, ..., a_n } where for every i, j, i < j, a_i >= a_j
```
일 때, 위에 제시된 알고리즘 대로 실행하면 결과값 R은
```
R = (K * a_1 + a_2) * M / (K + 1) + a_1 + M % (K + 1)
```
이다. 만약 이보다 최적의 해를 도출하는 또 다른 알고리즘 Alg2가 있다고 가정하자. 그 알고리즘에선 최소 한 번 이상 다음 조건을 만족시키는 a_i를 선택할 것이다.
```
i > 2, a_i <= a_2 <= a_1
```
그러면 Alg2이 도출하는 결과값 R_2는 다음과 같다.
```
R_2 = R - a_1 + a_i
```
그런데 정의에 따르면 ```a_1 >= a_i```이다. 다시 말해
```
a_i - a_1 <= 0
```
이 성립한다. 위 R_2에 대한 식을 다시 정리하면
```
R_2 = R - a_1 + a_i <= R
R_2 <= R
```
이는 최적의 해를 도출하는 Alg2의 결과값 R_2가 최적의 해라는 가정과 모순된다. 즉, Alg2는 존재하지 않는다. (Proof by contradiction)