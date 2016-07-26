package cwi.cambio;

import cwi.cambio.exceptions.InvalidCsvFileException;
import cwi.cambio.exceptions.InvalidQuotationDateException;
import cwi.cambio.exceptions.NonAvaliableDateException;
import cwi.cambio.exceptions.NonExistentFromCurrencyExchangeException;
import cwi.cambio.exceptions.NonExistentToCurrencyExchangeException;
import cwi.cambio.exceptions.ValueSmallerThanZeroException;

public class ExchangeRate {

	public void currencyQuotation(String from, String to, Number value, String quotation) {
		CurrencyExchangeService currencyExchangeService = new CurrencyExchangeService();
		try {

			System.out.println("O resultado é " + currencyExchangeService.currencyQuotation(from, to, value, quotation));

		} catch (InvalidCsvFileException e) {
			System.out.println("Arquivo CSV inválido!");
		} catch (InvalidQuotationDateException e) {
			System.out.println("Data inválida!");
		} catch (NonExistentToCurrencyExchangeException e) {
			System.out.println("Atributo \"To\" não encontrado!");
		} catch (NonExistentFromCurrencyExchangeException e) {
			System.out.println("Atributo \"From\" não encontrado!");
		} catch (ValueSmallerThanZeroException e) {
			System.out.println("Valor deve ser maior que zero!");
		} catch (NonAvaliableDateException e) {
			System.out.println("Data não encontrada!");
		}
	}

}
