package com.company;

import com.company.model.RateResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateClientTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void getCurrentRate() {
		webTestClient
			.get().uri("/api/exchange-rate")
			.accept(MediaType.TEXT_PLAIN)
			.exchange()
			.expectStatus().isOk()
			.expectBody(RateResponse.class).value(rate -> {
				assertThat(rate.getBase()).isEqualTo("EUR");
		});
	}
}
