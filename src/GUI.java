import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.beancontext.BeanContextServiceAvailableEvent;
import java.util.ArrayList;

public class GUI{
    GUI(){
        JFrame f = new JFrame("Simple Drawing");
        f.setLayout(new BorderLayout());

        
        designPanel designSettingPanel = new designPanel();
        drawingPanel Canvas = new drawingPanel(designSettingPanel);
        MenuBar Menus = new MenuBar(Canvas);
        
        //f.setLayout(new BorderLayout());
        f.setJMenuBar(Menus.getMb());
        f.add(designSettingPanel,BorderLayout.LINE_END);
        f.add(Canvas,BorderLayout.CENTER);

        f.setSize(1000,1000);
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //f.setUndecorated(true);
    }



}
