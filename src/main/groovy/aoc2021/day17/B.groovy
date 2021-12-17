package aoc2021.day17

int left = 111
int right = 161
int bottom = -154
int top = -101

Set<String> vs = new HashSet<>()
for (int v = 1; v <= right; v++) {

    for (int h = 1000; h >= bottom; h--) {

        int currentX = 0
        int currentY = 0
        int highestPoint = 0
        boolean passedTarget = false

        int currentHorizontalSpeed = v
        int currentVerticalSpeed = h
        while (!passedTarget && currentHorizontalSpeed > -1) {
            currentX += currentHorizontalSpeed
            currentY += currentVerticalSpeed

            if (currentY > highestPoint) {
                highestPoint = currentY
            }

            if (currentX >= left && currentX <= right && currentY <= top  && currentY >= bottom) {
                vs.add(v+","+h)
            } else if (currentY < bottom || currentX > right) {
                passedTarget = true
            }
            if (currentHorizontalSpeed != 0) {
                currentHorizontalSpeed--
            }
            currentVerticalSpeed--
        }
    }
}
print vs.size()
