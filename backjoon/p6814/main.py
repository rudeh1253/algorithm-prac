adjSet = {
    1: { 6 },
    2: { 6 },
    3: { 4, 5, 6, 15 },
    4: { 3, 5, 6 },
    5: { 3, 4, 6 },
    6: { 1, 2, 3, 4, 5, 7 },
    7: { 6, 8 },
    8: { 7, 9 },
    9: { 8, 10, 12 },
    10: { 9, 11 },
    11: { 10, 12 },
    12: { 9, 11, 13 },
    13: { 12, 14, 15 },
    14: { 13 },
    15: { 3, 13 },
    16: { 17, 18 },
    17: { 16, 18 },
    18: { 16, 17 }
}

def bfs(source: int):
    queue = [ source ]
    distance = dict()
    distance[source] = 0
    visited = { source }
    while len(queue) != 0:
        currentVertex = queue.pop(0)
        for each in adjSet[currentVertex]:
            if (each not in visited):
                distance[each] = distance[currentVertex] + 1
                queue.append(each)
                visited.add(each)
    return distance


while (True):
    line = input()
    parsed = line.split(" ")
    operator = parsed[0]
    if operator == "i":
        operand1 = int(parsed[1])
        operand2 = int(parsed[2])
        if (adjSet.get(operand1, 0) == 0):
            adjSet[operand1] = { operand2 }
        else:
            adjSet[operand1].add(operand2)
        if (adjSet.get(operand2, 0) == 0):
            adjSet[operand2] = { operand1 }
        else:
            adjSet[operand2].add(operand1)
    elif operator == "d":
        operand1 = int(parsed[1])
        operand2 = int(parsed[2])
        adjSet[operand1].remove(operand2)
    elif operator == "n":
        operand = int(parsed[1])
        print(len(adjSet[operand]))
    elif operator == "f":
        operand = int(parsed[1])
        count = 0
        for adjVertex in adjSet[operand]:
            for adjAdjVertex in adjSet[adjVertex]:
                if (adjAdjVertex != operand and adjAdjVertex not in adjSet[operand]):
                    count += 1
        print(count)
    elif operator == "s":
        operand1 = int(parsed[1])
        operand2 = int(parsed[2])
        distance = bfs(operand1)
        if (operand2 not in distance):
            print("Not connected")
        else:
            print(distance[operand2])
    elif operator == "q":
        break