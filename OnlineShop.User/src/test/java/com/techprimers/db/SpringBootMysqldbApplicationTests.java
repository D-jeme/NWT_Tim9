package com.techprimers.db;

import com.techprimers.db.model.Users;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootMysqldbApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void contextLoads()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/rest/users/";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		System.out.println("rezulat ej "+result.getBody());
		System.out.println("REZULTAAAT"+result.getStatusCodeValue());
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("ime"));
	}

	@Test
	public void createUserTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/rest/users/";
		URI uri = new URI(baseUrl);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("ime", "aaa14");
		jsonObject.put("prezime", "aaa14");
		jsonObject.put("email", "aaa14@gmail.com");
		jsonObject.put("password", "aaa13");
		jsonObject.put("newPassword_url", "https://ttt.usnews.com/static-travel/images/destinations/61/2016-main-getty.jpg");
		jsonObject.put("password", "aaa13");
		jsonObject.put("password", "aaa13");
		JSONObject role=new JSONObject();
		role.put("id","1");
		role.put("tip","admin");
		jsonObject.put("role",role);
		System.out.println("OBJEKAAAT"+jsonObject);
		ResponseEntity<String> result = restTemplate.postForEntity(uri, jsonObject, String.class);
		System.out.println("rezulat ej "+result.getBody());
		System.out.println("REZULTAAAT"+result.getStatusCodeValue());
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		//Assert.assertEquals(true, result.getBody().contains("ime"));
	}

	@Test
	public void createRole()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/rest/roles/";
		URI uri = new URI(baseUrl);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("tip", "guest");
		ResponseEntity<String> result = restTemplate.postForEntity(uri,jsonObject, String.class);
		System.out.println("rezulat ej "+result.getBody());
		System.out.println("REZULTAAAT"+result.getStatusCodeValue());
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		//Assert.assertEquals(true, result.getBody().contains("ime"));
	}


}
