package com.fubonlife.bio.mg.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.fubonlife.bio.mg.entity.mongo.Forms;
import com.fubonlife.bio.mg.entity.mongo.Systems;
import com.fubonlife.bio.mg.entity.mongo.Users;

public class ExamUtil {

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map setId(String lastId, Map map, String name){
		
		if(lastId.length()!=0){
			Integer number = Integer.valueOf(lastId.replace(name, "")) + 1;
			String id = "";
			for(int i = 0; i< 6-number.toString().length();i++){
				id += 0;
			}
			map.put(name+"Id", name + id + number);
			if(name.equals("user")){
				map.put("keyId", "key" + id + number);
			}
		} else {
			map.put(name+"Id", name+"000001");
			if(name.equals("user")){
				map.put("keyId", "key000001");
			}
		}
		return map;
	}
	
	
	
	public List<?> test(List<Users> users , List<Systems> systems) {
		
		System.out.println(users.get(0));
		List<Map<String, String>> list = new ArrayList<>();
		for (Users usera : users) {
			if (systems.size() == 0) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("userId", usera.getUserId());
				map.put("account", usera.getAccount());
				map.put("password", usera.getPassword());
				map.put("systemId", null);
				map.put("systemName", null);
				list.add(map);
			} else {
				for (Systems system : systems) {
					Map<String, String> map = new LinkedHashMap<String, String>();
					map.put("userId", usera.getUserId());
					map.put("account", usera.getAccount());
					map.put("password", usera.getPassword());
					map.put("systemId", system.getSystemId());
					map.put("systemName", system.getSystemName());
					list.add(map);
				}
			}
		}
		return list;
	}
	
}
