package com.accp.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accp.biz.PersonBiz;
import com.accp.pojo.AddressObj;
import com.accp.pojo.PersonObj;
import com.alibaba.fastjson.JSONPObject;
import com.github.pagehelper.PageInfo;




@RestController
@RequestMapping("/api/persons") // 命名空间
public class PersonAction {

	@Autowired
	private PersonBiz personBiz;

	@GetMapping("{p}/{s}")
	public PageInfo<PersonObj> queryPage(@PathVariable Integer p, @PathVariable Integer s) {
		return personBiz.findPersonListByPage(p, s);
	}

	@GetMapping(value = "{p}/{s}/jsonp")
	public JSONPObject getPersonListByPageToJSONP(@PathVariable Integer p, @PathVariable Integer s) throws Exception {
		// 跨域函数名称
		JSONPObject jsonp = new JSONPObject("callback");// 非常重要。对应jquery中jsonpCallback设置
		// 追加json数据
		// callback({})
		jsonp.addParameter(personBiz.findPersonListByPage(p, s));
		return jsonp;
	}

	@GetMapping("person/{pid}")
	public PersonObj queryPersonById(@PathVariable Integer pid) {
		
		return personBiz.getPersonById(pid);
	}

	@DeleteMapping("person/{pid}")
	public Map<String, Object> deletePersonById(@PathVariable Integer pid) {
		personBiz.removePersonById(pid);
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("code", "200");
		message.put("msg", "ok");
		return message;
	}

	@PutMapping("person")
	public Map<String, Object> updatePerson(@RequestBody PersonObj pojo) {
		personBiz.modifyPerson(pojo);
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("code", "200");
		message.put("msg", "ok");
		return message;
	}

	@PostMapping("person")
	public Map<String, Object> addPerson(@RequestBody PersonObj pojo) {
		PersonObj pe = new PersonObj();
		AddressObj add = new AddressObj();
		add.setAname("北京");
		add.setAtel("2");
		List<AddressObj> list = new ArrayList<AddressObj>();
		list.add(add);
		pe.setAddresses(list);		
		personBiz.addPerson(pojo);
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("code", "200");
		message.put("msg", "ok");
		return message;
	}

}
