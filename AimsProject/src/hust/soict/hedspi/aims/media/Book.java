package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media{

	private List<String> authors = new ArrayList<String>();

	public Book(String title) {
		super(title);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
	}
	
    public Book(String title, String category, float cost) throws IllegalArgumentException {
        super(title, category, cost);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
    }
	
    public void addAuthor(String authorName) throws IllegalArgumentException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }

        if (authors.contains(authorName)) {
            throw new IllegalArgumentException("Author '" + authorName + "' already exists.");
        }

        authors.add(authorName);
    }

    public void removeAuthor(String authorName) throws IllegalArgumentException {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be null or empty.");
        }

        if (!authors.contains(authorName)) {
            throw new IllegalArgumentException("Author '" + authorName + "' doesn't exist.");
        }

        authors.remove(authorName);
    }
	
	 @Override
	 public String toString() {
	     return "Book - " + this.id + " - " + this.title + " - " + this.category + " - " + this.authors + ": " + this.cost + " $";
	 }

}