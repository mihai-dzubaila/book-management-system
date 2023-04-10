package org.sda.bms.service;

import org.sda.bms.model.Book;
import org.sda.bms.model.Review;
import org.sda.bms.repository.BookRepository;
import org.sda.bms.repository.ReviewRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(int bookId, int score, String comment) {
        if (bookId <= 0) {
            throw new IllegalArgumentException(
                    "Provided ID is negative or 0. Provide a valid value."
            );
        }
        if (score < 1 || score > 5) {
            throw new IllegalArgumentException(
                    "Provided Score is not between 1 and 5. Please provide a valid value."
            );
        }
        if (comment == null || comment.isEmpty() || comment.isBlank()) {
            throw new IllegalArgumentException(
                    "Provided comment is empty. Please provide a valid comment"
            );
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            throw new EntityNotFoundException(
                    "Book with given ID was not found in the system"
            );
        }

        Book book = optionalBook.get();
        Review review = new Review(score, comment);
        review.setBook(book);
        reviewRepository.create(review);
    }
}
