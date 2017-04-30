package sg.edu.nus.cs2020;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * Public Class: TreeNodeComponent
 * 
 * Description: This class draws a tree node as a rectangle with its value
 * inside. If the length of value (as a string) is too long, it will cast and
 * use the substring of first 8 characters.
 * 
 * @author Niu Yunpeng
 *
 */
public class TreeNodeComponent extends JComponent {
	// Default serial version id
	private static final long serialVersionUID = 1L;

	// The size of the rectangle
	private final int unitX = 100;
	private final int unitY = 25;

	// Font to be used in the node graphic representation
	Font myFont = new Font("TimesRoman", Font.BOLD, 20);

	// Stores all rectangles and strings
	private ArrayList<Integer[]> rectangles = null;
	private ArrayList<StringComponent> values = null;
	private ArrayList<Integer[]> lines = null;
	
	/**
	 * Public Constructor: TreeNodeComponent
	 * 
	 * Description: Input the positions for all rectangles and values.
	 */
	public TreeNodeComponent(ArrayList<Integer[]> rectangles,
			ArrayList<StringComponent> values, ArrayList<Integer[]> lines) {
		this.rectangles = rectangles;
		this.values = values;
		this.lines = lines;
	}

	public void paint(Graphics g) {
		g.setFont(myFont);

		for (Integer[] i : rectangles) {
			g.drawRect(i[0] - unitX / 2, i[1] - -unitY / 2, unitX, unitY);
		}

		for (StringComponent j : values) {
			String value = j.getValue();

			if (value.length() > 8) {
				value = value.substring(0, 8);
			}

			// Notice the X and Y here is already the center coordinate.
			int stringX = (int) (j.getX() - getStringWidth(value) / 2);
			int stringY = j.getY() + 17 + unitY / 2;

			g.drawString(value, stringX, stringY);
		}

		for (Integer[] line : lines) {
			int startX = line[0];
			int startY = line[1] + unitY * 3 / 2;
			int endX = line[2];
			int endY = line[3] + unitY / 2;

			g.drawLine(startX, startY, endX, endY);
		}
	}

	/**
	 * Private Method: double getStringWidth()
	 * 
	 * Description: This method renders the string to calculate its width.
	 * Therefore, we can set central alignment to the boundary rectangle.
	 * 
	 * @return the width of the string as displayed.
	 */
	private double getStringWidth(String value) {
		FontRenderContext render = new FontRenderContext(null, true, true);
		return myFont.getStringBounds(value, render).getWidth();
	}
}
