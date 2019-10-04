package Bloomberg;

import java.util.ArrayList;
import java.util.List;

public class Lottery {

	private List<String> playerList;


	public Lottery() {
		this.playerList = new ArrayList<>();
	}

	void addPlayer(String player) {
		if (playerList != null) {
			playerList.add(player);
		}
	}

	void removePlayer(String player) {
		if (playerList != null && playerList.contains(player)) {
			playerList.remove(player);
		}
	}

	int drawwinner() {
		if ()
	} // return 5 random players

}
