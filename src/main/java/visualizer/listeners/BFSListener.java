package visualizer.listeners;

import visualizer.models.Edge;
import visualizer.MainFrame;
import visualizer.models.Vertex;
import visualizer.utils.AlgorithmsUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class BFSListener implements MouseListener {
    private MainFrame mainFrame;
    private List<Edge> edgesPath;
    private List<Vertex> verticesPath;
    public BFSListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Vertex start = (Vertex) mouseEvent.getComponent();
        AlgorithmsUtils.paintStartVertex(mainFrame, start);
        bfs(start);
        AlgorithmsUtils.paintFSAlgorithms(verticesPath, edgesPath, mainFrame, "BFS");
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
    private void bfs(Vertex start) {
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(start);
        List<Vertex> visited = new ArrayList<>();
        visited.add(start);
        verticesPath = new ArrayList<>();
        edgesPath = new ArrayList<>();
        verticesPath.add(start);
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            List<Edge> edges = new ArrayList<>(vertex.getNeighbors().keySet());
            edges.sort(new Comparator<Edge>() {
                @Override
                public int compare(Edge edge, Edge t1) {
                    return Integer.compare(edge.getWeight(), t1.getWeight());
                }
            });
            for (Edge edge: edges) {
                Vertex neighbor = vertex.getNeighbors().get(edge);
                if (!visited.contains(neighbor)) {
                    edgesPath.add(edge);
                    queue.add(neighbor);
                    visited.add(neighbor);
                    verticesPath.add(neighbor);
                }
            }
        }
    }
}
