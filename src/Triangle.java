import java.awt.*;

public class Triangle extends Polygon {
    Color color;
    int width;
    int height;
    int numOfSides;
    double rotationAngle;
    Triangle(int[] xPoints, int[] yPoints, int numOfSides, Color color, int width, int height, int rotationAngle){
        super(xPoints,yPoints,numOfSides);
        this.color = color;
        this.width = width;
        this.height = height;
        this.numOfSides = numOfSides;
        this.rotationAngle = rotationAngle;
    }
}
