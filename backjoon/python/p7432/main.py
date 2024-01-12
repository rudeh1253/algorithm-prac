class BinaryTree:

    def __init__(self, value) -> None:
        self.value = BTree(value)
        self.left = None
        self.right = None
    
    def insert(self, value) -> None:
        BinaryTreeInsertHelper(value, self)
    
    def toList(self) -> list:
        l = []
        toListHelper(self, l)
        return l

def BinaryTreeInsertHelper(toInsert, node: BinaryTree):
    print(type(toInsert))
    print(toInsert.value)
    print(type(node))
    print(type(node.value))
    print(type(node.value.value.value))
    print()
    comp = toInsert.compare(node.value)
    if comp == 0:
        return
    elif comp > 0:
        if node.right is None:
            node.right = BinaryTree(toInsert)
        else:
            BinaryTreeInsertHelper(toInsert, node.right)
    else:
        if node.left is None:
            node.left = BinaryTree(toInsert)
        else:
            BinaryTreeInsertHelper(toInsert, node.left)

def toListHelper(node: BinaryTree, arr: list):
    if node is None:
        return
    toListHelper(node.left, arr)
    arr.append(node.value)
    toListHelper(node.right, arr)

def getFirst(node: BinaryTree):
    if node.left is None:
        return node
    else:
        return getFirst(node.left)

class BTree:

    def __init__(self, value: str) -> None:
        self.value = value
        self.childTree = None
        self.childSet = set()
    
    def insert(self, arr: list):
        BTreeInsertHelper(self, arr)
    
    def compare(self, toCompare):
        if self.value == toCompare.value:
            return 0
        elif self.value > toCompare.value:
            return 1
        else:
            return -1
    
    def print(self):
        childs = self.childTree.toList()
        for child in childs:
            printHelper(child)
        
def BTreeInsertHelper(tree: BTree, array: list):
    if len(array) == 0:
        return
    
    if array[0] not in tree.childSet:
        newNode = BTree(array[0])
        if tree.childTree is None:
            tree.childTree = BinaryTree(newNode)
        else:
            tree.childTree.insert(newNode)
        tree.childSet.add(array[0])
    
    
    next = None
    childs = tree.childTree.toList()
    for i in childs:
        if i.value == array[0]:
            next = i
    if next is not None:    
        array.pop(0)
        BTreeInsertHelper(next, array)

def printHelper(tree: BTree):
    print(tree.value)
    childs = tree.childTree.toList()
    for child in childs:
        printHelper(child)

N = int(input())

btree = BTree(None)
for i in range(N):
    line = input().split("\\")
    print(len(line))
    btree.insert(line)

btree.print()