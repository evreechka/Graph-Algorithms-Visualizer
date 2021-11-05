package visualizer.utils;

import visualizer.MainFrame;
import visualizer.models.Vertex;

public class VertexUtils {
    public static void removeColor(MainFrame mainFrame) {
        for (Vertex vertex: mainFrame.getVertices()) {
            vertex.setClicked(false);
        }
        mainFrame.repaint();
    }
    public static void deleteVertexListeners(MainFrame mainFrame) {
        for (Vertex vertex: mainFrame.getVertices()) {
            if (vertex.getMouseListeners().length != 0)
                vertex.removeMouseListener(vertex.getMouseListeners()[0]);
        }
    }
}
