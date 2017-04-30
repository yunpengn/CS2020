package discussion_group;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class KeepAway {
	// HashMap for storing all Klingons.
	private HashMap<GameLoc, GameEntity> klingons = new HashMap<GameLoc, GameEntity>();

	public void addKlingon(GameLoc location, GameEntity klingon) {
		klingons.put(location, klingon);
	}

	// The parameter is the location of the player, we need to decide whether
	// any Klingon is near him (in the same zone).
	public boolean nearKlingon(GameLoc location) {
		return klingons.containsKey(location);
	}

	@Override
	public String toString() {
		String result = "";
		Collection<GameEntity> entities = klingons.values();

		for (GameEntity i : entities) {
			result += i.toString();
		}
		return result;
	}

	public static void main(String[] args) {
		KeepAway game = new KeepAway();
		Random generator = new Random();

		for (int i = 0; i < 5; i++) {
			GameLoc location = new GameLoc(i + generator.nextDouble(),
					i * 2 + generator.nextDouble());
			GameEntity entity = new GameEntity(location, "ThisIsWhat" + i);
			game.addKlingon(location, entity);
		}
		System.out.println(game.toString());

		System.out.println(game.nearKlingon(new GameLoc(3.5, 6.48)));
	}
}
