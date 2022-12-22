package Model;

public class InvoiceLine {
    private int id;
    private String name;
    private float price;
    private int count;
    public InvoiceLine(int id, String name, float price, int count){
        this.setId(id);
        this.setName(name);
        this.setPrice(price);
        this.setCount(count);
    }

    public String toString() {
        return getId() + ", " + getName() + ", " + getPrice() + ", " + getCount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
