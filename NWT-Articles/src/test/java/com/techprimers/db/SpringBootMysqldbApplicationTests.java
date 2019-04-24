package com.techprimers.db;

import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootMysqldbApplicationTests {

	@LocalServerPort
	int randomServerPort;

	@Test
	public void contextLoads() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + randomServerPort + "/articles/";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		System.out.println("rezulat ej "+result.getBody());
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("broj"));
	}

    @Test
    public void createArticleTest()  throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/articles/";
        URI uri = new URI(baseUrl);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("naziv", "testh123456wj");
        jsonObject.put("kratki_test", "test kratki_tekst");
        jsonObject.put("dugi_tekst", "test_dugi_tekst");
        jsonObject.put("cijena", 140);
        jsonObject.put("aktivan", true);
        jsonObject.put("kolicina", 6);
        jsonObject.put("popust", 20);
        jsonObject.put("objavio", 6);
        JSONObject pictures=new JSONObject();
        pictures.put("broj",3);
        pictures.put("slika","https://www.wired.com/story/scientists-reveal-the-first-picture-of-a-black-hole/");
        jsonObject.put("pictures",pictures);

        System.out.println("JSOOOOOOON"+jsonObject);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, jsonObject, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true,result.getBody().contains("testh123456wj"));
    }


    @Test
    public void createdArticleExistsTest()  throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/articles/";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true,result.getBody().contains("testh12345"));
    }


    @Test
    public void createArticleForNotExistingUserTest()  throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/articles/";
        URI uri = new URI(baseUrl);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("naziv", "testh1234");
        jsonObject.put("kratki_test", "test kratki_tekst");
        jsonObject.put("dugi_tekst", "test_dugi_tekst");
        jsonObject.put("cijena", 140);
        jsonObject.put("aktivan", true);
        jsonObject.put("kolicina", 6);
        jsonObject.put("popust", 20);
        jsonObject.put("objavio", 10);
        JSONObject pictures=new JSONObject();
        pictures.put("broj",3);
        pictures.put("slika","https://www.wired.com/story/scientists-reveal-the-first-picture-of-a-black-hole/");
        jsonObject.put("pictures",pictures);

        System.out.println("JSOOOOOOON"+jsonObject);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, jsonObject, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(true,result.getBody().contains("Vec postoji artikal"));
    }

    @Test
    public void DeleteArticleTest()  throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8082/articles/19";
        URI uri = new URI(baseUrl);
        restTemplate.delete(baseUrl);
    }

    @Test
    public void DeletedArticleDoesntExistsTest()  throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/articles/";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(false,result.getBody().contains( "19"));
    }

    @Test
    public void DeleteUserTest()  throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8081/rest/users/6";
        URI uri = new URI(baseUrl);
        restTemplate.delete(baseUrl);
    }


    @Test
    public void ArticlesOfDeletedUserDoesntExistsTest()  throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:" + randomServerPort + "/articles/";
        URI uri = new URI(baseUrl);

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        //Verify request succeed
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(false,result.getBody().contains( "22"));
    }


}
