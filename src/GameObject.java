import java.awt.*;
import javax.swing.*;

public class GameObject {
    private static final int SCALE = 2;

    protected float px, py;
    protected int size;
    protected Image icon;

    public GameObject(float _px, float _py, String path) {
        px = _px;
        py = _py;

        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource(path));
        icon = ii.getImage();
        size = icon.getWidth(null) * SCALE;
        icon = icon.getScaledInstance(size, -1, Image.SCALE_FAST);
    }

    public void move() {
    }

    public void draw(Graphics g) {
        g.drawImage(icon, (int)px, (int)py, null);
    }

    public float getPx(){
        return px;
    }

    public void setPx(int px){
        this.px = px;
    }

    public float getPy(){
        return py;
    }

    public void setPy(int py){
        this.py = py;
    }

    protected void loadImage(String name) {
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource(name));
        icon = ii.getImage();
    }
}
