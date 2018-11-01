package com.example.demo;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

public class restTemplateTest {
	private RestTemplate restTemplate;

	@Before
	public void init() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void delete() throws URISyntaxException {
		URI uri = new URI("http://localhost:8080/delete/1");
		restTemplate.delete(uri);

	}

	@Test
	public void deteleUriVariables() {
		String uri = "http://localhost:8080/delete/{id}/{name}";
		restTemplate.delete(uri, new String[] { "1", "张三" });

	}

	@Test
	public void deteleMap() {
		String uri = "http://localhost:8080/delete/{id}/{name}";
		Map<String, String> map = new HashMap<>();
		map.put("id", "2");
		map.put("name", "李四");
		restTemplate.delete(uri, map);

	}

	@Test
	public void exchange() throws URISyntaxException {
		// restTemplate.exchange(requestEntity, responseType)
		RequestEntity request = RequestEntity.post(new URI("http://localhost:8080/exchange"))
				.accept(MediaType.APPLICATION_JSON).body(new Person());
		ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		String body = response.getBody();
		System.out.println(body);

	}

	/**
	 * 此方法可以将返回的类型封装成对象
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void exchageParameterizedTypeReference() throws URISyntaxException {
		// restTemplate.exchange(requestEntity, responseType)

		RequestEntity request = RequestEntity.post(new URI("http://localhost:8080/exchangeWithRef"))
				.accept(MediaType.APPLICATION_JSON).body(new Person());
		ParameterizedTypeReference<List<Person>> myBean = new ParameterizedTypeReference<List<Person>>() {
		};
		ResponseEntity<List<Person>> response = restTemplate.exchange(request, myBean);
		List<Person> body = response.getBody();
		body.forEach(o -> {
			System.out.println(o.getId() + "-------------------" + o.getName());
		});

	}

	@Test
	public void exchageHttpMethodAndParameterizedTypeReference() {
		String uri = "http://localhost:8080/exchageHttpMethodAndParameterizedTypeReference";
		// restTemplate.exchange(url, method, requestEntity, responseType)
		ParameterizedTypeReference<Person> myBean = new ParameterizedTypeReference<Person>() {
		};

		ResponseEntity<Person> response = restTemplate.exchange(uri, HttpMethod.GET, null, myBean);
		Person body = response.getBody();

		System.out.println(body);
	}

	@Test
	public void httpHeadWithCharsets() throws URISyntaxException {
		RequestEntity request = RequestEntity.post(new URI("http://localhost:8080/httpHeadWithCharsets"))
				.acceptCharset(new Charset[] { StandardCharsets.UTF_8 }).accept(MediaType.APPLICATION_JSON)
				.body(new Person());
		ResponseEntity<Person> exchange = restTemplate.exchange(request, Person.class);
		Person body = exchange.getBody();
		System.out.println(body);

	}

	@Test
	public void getForEntity() throws RestClientException, URISyntaxException {
		ResponseEntity<Person> forEntity = restTemplate.getForEntity(new URI("http://localhost:8080/getForEntity"),
				Person.class);
		Person body = forEntity.getBody();
		System.out.println(body);

	}

	@Test
	public void getForEntityUriVariables() {
		String url = "http://localhost:8080/getForEntity/{name}";
		ResponseEntity<Person> forEntity = restTemplate.getForEntity(url, Person.class, new String[] { "草泥马" });
		Person body = forEntity.getBody();
		System.out.println(body);
	}

	/**
	 * NOTE: The standard JDK HTTP library does not support HTTP PATCH. You need
	 * to use the Apache HttpComponents or OkHttp request factory.
	 * 
	 * 
	 * @throws RestClientException
	 * @throws URISyntaxException
	 */
	@Test
	public void patchForObject() throws RestClientException, URISyntaxException {
		Person patchForObject = restTemplate.patchForObject(new URI("http://localhost:8080/patchForObject"),
				new Person(), Person.class);
		System.out.println(patchForObject);

	}

	@Test
	public void postForEntity() throws RestClientException, URISyntaxException {
		ResponseEntity<Person> postForEntity = restTemplate
				.postForEntity(new URI("http://localhost:8080/postForEntity"), new Person(), Person.class);
		Person body = postForEntity.getBody();
		System.out.println(body);

	}

	@Test
	public void put() throws RestClientException, URISyntaxException {
		// restTemplate.put(new URI("http://localhost:8080/put"), new Person());

		restTemplate.put("http://localhost:8080/puts/{name}", new Person(), "mmmmmm");
	}

	@Test
	public void postForEntityPingFan() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.convertValue(new T(), Map.class);

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http").host("dev.pingfang.net")
				.path("/cn-ruili-jicp1/api/business/vehicle_registration?"
						+ "license_plate_number={license_plate_number}&" + "license_plate_color={license_plate_color}&"
						+ "license_plate_nationality={license_plate_nationality}&" + "special_mark={special_mark}&"
						+ "operation_usage={operation_usage}&" + "owner_phone={owner_phone}&"
						+ "vehicle_type={vehicle_type}&" + "vehicle_color={vehicle_color}&" + "owner_name={owner_name}&"
						+ "engine_number={engine_number}")
				.buildAndExpand(map).encode();

		URI uri = uriComponents.toUri();
		String string = uriComponents.toString();

		RequestEntity request = RequestEntity.post(uri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).build();

		ResponseEntity<String> exchange = restTemplate.exchange(request, String.class);
		String body = exchange.getBody();
		System.out.println(body);

	}

	@Test
	public void testUri() {
		P p = new P("000303", "dd", "chn", "", "");
		ReflectionUtils.doWithFields(P.class, (f) -> {
			System.out.println(f.getName());

		}, (o) -> {
			return !o.getName().equals("this$0");
		});
	}

	class P {
		private String license_plate_number;
		private String license_plate_color;
		private String license_plate_nationality;
		private String special_mark;
		private String operation_usage;

		public String getLicense_plate_number() {
			return license_plate_number;
		}

		public void setLicense_plate_number(String license_plate_number) {
			this.license_plate_number = license_plate_number;
		}

		public String getLicense_plate_color() {
			return license_plate_color;
		}

		public void setLicense_plate_color(String license_plate_color) {
			this.license_plate_color = license_plate_color;
		}

		public String getLicense_plate_nationality() {
			return license_plate_nationality;
		}

		public void setLicense_plate_nationality(String license_plate_nationality) {
			this.license_plate_nationality = license_plate_nationality;
		}

		public String getSpecial_mark() {
			return special_mark;
		}

		public void setSpecial_mark(String special_mark) {
			this.special_mark = special_mark;
		}

		public String getOperation_usage() {
			return operation_usage;
		}

		public void setOperation_usage(String operation_usage) {
			this.operation_usage = operation_usage;
		}

		public P() {
			super();
			// TODO Auto-generated constructor stub
		}

		public P(String license_plate_number, String license_plate_color, String license_plate_nationality,
				String special_mark, String operation_usage) {
			super();
			this.license_plate_number = license_plate_number;
			this.license_plate_color = license_plate_color;
			this.license_plate_nationality = license_plate_nationality;
			this.special_mark = special_mark;
			this.operation_usage = operation_usage;
		}

	}

}
