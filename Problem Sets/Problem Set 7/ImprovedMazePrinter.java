package sg.edu.nus.cs2020;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Public Class: ImprovedMazePrinter
 * 
 * Description: An improved version of the MazePrinter, where the rooms and
 * walls are more correctly and neatly represented. Also, this improved Maze
 * Printer also searches for the source of the path in the Maze, then traces
 * this path to determine the route the MazeSolver took, and finally print out
 * the Maze with the proper walls correctly destroyed. (It is hard to decide
 * which wall to demolish if the path is unknown).
 * 
 * @author Mai Anh Vu
 */
public class ImprovedMazePrinter {
	/**
	 * Storing the different directions each move can take, and their
	 * corresponding offset.
	 */
	protected static enum Direction {
		NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

		private final int xOffset;
		private final int yOffset;

		Direction(Integer xOff, Integer yOff) {
			xOffset = xOff;
			yOffset = yOff;
		}

		Coordinates getOffset() {
			return new Coordinates(xOffset, yOffset);
		}
	}

	/**
	 * Store the ASCII box-drawing characters
	 */
	enum PrinterBlocks {
		WALL_VERTICAL(" ┃"),
		WALL_HORIZONTAL("━━"),
		WALL_TOPLEFT(" ┏"),
		WALL_TOPRIGHT("━┓"),
		WALL_BOTTOMLEFT(" ┗"),
		WALL_BOTTOMRIGHT("━┛"),
		
		WALL_LEFT("━┫"),
		WALL_RIGHT(" ┣"),
		WALL_UP("━┻"),
		WALL_DOWN("━┳"),
		WALL_CORNER("━╋"),
		
		WALL_LEFTHALF("━ "),
		WALL_RIGHTHALF(" ━"),
		WALL_TOPHALF(" ╹"),
		WALL_BOTTOMHALF(" ╻"),

		PATH_VERTICAL(" │"),
		PATH_HORIZONTAL("──"),
		PATH_TURN_SW("─╮"),
		PATH_TURN_NE(" ╰"),
		PATH_TURN_NW("─╯"),
		PATH_TURN_SE(" ╭"),
		PATH_POINT_LEFT("× "),
		PATH_POINT_RIGHT(" ×"),

		AIR("  "),
		DESTROY_VERTICAL("─░"),
		DESTROY_HORIZONTAL("━░");

		private final String block;

		PrinterBlocks(String s) {
			block = s;
		}

		String val() {
			return block;
		}
	};

	/**
	 * Binary logic for determining what type of wall to draw 4-bits: NSEW. Each
	 * bit is a boolean indicating if there is a wall in that direction at the
	 * current "corner".
	 */
	private static final String[] CORNERS = new String[] { PrinterBlocks.AIR.val(), // 0000
			PrinterBlocks.WALL_LEFTHALF.val(), // 0001
			PrinterBlocks.WALL_RIGHTHALF.val(), // 0010
			PrinterBlocks.WALL_HORIZONTAL.val(), // 0011
			PrinterBlocks.WALL_BOTTOMHALF.val(), // 0100
			PrinterBlocks.WALL_TOPRIGHT.val(), // 0101
			PrinterBlocks.WALL_TOPLEFT.val(), // 0110
			PrinterBlocks.WALL_DOWN.val(), // 0111
			PrinterBlocks.WALL_TOPHALF.val(), // 1000
			PrinterBlocks.WALL_BOTTOMRIGHT.val(), // 1001
			PrinterBlocks.WALL_BOTTOMLEFT.val(), // 1010
			PrinterBlocks.WALL_UP.val(), // 1011
			PrinterBlocks.WALL_VERTICAL.val(), // 1100
			PrinterBlocks.WALL_LEFT.val(), // 1101
			PrinterBlocks.WALL_RIGHT.val(), // 1110
			PrinterBlocks.WALL_CORNER.val(), // 1111
	};

	/**
	 * Binary logic for determining what type of path to draw Four bits: MSB 0 =
	 * northRoom.onPath 1 = southRoom.onPath 2 = eastRoom.onPath LSB 3 =
	 * westRoom.onPath
	 */
	private static final String[] PATHS = new String[] { PrinterBlocks.PATH_VERTICAL.val(), // 0000
			null, // 0001
			PrinterBlocks.PATH_TURN_SE.val(), // 0010
			PrinterBlocks.PATH_TURN_SW.val(), // 0011
			null, // 0100
			PrinterBlocks.PATH_VERTICAL.val(), // 0101
			PrinterBlocks.PATH_TURN_NE.val(), // 0110
			PrinterBlocks.PATH_TURN_NW.val(), // 0111
			PrinterBlocks.PATH_TURN_NW.val(), // 1000
			PrinterBlocks.PATH_TURN_SW.val(), // 1001
			PrinterBlocks.PATH_HORIZONTAL.val(), // 1010
			null, // 1011
			PrinterBlocks.PATH_TURN_NE.val(), // 1100
			PrinterBlocks.PATH_TURN_SE.val(), // 1101
			null, // 1110
			PrinterBlocks.PATH_HORIZONTAL.val() // 1111
	};

	/**
	 * Binary logic to determine what horizontal gap to draw 2-bit: MSB 0 = room
	 * on path LSB 2 = wall in that direction
	 */
	private static final String[] HORZ_GAPS = new String[] { PrinterBlocks.AIR.val(), PrinterBlocks.WALL_VERTICAL.val(),
			PrinterBlocks.PATH_HORIZONTAL.val(), PrinterBlocks.DESTROY_VERTICAL.val() };

	private static final String[] VERT_GAPS = new String[] { PrinterBlocks.AIR.val(),
			PrinterBlocks.WALL_HORIZONTAL.val(), PrinterBlocks.PATH_VERTICAL.val(),
			PrinterBlocks.DESTROY_HORIZONTAL.val() };

	/**
	 * Determines the starting point first, then prints the maze Performed by
	 * calling the main printMaze method supplying null starting position,
	 * signaling that the starting point needs to be searched for.
	 * 
	 * @param maze
	 *            the maze to be printed
	 */
	static void printMaze(Maze maze) {
		printMaze(maze, null, null);
	}

	/**
	 * Determines starting point if needed, then build the "walls" of the maze
	 * first. Then, back-trace the path found, and build the appropriate
	 * directions Finally, print out the maze and the path
	 * 
	 * @param maze
	 * @param startRow
	 * @param startCol
	 */
	static void printMaze(Maze maze, Integer startRow, Integer startCol) {
		// Determines if we need to find source
		boolean findSource = startRow == null || startCol == null;
		// Prepare data for recording down source
		Coordinates source = null;
		// pathRatio = (numOfOutgoingPaths - numOfWallsInThosePaths) *
		// numOfOutgoinPaths;
		// claim: the starting point (or ending point, interchangeable) has the
		// minimum pathRatio!
		// therefore, iterate through all rooms and calculate their pathRatio,
		// and keep track of the room with minimum pathRatio.
		Integer pathRatio = Integer.MAX_VALUE;
		// pathGraph has Coordinates (or Rooms) as vertices, and each outgoing
		// edge contains a valid (but may not be the correct path) pointer to
		// adjacent rooms. Using AdjacencyList method.
		HashMap<Coordinates, LinkedList<DirectedEdge>> pathGraph = findSource
				? new HashMap<Coordinates, LinkedList<DirectedEdge>>() : null;

		// mazeCorners contains the appropriate PrinterBlocks to draw for the
		// the walls of the maze
		// claim: if a maze has n rows and m columns, then there are n+1
		// horizontal walls and m+1
		// vertical walls
		String[][] mazeCorners = new String[maze.getRows() + 1][maze.getColumns() + 1];

		// first iteration: does essentially two things at once
		// 1. if findSource == true, then see if room(i, j) is onPath and
		// enumerate all possible outgoing paths, store it in pathGraph
		// 2. determine what kind of wall to draw
		for (int i = 0; i < maze.getRows() + 1; i++)
			for (int j = 0; j < maze.getColumns() + 1; j++) {
				// 1. search for source if needed
				Room r = null;
				if (findSource && i < maze.getRows() && j < maze.getColumns() && (r = maze.getRoom(i, j)).onPath) {
					// If source is not instantiated
					Coordinates roomCoord = new Coordinates(i, j);

					int paths = 0; // numOfOutgoingPaths
					int wallsInPath = 0; // numOfWallsInTheDirectionOfThosePaths

					// keep track of outgoing edges, in terms of Directed Edges
					// (called DirectedEdges)
					LinkedList<DirectedEdge> pathList = pathGraph.get(roomCoord);
					if (pathList == null) {
						pathList = new LinkedList<DirectedEdge>();
						pathGraph.put(roomCoord, pathList);
					}

					// iterate through all possible directions
					for (Direction d : Direction.values()) {
						Coordinates c = new Coordinates(roomCoord, d);

						// if coordinates is outside of maze bounds, then it's invalid
						if (c.getX() < 0 || c.getX() >= maze.getRows() || c.getY() < 0 || c.getY() >= maze.getRows()) {
							continue;
						}
						// don't bother with rooms that are not onPath
						if (!maze.getRoom(c.getX(), c.getY()).onPath)
							continue;

						// count the path
						paths++;

						// check for wall in the direction of the path
						boolean hasWall = d == Direction.NORTH && r.hasNorthWall()
								|| d == Direction.SOUTH && r.hasSouthWall() || d == Direction.EAST && r.hasEastWall()
								|| d == Direction.WEST && r.hasWestWall();
						if (hasWall)
							wallsInPath++;

						pathList.add(new DirectedEdge(hasWall ? 1 : 0, d));
					}
					// calculate ratio
					Integer ratio = (paths - wallsInPath) * paths;

					// update if ratio is min
					if (ratio.compareTo(pathRatio) < 0) {
						source = roomCoord;
						pathRatio = ratio;
					}
				}

				// 2. determine which kind of wall to draw
				// if the wall is at the corner
				if (i == 0 && j == 0)
					mazeCorners[i][j] = PrinterBlocks.WALL_TOPLEFT.val();
				else if (i == 0 && j == maze.getColumns())
					mazeCorners[i][j] = PrinterBlocks.WALL_TOPRIGHT.val();
				else if (i == maze.getRows() && j == 0)
					mazeCorners[i][j] = PrinterBlocks.WALL_BOTTOMLEFT.val();
				else if (i == maze.getRows() && j == maze.getColumns())
					mazeCorners[i][j] = PrinterBlocks.WALL_BOTTOMRIGHT.val();
				else {
					// If wall is not at corners
					boolean north = i > 0 && (j <= 0 || j >= maze.getColumns() || maze.getRoom(i - 1, j).hasWestWall());
					boolean south = i < maze.getRows()
							&& (j <= 0 || j >= maze.getColumns() || maze.getRoom(i, j).hasWestWall());
					boolean east = j < maze.getColumns()
							&& (i <= 0 || i >= maze.getRows() || maze.getRoom(i - 1, j).hasSouthWall());
					boolean west = j > 0 && (i <= 0 || i >= maze.getRows() || maze.getRoom(i, j - 1).hasNorthWall());
					// 4-bits: NSEW
					int corner = (north ? 1 : 0) * 8 + (south ? 1 : 0) * 4 + (east ? 1 : 0) * 2 + (west ? 1 : 0);
					mazeCorners[i][j] = CORNERS[corner];
				}
			}

		// Backtrack the graph for path
		LinkedHashMap<Coordinates, Block> roomPath = new LinkedHashMap<Coordinates, Block>();
		Coordinates currentPosition = findSource ? source : new Coordinates(startRow, startCol);
		Direction arrivedVia = null;

		// While there are still adjacent nodes to go to
		while (currentPosition != null) {
			Block currentBlock = new Block(null, arrivedVia);
			// Put next point into the HashMap
			roomPath.put(currentPosition, currentBlock);
			Room currentRoom = maze.getRoom(currentPosition.getX(), currentPosition.getY());
			int w = Integer.MAX_VALUE;
			Direction d = null;
			// Determine next room
			if (findSource) {
				// If source is automatically determined, then just iterate
				// through the LinkedList of valid
				// rooms prepared previously during the source search.
				for (Iterator<DirectedEdge> dir_it = pathGraph.get(currentPosition).iterator(); dir_it.hasNext();) {
					DirectedEdge v = dir_it.next();
					Direction dir = v.getDirection();
					Coordinates c = new Coordinates(currentPosition, dir);
					if (roomPath.containsKey(c))
						continue;
					if (v.getValue() < w) {
						d = dir;
						w = v.getValue();
					}
				}
			} else {
				// If source is given, then the source search has not been
				// conducted and there are no LinkedLists
				// to refer to to find the valid rooms. We have to do this
				// ourselves now instead.
				for (Direction dir : Direction.values()) {
					// Get coords of the room in this direction
					Coordinates c = new Coordinates(currentPosition, dir);
					// Check if room is out of bounds
					if (c.getX() < 0 || c.getX() >= maze.getRows() || c.getY() < 0 || c.getY() >= maze.getColumns())
						continue;
					// Check if room is onPath
					if (!maze.getRoom(c.getX(), c.getY()).onPath)
						continue;
					// Check if room already existed
					if (roomPath.containsKey(c))
						continue;
					boolean hasWall = dir == Direction.NORTH && currentRoom.hasNorthWall()
							|| dir == Direction.SOUTH && currentRoom.hasSouthWall()
							|| dir == Direction.EAST && currentRoom.hasEastWall()
							|| dir == Direction.WEST && currentRoom.hasWestWall();
					int weight = hasWall ? 1 : 0;
					if (weight < w) {
						d = dir;
						w = weight;
					}
				}
			}
			// If at the end of the path
			if (d == null) {
				currentPosition = null;
				break;
			} else {
				currentPosition = new Coordinates(currentPosition, d);
				arrivedVia = d;
			}
		}

		// Trace the path to determine correct type of block to draw
		Iterator<Coordinates> path_it = roomPath.keySet().iterator();
		if (path_it.hasNext()) {
			Block current = roomPath.get(path_it.next());
			while (path_it.hasNext()) {
				Block nextBlock = roomPath.get(path_it.next());
				// if current == start
				if (current.isStart()) {
					String startPoint = PrinterBlocks.PATH_POINT_RIGHT.val();
					if (nextBlock.arrivedVia(Direction.WEST))
						startPoint = PrinterBlocks.PATH_POINT_LEFT.val();
					current.setValue(startPoint);
					current = nextBlock;
					continue;
				}
				// if current equals start, use Binary logic magic
				current.setValue(PATHS[current.getArrivedVia().ordinal() * 4 + nextBlock.getArrivedVia().ordinal()]);
				current = nextBlock;
			}
			// Modify end point's block according to the incoming direction,
			// purely for cosmetic reasons
			if (current.arrivedVia(Direction.EAST)) {
				current.setValue(PrinterBlocks.PATH_POINT_LEFT.val());
			} else {
				current.setValue(PrinterBlocks.PATH_POINT_RIGHT.val());
			}
		}

		// Draw the maze finally
		for (int i = 0; i < mazeCorners.length; i++) {
			StringBuilder row = new StringBuilder();
			for (int j = 0; j < mazeCorners[i].length; j++) {
				row.append(mazeCorners[i][j]);
				if (j < mazeCorners[i].length - 1) {
					Block roomBlock = roomPath.get(new Coordinates(i, j));
					Block upperBlock = roomPath.get(new Coordinates(i - 1, j));
					int gapCode = (roomBlock != null
							&& (roomPath.containsKey(new Coordinates(i - 1, j)) && roomBlock.arrivedVia(Direction.SOUTH)
									|| upperBlock != null && upperBlock.arrivedVia(Direction.NORTH)) ? 1 : 0)
							* 2 + (i == 0 || i > 0 && maze.getRoom(i - 1, j).hasSouthWall() ? 1 : 0);
					row.append(VERT_GAPS[gapCode]);
				}
			}
			System.out.println(row);
			if (i < mazeCorners.length - 1) {
				row = new StringBuilder(PrinterBlocks.WALL_VERTICAL.val());
				for (int j = 0; j < maze.getColumns(); j++) {
					String roomString = PrinterBlocks.AIR.val();
					Block roomBlock = roomPath.get(new Coordinates(i, j));
					if (roomBlock != null)
						roomString = roomBlock.getValue();

					Block nextBlock = roomPath.get(new Coordinates(i, j + 1));
					int gapCode = ((roomBlock != null && roomBlock.arrivedVia(Direction.WEST)
							|| nextBlock != null && nextBlock.arrivedVia(Direction.EAST)) ? 1 : 0) * 2
							+ (maze.getRoom(i, j).hasEastWall() ? 1 : 0);

					row.append(roomString).append(HORZ_GAPS[gapCode]);
				}
				System.out.println(row);
			}
		}
	}

	/**
	 * Storing the PrinterBlock string for the appropriate incoming direction
	 * m_arrivedVia
	 */
	private static class Block {
		private String m_value;
		private Direction m_arrivedVia;

		public Block(String value, Direction arrivedVia) {
			this.m_value = value;
			this.m_arrivedVia = arrivedVia;
		}

		public boolean isStart() {
			return arrivedVia(null);
		}

		public boolean arrivedVia(Direction d) {
			return this.m_arrivedVia == d;
		}

		public String getValue() {
			return this.m_value;
		}

		public Direction getArrivedVia() {
			return this.m_arrivedVia;
		}

		public void setValue(String value) {
			this.m_value = value;
		}
	}

	/**
	 * Self-explanatory, DirectedEdge class for the graph
	 */
	private static class DirectedEdge {
		private Integer m_value;
		private Direction m_pointer;

		public DirectedEdge(Integer value, Direction pointer) {
			this.m_value = value;
			this.m_pointer = pointer;
		}

		public Direction getDirection() {
			return this.m_pointer;
		}

		public Integer getValue() {
			return this.m_value;
		}
	}

	/**
	 * Anti-Cartesian coordinates, as x is the vertical axis while y is the
	 * horizontal axis Downwards (south) and leftwards (east) are considered
	 * positive
	 */
	private static class Coordinates {
		private Integer m_x;
		private Integer m_y;
		private Integer m_hash;

		public Coordinates(Integer x, Integer y) {
			this.m_x = x;
			this.m_y = y;
		}

		// Can obtain a new coordinates using a base coordinates and an offset
		public Coordinates(Coordinates base, Direction d) {
			this.m_x = base.m_x + d.getOffset().m_x;
			this.m_y = base.m_y + d.getOffset().m_y;
		}

		public int getX() {
			return this.m_x;
		}

		public int getY() {
			return this.m_y;
		}

		@Override
		public int hashCode() {
			if (this.m_hash != null)
				return this.m_hash;
			int h = 7;
			h = h * 31 + this.m_x;
			h = h * 257 + this.m_y;
			this.m_hash = h;
			return h;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null)
				return false;
			if (this == o)
				return true;
			if (!(o instanceof Coordinates))
				return false;
			Coordinates c = (Coordinates) o;
			if (this.m_x.intValue() != c.m_x.intValue())
				return false;
			if (this.m_y.intValue() != c.m_y.intValue())
				return false;
			return true;
		}
	}
}
