import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = readInt(sc, "Chọn chức năng: ");

            switch (choice) {
                case 1 -> { // Thêm
                    Book b = new Book();
                    b.input(sc);

                    // check trùng id
                    boolean exists = listBook.stream().anyMatch(x -> x.getId() == b.getId());
                    if (exists) {
                        System.out.println("❌ ID đã tồn tại. Không thêm!");
                    } else {
                        listBook.add(b);
                        System.out.println("✅ Thêm sách thành công!");
                    }
                }

                case 2 -> { // Xóa theo id
                    int id = readInt(sc, "Nhập id cần xóa: ");
                    boolean removed = listBook.removeIf(b -> b.getId() == id);
                    System.out.println(removed ? "✅ Xóa thành công!" : "❌ Không tìm thấy id để xóa!");
                }

                case 3 -> { // Sửa theo id
                    int id = readInt(sc, "Nhập id cần sửa: ");
                    Book found = listBook.stream()
                            .filter(b -> b.getId() == id)
                            .findFirst()
                            .orElse(null);

                    if (found == null) {
                        System.out.println("❌ Không tìm thấy sách để sửa!");
                    } else {
                        System.out.println("Nhập thông tin mới (bỏ trống nếu muốn giữ nguyên):");

                        System.out.print("Tên sách mới: ");
                        String newTitle = sc.nextLine().trim();
                        if (!newTitle.isEmpty()) found.setTitle(newTitle);

                        System.out.print("Tác giả mới: ");
                        String newAuthor = sc.nextLine().trim();
                        if (!newAuthor.isEmpty()) found.setAuthor(newAuthor);

                        String priceStr;
                        while (true) {
                            System.out.print("Đơn giá mới: ");
                            priceStr = sc.nextLine().trim();
                            if (priceStr.isEmpty()) break; // giữ nguyên
                            try {
                                double newPrice = Double.parseDouble(priceStr);
                                found.setPrice(newPrice);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Sai định dạng double, nhập lại hoặc để trống!");
                            }
                        }

                        System.out.println("✅ Cập nhật thành công!");
                    }
                }

                case 4 -> {
                    if (listBook.isEmpty()) {
                        System.out.println("📌 Danh sách rỗng!");
                    } else {
                        System.out.println("===== DANH SÁCH SÁCH =====");
                        listBook.forEach(Book::output); // method reference
                    }
                }

                case 5 -> {
                    String key = "lập trình";
                    List<Book> result = listBook.stream()
                            .filter(b -> b.getTitle() != null &&
                                    b.getTitle().toLowerCase().contains(key))
                            .toList();

                    if (result.isEmpty()) {
                        System.out.println("❌ Không có sách nào chứa 'lập trình'!");
                    } else {
                        System.out.println("✅ Kết quả:");
                        result.forEach(Book::output);
                    }
                }

                case 6 -> {
                    int k = readInt(sc, "Nhập K: ");
                    double p = readDouble(sc, "Nhập P: ");

                    List<Book> result = listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .toList();

                    if (result.isEmpty()) {
                        System.out.println("❌ Không có sách nào có giá <= P!");
                    } else {
                        System.out.println("✅ Kết quả (tối đa " + k + " cuốn):");
                        result.forEach(Book::output);
                    }
                }

                case 7 -> {
                    System.out.println("Nhập danh sách tác giả (ngăn cách bằng dấu phẩy):");
                    System.out.print("Ví dụ: Nguyễn Nhật Ánh, Tô Hoài, ...\n> ");
                    String line = sc.nextLine().trim();

                    if (line.isEmpty()) {
                        System.out.println("❌ Bạn chưa nhập tác giả!");
                        break;
                    }


                    Set<String> authorSet = Arrays.stream(line.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .map(String::toLowerCase)
                            .collect(Collectors.toSet());

                    List<Book> result = listBook.stream()
                            .filter(b -> b.getAuthor() != null &&
                                    authorSet.contains(b.getAuthor().toLowerCase()))
                            .toList();

                    if (result.isEmpty()) {
                        System.out.println("❌ Không có sách của các tác giả đã nhập!");
                    } else {
                        System.out.println("✅ Sách của các tác giả đã nhập:");
                        result.forEach(Book::output);
                    }
                }

                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }

                default -> System.out.println("❌ Chọn sai, nhập lại!");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("========== QUẢN LÝ SÁCH ==========");
        System.out.println("1. Thêm 1 cuốn sách");
        System.out.println("2. Xóa 1 cuốn sách");
        System.out.println("3. Thay đổi cuốn sách");
        System.out.println("4. Xuất thông tin tất cả cuốn sách");
        System.out.println("5. Tìm sách có tựa đề chứa 'lập trình'");
        System.out.println("6. Lấy tối đa K sách có giá <= P");
        System.out.println("7. Nhập danh sách tác giả -> in sách của họ");
        System.out.println("0. Thoát");
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
