package discussion_group;

public class GameEntity {
	private String name;
	private GameLoc location = null;
	
	public GameEntity(GameLoc location, String name) {
		this.location = location;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public GameLoc getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return name + ": (" + location.getX() + ", " + location.getY() + ")\n";
	}
}
