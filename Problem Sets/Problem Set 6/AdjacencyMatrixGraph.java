package sg.edu.nus.cs2020;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

/**
 * Public Class: AdjacencyMatrixGraph
 * 
 * Description: This class generates the Adjacency Matrix, which serves as the
 * implicit representation of the underlying Graph.
 * 
 * @author Niu Yunpeng
 */
public class AdjacencyMatrixGraph {
	// Stores the path to the file
	private Path m_pathname;

	// Stores the Graph edge relationships
	private int[][] m_adjacencyMatrix;

	// Public constructor for AdjacencyMatrixGraph
	public AdjacencyMatrixGraph(String filename) {
		this.m_pathname = Paths.get(filename);

		try {
			constructGraph();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This method constructs the Graph from the data found in the text file.
	public void constructGraph() throws Exception {
		List<String> allLines = Files.readAllLines(this.m_pathname);
		Iterator<String> allLinesIterator = allLines.iterator();

		// All Graphs should have at least 2 nodes
		if (allLines.size() < 2) {
			throw new Exception("Not enough Vertices in text file!");
		}

		String sampleLine = allLines.get(0);
		String[] allValueString = sampleLine.split(",");

		int numberOfRows = allLines.size();
		int numberOfColumns = allValueString.length;

		if (numberOfRows != numberOfColumns) {
			throw new Exception("Column size and Row size mismatch");
		}

		this.m_adjacencyMatrix = new int[numberOfRows][numberOfColumns];

		int rowCounter = 0;

		String currentValue = "MAX";
		int currentIntValue = Integer.MAX_VALUE;

		while (allLinesIterator.hasNext()) {
			sampleLine = allLinesIterator.next();
			allValueString = sampleLine.split(",");

			for (int i = 0; i < numberOfColumns; i++) {
				currentValue = allValueString[i].trim();

				if (currentValue.equalsIgnoreCase("MAX")) {
					currentIntValue = Integer.MAX_VALUE;
				} else {
					currentIntValue = Integer.parseInt(currentValue);
				}

				this.m_adjacencyMatrix[rowCounter][i] = currentIntValue;
			}

			rowCounter++;
		}
	}
	
	public int getNumberOfVertices() {
		return this.m_adjacencyMatrix.length;
	}
	
	public int getEdgeWeight(int source, int destination) {
		return this.m_adjacencyMatrix[source][destination];
	}

	// Mainly for debugging usage.
	public int[][] getGraphMatrix() {
		return this.m_adjacencyMatrix;
	}
}
