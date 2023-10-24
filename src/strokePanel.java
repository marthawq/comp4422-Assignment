import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class strokePanel extends JPanel {
    private final JLabel StrokeColorLabel,StrokePositionLabel,StrokeWeightLabel;
    private final JTextField StrokeColorField,StrokeWeightField;
    private final JComboBox<String> StrokePosition;

    private final JButton StrokeColor;
    strokePanel(){
        setName("stroke");
        StrokeColorField = new JTextField("000000");
        StrokeColorLabel = new JLabel("Stroke Color");
        String[] sides = {"All","Left","Right","Top","Bottom"};
        StrokePosition = new JComboBox<String>(sides);
        StrokeWeightLabel = new JLabel("Weight");
        StrokeWeightField = new JTextField("1");
        StrokePositionLabel = new JLabel("Strokes per side");
        StrokeColor = new JButton("Choose Color");

        StrokeColor.addActionListener(new AbstractAction("Choose") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color initialcolor = Color.BLACK;
                Color color = JColorChooser.showDialog(StrokeColor,"select a color",initialcolor);
                if (color == null){
                    StrokeColorField.setText(Integer.toHexString(initialcolor.getRGB()).toUpperCase());
                } else{
                    StrokeColorField.setText(Integer.toHexString(color.getRGB()).toUpperCase());
                }

            }
        });

        add(StrokeColorLabel);
        add(StrokeColorField);
        add(StrokeColor);
        add(StrokeWeightLabel);
        add(StrokeWeightField);
        add(StrokePositionLabel);
        add(StrokePosition);
        setBackground(new Color(245,245,245));
    }
}
