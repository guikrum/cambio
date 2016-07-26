package cwi.cambio;

import java.math.BigDecimal;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import cwi.cambio.exceptions.InvalidCsvFileException;
import cwi.cambio.exceptions.InvalidQuotationDateException;
import cwi.cambio.exceptions.NonAvaliableDateException;
import cwi.cambio.exceptions.NonExistentFromCurrencyExchangeException;
import cwi.cambio.exceptions.NonExistentToCurrencyExchangeException;
import cwi.cambio.exceptions.ValueSmallerThanZeroException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

	public void testCorretParameters()
    {
		CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
		BigDecimal rate;
		try {
			rate = currencyExchangeService.currencyQuotation("ETB", "THB", 10, "19/07/2016");
			BigDecimal right = new BigDecimal("6.33");
			assertTrue(rate.equals(right));
		} catch (InvalidCsvFileException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (InvalidQuotationDateException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonExistentToCurrencyExchangeException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonExistentFromCurrencyExchangeException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (ValueSmallerThanZeroException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonAvaliableDateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
    }

	public void testValueSmallerThanZero() {
		CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
		try {
			currencyExchangeService.currencyQuotation("ETB", "THB", -10, "19/07/2016");
			assertTrue(false);
		} catch (InvalidCsvFileException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (InvalidQuotationDateException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonExistentToCurrencyExchangeException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonExistentFromCurrencyExchangeException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (ValueSmallerThanZeroException e) {
			assertTrue(true);
		} catch (NonAvaliableDateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testNonExistentFromCurrencyExchange() {
		CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
		try {
			currencyExchangeService.currencyQuotation("aaaaa", "THB", 10, "19/07/2016");
			assertTrue(false);
		} catch (InvalidCsvFileException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (InvalidQuotationDateException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonExistentToCurrencyExchangeException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonExistentFromCurrencyExchangeException e) {
			assertTrue(true);
		} catch (ValueSmallerThanZeroException e) {
			e.printStackTrace();
			assertTrue(true);
		} catch (NonAvaliableDateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testNonExistentToCurrencyExchange() {
		CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
		try {
			currencyExchangeService.currencyQuotation("ETB", "aaaaa", 10, "19/07/2016");
			assertTrue(false);
		} catch (InvalidCsvFileException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (InvalidQuotationDateException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonExistentToCurrencyExchangeException e) {
			assertTrue(true);
		} catch (NonExistentFromCurrencyExchangeException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (ValueSmallerThanZeroException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (NonAvaliableDateException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
