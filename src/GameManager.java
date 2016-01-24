import java.awt.Graphics;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.sound.sampled.*;

public class GameManager {

    private static final GameManager instance = new GameManager();

    private Mario mario;
    private List<GameObject> gameObjects;
    private List<MovableGameObject> movableGameObjects;

    private Clip bgm;

    HashMap<Integer, Boolean> keys;

    private GameManager() {
        gameObjects = new ArrayList<>();
        movableGameObjects= new ArrayList<>();
        mario = new Mario(320, 160, 9);
        movableGameObjects.add(mario);
        initObj();

        try {
            bgm = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("res/sound/bgm/01-main-theme-overworld.wav"));
            bgm.open(inputStream);
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (LineUnavailableException |
               UnsupportedAudioFileException |
               IOException e) {
            System.out.println("play sound error: " + e.getMessage());
        }

        // キーは押していない状態
        keys = new HashMap<Integer, Boolean>(3);
        keys.put(KeyEvent.VK_LEFT, false);
        keys.put(KeyEvent.VK_RIGHT, false);
        keys.put(KeyEvent.VK_SPACE, false);
    }

    public static GameManager getInstance() {
        return instance;
    }

    public void initObj() {
        gameObjects.add(new Block(256, 180));
        gameObjects.add(new Block(288, 180));
        gameObjects.add(new Block(320, 180));
        gameObjects.add(new Block(352, 180));
        gameObjects.add(new Block(384, 180));
    }

    public void update() {
        mario.keyAction(keys);

        for (int i = 0; i < movableGameObjects.size(); i++) {
            movableGameObjects.get(i).move();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < movableGameObjects.size(); i++) {
            movableGameObjects.get(i).draw(g);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).draw(g);
        }
    }

    /**
     * キーがタイプされたとき
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * キーが押されたとき
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keys.replace(key, true);
        if (key == KeyEvent.VK_LEFT) {
            keys.put(KeyEvent.VK_RIGHT, false);
        }
        else if (key == KeyEvent.VK_LEFT) {
            keys.put(KeyEvent.VK_LEFT, false);
        }
    }

    /**
     * キーが離されたとき
     */
    public void keyReleased(KeyEvent e) {
        keys.replace(e.getKeyCode(), false);
    }
}
