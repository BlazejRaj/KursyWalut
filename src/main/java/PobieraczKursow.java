import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class PobieraczKursow {

    public Waluta pobierzKurs(String skrotWaluta, Enum kurs, int ileDniWstecz) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -ileDniWstecz);
        while (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
            calendar.add(Calendar.DAY_OF_WEEK, -1);
        }

        String tabela = "";
        if (kurs == jakiKurs.MID) {
            tabela = "A";
        } else if (kurs == jakiKurs.ASK || kurs == jakiKurs.BID) {
            tabela = "C";
        }

        URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/" + tabela + "/" + skrotWaluta + "/"
                + sdf.format(calendar.getTime()) + "/?format=json");
        URLConnection connection = url.openConnection();
        Scanner scanner = new Scanner(connection.getInputStream());
        String newJason = scanner.nextLine();

        Gson g = new Gson();
        Waluta waluta = g.fromJson(newJason, Waluta.class);

        return waluta;
    }

    public void wypiszWartoscPLN(String skrotWaluta, Enum kurs, Double ilePLN, int ileDniWstecz) throws IOException {

        System.out.println(pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getCurrency());
        System.out.println("Kurs z dnia: " + pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getRates()[0].getEffectiveDate());

        System.out.println(ilePLN + " PLN jest warte:");

        if (kurs == jakiKurs.MID) {
            System.out.print(Math.round(ilePLN / pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getRates()[0].getMid() * 100) / 100.0);
            System.out.println(" " + pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getCode());
            System.out.println("Kurs średni: " + pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getRates()[0].getMid());
        } else if (kurs == jakiKurs.ASK) {

            System.out.print(Math.round(ilePLN / pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getRates()[0].getAsk() * 100) / 100.0);
            System.out.println(" " + pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getCode());
            System.out.println("Kurs sprzedaży: " + pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getRates()[0].getAsk());
        } else if (kurs == jakiKurs.BID) {

            System.out.print(Math.round(ilePLN / pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getRates()[0].getBid() * 100) / 100.0);
            System.out.println(" " + pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getCode());
            System.out.println("Kurs kupna: " + pobierzKurs(skrotWaluta, kurs, ileDniWstecz).getRates()[0].getBid());
        }

        System.out.println("----------------");
    }


    public void wypiszZyskStrate (String skrotWaluta, Double ilePLN, int ileDniWstecz)throws IOException{

        Double kursSprzedazyObecny = pobierzKurs(skrotWaluta, jakiKurs.ASK, 0).getRates()[0].getAsk();
        //System.out.println(kursSprzedazyObecny);

        Double kursKupnaKiedys = pobierzKurs(skrotWaluta, jakiKurs.BID, ileDniWstecz).getRates()[0].getBid();
        //System.out.println(kursKupnaKiedys);

        Double ZyskStrata = ilePLN/kursKupnaKiedys - ilePLN/kursSprzedazyObecny;
        System.out.print("Zysk z tej operacji to: ");
        System.out.println(Math.round(ZyskStrata*100.0)/100.0);

    }
}

