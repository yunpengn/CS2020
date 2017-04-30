package sg.edu.nus.cs2020;

public class MazePrinter {
	// The enumerate variable to store printing units.
	enum PrinterBlocks {
		WALL("##"), PATH("PP"), AIR("  "), DESTROY("XX");

		private final String block;

		PrinterBlocks(String s) {
			block = s;
		}

		String val() {
			return block;
		}
	};

	static void printMaze(Maze maze) {
		// Prints the maze row by row.
		for (int i = 0; i < maze.getRows(); i++) {
			System.out.print(PrinterBlocks.WALL.val());

			// Prints the north wall of the current row of rooms.
			for (int j = 0; j < maze.getColumns(); j++) {
				Room room = maze.getRoom(i, j);

				if (room.hasNorthWall()) {
					System.out.print(PrinterBlocks.WALL.val());
				} else if (i > 0 && maze.getRoom(i - 1, j).onPath == true && room.onPath == true) {
					System.out.print(PrinterBlocks.PATH.val());
				} else {
					System.out.print(PrinterBlocks.AIR.val());
				}

				System.out.print(PrinterBlocks.WALL.val());
			}
			System.out.println();

			// Prints the east and south wall of the current row of rooms.
			for (int j = 0; j < maze.getColumns(); j++) {
				Room room = maze.getRoom(i, j);

				if (room.hasWestWall()) {
					System.out.print(PrinterBlocks.WALL.val());
				} else if (j > 0 && maze.getRoom(i, j - 1).onPath == true && room.onPath == true) {
					System.out.print(PrinterBlocks.PATH.val());
				} else {
					System.out.print(PrinterBlocks.AIR.val());
				}

				if (room.onPath == true) {
					System.out.print(PrinterBlocks.PATH.val());
				} else {
					System.out.print(PrinterBlocks.AIR.val());
				}
			}

			System.out.println(PrinterBlocks.WALL.val());
		}

		// Prints the bottom line.
		for (int j = 0; j < maze.getColumns() * 2 + 1; ++j) {
			System.out.print(PrinterBlocks.WALL.val());
		}
		System.out.print("\n");
	}
}
