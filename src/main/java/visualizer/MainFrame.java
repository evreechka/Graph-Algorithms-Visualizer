package visualizer;

import visualizer.listeners.*;
import visualizer.models.Edge;
import visualizer.models.Vertex;
import visualizer.utils.EdgeUtils;
import visualizer.utils.VertexUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JPanel graph;
    private List<Vertex> vertices;
    private List<Edge> edges;
    private JMenuBar menuBar;
    private JMenu mode;
    private JMenu file;
    private JMenuItem addVertex;
    private JMenuItem addEdge;
    private JMenuItem removeEdge;
    private JMenuItem removeVertex;
    private JMenuItem newGraph;
    private JMenuItem exit;
    private JMenuItem none;
    private JLabel algorithmResult;
    private JMenu algorithm;
    private JMenuItem bfs;
    private JMenuItem dfs;
    private JMenuItem dijkstra;
    private JMenuItem prime;
    private JLabel modeStatus;
    private Vertex clickedVertex;

    public MainFrame() {
        super("Graph-Algorithms Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        createComponents();
        addListeners();
        setVisible(true);
    }

    private void createComponents() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();

        graph = new JPanel();
        graph.setName("Graph");
        graph.setSize(800, 600);
        graph.setLocation(0, 0);
        graph.setBackground(Color.BLACK);
        graph.setLayout(null);

        menuBar = new JMenuBar();
        file = new JMenu("File");
        mode = new JMenu("Mode");
        algorithm = new JMenu("Algorithm");

        newGraph = new JMenuItem("New");
        newGraph.setName("New");
        exit = new JMenuItem("Exit");
        exit.setName("Exit");
        addVertex = new JMenuItem("Add a Vertex");
        addVertex.setName("Add a Vertex");
        addEdge = new JMenuItem("Add an Edge");
        addEdge.setName("Add an Edge");
        removeVertex = new JMenuItem("Remove a Vertex");
        removeVertex.setName("Remove a Vertex");
        removeEdge = new JMenuItem("Remove an Edge");
        removeEdge.setName("Remove an Edge");
        none = new JMenuItem("None");
        none.setName("None");
        bfs = new JMenuItem("Breadth-First Search");
        bfs.setName("Breadth-First Search");
        dfs = new JMenuItem("Depth-First Search");
        dfs.setName("Depth-First Search");
        dijkstra = new JMenuItem("Dijkstra's Algorithm");
        dijkstra.setName("Dijkstra's Algorithm");
        prime = new JMenuItem("Prim's Algorithm");
        prime.setName("Prim's Algorithm");

        mode.add(addVertex);
        mode.add(addEdge);
        mode.add(removeVertex);
        mode.add(removeEdge);
        mode.add(none);
        file.add(newGraph);
        file.add(exit);
        algorithm.add(bfs);
        algorithm.add(dfs);
        algorithm.add(dijkstra);
        algorithm.add(prime);
        menuBar.add(file);
        menuBar.add(mode);
        menuBar.add(algorithm);


        modeStatus = new JLabel();
        modeStatus.setName("Mode");
        modeStatus.setText("Current Mode -> " + addVertex.getText());
        modeStatus.setHorizontalAlignment(SwingConstants.RIGHT);
        modeStatus.setBackground(Color.BLACK);
        modeStatus.setForeground(Color.WHITE);
        modeStatus.setOpaque(true);

        algorithmResult = new JLabel();
        algorithmResult.setHorizontalAlignment(SwingConstants.CENTER);
        algorithmResult.setName("Display");
        setJMenuBar(menuBar);
        add(graph, BorderLayout.CENTER);
        add(modeStatus, BorderLayout.NORTH);
        add(algorithmResult, BorderLayout.SOUTH);
    }

    private void addListeners() {
        addMouseListener(new CreateVertexListener(MainFrame.this));
        algorithmResult.setVisible(false);
        addVertex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(addVertex.getText());
                MainFrame.this.addMouseListener(new CreateVertexListener(MainFrame.this));
            }
        });
        addEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(addEdge.getText());
                for (Vertex vertex : vertices) {
                    vertex.addMouseListener(new CreateEdgeListener(MainFrame.this));
                }
            }
        });
        removeVertex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(removeVertex.getText());
                for (Vertex vertex : vertices) {
                    vertex.addMouseListener(new RemoveVertexListener(MainFrame.this));
                }
            }
        });
        removeEdge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(removeEdge.getText());
                for (Edge edge : edges) {
                    edge.addMouseListener(new RemoveEdgeListener(MainFrame.this));
                }
            }
        });
        none.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(none.getText());
            }
        });
        newGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(addVertex.getText());
                edges.clear();
                vertices.clear();
                graph.removeAll();
                repaint();
                MainFrame.this.addMouseListener(new CreateVertexListener(MainFrame.this));
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        bfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(none.getText());
                algorithmResult.setText("Please choose a starting vertex");
                modeStatus.setText("Current Mode -> " + none.getText());
                algorithmResult.setVisible(true);
                for (Vertex vertex : vertices) {
                    vertex.addMouseListener(new BFSListener(MainFrame.this));
                }
            }
        });
        dfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(none.getText());
                algorithmResult.setText("Please choose a starting vertex");
                algorithmResult.setVisible(true);
                for (Vertex vertex : vertices) {
                    vertex.addMouseListener(new DFSListener(MainFrame.this));
                }
            }
        });
        dijkstra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(none.getText());
                algorithmResult.setText("Please choose a starting vertex");
                algorithmResult.setVisible(true);
                for (Vertex vertex : vertices) {
                    vertex.addMouseListener(new DijkstraListener(MainFrame.this));
                }
            }
        });
        prime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                deleteActions(none.getText());
                algorithmResult.setText("Please choose a starting vertex");
                algorithmResult.setVisible(true);
                for (Vertex vertex : vertices) {
                    vertex.addMouseListener(new PrimeListener(MainFrame.this));
                }
            }
        });
    }

    public void setClickedVertex(Vertex clickedVertex) {
        this.clickedVertex = clickedVertex;
    }

    public JLabel getAlgorithmResult() {
        return algorithmResult;
    }

    public Vertex getClickedVertex() {
        return clickedVertex;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public JPanel getGraph() {
        return graph;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    private void deleteActions(String status) {
        algorithmResult.setVisible(false);
        VertexUtils.removeColor(this);
        EdgeUtils.removeColor(this);
        clickedVertex = null;
        VertexUtils.deleteVertexListeners(this);
        EdgeUtils.deleteEdgeListeners(this);
        if (getMouseListeners().length != 0)
            MainFrame.this.removeMouseListener(getMouseListeners()[0]);
        modeStatus.setText("Current Mode -> " + status);
    }

}