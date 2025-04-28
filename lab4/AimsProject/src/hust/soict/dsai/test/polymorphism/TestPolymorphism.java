package hust.soict.dsai.test.polymorphism;
import hust.soict.dsai.aims.media.*;
import java.util.ArrayList;
import java.util.List;
public class TestPolymorphism {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<Media>();
        DigitalVideoDisc dvd = new DigitalVideoDisc(1,"Neu ngay ay","Nhac tre", 18.5f,"Trinh Minh Duc",97);
        ArrayList<Track> tracks = new ArrayList<Track>();
        tracks.add(new Track("chuc mung ban",3));
        tracks.add(new Track("toi yeu ban",4));
        CompactDisc cd = new CompactDisc(2,"mot minh anh noi dau","that tinh",25.5f,"Khanh Duy",tracks);
        List<String> authors = new ArrayList<String>();
        authors.add(" Huy Le");
        authors.add("Minh Duc");
        Book book   = new Book(3,"chuyen tinh anh va em","teen",25.2f,authors);

        mediae.add(dvd);
        mediae.add(cd);
        mediae.add(book);

        for(Media item : mediae) {
            System.out.println(item);
        }
    }
}