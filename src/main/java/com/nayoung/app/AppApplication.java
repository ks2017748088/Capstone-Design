package com.nayoung.app;

import com.nayoung.app.domain.Account;
import com.nayoung.app.domain.Reserve;
import com.nayoung.app.domain.Trainer;
import com.nayoung.app.repository.AccountRepository;
import com.nayoung.app.repository.ReserveRepository;
import com.nayoung.app.repository.TrainerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(AccountRepository accountRepository,
									ReserveRepository reserveRepository,
									TrainerRepository trainerRepository) {
		return (args) -> {
			reserveRepository.save(Reserve.builder().name("홍길동").period("3개월").date("0610").time("14시").build());
			trainerRepository.save(Trainer.builder().name("김땡땡").field("코어").time("14시").build());
		};
	}
}
