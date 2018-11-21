package view;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {

    private int x;
    private int y;

    public CustomJButton(String text, int x, int y) {
        super(text);
        this.x=x;
        this.y=y;
    }

    public int getSpecialX() {
        return x;
    }

    public int getSpecialY() {
        return y;
    }

    protected void paintComponent(Graphics g) {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
