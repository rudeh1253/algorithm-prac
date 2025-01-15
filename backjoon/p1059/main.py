import sys

def solution(l: int, s: list[int], n: int) -> int:
    s.sort()
    lower = 1
    upper = 0
    for i in range(len(s)):
        if s[i] > n:
            upper = s[i]
            break

        lower = s[i]

    lower += 1
    upper -= 1
    result =  (n - lower) * (upper - n) + (n - lower) + (upper - n)

    if result < 0:
        result = 0
    
    return result

if __name__ == "__main__":
    l = int(sys.stdin.readline())
    s = [int(i) for i in sys.stdin.readline().split(" ")]
    n = int(sys.stdin.readline())

    answer = solution(l, s, n)
    print(answer)