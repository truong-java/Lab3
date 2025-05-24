package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
	

    public DigitalVideoDisc(String title) {
        super(title);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }
	
    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
    }
	
    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty.");
        }
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Director cannot be null or empty.");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }

        this.director = director;
        this.length = length;
    }

   @Override
   public String toString() {
       return "DVD - " + this.id + " - " + this.title + " - " + this.category + " - " + this.director + " - " + this.length + ": " + this.cost + " $";
   }
    
    public boolean isMatch(String title) {
    	if (this.title.equals(title) == true) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
	@Override
	public void play() throws PlayerException {
	    if (this.getLength() > 0) {
		    System.out.println("Playing DVD: " + this.getTitle());
		    System.out.println("DVD length: " + this.getLength());
	    } else {
	        throw new PlayerException("ERROR: DVD length is non-positive!");
	    }
	}
}