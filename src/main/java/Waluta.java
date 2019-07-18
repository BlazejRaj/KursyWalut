public class Waluta {

    private String table;
    private String currency;
    private String code;
    private Rates[] rates;


    public String getCurrency() {
        return currency;
    }

    public Rates[] getRates() {
        return rates;
    }

    public String getCode() {
        return code;
    }
}
