package visualizer.models;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Edge extends JComponent {
    private List<Vertex> vertices;
    private int diagonal;
    private Edge neighborEdge;
    private Integer weight;
    private JLabel weightLabel;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private boolean isPainted;
    public Edge(Vertex vertex1, Vertex vertex2, Integer weight) {
        setName(String.format("Edge <%s -> %s>", vertex1.getId(), vertex2.getId()));
        this.weight = weight;
        vertices = new ArrayList<>();
        vertices.add(vertex1);
        vertices.add(vertex2);
        setLayout(null);
        locateEdge();
        setBounds(startX, startY, endX - startX, Math.abs(endY - startY));
        weightLabel = new JLabel();
        weightLabel.setForeground(Color.ORANGE);
        weightLabel.setBackground(Color.BLACK);
        weightLabel.setOpaque(true);
        weightLabel.setName(String.format("EdgeLabel <%s -> %s>", vertex1.getId(), vertex2.getId()));
        weightLabel.setText(weight.toString());
        weightLabel.setLabelFor(this);
        weightLabel.setLocation(startX + (endX - startX) / 2 - 20, startY + Math.abs(endY - startY) / 2);
        weightLabel.setSize(50, 50);
        weightLabel.setVerticalAlignment(SwingConstants.TOP);
    }

    public void setNeighborEdge(Edge neighborEdge) {
        this.neighborEdge = neighborEdge;
    }

    public Edge getNeighborEdge() {
        return neighborEdge;
    }

    public void setPainted(boolean painted) {
        isPainted = painted;
    }

    public Integer getWeight() {
        return weight;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public void updateWeight(int newWeight) {
        weight = newWeight;
        weightLabel.setText(weight.toString());
    }
    public JLabel getWeightLabel() {
        return weightLabel;
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke pen1 = new BasicStroke(5); //толщина линии 20
        g2.setStroke(pen1);
        if (isPainted)
            g2.setColor(Color.ORANGE);
        else
            g2.setColor(Color.WHITE);
        switch (diagonal) {
            case 1:
                g2.drawLine(0, 0, endX - startX, Math.abs(endY - startY));
                break;
            case 2:
                g2.drawLine(endX - startX, 0, 0, Math.abs(endY - startY));
                break;
        }

    }

    private void locateEdge() {
        if (vertices.get(0).getLocation().x < vertices.get(1).getLocation().x) {
            startX = vertices.get(0).getLocation().x + 25;
            endX = vertices.get(1).getLocation().x + 25;
        } else {
            startX = vertices.get(1).getLocation().x + 25;
            endX = vertices.get(0).getLocation().x + 25;
        }
        if (vertices.get(0).getLocation().y < vertices.get(1).getLocation().y) {
            startY = vertices.get(0).getLocation().y + 25;
            endY = vertices.get(1).getLocation().y + 25;
        } else {
            startY = vertices.get(1).getLocation().y + 25;
            endY = vertices.get(0).getLocation().y + 25;
        }
        int subX = vertices.get(0).getLocation().x - vertices.get(1).getLocation().x;
        int subY = vertices.get(0).getLocation().y - vertices.get(1).getLocation().y;
        if (subX <= 0 && subY <= 0) {
            diagonal = 1;
        } else if (subX >= 0 && subY >= 0) {
            diagonal = 1;
        } else if (subX <= 0) {
            diagonal = 2;
        } else {
            diagonal = 2;
        }
    }
}
