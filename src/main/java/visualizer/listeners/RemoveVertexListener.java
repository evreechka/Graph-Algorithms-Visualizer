package visualizer.listeners;

import visualizer.MainFrame;
import visualizer.models.Vertex;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RemoveVertexListener implements MouseListener {
    private MainFrame mainFrame;
    public RemoveVertexListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Vertex deletedVertices = (Vertex) mouseEvent.getComponent();
        mainFrame.getGraph().remove(deletedVertices);
        mainFrame.getVertices().remove(deletedVertices);
        for(int i = 0; i < mainFrame.getEdges().size(); i++) {
            if (!mainFrame.getEdges().get(i).getVertices().contains(deletedVertices))
                continue;
            mainFrame.getGraph().remove(mainFrame.getEdges().get(i));
            mainFrame.getGraph().remove(mainFrame.getEdges().get(i).getWeightLabel());
            mainFrame.getEdges().remove(mainFrame.getEdges().get(i));
            i--;
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
