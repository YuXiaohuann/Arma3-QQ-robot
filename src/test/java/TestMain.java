import com.github.koraktor.steamcondenser.steam.servers.GoldSrcServer;
import lombok.SneakyThrows;

/**
 * @author : 于小环
 * @date : 2022/6/25 14:37
 */
public class TestMain {
	@SneakyThrows
	public static void main(String[] args) {
		GoldSrcServer server = new GoldSrcServer("127.0.0.1:2304");
		server.updatePlayers();
		System.out.println(server.getPlayers());
		/*for (String key : players.keySet()) {
			System.out.println(key);
		}
		Map<String, SteamPlayer> players2 = server.getPlayers();
		for (String key : players2.keySet()) {
			System.out.println(key);
		}*/
	}
}
