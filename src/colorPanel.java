import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class colorPanel extends JPanel {
    private JLabel TextColorLabel;
    private JTextField TextColorField;
    private JColorChooser colorChooser;
    private JButton TextColor;

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    private Color selectedColor;
    colorPanel(){
        TextColorLabel = new JLabel("Color");
        TextColorField = new JTextField("000000");
        //colorField.setPreferredSize(new Dimension(50,0));
        colorChooser = new JColorChooser();
        setBackground(new Color(245,245,245));
        TextColor = new JButton("Choose color");
        selectedColor = Color.BLACK;
        add(TextColorLabel);
        add(TextColorField);
        add(TextColor);

        TextColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color initalcolor = Color.WHITE;
                setSelectedColor(JColorChooser.showDialog(TextColor,"select a color",initalcolor));
                TextColorField.setText(Integer.toHexString(selectedColor.getRGB()).toUpperCase());
            }
        });
    }
}
