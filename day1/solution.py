current = 0
maxCalorie = 0
calories = []
with open('day1/calories.txt', 'r') as f:
    for line in f.readlines():
        currentLine = line.strip()
        if(currentLine == ''):
            if(maxCalorie < current):
                maxCalorie = current
            calories.append(current)
            current = 0
        else:
            calorie = int(line.strip())
            current = current + calorie
calories.append(current)

print(maxCalorie)
calories.sort(reverse=True)
print(calories[0] + calories[1] + calories[2])
