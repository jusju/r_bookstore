package com.example.roni_bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.roni_bookstore.domain.Book;
import com.example.roni_bookstore.domain.BookRepository;
import com.example.roni_bookstore.domain.Category;
import com.example.roni_bookstore.domain.CategoryRepository;
import com.example.roni_bookstore.domain.User;
import com.example.roni_bookstore.domain.UserRepository;

@SpringBootApplication
public class RoniBookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(RoniBookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RoniBookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("create book categories");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Mystery"));
			crepository.save(new Category("Scifi"));
			crepository.save(new Category("Horror"));

			repository.save(new Book("Stephen King", "IT", "030-2211-3-8756-07", 1986, 22,
					crepository.findByName("Horror").get(0)));
			repository.save(new Book("Edgar Rice Burroughs", "John Carter", "030-2630-0-85-0768", 1964, 16,
					crepository.findByName("Fantasy").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@admin.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}