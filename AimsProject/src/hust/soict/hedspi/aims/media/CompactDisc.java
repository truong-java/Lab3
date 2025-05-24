package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
	private String artist;
	private List<Track> tracks = new ArrayList<Track>();
	
	public CompactDisc(String title) {
		super(title);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
	}
	
    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
    }
	
    public CompactDisc(String title, String category, String director, float cost, String artist) {
        super(title, category, cost);
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative.");
        }
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Artist name cannot be null or empty.");
        }
        if (director == null || director.trim().isEmpty()) {
            throw new IllegalArgumentException("Director name cannot be null or empty.");
        }
        this.director = director;
        this.artist = artist;
    }

	public String getArtist() {
		return artist;
	}
	
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }
    
    public List<Track> getTracks() {
		return tracks;
	}
    
    public void setTracks(List<Track> tracks) {
        if (tracks == null) {
            throw new IllegalArgumentException("Track list cannot be null.");
        }
        this.tracks = tracks;
    }
	
    public void addTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        if (tracks.contains(track)) {
            throw new IllegalArgumentException("The track '" + track.getTitle() + "' already exists.");
        }
        tracks.add(track);
    }

    public void removeTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        if (!tracks.contains(track)) {
            throw new IllegalArgumentException("The track '" + track.getTitle() + "' doesn't exist.");
        }
        tracks.remove(track);
    }
    
	@Override
	public void play() throws PlayerException{
	    if(this.getLength() > 0) {
	        for (Track track : tracks) {
                track.play();
            }
	        java.util.Iterator iter = tracks.iterator();
	        Track nextTrack;
	        while(iter.hasNext()) {
	            nextTrack = (Track) iter.next();
	            try {
	                nextTrack.play();
	            }catch(PlayerException e) {
	                throw e;
	            }
	        }
	    } else {
	        throw new PlayerException("ERROR: CD length is non-positive!");
	    }
	}

	@Override
	public String toString() {
	    return "CD - " + this.id + " - " + this.title + " - " + this.category + " - " + this.director + " - " + this.artist + " - " + this.length + ": " + this.cost + " $";
	}
}