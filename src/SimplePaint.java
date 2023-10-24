import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class SimplePaint {

    private JFrame frame;
    private JPanel drawPanel;
    private Color currentColor = Color.BLACK;
    private boolean canDraw = false;
    private boolean rectangleMode = false;
    private Point rectangleStart;

    public SimplePaint() {
        frame = new JFrame("Simple Paint");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        drawPanel = new DrawArea();
        drawPanel.setBackground(Color.WHITE);
        frame.add(drawPanel, BorderLayout.CENTER);

        frame.add(createToolbar(), BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private JToolBar createToolbar() {
        JToolBar toolbar = new JToolBar();
        JButton blackButton = new JButton("Black");
        JButton redButton = new JButton("Red");
        JButton greenButton = new JButton("Green");
        JButton blueButton = new JButton("Blue");
        JButton clearButton = new JButton("Clear");
        JButton startDrawingButton = new JButton("Start Drawing");
        JButton stopDrawingButton = new JButton("Stop Drawing");
        JButton rectangleButton = new JButton("Rectangle");

        blackButton.addActionListener(e -> currentColor = Color.BLACK);
        redButton.addActionListener(e -> currentColor = Color.RED);
        greenButton.addActionListener(e -> currentColor = Color.GREEN);
        blueButton.addActionListener(e -> currentColor = Color.BLUE);
        clearButton.addActionListener(e -> {
            Graphics g = drawPanel.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, drawPanel.getWidth(), drawPanel.getHeight());
        });
        startDrawingButton.addActionListener(e -> {
            canDraw = true;
            rectangleMode = false;
        });
        stopDrawingButton.addActionListener(e -> {
            canDraw = false;
            rectangleMode = false;
        });
        rectangleButton.addActionListener(e -> {
            canDraw = true;
            rectangleMode = true;
        });

        toolbar.add(blackButton);
        toolbar.add(redButton);
        toolbar.add(greenButton);
        toolbar.add(blueButton);
        toolbar.add(clearButton);
        toolbar.add(startDrawingButton);
        toolbar.add(stopDrawingButton);
        toolbar.add(rectangleButton);

        return toolbar;
    }

    private class DrawArea extends JPanel {
        Point pointStart = null;
        Point pointEnd = null;

        public DrawArea() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (canDraw) {
                        pointStart = e.getPoint();
                        if (rectangleMode) {
                            rectangleStart = pointStart;
                        }
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (canDraw && rectangleMode) {
                        Graphics g = drawPanel.getGraphics();
                        g.setColor(currentColor);
                        int x = Math.min(rectangleStart.x, e.getX());
                        int y = Math.min(rectangleStart.y, e.getY());
                        int width = Math.abs(rectangleStart.x - e.getX());
                        int height = Math.abs(rectangleStart.y - e.getY());
                        g.drawRect(x, y, width, height);
                        pointStart = null;
                    } else if (canDraw) {
                        pointStart = null;
                    }
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    if (canDraw && !rectangleMode) {
                        pointEnd = e.getPoint();
                        Graphics g = drawPanel.getGraphics();
                        g.setColor(currentColor);
                        if (pointStart != null) {
                            g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                            pointStart = pointEnd;
                        }
                    }
                }
            });
        }
    }
}