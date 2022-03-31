package com.example.roni_bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.roni_bookstore.domain.Book;
import com.example.roni_bookstore.domain.BookRepository;
import com.example.roni_bookstore.domain.Category;
import com.example.roni_bookstore.domain.CategoryRepository;
import com.example.roni_bookstore.domain.User;
import com.example.roni_bookstore.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;

	@Autowired
	private UserRepository urepository;

	@Test
	public void findBookTest() {
		List<Book> books = repository.findByTitle("IT");

		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Stephen King");
	}

	@Test
	public void findCategoryTest() {
		List<Category> categories = crepository.findByName("Horror");

		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getCategoryId()).isEqualTo(4);
	}

	@Test
	public void findUserTest() {
		User users = urepository.findByUsername("user");

		assertThat(users).isNotNull();
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Test Author", "Test Book", "Test ISBN", 1111, 11, new Category("Test"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteNewBook() {
		List<Book> books = repository.findByTitle("IT");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("IT");
		assertThat(newBooks).hasSize(0);
	}

}
