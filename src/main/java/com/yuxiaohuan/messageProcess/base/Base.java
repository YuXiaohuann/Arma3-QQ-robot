package com.yuxiaohuan.messageProcess.base;

import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * @author : 于小环
 * @date : 2022/7/8 16:14
 */
public interface Base {
	/**
	 * 消息处理方法
	 *
	 * @param event       消息事件
	 * @param msg         消息内容
	 * @param senderQq    发送人qq号
	 * @param senderLevel 发送人权限登记
	 */
	void process(GroupMessageEvent event, String msg, Long senderQq, Integer senderLevel);
}
