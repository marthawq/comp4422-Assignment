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



    JMenuBar Mb;
    JMenu select,shape,pens,text,drag,clear;
    JMenuItem rectangle,line,ellipse,picture,pen,triangle,moveShape,adjustShape,clearScreen,clearObject;
    MenuBar(drawingPanel Canvas) {
        setCanvas(Canvas);
        setDesign(design);
        Mb = new JMenuBar();
        select = new JMenu("Move");
        shape = new JMenu("Shapes");
        pens = new JMenu("Pens");
        text = new JMenu("Text");
        drag = new JMenu("Tools");
        clear = new JMenu("Clear");

        moveShape = new JCheckBoxMenuItem("Move Element");
        moveShape.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setMoveMode(selected);
            getCanvas().setEllipseMode(false);
            getCanvas().setTriangleMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setSelectPen(false);
            getCanvas().setLineMode(false);
            getCanvas().setAdjustMode(false);
            getCanvas().setClearObjectMode(false);
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
            getCanvas().setClearObjectMode(false);
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
            getCanvas().setClearObjectMode(false);
        });

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
            getCanvas().setClearObjectMode(false);

        });

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
            getCanvas().setClearObjectMode(false);
        });



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
            getCanvas().setClearObjectMode(false);
        });
        drag.add(adjustShape);


        clearObject = new JCheckBoxMenuItem("Erase Shape");
        clearObject.addActionListener(e -> {
            AbstractButton aButton = (AbstractButton) e.getSource();
            boolean selected = aButton.getModel().isSelected();
            getCanvas().setClearObjectMode(selected);
            getCanvas().setAdjustMode(false);
            getCanvas().setTriangleMode(false);
            getCanvas().setMoveMode(false);
            getCanvas().setEllipseMode(false);
            getCanvas().setLineMode(false);
            getCanvas().setRectangleMode(false);
            getCanvas().setSelectPen(false);
        });

        ButtonGroup shapeSelection = new ButtonGroup();
        shapeSelection.add(rectangle);
        shapeSelection.add(line);
        shapeSelection.add(ellipse);

        shapeSelection.add(triangle);
        shapeSelection.add(moveShape);
        shapeSelection.add(pen);
        shapeSelection.add(adjustShape);
        shapeSelection.add(clearObject);

        shape.add(rectangle);
        shape.add(line);
        shape.add(ellipse);
        shape.add(triangle);

        pens.add(pen);

        clear.add(clearObject);

        Mb.add(select);
        Mb.add(shape);
        Mb.add(pens);
        Mb.add(text);
        Mb.add(drag);
        Mb.add(clear);

    }



}
