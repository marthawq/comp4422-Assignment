import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Objects;

public class drawingPanel extends JPanel {
    Point pointStart = null;
    Point pointEnd = null;
    Point lastDragPoint = null;
    private boolean selectPen = false;


    private boolean rectangleMode = false;


    private boolean triangleMode = false;

    private boolean lineMode = false;


    private boolean ellipseMode = false;

    private boolean moveMode = false;

    private boolean adjustMode = false;
    ArrayList<rectangle> rectangles = new ArrayList<>();
    ArrayList<Triangle> triangles = new ArrayList<>();
    ArrayList<ellipse> ellipses = new ArrayList<>();
    ArrayList<line> lines = new ArrayList<>();
    ArrayList<Polygon> polygons = new ArrayList<>();
    rectangle currentRectangle;
    Triangle currentTriangle;
    ellipse currentEllipse;

    Polygon currentPolygon;
    line currentLine;
    //double rotationAngle = 0;
    private Color color = Color.BLACK;

    public designPanel getDesign() {
        return design;
    }

    public void setDesign(designPanel design) {
        this.design = design;
    }

    designPanel design;


    drawingPanel(designPanel design){
        //setBackground(new Color(245,245,245));
        setDesign(design);
        //setPreferredSize(new Dimension(600,0));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (selectPen || rectangleMode || triangleMode || lineMode || ellipseMode) {
                    pointStart = e.getPoint();
                } else if(moveMode || adjustMode) {
                    //System.out.println(design.positionSettingPanel.getRotationAngle());
                    if(!rectangles.isEmpty()){
                        for (int i = 0; i < rectangles.size(); i++) {
                            if ((rectangles.get(i)).contains(e.getPoint())) {
                                lastDragPoint = e.getPoint();
                                currentRectangle = rectangles.get(i);
                                //System.out.println("It's me");
                                if(design.getPositionSettingPanel().getRotationAngle() != 0 && adjustMode){
                                    currentRectangle.rotationAngle = Math.toRadians(design.positionSettingPanel.getRotationAngle());
                                }
                                //currentRectangle.scaleFactor = design.getPositionSettingPanel().getScaleFactor();
                                if(design.getPositionSettingPanel().getScaleFactor() != 0 && adjustMode){
                                    double scaleFactor = design.getPositionSettingPanel().getScaleFactor();
                                    if(scaleFactor < 0){
                                        scaleFactor = Math.abs(1/scaleFactor);
                                    }
                                    int newWidth = (int) (currentRectangle.getWidth() * scaleFactor);
                                    int newHeight = (int) (currentRectangle.getHeight() * scaleFactor);

                                    currentRectangle.setSize(newWidth,newHeight);
                                }
                                if(design.getPositionSettingPanel().getxShear() != 0 || design.getPositionSettingPanel().getyShear() != 0){
                                    int x = currentRectangle.x;
                                    int y = currentRectangle.y;
                                    int width = currentRectangle.width;
                                    int height = currentRectangle.height;

                                    int shearX = design.getPositionSettingPanel().getxShear();
                                    int shearY = design.getPositionSettingPanel().getyShear();
                                    int[] xPoints = {
                                            x,
                                            x + width + (shearY * height),
                                            x + width + (shearY * height) - (shearX * height),
                                            x - (shearX * height)
                                    };

                                    int[] yPoints = {
                                            y,
                                            y - (shearX * width),
                                            y + height - (shearX * width),
                                            y + height
                                    };
                                    rectangles.remove(currentRectangle);
                                    currentPolygon = new Polygon(xPoints, yPoints, 4); // Represent the sheared rectangle as a polygon
                                    polygons.add(currentPolygon);
                                }
                                repaint();
                            }
                        }
                    }
                    if(!triangles.isEmpty()){
                        for (int i = 0; i < triangles.size(); i++) {
                            if (triangles.get(i).contains(e.getPoint())) {
                                lastDragPoint = e.getPoint();
                                currentTriangle = triangles.get(i);
                                if(design.getPositionSettingPanel().getRotationAngle() != 0 && adjustMode) {
                                    currentTriangle.rotationAngle = Math.toRadians(design.positionSettingPanel.getRotationAngle());
                                }
                                if(design.getPositionSettingPanel().getScaleFactor() != 0 && adjustMode){
                                    double scaleFactor = design.getPositionSettingPanel().getScaleFactor();
                                    //System.out.println(scaleFactor);
                                    if(scaleFactor < 0){
                                        scaleFactor = Math.abs(1/scaleFactor);
                                    }
                                    int[] scaledXPoints = new int[currentTriangle.xpoints.length];
                                    //System.out.println(currentTriangle.xpoints[1]);
                                    int[] scaledYPoints = new int[currentTriangle.ypoints.length];
                                    //System.out.println(currentTriangle.ypoints[1]);
                                    for (int j = 0; j < currentTriangle.npoints; j++) {
                                        scaledXPoints[j] = (int) (currentTriangle.xpoints[j] * scaleFactor);
                                        scaledYPoints[j] = (int) (currentTriangle.ypoints[j] * scaleFactor);
                                    }
                                    int newWidth = (int)(currentTriangle.width*scaleFactor);
                                    int newHeight = (int)(currentTriangle.height*scaleFactor);
                                    currentTriangle = new Triangle(scaledXPoints, scaledYPoints, currentTriangle.npoints,color,newWidth,newHeight,(int)(currentTriangle.rotationAngle));
                                    triangles.set(i,currentTriangle);
                                }
                                repaint();
                            }
                        }
                    }
                    if(!ellipses.isEmpty()){
                        for (int i = 0; i < ellipses.size(); i++) {
                            if (ellipses.get(i).contains(e.getPoint())) {
                                lastDragPoint = e.getPoint();
                                currentEllipse = ellipses.get(i);
                                if(design.getPositionSettingPanel().getRotationAngle() != 0 && adjustMode) {
                                    currentEllipse.rotationAngle = Math.toRadians(design.positionSettingPanel.getRotationAngle());
                                }
                                if(design.positionSettingPanel.getScaleFactor() != 0 && adjustMode){
                                    double scaleFactor = design.positionSettingPanel.getScaleFactor();
                                    if(scaleFactor < 0){
                                        scaleFactor = Math.abs(1/scaleFactor);
                                    }
                                    int newWidth = (int)(currentEllipse.width*scaleFactor);
                                    int newHeight = (int)(currentEllipse.height*scaleFactor);

                                    currentEllipse = new ellipse(currentEllipse.x, currentEllipse.y,newWidth,newHeight, currentEllipse.rotationAngle);
                                    ellipses.set(i,currentEllipse);
                                }
                                repaint();
                            }
                        }
                    }
                    if(!lines.isEmpty()){
                        for (int i = 0; i < lines.size(); i++) {
                            System.out.println(lines.get(i).ptSegDist(e.getPoint()));
                            if (lines.get(i).ptSegDist(e.getPoint()) < 450.0) {
                                lastDragPoint = e.getPoint();
                                currentLine = lines.get(i);
                                if(design.getPositionSettingPanel().getRotationAngle() != 0 && adjustMode) {
                                    currentLine.rotationAngle = Math.toRadians(design.positionSettingPanel.getRotationAngle());
                                }
                                if(design.positionSettingPanel.getScaleFactor() != 0 && adjustMode){
                                    double scaleFactor = design.positionSettingPanel.getScaleFactor();
                                    if(scaleFactor < 0){
                                        scaleFactor = Math.abs(1/scaleFactor);
                                    }
                                    int newX1 = (int)(currentLine.x1 * scaleFactor);
                                    int newY1 = (int)(currentLine.y1 * scaleFactor);
                                    int newX2 = (int)(currentLine.x2 * scaleFactor);
                                    int newY2 = (int)(currentLine.y2 * scaleFactor);

                                    System.out.println(currentLine.x1);
                                    System.out.println(currentLine.y1);
                                    System.out.println(currentLine.x2);
                                    System.out.println(currentLine.y2);

                                    //currentLine.setLine(newX1, newY1, newX2, newY2);
                                    currentLine.x1 = newX1;
                                    currentLine.x2 = newX2;
                                    currentLine.y1 = newY1;
                                    currentLine.y2 = newY2;
                                    lines.set(i,currentLine);
                                }
                                repaint();
                            }
                        }
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (selectPen) {
                    pointStart = null;
                } else if(rectangleMode){
                    pointStart = drawRectangle(pointStart,e);
                } else if(triangleMode){
                    pointStart = drawTriangle(pointStart,e);
                } else if(lineMode){
                    pointStart = drawLine(pointStart,e);
                } else if(ellipseMode){
                    pointStart = drawEllipse(pointStart,e);
                } else if(moveMode){
                    lastDragPoint = null;
                }
//                else if(rotationAngle != 0){
//                    rotationAngle = 0;
//                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if(selectPen && !rectangleMode) {
                    pointEnd = e.getPoint();
                    Graphics2D g = (Graphics2D) getGraphics();
                    color = design.getColorSettingPanel().getSelectedColor();
                    g.setColor(color);
                    if (pointStart != null) {
                        g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                        pointStart = pointEnd;
                    }
                } else if(moveMode){
                    if(currentRectangle != null && lastDragPoint != null){
                        //moveRect(lastDragPoint,currentRectangle,e);
                        int dx = e.getX() - lastDragPoint.x;
                        int dy = e.getY() - lastDragPoint.y;
                        currentRectangle.translate(dx, dy);
                        lastDragPoint = e.getPoint();
                        design.positionSettingPanel.setX(String.valueOf(currentRectangle.x + dx));
                        design.positionSettingPanel.setY(String.valueOf(currentRectangle.y + dy));
                        design.positionSettingPanel.setWidth(String.valueOf(currentRectangle.width));
                        design.positionSettingPanel.setHeight(String.valueOf(currentRectangle.height));
                        repaint();
                    } else if(currentTriangle != null && lastDragPoint != null){
                        //moveTri(lastDragPoint,currentTriangle,e);
                        int dx = e.getX() - lastDragPoint.x;
                        int dy = e.getY() - lastDragPoint.y;
                        currentTriangle.translate(dx, dy);
                        lastDragPoint = e.getPoint();
                        design.positionSettingPanel.setX(String.valueOf(currentTriangle.xpoints[0] + dx));
                        design.positionSettingPanel.setY(String.valueOf(currentTriangle.ypoints[0] + dy));
                        design.positionSettingPanel.setWidth(String.valueOf(currentTriangle.width));
                        design.positionSettingPanel.setHeight(String.valueOf(currentTriangle.height));
                        repaint();
                    } else if(currentEllipse != null && lastDragPoint != null){
                        //moveEllipse(lastDragPoint,currentEllipse,e);
                        int dx = e.getX() - lastDragPoint.x;
                        int dy = e.getY() - lastDragPoint.y;
                        currentEllipse.x += dx;
                        currentEllipse.y += dy;
                        lastDragPoint = e.getPoint();
                        design.positionSettingPanel.setX(String.valueOf(currentEllipse.x));
                        design.positionSettingPanel.setY(String.valueOf(currentEllipse.y));
                        design.positionSettingPanel.setWidth(String.valueOf(currentEllipse.width));
                        design.positionSettingPanel.setHeight(String.valueOf(currentEllipse.height));
                        repaint();
                    } else if(currentLine != null && lastDragPoint != null){
                        int dx = e.getX() - lastDragPoint.x;
                        int dy = e.getY() - lastDragPoint.y;
                        currentLine.x1 += dx;
                        currentLine.y1 += dy;
                        currentLine.x2 += dx;
                        currentLine.y2 += dy;
                        int deltaX = Math.abs(currentLine.x2 - currentLine.x1);
                        int deltaY = Math.abs(currentLine.y2 - currentLine.y1);
                        lastDragPoint = e.getPoint();
                        design.positionSettingPanel.setX(String.valueOf(currentLine.x1));
                        design.positionSettingPanel.setY(String.valueOf(currentLine.y1));
                        design.positionSettingPanel.setWidth(String.valueOf(Math.round(Math.sqrt(deltaX*deltaX + deltaY*deltaY))));
                        design.positionSettingPanel.setHeight(String.valueOf("0"));
                        repaint();
                    }
                }
            }
        });


    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (rectangle rect : rectangles) {
            if(rect.rotationAngle != 0){
                Graphics2D g2d = (Graphics2D) g;
                AffineTransform oldTransform = g2d.getTransform();
                double centerX = rect.getX() + rect.getWidth() / 2.0;
                double centerY = rect.getY() + rect.getHeight() / 2.0;

                // Apply the rotation
                g2d.rotate(rect.rotationAngle, centerX, centerY);
                g2d.fill(rect);

                // Reset the transform
                g2d.setTransform(oldTransform);
            }else {
                g.setColor(rect.color);
                g.fillRect(rect.x,rect.y,rect.width,rect.height);
            }
        }
        for(Polygon poly : polygons){
            g.setColor(color);
            //g.drawPolygon(poly);
            g.fillPolygon(poly);
        }
        for (Triangle triangle : triangles){
            if(triangle.rotationAngle != 0){
                Graphics2D g2d = (Graphics2D) g;
                g.setColor(color);

                AffineTransform oldTransform = g2d.getTransform();

                // Compute the centroid of the triangle
                double centroidX = (triangle.xpoints[0] + triangle.xpoints[1] + triangle.xpoints[2]) / 3.0;
                double centroidY = (triangle.ypoints[0] + triangle.ypoints[1] + triangle.ypoints[2]) / 3.0;

                // Apply the rotation
                g2d.rotate(triangle.rotationAngle, centroidX, centroidY);
                g2d.fillPolygon(new int[] {
                        triangle.xpoints[0],
                        triangle.xpoints[1],
                        triangle.xpoints[2]
                }, new int[] {
                        triangle.ypoints[0],
                        triangle.ypoints[1],
                        triangle.ypoints[2]
                }, 3);

                // Reset the transform
                g2d.setTransform(oldTransform);
            }
            else{
                g.setColor(triangle.color);
                g.fillPolygon(triangle.xpoints,triangle.ypoints,triangle.numOfSides);
            }
        }
        for(ellipse elli : ellipses){
            g.setColor(color);
            Graphics2D g2d = (Graphics2D) g;
            if(elli.rotationAngle != 0){
                AffineTransform oldTransform = g2d.getTransform();

                double centroidX = elli.x + elli.width/2.0;
                double centroidY = elli.y + elli.height/2.0;

                g2d.rotate(elli.rotationAngle,centroidX,centroidY);
                g2d.fillOval(elli.x,elli.y,elli.width, elli.height);

                g2d.setTransform(oldTransform);
            }
            else{
                g2d.fillOval(elli.x,elli.y,elli.width,elli.height);
            }
        }
        for(line li : lines){
            if(li.rotationAngle != 0){
                Graphics2D g2d = (Graphics2D) g;
                g.setColor(color);

                AffineTransform oldTransform = g2d.getTransform();
                double centroidX = (li.x1 + li.x2)/2.0;
                double centroidY = (li.y1 + li.y2)/2.0;

                g2d.rotate(li.rotationAngle,centroidX,centroidY);
                g2d.drawLine(li.x1,li.y1,li.x2,li.y2);
                g2d.setTransform(oldTransform);
            }
            else {
                g.setColor(color);
                g.drawLine(li.x1, li.y1, li.x2, li.y2);
            }
        }
    }

    public boolean isSelectPen() {
        return selectPen;
    }

    public void setSelectPen(boolean canDraw) {
        this.selectPen = canDraw;
    }


    public boolean isRectangleMode() {
        return rectangleMode;
    }

    public void setRectangleMode(boolean rectangleMode) {
        this.rectangleMode = rectangleMode;
    }

    public boolean isTriangleMode() {
        return triangleMode;
    }

    public void setTriangleMode(boolean triangleMode) {
        this.triangleMode = triangleMode;
    }

    public boolean isLineMode() {
        return lineMode;
    }

    public void setLineMode(boolean lineMode) {
        this.lineMode = lineMode;
    }

    public boolean isEllipseMode() {
        return ellipseMode;
    }

    public void setEllipseMode(boolean ellipseMode) {
        this.ellipseMode = ellipseMode;
    }

    public boolean isMoveMode() {
        return moveMode;
    }

    public void setMoveMode(boolean moveMode) {
        this.moveMode = moveMode;
    }

    public boolean isAdjustMode() {
        return adjustMode;
    }

    public void setAdjustMode(boolean adjustMode) {
        this.adjustMode = adjustMode;
    }
    private Point drawRectangle(Point pointStart,MouseEvent e){
        Graphics2D g = (Graphics2D) getGraphics();
        color = design.getColorSettingPanel().getSelectedColor();
        g.setColor(color);
        int x = Math.min(pointStart.x, e.getX());
        int y = Math.min(pointStart.y, e.getY());
        int width = Math.abs(pointStart.x - e.getX());
        int height = Math.abs(pointStart.y - e.getY());


        design.positionSettingPanel.setX(String.valueOf(x));
        design.positionSettingPanel.setY(String.valueOf(y));
        design.positionSettingPanel.setWidth(String.valueOf(width));
        design.positionSettingPanel.setHeight(String.valueOf(height));
        g.fillRect(x, y, width, height);
        rectangle newRectangle = new rectangle(x,y,width,height,color,0);
        rectangles.add(newRectangle);
        return pointStart = null;
    }

    private Point drawTriangle(Point pointStart, MouseEvent e){
        pointEnd = e.getPoint();
        Graphics2D g = (Graphics2D) getGraphics();
        color = design.getColorSettingPanel().getSelectedColor();
        g.setColor(color);

        int dx = pointEnd.x - pointStart.x;
        int dy = pointEnd.y - pointStart.y;

        int[] xPoints = {pointStart.x, pointEnd.x, pointEnd.x - dx*2};
        int[] yPoints = {pointStart.y, pointEnd.y, pointEnd.y};

        g.fillPolygon(xPoints, yPoints, 3);

        design.positionSettingPanel.setX(String.valueOf(pointStart.x));
        design.positionSettingPanel.setY(String.valueOf(pointStart.y));
        design.positionSettingPanel.setWidth(String.valueOf(Math.abs(dx*2)));
        design.positionSettingPanel.setHeight(String.valueOf(Math.abs(dy)));
        Triangle newTriangle = new Triangle(xPoints,yPoints,3,color,dx*2,dy,0);
        triangles.add(newTriangle);
        pointStart = null;
        return pointStart;
    }

    private Point drawLine(Point pointStart, MouseEvent e){
        Graphics2D g = (Graphics2D) getGraphics();
        pointEnd = e.getPoint();
        color = design.getColorSettingPanel().getSelectedColor();
        g.setColor(color);
        g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
        int dx = pointEnd.x - pointStart.x;
        int dy = pointEnd.y - pointStart.y;
        design.positionSettingPanel.setX(String.valueOf(pointStart.x));
        design.positionSettingPanel.setY(String.valueOf(pointStart.y));
        design.positionSettingPanel.setWidth(String.valueOf(Math.round(Math.sqrt(dx*dx + dy*dy))));
        design.positionSettingPanel.setHeight("0");
        line newLine = new line(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y,0);
        lines.add(newLine);
        pointStart = null;
        return pointStart;
    }

    private Point drawEllipse(Point pointStart, MouseEvent e){
        pointEnd = e.getPoint();
        Graphics2D g = (Graphics2D) getGraphics();
        color = design.getColorSettingPanel().getSelectedColor();
        g.setColor(color);

        int width = Math.abs(pointEnd.x - pointStart.x);
        int height = Math.abs(pointEnd.y - pointStart.y);


        g.fillOval(pointStart.x, pointStart.y, width,height);

        design.positionSettingPanel.setX(String.valueOf(pointStart.x));
        design.positionSettingPanel.setY(String.valueOf(pointStart.y));
        design.positionSettingPanel.setWidth(String.valueOf(width));
        design.positionSettingPanel.setHeight(String.valueOf(height));

        ellipse newEllipse = new ellipse(pointStart.x,pointStart.y, width,height,0);
        ellipses.add(newEllipse);
        pointStart = null;
        return pointStart;
    }

}
