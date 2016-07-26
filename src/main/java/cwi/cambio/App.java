package cwi.cambio;



public class App 
{
	public static void main(String[] args)
    {
		ExchangeRate exchangeRate = new ExchangeRate();
		exchangeRate.currencyQuotation("ETB", "THB", 10, "19/07/2016");
    }
}
