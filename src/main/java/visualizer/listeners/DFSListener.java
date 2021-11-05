package visualizer.listeners;

import visualizer.models.Edge;
import visualizer.MainFrame;
import visualizer.models.Vertex;
import visualizer.utils.AlgorithmsUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DFSListener implements MouseListener {
    private MainFrame mainFrame;
    private List<Edge> edgesPath = new ArrayList<>();
    private List<Vertex> verticesPath = new ArrayList<>();
    private List<Vertex> visited = new ArrayList<>();
    public DFSListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Vertex start = (Vertex) mouseEvent.getComponent();
        AlgorithmsUtils.paintStartVertex(mainFrame, start);
        dfs(start);
        AlgorithmsUtils.paintFSAlgorithms(verticesPath, edgesPath, mainFrame, "DFS");
    }

    private void dfs(Vertex vertex) {
        verticesPath.add(vertex);
        visited.add(vertex);
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
                dfs(neighbor);
            }
        }
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
}
