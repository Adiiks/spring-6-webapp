package com.adrian.spring6webapp.bootstrap;

import com.adrian.spring6webapp.domain.Author;
import com.adrian.spring6webapp.domain.Book;
import com.adrian.spring6webapp.domain.Publisher;
import com.adrian.spring6webapp.repositories.AuthorRepository;
import com.adrian.spring6webapp.repositories.BookRepository;
import com.adrian.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Author ericSaved = authorRepository.save(eric);
        Book dddSaved = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("567890");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        ericSaved.getBooks().add(dddSaved);
        rodSaved.getBooks().add(noEJBSaved);
        dddSaved.getAuthors().add(ericSaved);
        noEJBSaved.getAuthors().add(rodSaved);

        Publisher pearson = new Publisher();
        pearson.setAddress("80 Strand");
        pearson.setCity("London");
        pearson.setState("Great Britain");
        pearson.setZip("E");
        pearson.setPublisherName("Pearson");
        Publisher pearsonSaved = publisherRepository.save(pearson);

        dddSaved.setPublisher(pearsonSaved);
        noEJBSaved.setPublisher(pearsonSaved);

        authorRepository.saveAll(List.of(ericSaved, rodSaved));
        bookRepository.saveAll(List.of(dddSaved, noEJBSaved));

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());
    }
}
