package com.yuxiaohuan.steamCondenser;

import com.alibaba.fastjson.JSON;
import com.github.koraktor.steamcondenser.steam.SteamPlayer;
import com.github.koraktor.steamcondenser.steam.servers.GoldSrcServer;
import lombok.Data;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * @author : 于小环
 * @date : 2022/6/25 14:59
 */
@Data
public class CustomGoldSrcServer {

	private String address;
	protected GoldSrcServer server;

	@SneakyThrows
	public CustomGoldSrcServer(String address) {
		this.address = address;
		this.server = new GoldSrcServer(address);
	}

	public String getPlayersInfo() {
		return this.getPlayersInfo(false);
	}

	@SneakyThrows
	public String getPlayersInfo(boolean details) {
		try {
			this.server.updatePlayers();
		} catch (TimeoutException e) {
			e.printStackTrace();
			this.server.initSocket();
			this.server.updatePlayers();
		}
		List<SteamPlayer> playerList = this.server.getPlayers().values().stream().sorted(Comparator.comparing(SteamPlayer::getConnectTime)).collect(Collectors.toList());

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));

		StringBuilder builder = new StringBuilder();
		if (!details) {
			builder.append("服务器直连IP：").append(this.address, 0, this.address.length() - 1).append(Integer.parseInt(this.address.substring(this.address.length() - 1)) - 1).append("\n");
			builder.append("在线时间|得分|玩家名称\n");
		}
		for (SteamPlayer player : playerList) {
			if (details) {
				builder.append(JSON.toJSONString(player)).append("\n");
			} else {
				builder.append(simpleDateFormat.format(player.getConnectTime() * 1000)).append("|");
				builder.append(player.getScore()).append("|");
				builder.append(player.getName()).append("\n");
			}
		}
		builder.append("在线玩家 : ").append(playerList.size());
		return builder.toString();
	}
}
