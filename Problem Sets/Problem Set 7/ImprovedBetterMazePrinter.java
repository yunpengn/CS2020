package sg.edu.nus.cs2020;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Draws a maze using BufferedImage and JFrame
 * @author Henry
 *
 */
public class ImprovedBetterMazePrinter {
	/**
	 * Component inside JFrame
	 */
	private class MyCanvas extends JPanel {
		private static final long serialVersionUID = -5474207287325874035L;
		private Image image;
		public MyCanvas(BufferedImage img) {
			image = img;
			setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		}
		@Override
		public void paint(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);
		}
	}
	
	BufferedImage img;
	
	/**
	 * Constructor, takes in a maze (preferably solved)
	 */
	public ImprovedBetterMazePrinter(Maze maze) {
		BufferedImage newimg = new BufferedImage(maze.getColumns() * 20, maze.getRows() * 20, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = newimg.createGraphics();
		//White background
		g2d.setBackground(Color.WHITE);
		g2d.fillRect(0, 0, newimg.getWidth(), newimg.getHeight());
		//Black strokes of width 2
		g2d.setColor(Color.BLACK);
		BasicStroke bs = new BasicStroke(2);
		g2d.setStroke(bs);
		//Draw all walls
		for (int row = 0; row < maze.getRows(); row++) {
			for (int col = 0; col < maze.getColumns(); col++) {
				Room current = maze.getRoom(row, col);
				//Cells are 20 x 20
				int x = col * 20;
				int y = row * 20;
				if (current.hasEastWall()) {
					g2d.drawLine(x + 19, y, x + 19, y + 19);
				}
				if (current.hasWestWall()) {
					g2d.drawLine(x, y, x, y + 19);
				}
				if (current.hasNorthWall()) {
					g2d.drawLine(x, y, x + 19, y);
				}
				if (current.hasSouthWall()) {
					g2d.drawLine(x, y + 19, x + 19, y + 19);
				}
			}
		}

		//Red strokes of width 2
		g2d.setColor(Color.RED);
		//Draw the path taken
		for (int row = 0; row < maze.getRows(); row++) {
			for (int col = 0; col < maze.getColumns(); col++) {
				Room current = maze.getRoom(row, col);
				if (current.onPath) {
					//Cells are 20 x 20
					int x = col * 20;
					int y = row * 20;
					//Try all neighbours
					if (!current.hasEastWall()) {
						if (maze.getRoom(row, col + 1).onPath) {
							g2d.drawLine(x + 9, y + 9, x + 19, y + 9);
						}
					}
					if (!current.hasWestWall()) {
						if (maze.getRoom(row, col - 1).onPath) {
							g2d.drawLine(x, y + 9, x + 9, y + 9);
						}
					}
					if (!current.hasNorthWall()) {
						if (maze.getRoom(row - 1, col).onPath) {
							g2d.drawLine(x + 9, y, x + 9, y + 9);
						}
					}
					if (!current.hasSouthWall()) {
						if (maze.getRoom(row + 1, col).onPath) {
							g2d.drawLine(x + 9, y + 9, x + 9, y + 19);
						}
					}
				}
			}
		}

		img = newimg;
	}
	
	//Draws the image in a JFrame
	public void draw() {
		JScrollPane jsp = new JScrollPane(new MyCanvas(img));
		JFrame window = new JFrame("Maze");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize((int)(img.getWidth() * 1.1), (int)(img.getHeight() * 1.5));
		window.add(jsp);
		window.setVisible(true);
	}
}
