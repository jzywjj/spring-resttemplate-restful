package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class sController {

	@DeleteMapping("delete/{id}")
	@ResponseBody
	public String delete(@PathVariable(name = "id") String id) {
		System.out.println(id);
		return id;
	}

	@DeleteMapping("delete/{id}/{name}")
	@ResponseBody
	public String deteleUriVariables(@PathVariable(name = "id") String id, @PathVariable(name = "name") String name) {
		System.out.println(id + "-------------" + name);

		return name;

	}

	@RequestMapping("exchange")
	@ResponseBody
	public Person exchange(Person p) {
		System.out.println("接收到了-------------------" + p);
		return new Person();
	}

	@RequestMapping(name = "exchangeWithRef")
	@ResponseBody
	public List<Person> exchangeWithRef(Person p) {
		System.out.println("接收到了-------------------" + p);
		p.setName("aaaa=========================");
		List<Person> ps = new ArrayList<>();
		ps.add(p);
		return ps;
	}

	@RequestMapping(name = "exchageHttpMethodAndParameterizedTypeReference", method = { RequestMethod.GET })
	@ResponseBody
	public Person exchageHttpMethodAndParameterizedTypeReference(Person p) {
		p.setName("exchageHttpMethodAndParameterizedTypeReference=========================");
		return p;

	}

	@RequestMapping(name = "httpHeadWithCharsets", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	@ResponseBody
	public Person httpHeadWithCharsets(Person p) {
		p.setName("Charsets=========================UtF-8");
		return p;

	}

	@RequestMapping("getForEntity")
	@ResponseBody
	public Person getForEntity() {
		Person p = new Person();
		p.setName("getForEntity");
		return p;

	}

	@RequestMapping("getForEntity/{name}")
	@ResponseBody
	public Person getForEntityUriVariables(@PathVariable(name = "name") String name) {
		Person p = new Person();
		p.setName(name);

		return p;
	}

	// @PatchMapping(name = "patchForObject")
	// @ResponseBody
	// public Person patchForObject(Person p) {
	// p.setName("patchForObject");
	// return p;
	//
	// }

	@PostMapping("postForEntity")
	@ResponseBody
	public Person postForEntity(Person p) {
		p.setName("postForEntity----------------------------------------------------");
		return p;

	}

	@PutMapping("put")
	@ResponseBody
	public Person put(Person p) {
		p.setName("put-----------");
		return p;

	}

	@RequestMapping(name = "putssss/{aa}")
	@ResponseBody
	public void putvarab(Person p, @PathVariable(name = "aa") String name) {
		// Person p = new Person();
		p.setName("put-----------");
		System.out.println(name);
		System.out.println(p);
	}

}
