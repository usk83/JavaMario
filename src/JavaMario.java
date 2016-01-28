import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class JavaMario extends JFrame implements KeyListener {

    private static final JavaMario instance = new JavaMario();

    TitlePanel titlePanel;
    JScrollPane scrollPane;

    private JavaMario() {
        setTitle("JavaMario");
    }

    public void init() {
        titlePanel = new TitlePanel();
        getContentPane().add(titlePanel);

        initManuBar();

        pack();
        setResizable(false);
    }

    public static JavaMario getInstance() {
        return instance;
    }

    private void initManuBar() {
        // ファイルメニュー
        JMenu gameStartMenu = new GameStartMenu("ゲームプレイ");
        JMenu mapEditorMenu = new MapEditorMenu("マップ作成");

        // メニューバー
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameStartMenu);
        menuBar.add(mapEditorMenu);
        setJMenuBar(menuBar);
    }

    public void gameStart(String mapFileName) {
        getContentPane().remove(titlePanel);

        JMenu gameMenu = new GameMenu("メニュー");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);

        MainPanel mainPanel = new MainPanel(mapFileName);
        getContentPane().add(mainPanel);
        addKeyListener(mainPanel);
        pack();
    }

    private void initEditorStart() {
        getContentPane().remove(titlePanel);

        JMenu editorMenu = new EditorMenu("メニュー");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(editorMenu);
        setJMenuBar(menuBar);
    }

    public void editorStart(int columns) {
        initEditorStart();
        EditorPanel editorPanel = new EditorPanel(columns);
        scrollPane = new JScrollPane(editorPanel);

        getContentPane().add(scrollPane);

        pack();

        if (columns > editorPanel.DEFAULT_COL) {
            editorPanel.adjustSize();
            pack();
        }

        editorPanel.setColumns(columns);
    }

    public void editorStart(String loadMapName) {
        initEditorStart();
        EditorPanel editorPanel = new EditorPanel(loadMapName);
        scrollPane = new JScrollPane(editorPanel);

        getContentPane().add(scrollPane);

        pack();

        if (editorPanel.getCol() > editorPanel.DEFAULT_COL) {
            editorPanel.adjustSize();
            pack();
        }

        editorPanel.setColumns(editorPanel.getCol());
    }

    public void keyTyped(KeyEvent e) {
    }
    public void keyPressed(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        JavaMario frame = JavaMario.getInstance();
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
