package com.yuxiaohuan;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuxiaohuan.steamCondenser.CustomGoldSrcServer;
import lombok.Data;
import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取配置信息
 *
 * @author : 于小环
 * @date : 2022/7/8 15:08
 */
@Data
public class Config {
	public static Long qq;
	public static String password;

	public static Map<String, CustomGoldSrcServer> a3ServerMap = new HashMap<>();

	@SneakyThrows
	public static void readConfig() {
		FileInputStream inputStream = new FileInputStream("config.yml");
		Yaml yaml = new Yaml();
		JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(yaml.load(inputStream)));
		JSONObject robot = jsonObject.getJSONObject("robot");
		qq = robot.getLong("qq");
		password = robot.getString("password");
		JSONArray serverList = robot.getJSONArray("serverList");
		for (int i = 0; i < serverList.size(); i++) {
			JSONObject object = serverList.getJSONObject(i);
			a3ServerMap.put(object.getString("msgKey"), new CustomGoldSrcServer(object.getString("address")));
		}
	}
}
