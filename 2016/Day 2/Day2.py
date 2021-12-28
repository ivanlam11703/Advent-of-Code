with open("input.txt") as f:
    content = f.read().splitlines()
# Part A keypad
# keypad = [[1,2,3],[4,5,6],[7,8,9]]

# Part B keypad
keypad = [[-1,-1,1,-1,-1],[-1,2,3,4,-1],[5,6,7,8,9],[-1,'A','B','C',-1],[-1,-1,'D',-1,-1]]

# Part A
# def inRange(x, y):
#     return x >= 0 and x < 3 and y>= 0 and y < 3

# Part B
def inRange(x, y):
    return x >= 0 and x < len(keypad) and y >= 0 and y < len(keypad[x]) and keypad[x][y] != -1

# Initial position is [1,1] for Part A
initialPosition = [2,0]
answer = ""
for line in content:
    for char in line:
        if char == 'R':
            if inRange(initialPosition[0], initialPosition[1] + 1):
                initialPosition[1] += 1
        elif char == "L":
            if inRange(initialPosition[0], initialPosition[1] - 1):
                initialPosition[1] -= 1
        elif char == "U":
            if inRange(initialPosition[0] - 1, initialPosition[1]):
                initialPosition[0] -= 1
        elif char == "D":
            if inRange(initialPosition[0] + 1, initialPosition[1]):
                initialPosition[0] += 1
    print(initialPosition)
    answer += str(keypad[initialPosition[0]][initialPosition[1]])
print(answer)