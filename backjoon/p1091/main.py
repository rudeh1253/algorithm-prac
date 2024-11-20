N = int(input())
P = [ int(i) for i in input().split(" ") ]
S = [ int(i) for i in input().split(" ") ]
cards = [ i for i in range(0, N) ]

NUM_OF_PLAYER = 3

def shuffle():
    newCards = [ 0 for i in range(0, N) ]
    for i in range(0, N):
        newCards[i] = cards[S[i]]
    return newCards

def validate():
    for i in range(0, N):
        if (P[i] != cards[i] % NUM_OF_PLAYER):
            return False
    return True

dCards = [ i for i in cards ]

def compare():
    for i in range(0, len(dCards)):
        if dCards[i] != cards[i]:
            return False
    return True

i = 0
while True:
    if (validate()):
        print(i)
        break
    cards = shuffle()
    i += 1
    if (compare()):
        print(-1)
        break