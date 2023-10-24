import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ellipse extends Ellipse2D{

    int x;
    int y;
    int width;
    int height;

    double rotationAngle;


    ellipse(int x, int y, int width, int height, double rotationAngle){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotationAngle = rotationAngle;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {

    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
