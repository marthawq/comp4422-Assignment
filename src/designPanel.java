import javax.swing.*;
import java.awt.*;

public class designPanel extends JPanel {

    public positionPanel getPositionSettingPanel() {
        return positionSettingPanel;
    }

    public void setPositionSettingPanel(positionPanel positionSettingPanel) {
        this.positionSettingPanel = positionSettingPanel;
    }

    public strokePanel getStrokeSettingPanel() {
        return strokeSettingPanel;
    }

    public void setStrokeSettingPanel(strokePanel strokeSettingPanel) {
        this.strokeSettingPanel = strokeSettingPanel;
    }

    public colorPanel getColorSettingPanel() {
        return colorSettingPanel;
    }

    public void setColorSettingPanel(colorPanel colorSettingPanel) {
        this.colorSettingPanel = colorSettingPanel;
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
