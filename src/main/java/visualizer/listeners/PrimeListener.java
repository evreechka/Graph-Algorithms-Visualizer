package visualizer.listeners;

import visualizer.models.Edge;
import visualizer.MainFrame;
import visualizer.models.Vertex;
import visualizer.utils.AlgorithmsUtils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class PrimeListener implements MouseListener{
    private MainFrame mainFrame;
    private List<Vertex> treeVertices;
    private List<Edge> treeEdges;
    private Map<Vertex, Vertex> children;
    public PrimeListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Vertex start = (Vertex) mouseEvent.getComponent();
        AlgorithmsUtils.paintStartVertex(mainFrame, start);
        prime(start);
        AlgorithmsUtils.paintPrimeAlgorithm(treeVertices, treeEdges, children, mainFrame);
    }
    private void prime(Vertex start) {
        List<Vertex> vertices = mainFrame.getVertices();
        treeVertices = new ArrayList<>();
        treeEdges = new ArrayList<>();
        children = new HashMap<>();
        treeVertices.add(start);
        while (treeVertices.size() != vertices.size()) {
            int minWeight = Integer.MAX_VALUE;
            Vertex parentVertex = null;
            Vertex childVertex = null;
            Edge minEdge = null;
            for (Vertex treeVertex: treeVertices) {
                for (Edge edge: treeVertex.getNeighbors().keySet()) {
                    if (edge.getWeight() < minWeight && !treeVertices.contains(treeVertex.getNeighbors().get(edge))) {
                        minWeight = edge.getWeight();
                        parentVertex = treeVertex;
                        childVertex = treeVertex.getNeighbors().get(edge);
                        minEdge = edge;
                    }
                }
            }
            if (minEdge == null) {
                continue;
            }
            treeVertices.add(childVertex);
            children.put(childVertex, parentVertex);
            treeEdges.add(minEdge);
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
