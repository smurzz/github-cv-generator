package com.example.githubcvgenerator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.githubcvgenerator.modell.Owner;
import com.example.githubcvgenerator.modell.Repo;
import com.example.githubcvgenerator.modell.Resume;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

	private final WebClient webClient;

	private WebClientService(WebClient.Builder builder) {
		webClient = builder.baseUrl("https://api.github.com").build();
	}

	// get resume from owner and repo
	public Mono<Resume> getResumeByLogin(String login) {
		try {
			Owner owner = getOwnerByLogin(login)
					.toFuture()
					.get();
			List<Repo> repos = new ArrayList<>();
			Long pages = (long) (Math.ceil(Double.valueOf(owner.getPublicRepos() / 30.0)));
			
			for (long page = 1; page <= pages; page++) {
				repos.addAll(getReposByOwnerLogin(login, page)
						.collectList()
						.toFuture()
						.get());
			}
			return Mono.just(new Resume(owner, repos));

		} catch (InterruptedException | ExecutionException e) {
			return Mono.error(e);
		}
	}

	// get user-data from "api.github.com"
	public Mono<Owner> getOwnerByLogin(String login) {
		return webClient
				.get()
				.uri("/users/{login}", login)
				.retrieve().onStatus(httpStatus -> httpStatus.value() == 404,
					error -> Mono.error(new IllegalArgumentException("Not Found")))
				.bodyToMono(Owner.class);
	}

	// get repos-data from "api.github.com"
	public Flux<Repo> getReposByOwnerLogin(String login, Long page) {
		return webClient
				.get()
				.uri(uriBuilder -> uriBuilder.path("/users/{login}/repos")
						.queryParam("page", "{page}")
						.build(login, page))
				.retrieve()
				.bodyToFlux(Repo.class);
	}
}
