package com.yuxiaohuan;

import com.yuxiaohuan.messageProcess.A3BindServer;
import com.yuxiaohuan.messageProcess.A3GetPlayerInfo;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;

/**
 * @author : YuXiaohuann
 * @date : 2022/7/8 14:30
 */
public class Robot {
	public static void main(String[] args) {
		Config.readConfig();

		Bot bot = BotFactory.INSTANCE.newBot(Config.qq, Config.password, new BotConfiguration() {{
			fileBasedDeviceInfo();
			setProtocol(MiraiProtocol.ANDROID_PAD);
			setHeartbeatStrategy(HeartbeatStrategy.STAT_HB);
		}});
		bot.login();
		bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, groupMessageEvent -> {
			new A3GetPlayerInfo().process(groupMessageEvent);
			new A3BindServer().process(groupMessageEvent);
		});
	}
}
