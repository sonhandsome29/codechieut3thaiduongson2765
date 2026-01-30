import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private double price;

    public Book() {}

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }


    public void input(Scanner sc) {
        this.id = readInt(sc, "Nhập mã sách (int): ");
        System.out.print("Nhập tên sách: ");
        this.title = sc.nextLine().trim();
        System.out.print("Nhập tác giả: ");
        this.author = sc.nextLine().trim();
        this.price = readDouble(sc, "Nhập đơn giá (double): ");
    }


    public void output() {
        System.out.printf("Book{id=%d, title='%s', author='%s', price=%.2f}%n",
                id, title, author, price);
    }


    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("❌ Sai định dạng int, nhập lại!");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("❌ Sai định dạng double, nhập lại!");
            }
        }
    }
}
