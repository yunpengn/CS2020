package discussion_group;

public class GameLoc {
	private double x = 0;
	private double y = 0;

	public GameLoc(double x, double y) {
		if (x < 0 || x > 100) {
			throw new IllegalArgumentException();
		} else if (y < 0 || y > 100) {
			throw new IllegalArgumentException();
		} else {
			this.x = x;
			this.y = y;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		if (x < 0 || x > 100) {
			throw new IllegalArgumentException();
		} else {
			this.x = x;
		}
	}

	public void setY(double y) {
		if (y < 0 || y > 100) {
			throw new IllegalArgumentException();
		} else {
			this.y = y;
		}
	}

	@Override
	public int hashCode() {
		return 100 * ((int) x) + (int) y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj == this) {
			return true;
		} else if (!(obj instanceof GameLoc)) {
			return false;
		} else {
			GameLoc location = (GameLoc) obj;

			if ((int) this.x != (int) location.x) {
				return false;
			} else if ((int) this.y != (int) location.y) {
				return false;
			} else {
				return true;
			}
		}
	}
}
