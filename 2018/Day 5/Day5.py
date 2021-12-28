with open("input.txt") as f:
    content = f.readline().strip()
originalLength = -1
minVal = 1000000

def removeAdjacents():
    global content
    i = 0
    while i < len(content) - 1:
        if content[i].islower():
            if content[i + 1] == content[i].upper():
                content = content[:i] + content[i+2:]
        elif content[i].isupper():
            if content[i + 1] == content[i].lower():
                content = content[:i] + content[i+2:]
        i += 1

def reset():
    global content, originalLength
    content = original
    originalLength = -1

# Part A

# while (originalLength != len(content)):
#     originalLength = len(content)
#     removeAdjacents()
# print(len(content))

# Part B

original = content
for i in range(97, 123):
    content = content.replace(chr(i), "")
    content = content.replace(chr(i).upper(), "")
    while (originalLength != len(content)):
        originalLength = len(content)
        removeAdjacents()
    minVal = min(minVal, len(content))
    reset()
print(minVal)