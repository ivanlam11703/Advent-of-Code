with open('input.txt', 'r') as f:
    content = f.readlines()

    left_list = []
    right_list = []

    for line in content:
        parts = line.split()
        left_list.append(int(parts[0]))
        right_list.append(int(parts[1]))

    left_list.sort()
    right_list.sort()

    # Part 1
    # sum = 0
    # for i in range(len(left_list)):
    #     sum += abs(left_list[i] - right_list[i])

    # Part 2
    sum = 0
    counts = {}

    for number in right_list:
        if number in counts:
            counts[number] += 1
        else:
            counts[number] = 1

    for i in range(len(left_list)):
        if left_list[i] in counts:
            sum += left_list[i] * counts[left_list[i]]

    print(sum)