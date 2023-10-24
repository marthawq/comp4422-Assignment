import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuBar extends JMenuBar {

    public drawingPanel getCanvas() {
        return Canvas;
    }

    public void setCanvas(drawingPanel canvas) {
        Canvas = canvas;
    }

    private drawingPanel Canvas;

    public designPanel getDesign() {
        return design;
    }

    public void setDesign(designPanel design) {
        this.design = design;
    }

    private designPanel design;
    public JMenuBar getMb() {
        return Mb;
    }

    public boolean isSelectPen() {
        return selectPen;
    }

    public void setSelectPen(boolean selectPen) {
        this.selectPen = selectPen;
    }

    private boolean selectPen = false;

    JMenuBar Mb;
    JMenu select,shape,pens,text,drag;
    JMenuItem rectangle,line,ellipse,picture,pen,triangle,moveShape,adjustShape;
    MenuBar(drawingPanel Canvas) {
        setCanvas(Canvas);
        setDesign(design);
        Mb = new JMenuBar();
        select = new JMenu("Move");
        shape = new JMenu("Shapes");
        pens = new JMenu("Pens");
        text = new JMenu("Text");
        drag = new JMenu("Tools");

        moveShape = new JCheckBoxMenuItem("Move Element");
        moveShape.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            //System.out.println(selected);
            getCanvas().setMoveMode(selected);
            getCanvas().setEllipseMode(false);
            getCanvas().setTriangleMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setSelectPen(false);
            getCanvas().setLineMode(false);
            getCanvas().setAdjustMode(false);
        });

        select.add(moveShape);

        rectangle = new JCheckBoxMenuItem("Rectangle");
        rectangle.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setRectangleMode(selected);
        });
        line = new JCheckBoxMenuItem("Line");
        line.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setLineMode(selected);
            getCanvas().setMoveMode(false);
            getCanvas().setEllipseMode(false);
            getCanvas().setTriangleMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setSelectPen(false);
            getCanvas().setAdjustMode(false);
        });
        ellipse = new JCheckBoxMenuItem("Ellipse");
        ellipse.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setEllipseMode(selected);
            getCanvas().setMoveMode(false);
            getCanvas().setTriangleMode(false);
            getCanvas().setLineMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setSelectPen(false);
            getCanvas().setAdjustMode(false);
        });
        //arrow = new JCheckBoxMenuItem("Arrow");
        //star = new JCheckBoxMenuItem("Star");
        picture = new JCheckBoxMenuItem("Insert Picture");
        picture.addActionListener(e ->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
        });
        pen = new JCheckBoxMenuItem("Pen");
        pen.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setSelectPen(selected);
            getCanvas().setMoveMode(false);
            getCanvas().setEllipseMode(false);
            getCanvas().setLineMode(false);
            getCanvas().setTriangleMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setAdjustMode(false);

        });
        //pencil = new JCheckBoxMenuItem("Pencil");
        triangle = new JCheckBoxMenuItem("Triangle");
        triangle.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setTriangleMode(selected);
            getCanvas().setMoveMode(false);
            getCanvas().setEllipseMode(false);
            getCanvas().setLineMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setSelectPen(false);
            getCanvas().setAdjustMode(false);
        });
        //pentagon = new JCheckBoxMenuItem("Pentagon");


        adjustShape = new JCheckBoxMenuItem("Adjust Shape");
        adjustShape.addActionListener(e ->{
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setAdjustMode(selected);
            getCanvas().setTriangleMode(false);
            getCanvas().setMoveMode(false);
            getCanvas().setEllipseMode(false);
            getCanvas().setLineMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setSelectPen(false);
        });
        drag.add(adjustShape);


        ButtonGroup shapeSelection = new ButtonGroup();
        shapeSelection.add(rectangle);
        shapeSelection.add(line);
        shapeSelection.add(ellipse);
        //shapeSelection.add(arrow);
        //shapeSelection.add(star);
        //shapeSelection.add(picture);
        shapeSelection.add(triangle);
        shapeSelection.add(moveShape);
        shapeSelection.add(pen);
        shapeSelection.add(adjustShape);

        shape.add(rectangle);
        shape.add(line);
        shape.add(ellipse);
        //shape.add(arrow);
        //shape.add(star);
        //shape.add(picture);
        //shape.add(pentagon);
        shape.add(triangle);

        pens.add(pen);
        //pens.add(pencil);

        Mb.add(select);
        Mb.add(shape);
        Mb.add(pens);
        Mb.add(text);
        Mb.add(drag);

    }



}
