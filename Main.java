import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Integer> products = new HashMap<>();
        products.put("Хлеб", 56);
        products.put("Масло", 153);
        products.put("Колбаса", 211);
        products.put("Пирожок", 45);

        System.out.println("В МАГАЗИНЕ В НАЛИЧИИ");
        for (Map.Entry<String, Integer> productAndPrice : products.entrySet()) {
            System.out.println(productAndPrice.getKey() + " за " + productAndPrice.getValue() + " руб./шт.");
        }

        System.out.println("Введите два слова: название товара и количество. Или end");
        Scanner scanner = new Scanner(System.in);
        PurchaseManager purchaseManager = new PurchaseManager();
        while (true) {
            String line = scanner.nextLine();
            if ("end".equals(line)) break;
            String[] parts = line.split(" ");
            if (parts.length != 2) {
                System.out.println("Некорректный ввод. Попробуйте снова.");
                continue;
            }
            String product = parts[0];
            int count;
            try {
                count = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Некорректное количество. Попробуйте снова.");
                continue;
            }
            purchaseManager.addPurchase(product, count);
        }
        long sum = purchaseManager.calculateTotal(products);
        System.out.println("ИТОГО: " + sum + " руб.");
    }
}

class PurchaseManager {
    private Map<String, Integer> purchases;

    public PurchaseManager() {
        purchases = new HashMap<>();
    }

    public void addPurchase(String product, int count) {
        purchases.put(product, purchases.getOrDefault(product, 0) + count);
    }

    public long calculateTotal(Map<String, Integer> products) {
        long total = 0;
        System.out.println("КОРЗИНА:");
        for (Map.Entry<String, Integer> entry : purchases.entrySet()) {
            String product = entry.getKey();
            int count = entry.getValue();
            int price = products.getOrDefault(product, 0);
            int subtotal = count * price;
            System.out.println(product + " " + count + " шт. в сумме " + subtotal + " руб.");
            total += subtotal;
        }
        return total;
    }
}
