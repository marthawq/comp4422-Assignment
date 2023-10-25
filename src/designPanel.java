import javax.swing.*;
import java.awt.*;

public class designPanel extends JPanel {

    public positionPanel getPositionSettingPanel() {
        return positionSettingPanel;
    }


    public strokePanel getStrokeSettingPanel() {
        return strokeSettingPanel;
    }


    public colorPanel getColorSettingPanel() {
        return colorSettingPanel;
    }


    positionPanel positionSettingPanel;
    strokePanel strokeSettingPanel;
    colorPanel colorSettingPanel;
    designPanel(){
        setPreferredSize(new Dimension(400,0));
        setBackground(new Color(245,245,245));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        positionSettingPanel = new positionPanel();
        strokeSettingPanel = new strokePanel();
        colorSettingPanel = new colorPanel();
        add(positionSettingPanel);
        add(strokeSettingPanel);
        add(colorSettingPanel);
    }
}
