package beadando;

public class Good {
    private String name;
    private int price;
    private int id;
    private String category;

    public Good(String name, int price, int id, String category) {
        this.name = name;
        this.price = price;
        this.id = id;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

