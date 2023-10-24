import java.awt.*;

public class rectangle extends Polygon {
    Color color;
    double rotationAngle = 0;

    int width;
    int height;
    int x;
    int y;

    //double scaleFactor = 0;
    public rectangle(int[] xPoints, int[] yPoints, int width, int height, Color color, double rotationAngle){
        super(xPoints, yPoints, 4);
        this.color = color;
        this.rotationAngle = rotationAngle;
        this.width = width;
        this.height = height;
        this.x = xPoints[0];
        this.y = yPoints[0];
        //this.scaleFactor = scaleFactor;
    }
}
