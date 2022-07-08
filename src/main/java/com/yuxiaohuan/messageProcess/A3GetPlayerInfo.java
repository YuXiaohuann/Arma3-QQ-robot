package com.yuxiaohuan.messageProcess;

import com.yuxiaohuan.Config;
import com.yuxiaohuan.messageProcess.base.BaseImpl;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 获取玩家在线信息
 *
 * @author : 于小环
 * @date : 2022/7/8 16:39
 */
public class A3GetPlayerInfo extends BaseImpl {
	@Override
	public void process(GroupMessageEvent event, String msg, Long senderQq, Integer senderLevel) {
		if (Config.a3ServerMap.containsKey(msg)) {
			try {
				event.getGroup().sendMessage(Config.a3ServerMap.get(msg).getPlayersInfo());
			} catch (Exception e) {
				e.printStackTrace();
				event.getGroup().sendMessage("啊咧、服务器找不到诶。赶快喊狗管理开服吧");
			}
		}
	}
}
