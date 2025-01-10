import sys

def solution(n: int, h: int, obstacles: list[int]) -> list[int]:
    stalactites = obstacles[::2]
    stalagmites = obstacles[1::2]

    stalactites.sort()
    stalagmites.sort()

    stalacititeSize = len(stalactites)
    stalagmiteSize = len(stalagmites)

    obstacleMap = dict()

    results = {1: 0}
    cur = 0
    for i in range(1, h + 1):
        if cur < stalacititeSize:
            currentObstacleHeight = stalactites[cur]
            if i > currentObstacleHeight:
                while cur < stalacititeSize and currentObstacleHeight >= stalactites[cur]:
                    cur += 1
        results[i] = stalacititeSize - cur

    cur = 0
    for i in range(1, h + 1):
        if cur < stalagmiteSize:
            currentObstacleHeight = stalagmites[cur]
            if i > currentObstacleHeight:
                while cur < stalagmiteSize and currentObstacleHeight >= stalagmites[cur]:
                    cur += 1
        results[h - i + 1] += stalagmiteSize - cur
    
    min = 200001
    count = 0
    for k, v in results.items():
        if v < min:
            count = 1
            min = v
        elif v == min:
            count += 1

    return [min, count]



if __name__ == '__main__':

    [n, h] = [int(i) for i in sys.stdin.readline().split(" ")]
    obstacles = []
    for i in range(n):
        obstacles.append(int(sys.stdin.readline()))
    answer = solution(n, h, obstacles)
    print(str(answer[0]) + " " + str(answer[1]))

