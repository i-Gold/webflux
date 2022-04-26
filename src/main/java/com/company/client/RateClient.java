package com.company.client;

import com.company.model.RateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
public class RateClient {

	private final WebClient client;

	@Autowired
	public RateClient(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:8080/").build();
	}

	public RateResponse getExchangeRate() {
		return this.client.get().uri("/api/exchange-rate").accept(MediaType.TEXT_PLAIN)
				.retrieve()
				.bodyToMono(RateResponse.class)
				.block();
	}

}
