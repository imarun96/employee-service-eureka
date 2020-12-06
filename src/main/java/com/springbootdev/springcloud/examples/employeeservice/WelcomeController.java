package com.springbootdev.springcloud.examples.employeeservice;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RefreshScope
@RestController
public class WelcomeController {

<<<<<<< SBR-1

=======
>>>>>>> master
	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplate template;

	@Value("${app.service-name}")
	private String serviceName;

	@GetMapping("/service")
	public String getServiceName() {
		return "service name [" + this.serviceName + "]";
	}

	@GetMapping("/refresh-specific-endpoint")
	public String getInstance(@RequestParam(name = "target", required = true) String serverName) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(header);
		Application application = eurekaClient.getApplication(serverName);
		application.getInstances().stream().forEach(action -> {
			URI refreshURI = URI.create("http://localhost:" + action.getPort() + "/actuator/refresh");
			template.exchange(refreshURI, HttpMethod.POST, entity, String.class);
			System.out.println("URI = " + refreshURI);
			refreshURI.toString();
		});
		return "Config Properties of the service [" + serverName + "] has been refreshed.";
	}
}