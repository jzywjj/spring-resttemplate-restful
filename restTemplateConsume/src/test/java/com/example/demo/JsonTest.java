package com.example.demo;

import java.io.IOException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 测试 JackSon
 * 
 * @author apple
 *
 */
public class JsonTest {

	private ObjectMapper mapper;

	@Before
	public void init() {
		mapper = new ObjectMapper();
	}

	@Test
	public void testObectToJson() throws JsonProcessingException {
		Person p = new Person();
		String json = mapper.writeValueAsString(p);
		System.out.println(json);
	}

	@Test
	public void testJsonToObject() throws JsonParseException, JsonMappingException, IOException {
		String json = "{\"id\":\"000111111\",\"name\":\"里斯 是是 是\"}";
		Person readValue = mapper.readValue(json, Person.class);
		System.out.println(readValue);
	}

	@Test
	public void testObjTomap() {
		Person p = new Person();
		Map<String, Object> m = mapper.convertValue(p, Map.class);
		m.forEach((k, v) -> {
			System.out.println(k + "---------" + v);
		});
	}

}
