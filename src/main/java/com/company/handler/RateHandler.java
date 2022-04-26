package com.company.handler;

import com.company.model.RateResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class RateHandler {
	// Trial API key
	private static final String API_KEY = "6ee1de713fa6487b9df817edb551ba02";
	private static final String URL = "http://data.fixer.io/api/latest?access_key=" + API_KEY + "&base=EUR";

	public Mono<ServerResponse> getRate(ServerRequest request) {
		RateResponse response = new RateResponse();
		try {
			URL url = new URL(URL );
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			http.setRequestProperty("Accept", "application/json");

			BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
			StringBuilder data = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				data.append(line).append("\n");
			}

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			response = mapper.readValue(data.toString(), RateResponse.class);

			System.out.println(response.getBase());

			reader.close();
			http.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ServerResponse.ok()
			.body(BodyInserters.fromValue(response.toString()));
	}

}
