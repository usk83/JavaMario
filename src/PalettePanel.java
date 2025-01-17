import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JPanel;

public class PalettePanel extends JPanel implements MouseListener {
    private static final PalettePanel instance = new PalettePanel();

    public static final int COL = 1;
    public static final int ROW = 15;
    public static final int TILE_SIZE = 32;

    private int width;
    private int height;

    private Tsumugi tsumugi;
    private Block block;
    private CoinBlock coinBlock;
    private Coin coin;
    private Kuribo kuribo;
    private Goal goal;

    private int selectedNo;

    public PalettePanel() {
        setPreferredSize(new Dimension(COL * TILE_SIZE, ROW * TILE_SIZE));
        width = COL * TILE_SIZE;
        height = ROW * TILE_SIZE;

        tsumugi = new Tsumugi(0, 0);
        block = new Block(0, 0);
        coinBlock = new CoinBlock(0, 0);
        coin = new Coin(0, 0);
        kuribo = new Kuribo(0, 0);
        goal = new Goal(0, 0);

        selectedNo = 0;
        addMouseListener(this);
    }

    public static PalettePanel getInstance() {
        return instance;
    }

    public void adjustSize() {
        setPreferredSize(new Dimension(width, height + 30));
        height += 30;
    }

    public void paintComponent(Graphics g) {
        g.setColor(new Color(32, 0, 0));
        g.fillRect(0, 0, width, height);
        tsumugi.draw(g, 0 * TILE_SIZE, 0 * TILE_SIZE, 0);
        block.draw(g, 0 * TILE_SIZE, 1 * TILE_SIZE, 0);
        coinBlock.draw(g, 0 * TILE_SIZE, 2 * TILE_SIZE, 0);
        coin.draw(g, 0 * TILE_SIZE, 3 * TILE_SIZE, 0);
        kuribo.draw(g, 0 * TILE_SIZE, 4 * TILE_SIZE, 0);
        goal.draw(g, 0 * TILE_SIZE, 5 * TILE_SIZE, 0);
        g.setColor(Color.WHITE);
        g.fillRect(0 * TILE_SIZE, 6 * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        g.setColor(Color.RED);
        g.drawRect(0, selectedNo * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public char getSelected() {
        switch(selectedNo) {
            case 0:
                return 't';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'c';
            case 4:
                return 'k';
            case 5:
                return 'G';
            default:
                return ' ';
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        int clicked = e.getY() / TILE_SIZE;

        if (clicked > 6) {
            return;
        }

        selectedNo = clicked;
        repaint();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}
