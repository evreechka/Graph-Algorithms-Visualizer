package visualizer.listeners;

import visualizer.models.Edge;
import visualizer.MainFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RemoveEdgeListener implements MouseListener {
    private MainFrame mainFrame;

    public RemoveEdgeListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Edge deletedEdge = (Edge) mouseEvent.getComponent();
        for (int i = 0; i < mainFrame.getEdges().size(); i++) {
            if (mainFrame.getEdges().get(i).getVertices().contains(deletedEdge.getVertices().get(0)) && mainFrame.getEdges().get(i).getVertices().contains(deletedEdge.getVertices().get(1))) {
                mainFrame.getGraph().remove(mainFrame.getEdges().get(i));
                mainFrame.getGraph().remove(mainFrame.getEdges().get(i).getWeightLabel());
                mainFrame.getEdges().remove(mainFrame.getEdges().get(i));
                i--;
            }
        }
        mainFrame.repaint();
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
