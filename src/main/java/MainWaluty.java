import java.io.IOException;


public class MainWaluty {

    public static void main(String[] args) throws IOException {

        PobieraczKursow pobieraczKursow = new PobieraczKursow();

        pobieraczKursow.wypiszWartoscPLN("USD", "A", 100.00);
        pobieraczKursow.wypiszWartoscPLN("EUR", "A", 100.00);
        pobieraczKursow.wypiszWartoscPLN("GBP", "A", 100.00);
        pobieraczKursow.wypiszWartoscPLN("CHF", "A", 100.00);

    }
}
