package visualizer.listeners;

import visualizer.models.Edge;
import visualizer.MainFrame;
import visualizer.models.Vertex;
import visualizer.utils.VertexUtils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreateEdgeListener implements MouseListener {
    private MainFrame mainFrame;

    public CreateEdgeListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Vertex currVertex = (Vertex) mouseEvent.getComponent();
        if (mainFrame.getClickedVertex() != null && currVertex != mainFrame.getClickedVertex()) {
            String weight = JOptionPane.showInputDialog(mainFrame, "Input weight of the edge:", "Edge", JOptionPane.OK_CANCEL_OPTION);
            while (weight != null && !weight.matches("[+-]?[1-9][0-9]*")) {
                weight = JOptionPane.showInputDialog(mainFrame, "Invalid weight! Repeat:", "Edge", JOptionPane.OK_CANCEL_OPTION);
            }
            if (weight != null)
                connectVertices(mainFrame.getClickedVertex(), currVertex, Integer.parseInt(weight));
            mainFrame.setClickedVertex(null);
            VertexUtils.removeColor(mainFrame);
        } else {
            mainFrame.setClickedVertex(currVertex);
            currVertex.setClicked(true);
            currVertex.repaint();
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
    private void connectVertices(Vertex vertex1, Vertex vertex2, int weight) {
        boolean isUpdate = false;
        for (Edge edge: mainFrame.getEdges()) {
            if (edge.getVertices().contains(vertex1) && edge.getVertices().contains(vertex2)) {
                edge.updateWeight(weight);
                mainFrame.repaint();
                isUpdate = true;
            }

        }
        if (isUpdate)
            return;
        mainFrame.getEdges().add(new Edge(vertex1, vertex2, weight));
        mainFrame.getEdges().add(new Edge(vertex2, vertex1, weight));
        vertex1.addNeighbor(vertex2, mainFrame.getEdges().get(mainFrame.getEdges().size() - 2));
        mainFrame.getEdges().get(mainFrame.getEdges().size() - 2).setNeighborEdge(mainFrame.getEdges().get(mainFrame.getEdges().size() - 1));
        mainFrame.getEdges().get(mainFrame.getEdges().size() - 1).setNeighborEdge(mainFrame.getEdges().get(mainFrame.getEdges().size() - 2));
        vertex2.addNeighbor(vertex1, mainFrame.getEdges().get(mainFrame.getEdges().size() - 1));
        mainFrame.getGraph().add(mainFrame.getEdges().get(mainFrame.getEdges().size() - 2));
        mainFrame.getGraph().add(mainFrame.getEdges().get(mainFrame.getEdges().size() - 1));
        mainFrame.getGraph().add(mainFrame.getEdges().get(mainFrame.getEdges().size() - 2).getWeightLabel());
        mainFrame.repaint();
    }
}
