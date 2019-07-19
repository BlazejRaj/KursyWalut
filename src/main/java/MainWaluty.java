import java.io.IOException;



public class MainWaluty {

    public static void main(String[] args) throws IOException {

        PobieraczKursow pobieraczKursow = new PobieraczKursow();



        pobieraczKursow.wypiszWartoscPLN("USD", jakiKurs.MID, 100.00, 0);
        pobieraczKursow.wypiszWartoscPLN("EUR", jakiKurs.MID, 100.00, 0);
        pobieraczKursow.wypiszWartoscPLN("GBP", jakiKurs.MID, 100.00, 0);
        pobieraczKursow.wypiszWartoscPLN("CHF", jakiKurs.MID, 100.00, 0);
//        pobieraczKursow.wypiszWartoscPLN("CHF", jakiKurs.ASK, 100.00, 0);
//        pobieraczKursow.wypiszWartoscPLN("CHF", jakiKurs.BID, 100.00, 0);

        System.out.println("-----------------------------------------------------------");
//      Zadanie dodatkowe 1 - kurs sprzedaży
        pobieraczKursow.wypiszWartoscPLN("USD", jakiKurs.ASK, 100.00, 0);
        pobieraczKursow.wypiszWartoscPLN("EUR", jakiKurs.ASK, 100.00, 0);
        pobieraczKursow.wypiszWartoscPLN("GBP", jakiKurs.ASK, 100.00, 0);
        pobieraczKursow.wypiszWartoscPLN("CHF", jakiKurs.ASK, 100.00, 0);

        System.out.println("-----------------------------------------------------------");
//        zadanie dodatkowe - zamiana na walutę miesiąc temu.

        pobieraczKursow.wypiszZyskStrate("EUR", 100.00, 30);

    }
}
