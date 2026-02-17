package com.example.demo_hstu_pathfinder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ShortestPath {

    public int maxVertices = 14;

    public int[][] adjacencyMatrix = new int[maxVertices][maxVertices];
    public int[][] shortestPath = new int[maxVertices][maxVertices];
    public int[][] prevPath = new int[maxVertices][maxVertices];

    private static final int INF = 1000000;


    public ShortestPath() {
        readAdjMat();
        calculateShortestPath();
    }

    private void readAdjMat() {
        try {
            InputStream is = getClass().getResourceAsStream("Adjacency.txt");
            if (is == null) {
                throw new RuntimeException("Adjacency.txt not found in resources.");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            int row = 0;

            while ((line = br.readLine()) != null && row < maxVertices) {

                String[] nums = line.trim().split("\\s+");

                for (int col = 0; col < maxVertices; col++) {
                    int num = Integer.parseInt(nums[col]);

                    // If your file uses 0 for no edge (except diagonal),
                    // convert it to INF
                    if (num == 0 && row != col) {
                        adjacencyMatrix[row][col] = INF;
                    } else {
                        adjacencyMatrix[row][col] = num;
                    }
                }
                row++;
            }

            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void calculateShortestPath() {

        // Initialize shortestPath and prevPath
        for (int i = 0; i < maxVertices; i++) {
            for (int j = 0; j < maxVertices; j++) {

                shortestPath[i][j] = adjacencyMatrix[i][j];

                if (i == j) {
                    prevPath[i][j] = i;
                } else if (adjacencyMatrix[i][j] != INF) {
                    prevPath[i][j] = i;
                } else {
                    prevPath[i][j] = -1;
                }
            }
        }

        // Floyd–Warshall
        for (int k = 0; k < maxVertices; k++) {
            for (int i = 0; i < maxVertices; i++) {
                for (int j = 0; j < maxVertices; j++) {

                    if (shortestPath[i][k] != INF &&
                            shortestPath[k][j] != INF &&
                            shortestPath[i][k] + shortestPath[k][j] < shortestPath[i][j]) {

                        shortestPath[i][j] = shortestPath[i][k] + shortestPath[k][j];

                        prevPath[i][j] = prevPath[k][j];
                    }
                }
            }
        }
    }

    public List<Integer> pathConstruct(int u, int v) {

        LinkedList<Integer> path = new LinkedList<>();

        // No path exists
        if (prevPath[u][v] == -1) {
            return path;
        }

        path.add(v);

        while (u != v) {
            v = prevPath[u][v];
            path.addFirst(v);
        }

        return path;
    }

    public int getDistance(int u, int v) {
        return shortestPath[u][v] == INF ? -1 : shortestPath[u][v];
    }


}
