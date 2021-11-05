package visualizer.listeners;

import visualizer.models.Edge;
import visualizer.MainFrame;
import visualizer.models.Vertex;
import visualizer.utils.AlgorithmsUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class DijkstraListener implements MouseListener {
    private MainFrame mainFrame;
    private int[] distances;

    public DijkstraListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Vertex start = (Vertex) mouseEvent.getComponent();
        AlgorithmsUtils.paintStartVertex(mainFrame, start);
        dijkstra(start);
        AlgorithmsUtils.paintDijkstraAlgorithm(mainFrame, distances, start, mainFrame.getVertices());
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

    private void dijkstra(Vertex start) {
        List<Vertex> vertices = mainFrame.getVertices();
        distances = new int[vertices.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[vertices.indexOf(start)] = 0;
        List<Node> queue = new ArrayList<>();
        queue.add(new Node(vertices.indexOf(start), 0));
        while (!queue.isEmpty()) {
            queue.sort(new Comparator<Node>() {
                @Override
                public int compare(Node node, Node t1) {
                    return Integer.compare(node.distance, t1.distance);
                }
            });
            int v = queue.remove(0).index;
            for (Edge edge : vertices.get(v).getNeighbors().keySet()) {
                int toIndex = vertices.indexOf(vertices.get(v).getNeighbors().get(edge));
                int len = edge.getWeight();
                if (distances[v] + len < distances[toIndex]) {
                    distances[toIndex] = distances[v] + len;
                    queue.add(new Node(toIndex, distances[toIndex]));
                }
            }
        }
    }

    static class Node {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", distance=" + distance +
                    '}';
        }
    }
}
