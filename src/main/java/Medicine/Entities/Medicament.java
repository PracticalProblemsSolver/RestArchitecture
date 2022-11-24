package Medicine.Entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Enter a name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Enter a name")
    @Column(name = "type", nullable = false)
    private String type;

    @NotEmpty(message = "Enter a name")
    @Column(name = "form", nullable = false)
    private String form;

    @Min(value = 1, message = "Price can't be null or negative")
    @Column(name = "price", nullable = false)
    private double price;

    @Min(value = 0, message = "Count can't be negative")
    @Column(name = "count", nullable = false)
    private int count;

    public Medicament() {
    }

    public Medicament(String name, String type, String form, double price, int count) {
        this.name = name;
        this.type = type;
        this.form = form;
        this.price = price;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", form='" + form + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
