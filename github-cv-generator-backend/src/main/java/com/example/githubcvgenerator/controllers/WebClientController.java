package com.example.githubcvgenerator.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.githubcvgenerator.modell.Owner;
import com.example.githubcvgenerator.modell.Repo;
import com.example.githubcvgenerator.modell.Resume;
import com.example.githubcvgenerator.service.WebClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class WebClientController {
	
	@Autowired
	WebClientService webClientService;
	
	@GetMapping("/resume/{login}")
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler({InterruptedException.class, ExecutionException.class})
	public Mono<Resume> getResumeByLogin(@PathVariable String login){
		return webClientService.getResumeByLogin(login);
	}
	
	@GetMapping("/users/{login}")
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(IllegalArgumentException.class)
	public Mono<Owner> getOwnerByLogin(@PathVariable String login){
		return webClientService.getOwnerByLogin(login);
	}
	
	@GetMapping("/users/{login}/repos")
	@ResponseStatus(HttpStatus.OK)
	public Flux<Repo> findAllUsers(@PathVariable String login, @RequestParam Long page){
		return webClientService.getReposByOwnerLogin(login, page);
	}
 
}
