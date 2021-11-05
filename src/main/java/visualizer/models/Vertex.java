package visualizer.models;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Vertex extends JPanel {
    private JLabel vertexLabel;
    private Map<Edge, Vertex> neighbors = new HashMap<>();
    private int X;
    private int Y;
    private boolean clicked = false;
    private String id;
    public Vertex(String id, int vertexX, int vertexY) {
        this.X = vertexX;
        this.Y = vertexY;
        this.id = id;
        initVertex();
        setSize(50, 50);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setLocation(X, Y);
        add(vertexLabel, BorderLayout.CENTER);
    }

    public Map<Edge, Vertex> getNeighbors() {
        return neighbors;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
    public String getId() {
        return id;
    }

    public void addNeighbor(Vertex vertex, Edge edge) {
        neighbors.put(edge, vertex);
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (clicked)
            g.setColor(Color.ORANGE);
        else
            g.setColor(Color.WHITE);
        g.fillOval(0, 0, 50, 50);
    }
    private void initVertex() {
        setName("Vertex " + id);
        vertexLabel = new JLabel();
        vertexLabel.setText(id);
        vertexLabel.setName("VertexLabel " + id);
        vertexLabel.setSize(50, 50);
        vertexLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
