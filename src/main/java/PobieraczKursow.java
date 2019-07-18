import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class PobieraczKursow {

    public Waluta pobierzKurs(String skrotWaluta, String tabela) throws IOException {

    URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/"+tabela+"/"+skrotWaluta+"/?format=json");
    URLConnection connection = url.openConnection();
    Scanner scanner = new Scanner(connection.getInputStream());
    String newJason = scanner.nextLine();

    Gson g = new Gson();
    Waluta waluta = g.fromJson(newJason, Waluta.class);

    return waluta;
    }

    public void wypiszWartoscPLN(String skrotWaluta, String tabela, Double ilePLN) throws IOException {

        System.out.println(pobierzKurs(skrotWaluta, tabela).getCurrency());

        System.out.println(ilePLN + " PLN jest warte:");

       // System.out.print(ilePLN / pobierzKurs(skrotWaluta, tabela).getRates()[0].getMid());
        System.out.print(Math.round(ilePLN / pobierzKurs(skrotWaluta, tabela).getRates()[0].getMid()*100)/100.0);
        System.out.println(" " + pobierzKurs(skrotWaluta, tabela).getCode());
        System.out.println("----------------");

    }
}
