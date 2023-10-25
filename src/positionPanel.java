import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class positionPanel extends JPanel {
    private JLabel xLabel,yLabel,hLabel,wLabel,aLabel,rLabel,sXAxisLabel,sYAxisLabel;
    private JTextField x;
    private JTextField y;
    private JTextField h;
    private JTextField w;
    private JTextField scale;
    private JTextField sXAxisValue;
    private JTextField sYAxisValue;


    private int rotationAngle;

    private double scaleFactor;


    private double xShear;
    private double yShear;



    private JTextField angle;
    positionPanel(){
        //panel for adjusting size and position
        super(new GridLayout(4,4));

        setBackground(new Color(245,245,245));
        xLabel = new JLabel("x:");
        x = new JTextField();

        add(xLabel);
        add(x);

        yLabel = new JLabel("y:");
        y = new JTextField();
        add(yLabel);
        add(y);

        hLabel = new JLabel("H:");
        h = new JTextField();
        add(hLabel);
        add(h);


        wLabel = new JLabel("W:");
        w = new JTextField();
        add(wLabel);
        add(w);


        rLabel = new JLabel("Scale:");
        scale = new JTextField();
        scale.setText("0");
        scale.setEditable(true);
        scale.addActionListener(e ->{
            scaleFactor = Double.parseDouble(scale.getText());
        });
        add(rLabel);
        add(scale);


        aLabel = new JLabel("Angle:");
        angle = new JTextField();
        angle.setText("0");
        angle.setEditable(true);
        angle.addActionListener(e -> {
            rotationAngle = Integer.parseInt(angle.getText());
            //System.out.println(rotationAngle);
        });
        add(aLabel);
        add(angle);

        sXAxisLabel = new JLabel("Shear Along x");
        sXAxisValue = new JTextField("0");
        sXAxisValue.addActionListener(e->{
            xShear = Double.parseDouble(sXAxisValue.getText());
        });
        sYAxisLabel = new JLabel("Shear Along y");
        sYAxisValue = new JTextField("0");
        sXAxisValue.addActionListener(e->{
            yShear = Double.parseDouble(sYAxisValue.getText());
        });
        add(sXAxisLabel);
        add(sXAxisValue);
        add(sYAxisLabel);
        add(sYAxisValue);


    }

    public void setX(String data){
        x.setText(data);
    }
    public void setY(String data){
        y.setText(data);
    }
    public void setHeight(String data){
        h.setText(data);
    }

    public void setWidth(String data){
        w.setText(data);
    }
    public int getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(int rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public double getxShear() {
        return xShear;
    }

    public void setxShear(int xShear) {
        this.xShear = xShear;
    }

    public double getyShear() {
        return yShear;
    }

    public void setyShear(int yShear) {
        this.yShear = yShear;
    }
}
