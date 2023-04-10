package org.sda.bms.controller;

import org.sda.bms.model.Author;
import org.sda.bms.model.Book;
import org.sda.bms.repository.exception.EntityCreationFailedException;
import org.sda.bms.repository.exception.EntityFetchingFailedException;
import org.sda.bms.service.AuthorService;
import org.sda.bms.service.BookService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BookController {
    private final BookService bookService;
    private final Scanner scanner;

    public BookController(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.scanner = scanner;
    }

    public void create() {
        try {
            System.out.println("Please insert the title");
            String title = scanner.nextLine().trim();
            System.out.println("Please insert the description");
            String description = scanner.nextLine().trim();
            System.out.println("Please provide the author id");
            int authorId = Integer.parseInt(scanner.nextLine().trim());
            bookService.create(title, description, authorId);
            System.out.println("Book Successfully created");
        } catch (NumberFormatException e) {
            System.err.println("Provided ID is not a number. Please enter a numeric value");
        } catch (EntityNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (EntityCreationFailedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }

    public void displayAll() {
        try {
            List<Book> existingBooks = bookService.findAll();
            if (existingBooks.isEmpty()) {
                System.out.println("No books found in the system");
            } else {
                for (Book book : existingBooks) {
                    System.out.println("ID: " + book.getId() + " Title: " + book.getTitle() + " Description: " + book.getDescription() + " Author: " + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
                }
            }
        } catch (EntityFetchingFailedException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Internal server error. Please contact your administrator.");
        }

    }

    public void displayById() {
        try {
            System.out.println("Please provide the book id: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());
            Optional<Book> optionalBook = bookService.findById(bookId);
            if (optionalBook.isEmpty()) {
                System.out.println("No book found by the current id : ");
            } else {
                Book book = optionalBook.get();
                System.out.println("ID:                " + book.getId());
                System.out.println("Title:             " + book.getTitle());
                System.out.println("Book Description:  " + book.getDescription());
                System.out.println("Author ID:         " + book.getAuthor().getId());
                System.out.println("Author First Name: " + book.getAuthor().getLastName());
                System.out.println("Author Last Name:  " + book.getAuthor().getFirstName());
            }
        } catch(NumberFormatException e){System.err.println(e.getMessage());}
        catch(IllegalArgumentException e){System.err.println(e.getMessage());}
        catch(EntityFetchingFailedException e ){System.err.println(e.getMessage());}
        catch(Exception e ){
            System.err.println("Internal server error. Please contact your administrator.");
        }
    }

    private static String formatBookDescription(String description){
        String result = "\t";

        final int maxNumberOfWords = 5;
        String [] words = description.split("\s");
        for (int index = 0; index < words.length; index++){
            if(index % maxNumberOfWords==0){
                result = result + "\n\t";
            }
            result = result + "\s" + words[index];
        }
        return result;
    }
}
