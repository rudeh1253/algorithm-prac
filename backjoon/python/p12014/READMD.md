## Problem
[https://www.acmicpc.net/problem/12014](https://www.acmicpc.net/problem/12014)

## Longest Increasing Sequence - O(n log(n))
### Algorithm
```
S: A sequence to find the longest increasing sequence in (input). The length of S is N. The indices of S ranges from 0 to N - 1 inclusively.
D: An auxiliary array or list. Initially is empty.

1. For i = 0, let D[i] = S[i]
2. For i = k where k is a constant, 0 < k < N:
If S[i] < D.elementOfLastIndex, append S[i] at the end of D
If S[i] >= D.elementOfLastIndex, insert S[i] at the position j where (S[i] < D[j]) ∧ (S[i] > D[j - 1]). Find j by binary search.
3. Increment i and do the task of step 2 until i = n - 1 (the last index of S).
```

### Correctness
#### Theorem. The length of D is equal to that of the logest incresing sequence of S.
##### Proof.
```
Proof by induction.
First of all, for any integer s, t where s <= k, let S[s ~ t] be a vector which containing all elements of S ranging from s to t. In other words, S[s ~ t] = (S[s], S[s + 1], ... S[t]).
S[s ~ s] = (S[s]).

Let's define an induction hypothesis (P(i)) as follows:
P(i) = "For some i where D.length = i, D.length is equal to the length of the longest increasing sequence among subsequences of S[0 ~ i]."

Base case: i = 0
In this case, D contains only S[0], i.e. D = (S[0]).
S[0 ~ 0] = (S[0]), so D contains the longest increasing sequence of S[0 ~ 0] which implies that the hypothesis holds.

Assume that P(j) is true. We should show that P(j) IMPLIES P(j + 1).
Suppose that S[j + 1] = k.
Case 1: D.elementOfLastIndex < k.
In this case, just attach k at the end of D. Trivially the length of the new D is equal to the length of LIS of S[0 ~ k + 1].
Case 2: D.elementOfLastIndex >= k.
Assume that there is a subsequence E which contains k and D.length < E.length, i.e. E.length = D.length + 1.
Since D.elementOfLastIndex >= k, there is at least one element in D which E can't contain. By assumption that P(j) is true, D.length is that of LIS of S[0 ~ j], and by step 2 of the algorithm, D only contains the smallest elements of S. Thus, E.length can't be bigger than D.length + 1, so this is a contradiction. ∎
```

### Effectiveness
```
Time complexity of step 2: O(log(n)) (Binary search)
Step 2 is repeated n times. So the time complexity of the algorithm is O(n log(n))
```