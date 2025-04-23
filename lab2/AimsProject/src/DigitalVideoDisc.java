public class DigitalVideoDisc {
    private  String title;
    private  String category;
    private  String director;
    private  int length;
    private  float cost;

    // Constructors
     public DigitalVideoDisc(String title) {
        super();
        this.title = title;
     }


    public DigitalVideoDisc(String category, String title, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public DigitalVideoDisc(String director, String category, String title, float cost){
        super();
        this.title = title;
        this.category = category;
        this.cost = cost;
        this.director = director;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    // toString method to display DVD information
    @Override
    public String toString() {
        return "DVD [Title=" + title + ", Category=" + category + ", Director=" + director + ", Length=" + length + ", Cost=" + cost + "]";
    }
}
