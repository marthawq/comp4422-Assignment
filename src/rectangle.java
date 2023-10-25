import java.awt.*;

public class rectangle extends Rectangle {
    Color color;
    double rotationAngle = 0;

    public rectangle(int x, int y, int width, int height, Color color, double rotationAngle){
        super(x, y, width, height);
        this.color = color;
        this.rotationAngle = rotationAngle;
    }
}