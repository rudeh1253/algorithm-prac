N = int(input())
inputStr = input()
arr = [int(i) for i in inputStr.split(" ")]

dp = {}

def solve(input: list, N: int):
    return helper(input, N - 1)

def helper(input: list, i: int):
    if i == 0:
        if i not in dp:
            dp[i] = [input[i]]
        return dp[i]
    else:
        if i in dp:
            return dp[i]
        else:
            maxArr = []
            for idx in range(i):
                subresult = helper(input, idx)
                if (len(subresult) >= 2):
                    if ((subresult[-1] > subresult[-2]) or ((subresult[-1] < subresult[-2]) and (subresult[-1] > input[i]))) and subresult[-1] != input[i]:
                        if (len(maxArr) <= len(subresult)):
                            maxArr = subresult
                else:
                    if (subresult[0] == input[i]):
                        if (len(maxArr) < 1):
                            maxArr = []
                    else:
                        if (len(maxArr) < 2):
                            maxArr = [subresult[0]]
                maxArr.append(input[i])
                dp[i] = maxArr
                return dp[i]

print(solve(arr, N))
print(dp)