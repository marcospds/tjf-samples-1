package br.com.star.wars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.totvs.tjf.api.jpa.repository.impl.ApiJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = ApiJpaRepositoryImpl.class)
public class StarWarsApiJpaSpecificationSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsApiJpaSpecificationSampleApplication.class, args);
	}

}
