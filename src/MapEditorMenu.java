import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MapEditorMenu extends JMenu {

    private JavaTsumugi frame;

    public MapEditorMenu(String label) {
        super(label);
        frame = JavaTsumugi.getInstance();

        JMenuItem newMap = new JMenuItem("新規作成");
        JMenuItem loadMap = new JMenuItem("マップを開く");
        this.add(newMap);
        this.add(loadMap);

        newMap.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    initColumns();
                }
            }
        );

        loadMap.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    loadMap();
                }
            }
        );
    }

    private void initColumns() {
        int columns;

        MapSizeDialog dialog = new MapSizeDialog(frame);
        dialog.setVisible(true);

        if (!dialog.isOKPressed()) {
            return;
        }

        columns = dialog.getMapColumns();

        frame.editorStart(columns);
    }

    private void loadMap() {
        String loadMapName;

        MapLoadDialog dialog = new MapLoadDialog(frame);
        dialog.setVisible(true);

        if (!dialog.isOKPressed()) {
            return;
        }

        loadMapName = dialog.getLoadMapName();

        frame.editorStart(loadMapName);
    }
}
