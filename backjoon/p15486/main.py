from sys import stdin

N = int(input())

T = []
P = []
for _ in range(N):
    t, p = map(int, stdin.readline().split())
    T.append(t)
    P.append(p)

dpTable = { N: 0 }

for i in range(N - 1, -1, -1):
    time = T[i]
    priceHere = P[i]
    if i + time > N:
        dpTable[i] = 0
        continue
    dpTable[i] = max(priceHere + dpTable[i + time], dpTable[i + 1])

print(dpTable[0])