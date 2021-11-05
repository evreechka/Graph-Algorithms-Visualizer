package visualizer.utils;

import visualizer.models.Edge;
import visualizer.MainFrame;
import visualizer.models.Vertex;

import java.util.*;

public class AlgorithmsUtils {
    public static void paintStartVertex(MainFrame mainFrame, Vertex start) {
        VertexUtils.removeColor(mainFrame);
        mainFrame.getAlgorithmResult().setText("Please wait...");
        start.setClicked(true);
        mainFrame.repaint();
    }
    public static void paintFSAlgorithms(List<Vertex> path, List<Edge> edgesPath, MainFrame mainFrame, String name) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < path.size(); i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    path.get(i).setClicked(true);
                    edgesPath.get(i - 1).setPainted(true);
                    edgesPath.get(i - 1).getNeighborEdge().setPainted(true);
                    mainFrame.repaint();
                }
                StringBuilder result = new StringBuilder().append(name).append(" : ");
                for (int i = 0; i < path.size(); i++) {
                    result.append(path.get(i).getId()).append(" -> ");
                }
                result.delete(result.length() - 4, result.length());
                mainFrame.getAlgorithmResult().setText(result.toString());
                mainFrame.repaint();
            }
        });
        thread.start();
    }

    public static void paintDijkstraAlgorithm(MainFrame mainFrame, int[] distances, Vertex start, List<Vertex> vertices) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < distances.length; i++) {
                    if (vertices.get(i).equals(start) || distances[i] == Integer.MAX_VALUE)
                        continue;
                    result.append(vertices.get(i).getId()).append("=").append(distances[i]).append(", ");
                }
                if (result.length() == 0)
                    mainFrame.getAlgorithmResult().setText("Vertex " + start.getId() + " hasn't neighbors");
                else {
                    result.delete(result.length() - 2, result.length());
                    mainFrame.getAlgorithmResult().setText(result.toString());
                }

            }
        });
        thread.start();
    }

    public static void paintPrimeAlgorithm(List<Vertex> treeVertices, List<Edge> treeEdges, Map<Vertex, Vertex> children, MainFrame mainFrame) {
        treeVertices.sort(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex vertex, Vertex t1) {
                return vertex.getId().compareTo(t1.getId());
            }
        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder result = new StringBuilder();
                for (int i = 1; i < treeVertices.size(); i++) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    treeVertices.get(i).setClicked(true);
                    treeEdges.get(i - 1).setPainted(true);
                    treeEdges.get(i - 1).getNeighborEdge().setPainted(true);
                    result.append(treeVertices.get(i).getId()).append("=").append(children.get(treeVertices.get(i)).getId()).append(", ");
                    mainFrame.repaint();
                }
                result.delete(result.length() - 2, result.length());
                mainFrame.getAlgorithmResult().setText(result.toString());
            }
        });
        thread.start();
    }

}
