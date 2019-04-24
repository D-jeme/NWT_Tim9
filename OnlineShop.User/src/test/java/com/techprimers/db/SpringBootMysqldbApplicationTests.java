package com.techprimers.db;

import com.techprimers.db.model.Users;
import net.minidev.json.JSONObject;
import org.codehaus.jettison.json.JSONStringer;
import org.codehaus.jettison.json.JSONTokener;
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


	//CREATING NEW USER

	@Test
	public void createUserTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + randomServerPort + "/rest/users/";
		URI uri = new URI(baseUrl);
		JSONObject jsonObject=new JSONObject();

		jsonObject.put("ime", "test1232");
		jsonObject.put("prezime", "test12");
		jsonObject.put("email", "test1762@gmail.com");
		jsonObject.put("password", "test12");
		jsonObject.put("newPassword_url", "https://i.redd.it/5el0ah4l5hjkfz.jpg");

		JSONObject role=new JSONObject();
		role.put("id","1");
		role.put("tip","admin");
		jsonObject.put("role",role);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, jsonObject, String.class);
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

		baseUrl = "http://localhost:8081/rest/users/";
		ResponseEntity<String> rez = restTemplate.postForEntity(uri, jsonObject, String.class);
		//Verify request succeed
		Assert.assertEquals(302, rez.getStatusCodeValue());
	}

	@Test
	public void GetCreatedUserTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + randomServerPort + "/rest/users/";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

		Assert.assertEquals(true, result.getBody().contains("test12361@gmail.com"));

	}


	@Test
	public void createExistingUserTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + randomServerPort + "/rest/users/";
		URI uri = new URI(baseUrl);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("ime", "test12361");
		jsonObject.put("prezime", "test12361");
		jsonObject.put("email", "test12361@gmail.com");
		jsonObject.put("password", "test12361");
		jsonObject.put("newPassword_url", "https://test12361.redd.it/5el0ahv4l5hjkfz.jpg");
		JSONObject role=new JSONObject();
		role.put("id","1");
		role.put("tip","admin");
		jsonObject.put("role",role);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, jsonObject, String.class);
		//Verify request succeed
		Assert.assertEquals(302, result.getStatusCodeValue());
	}

	@Test
	public void createUserWithExistingProfilePictrueTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + randomServerPort + "/rest/users/";
		URI uri = new URI(baseUrl);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("ime", "test123471");
		jsonObject.put("prezime", "test123471");
		jsonObject.put("email", "test123471@gmail.com");
		jsonObject.put("password", "test123471");
		jsonObject.put("newPassword_url", "https://test12361.redd.it/5el0ahv4l5hjkfz.jpg");
		JSONObject role=new JSONObject();
		role.put("id","1");
		role.put("tip","admin");
		jsonObject.put("role",role);

		ResponseEntity<String> result = restTemplate.postForEntity(uri, jsonObject, String.class);
		//Verify request succeed
		Assert.assertEquals(201, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Profile picture already exists. USer is created."));
	}


	@Test
	public void UserProfilePictureIsAddedInPicturesTableTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:8082/pictures/";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		System.out.println("TIJELOO"+result.getBody());
		Assert.assertEquals(true, result.getBody().contains("https://test12361.redd.it/5el0ahv4l5hjkfz.jpg"));
	}


	@Test
	public void DeleteUserTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:8081/rest/users/19";
		URI uri = new URI(baseUrl);
		restTemplate.delete(baseUrl);
		//Verify request succeed
	}


	@Test
	public void GetDeletedUserTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:8081/rest/users/";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		Assert.assertEquals(false, result.getBody().contains("\"id\": 19,"));
		//Verify request succeed
	}




	//UPDATE USER
	/*@Test
	public void UpdateUserTest()  throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		String baseUrl = "http://localhost:" + randomServerPort + "/rest/users/5";
		URI uri = new URI(baseUrl);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("ime", "updatetest120");
		jsonObject.put("prezime", "updatetest12");
		jsonObject.put("email", "updatetest12@gmail.com");
		jsonObject.put("password", "udpdatetest12");
		jsonObject.put("newPassword_url", "https://i.updateredd.it/5el0ahv4l5hjkfz.jpg");
		JSONObject role=new JSONObject();
		role.put("id","1");
		role.put("tip","admin");
		jsonObject.put("role",role);

		restTemplate.put(baseUrl,jsonObject);
		System.out.println("Tu saam");
		//Verify request succeed
		//Assert.assertEquals(200, result.getStatusCodeValue());
	}*/



	/*@Test
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
*/



}
