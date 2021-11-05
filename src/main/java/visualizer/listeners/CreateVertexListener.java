package visualizer.listeners;

import visualizer.models.Edge;
import visualizer.MainFrame;
import visualizer.models.Vertex;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreateVertexListener implements MouseListener {
    private MainFrame mainFrame;
    public CreateVertexListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY() - 66;
        for (Vertex vertex: mainFrame.getVertices()) {
            if (vertex.getX() <= x && vertex.getX() + 50 >= x && vertex.getY() <= y && vertex.getY() + 50 >= y)
                return;
        }
        for (Edge edge: mainFrame.getEdges()) {
            if (edge.getStartX() <= x && edge.getEndX() >= x && edge.getStartY() <= y && edge.getEndY() >= y)
                return;
        }
        String vertexId = JOptionPane.showInputDialog(mainFrame, null, "vertex", JOptionPane.OK_CANCEL_OPTION);
        while(vertexId != null && vertexId.trim().length() != 1) {
            vertexId = JOptionPane.showInputDialog(mainFrame, "Invalid vertex's name! Repeat:","vertex", JOptionPane.ERROR_MESSAGE);
        }
        if (vertexId == null)
            return;
        createVertex(vertexId, x, y);
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
    private void createVertex(String vertexId, int x, int y) {
        mainFrame.getVertices().add(new Vertex(vertexId, x - 25, y - 25));
        mainFrame.getGraph().add(mainFrame.getVertices().get(mainFrame.getVertices().size() - 1));
        mainFrame.repaint();
    }
}
