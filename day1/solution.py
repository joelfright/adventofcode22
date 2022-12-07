current = 0
maxCalorie = 0
secondMax = 0
thirdMax = 0
with open('calories.txt', 'r') as f:
    for line in f.readlines():
        currentLine = line.strip()
        if(currentLine == ''):
            if(maxCalorie > current):
                maxCalorie = current
            if(secondMax > thirdMax):
                secondMax = current
            if( > secondMax):
                maxCalorie = current
            current = 0
        else:
            calorie = int(line.strip())
            current = current + calorie

print(maxCalorie)  
print(secondMax)
print(thirdMax)
