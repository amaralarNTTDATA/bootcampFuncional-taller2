import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> shopping = List.of( new Product("Clothes", new BigDecimal("15.90"), Tax.NORMAL),
                new Product("Bread", new BigDecimal("1.5"), Tax.SUPERREDUCED),
                new Product("Meat", new BigDecimal("13.99"), Tax.REDUCED),
                new Product("Cheese", new BigDecimal("3.59"), Tax.SUPERREDUCED),
                new Product("Coke", new BigDecimal("1.89"), Tax.REDUCED),
                new Product("Whiskey", new BigDecimal("19.90"), Tax.NORMAL));

        BigDecimal coste = shopping.stream()
                .map(p -> p.price.add(p.price.multiply(new BigDecimal(p.tax.percent)).divide(new BigDecimal(100))))
                .reduce(BigDecimal.ZERO, (x, y) -> x.add(y)).setScale(2, RoundingMode.CEILING);

        System.out.println("El coste total es: " + coste);

        shopping.stream()
                .filter(p -> p.getName().charAt(0) == 'C')
                .map(Product::getName)
                .sorted()
                .forEach(p -> System.out.println(p + " ,"));

    }
}