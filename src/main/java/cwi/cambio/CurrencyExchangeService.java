package cwi.cambio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cwi.cambio.exceptions.InvalidCsvFileException;
import cwi.cambio.exceptions.InvalidQuotationDateException;
import cwi.cambio.exceptions.NonAvaliableDateException;
import cwi.cambio.exceptions.NonExistentFromCurrencyExchangeException;
import cwi.cambio.exceptions.NonExistentToCurrencyExchangeException;
import cwi.cambio.exceptions.ValueSmallerThanZeroException;

public class CurrencyExchangeService {

	private CurrencyExchangeRepository currencyExchangeRepository;
	
	public CurrencyExchangeService() {
		currencyExchangeRepository = new CurrencyExchangeRepository();
	}

	private Date previousBusinessDayOf(String quotation)
			throws InvalidQuotationDateException {
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(quotation);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				calendar.add(Calendar.DATE, -1);
			} else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
				calendar.add(Calendar.DATE, -2);
			}
			return calendar.getTime();
		} catch (ParseException e) {
			throw new InvalidQuotationDateException();
		}
	}

	private BigDecimal convert(Number value,
			CurrencyExchange currencyExchangeFrom,
			CurrencyExchange currencyExchangeTo) {
		BigDecimal result = new BigDecimal(0);
		result = currencyExchangeTo.getBuyingRate().multiply(new BigDecimal(value.toString()));
		result = result.divide(currencyExchangeFrom.getBuyingRate(), 2, RoundingMode.HALF_UP);
		return result;
	}

	public BigDecimal currencyQuotation(String from, String to, Number value,
			String quotation) throws InvalidCsvFileException,
			InvalidQuotationDateException,
			NonExistentToCurrencyExchangeException,
			NonExistentFromCurrencyExchangeException,
			ValueSmallerThanZeroException, NonAvaliableDateException {
		if (value.floatValue() < 0) {
			throw new ValueSmallerThanZeroException();
		}
		Date date = previousBusinessDayOf(quotation);
		CurrencyExchange currencyExchange = currencyExchangeRepository.findByDate(date);
		if (currencyExchange == null) {
			throw new NonAvaliableDateException();
		}
		CurrencyExchange currencyExchangeFrom = currencyExchangeRepository.findByDateAndCurrencyAcronym(date, from);
		if (currencyExchangeFrom == null) {
			throw new NonExistentFromCurrencyExchangeException();
		}
		CurrencyExchange currencyExchangeTo = currencyExchangeRepository.findByDateAndCurrencyAcronym(date, to);
		if (currencyExchangeTo == null) {
			throw new NonExistentToCurrencyExchangeException();
		}
		BigDecimal converted = convert(value, currencyExchangeFrom, currencyExchangeTo);
		return converted;
	}


}
