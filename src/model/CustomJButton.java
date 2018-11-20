package model;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {

    public CustomJButton(String text) {
        super(text);
    }

    protected void paintComponent(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
