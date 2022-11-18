package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //publisher
        Publisher aPublisher = new Publisher();
        aPublisher.setName("Lumina");
        aPublisher.setCity("New York");
        aPublisher.setAddressLine1("Stefan cel Mare");
        publisherRepository.save(aPublisher);

        //author and book 1
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "12345");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(aPublisher);
        aPublisher.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        //author and book 2
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development", "67890");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(aPublisher);
        aPublisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        publisherRepository.save(aPublisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher " + aPublisher.getName() + " has " + aPublisher.getBooks().size() + " books");
    }
}
