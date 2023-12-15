numOfTestCase = int(input())

def forEachTestCase(ith: int):
    firstLine = input().split(" ")
    N = int(firstLine[0])
    K = int(firstLine[1])
    stocks = [ int(i) for i in input().split(" ") ]
    result = solve(N, stocks)
    print("Case #%d" %ith)
    if result >= K:
        print(1)
    else:
        print(0)

def solve(N: int, stocks: list):
    D = [ stocks[0] ]
    for i in range(1, N):
        if D[-1] < stocks[i]:
            D.append(stocks[i])
        else:
            toInsert = binarySearch(stocks[i], D)
            D[toInsert] = stocks[i]
    return len(D)

def binarySearch(target: int, array: list):
    return binarySearchHelper(0, len(array) - 1, target, array)

def binarySearchHelper(left: int, right: int, target: int, array: list):
    while left <= right:
        mid = (left + right) // 2
        if array[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return left


if __name__ == "__main__":
    for i in range(0, numOfTestCase):
        forEachTestCase(i + 1)