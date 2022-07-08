package com.yuxiaohuan.messageProcess.base;

import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * @author : 于小环
 * @date : 2022/7/8 16:18
 */
public abstract class BaseImpl implements Base {

	public void process(GroupMessageEvent event) {
		process(event, event.getMessage().contentToString(), event.getSender().getId(), event.getSender().getPermission().getLevel());
	}
}
