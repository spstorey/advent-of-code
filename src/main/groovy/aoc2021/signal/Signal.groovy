package aoc2021.signal

import javax.imageio.ImageIO
import javax.swing.*
import java.awt.*
import java.awt.image.BufferedImage
import java.util.List

String noDistortionImage = removeDistortion("startImage.png")

String sortedToGroupsImage = sortLinesToGroups(noDistortionImage)

JFrame frame = new JFrame("Test")
frame.setVisible(true)
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
frame.setSize(800,400)

JPanel panel = new JPanel() {
    @Override
    protected void paintComponent(Graphics g) {
        Image image = null
        try {
            Image rawImage = ImageIO.read(new File(sortedToGroupsImage))
            image = new BufferedImage(600, 300, BufferedImage.TYPE_INT_ARGB)
            Graphics2D graphics2D = image.createGraphics()
            graphics2D.drawImage(rawImage, 0, 0, 600, 300, null)
            graphics2D.dispose()
        } catch (IOException e) {
            e.printStackTrace()
        }
        super.paintComponent(g)
        g.drawImage(image, 0, 0, null)
    }
}
frame.add(panel)

static String removeDistortion(String fileName) {

    BufferedImage inputImage = ImageIO.read(new File(fileName))

    Map<Integer, Integer> colourCounts = new TreeMap<>()
    for (int x = 0; x < inputImage.width; x++) {
        for (int y = 0; y < inputImage.height; y++) {
            Integer occurances = colourCounts.get(inputImage.getRGB(x, y))
            if (occurances == null) {
                occurances = 0
            }
            colourCounts.put(inputImage.getRGB(x, y), occurances+1)
        }
    }

    for (int x = 0; x < inputImage.width; x++) {
        for (int y = 0; y < inputImage.height; y++) {
            if (colourCounts.get(inputImage.getRGB(x, y)) < 10) {
                inputImage.setRGB(x, y, 0)
            }
        }
    }

    File outputFile = new File("no_distortion.png")
    ImageIO.write(inputImage, "png", outputFile)
    return "no_distortion.png"
}

static String sortLinesToGroups(String fileName) {

    BufferedImage inputImage = ImageIO.read(new File(fileName))
    BufferedImage savedImage = ImageIO.read(new File(fileName))

    TreeMap<Integer, List<Integer>> sorted = new TreeMap<>()
    for (int y = 0; y < inputImage.height; y++) {
        Long totalColour = 0
        Integer cells = 0
        int[] row = new int[inputImage.width]
        for (int x = 0; x < inputImage.width; x++) {
            row[x] = inputImage.getRGB(x, y)
            if (inputImage.getRGB(x, y) != 0) {
                cells++
                totalColour += inputImage.getRGB(x, y)
            }
        }
        Integer avgColour = 0
        if (cells != 0) {
            avgColour = (totalColour/cells).intValue()
        }
        List<Integer> rows = sorted.get(avgColour)
        if (rows == null) {
            rows = new ArrayList<>()
        }
        rows.add(y)
        sorted.put(avgColour, rows)
    }

    int rowNum = 0;
    for (Integer key : sorted.keySet()) {
        List<Integer> rows = sorted.get(key)
        for (Integer row : rows) {
            for (int x = 0; x < inputImage.width; x++) {
                savedImage.setRGB(x, rowNum, inputImage.getRGB(x, row))
            }
            rowNum++
        }
    }

    File outputFile = new File("sorted_to_groups.png")
    ImageIO.write(savedImage, "png", outputFile)
    return "sorted_to_groups.png"
}
