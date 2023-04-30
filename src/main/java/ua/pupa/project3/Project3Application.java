package ua.pupa.project3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ua.pupa.project3.DTO.SensorDTO;
import ua.pupa.project3.models.Sensor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
public class Project3Application {

	public static void main(String[] args) {
		SpringApplication.run(Project3Application.class, args);

		RestTemplate restTemplate = new RestTemplate();
		for (int i = 0; i < 1000; i++) {
			Map<String,String> jsonMap = new HashMap<>();
			jsonMap.put("name", "Pupa"+i);
			HttpEntity<Map<String,String>>  request  = new HttpEntity<>(jsonMap);
			String url = "http://localhost:8080/sensors/registration";
			String response = restTemplate.postForObject(url, request, String.class);
			System.out.println(response);
		}
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			Map<String,Object> jsonMap = new HashMap<>();
			jsonMap.put("sensor", Map.of("name", "Pupa"+i));
			jsonMap.put("tvalue", -100 + (100 + 100) * random.nextDouble());
			jsonMap.put("raining", random.nextBoolean());

			HttpEntity<Map<String, Object>>  request  = new HttpEntity<>(jsonMap);
			String url = "http://localhost:8080/measurements/add";
			String response = restTemplate.postForObject(url, request, String.class);
			System.out.println(response);
		}
	}

}
