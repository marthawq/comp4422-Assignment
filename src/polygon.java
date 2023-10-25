import java.awt.*;

public class polygon extends Polygon {
    Color color;

    int width;
    int height;
    double rotationAngle;

    polygon(int[] xPoints, int[]yPoints, Color color, int width, int height, int rotationAngle){
        super(xPoints,yPoints,4);
        this.width = width;
        this.color = color;
        this.height = height;
        this.rotationAngle = rotationAngle;
    }
}
