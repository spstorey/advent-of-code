int left = 111
int right = 161
int bottom = -154
int top = -101

int maxHighestPoint = 0
for (int v = 15; v < 162; v++) {

    for (int h = 1000; h > 0; h--) {

        int currentX = 0
        int currentY = 0
        int highestPoint = 0
        boolean passedTarget = false
        boolean hit = false

        int currentHorizontalSpeed = v
        int currentVerticalSpeed = h
        while (!passedTarget && !hit) {
            currentX += currentHorizontalSpeed
            currentY += currentVerticalSpeed

            if (currentY > highestPoint) {
                highestPoint = currentY
            }

            if (currentX >= left && currentX <= right && currentY <= top  && currentY >= bottom) {
                hit = true
                if (highestPoint > maxHighestPoint) {
                    maxHighestPoint = highestPoint
                }
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
print maxHighestPoint
