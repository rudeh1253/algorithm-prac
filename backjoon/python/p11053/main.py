def solve(N: int, stocks: list):
    maxSequence = {
        0: 1
    }
    max = 1
    for i in range(1, len(stocks)):
        max = subproblem(i, max, stocks, maxSequence)
    return max

def subproblem(i: int, m: int, stocks: list, maxSequence: dict):
    subMax = 1
    for j in range(0, i):
        if stocks[j] < stocks[i] and maxSequence[j] + 1 > subMax:
            subMax = maxSequence[j] + 1
    maxSequence[i] = subMax
    return max(m, subMax)

if __name__ == "__main__":
    N = int(input())
    arr = [ int(i) for i in input().split(" ") ]
    answer = solve(N, arr)
    print(answer)