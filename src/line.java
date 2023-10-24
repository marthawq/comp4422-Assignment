import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class line extends Line2D {
    int x1;
    int y1;
    int x2;
    int y2;
    double rotationAngle;

    line(int x1, int y1, int x2, int y2, double rotationAngle){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.rotationAngle = rotationAngle;
    }

    @Override
    public double getX1() {
        return 0;
    }

    @Override
    public double getY1() {
        return 0;
    }

    @Override
    public Point2D getP1() {
        return null;
    }

    @Override
    public double getX2() {
        return 0;
    }

    @Override
    public double getY2() {
        return 0;
    }

    @Override
    public Point2D getP2() {
        return null;
    }

    @Override
    public void setLine(double x1, double y1, double x2, double y2) {

    }

    @Override
    public Rectangle2D getBounds2D() {
        return null;
    }
}
