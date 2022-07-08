package com.yuxiaohuan.messageProcess;

import com.yuxiaohuan.Config;
import com.yuxiaohuan.messageProcess.base.BaseImpl;
import com.yuxiaohuan.steamCondenser.CustomGoldSrcServer;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 绑定新服务器
 *
 * @author : 于小环
 * @date : 2022/7/8 18:01
 */
public class A3BindServer extends BaseImpl {
	@Override
	public void process(GroupMessageEvent event, String msg, Long senderQq, Integer senderLevel) {
		if (event.getSender().getPermission().getLevel() > 0) {
			if (msg.startsWith("绑定")) {
				String[] s = msg.split(" ");
				if (s.length != 3) {
					event.getGroup().sendMessage("格式错误请使用(注意空格):绑定 关键词 ip:port");
				} else {
					event.getGroup().sendMessage("绑定中请稍后");
					Config.a3ServerMap.put(s[1], new CustomGoldSrcServer(s[2]));
					StringBuilder builder = new StringBuilder();
					builder.append("绑定成功！当前关键词列表:\n");
					Config.a3ServerMap.keySet().forEach(key -> {
						builder.append(key).append(" ").append(Config.a3ServerMap.get(key).getAddress()).append("\n");
					});
					event.getGroup().sendMessage(builder.toString());
				}
			}
		}
	}
}
