package visualizer.utils;

import visualizer.models.Edge;
import visualizer.MainFrame;

public class EdgeUtils {
    public static void removeColor(MainFrame mainFrame) {
        for (Edge edge: mainFrame.getEdges()) {
            edge.setPainted(false);
        }
        mainFrame.repaint();
    }
    public static void deleteEdgeListeners(MainFrame mainFrame) {
        for (Edge edge: mainFrame.getEdges()) {
            if (edge.getMouseListeners().length != 0)
                edge.removeMouseListener(edge.getMouseListeners()[0]);
        }
    }
}
