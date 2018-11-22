package view;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {

    private int x;
    private int y;

    private double rotation;

    public CustomJButton(String text, int x, int y) {
        super(text);
        this.x=x;
        this.y=y;
        this.rotation=0;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
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
        Graphics2D g2 = (Graphics2D) g;
        g2.rotate(this.rotation, getWidth() / 2, getHeight() / 2);
        super.paintComponent(g);
    }
}
