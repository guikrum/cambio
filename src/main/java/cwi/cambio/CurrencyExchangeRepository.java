package cwi.cambio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import cwi.cambio.exceptions.InvalidCsvFileException;

public class CurrencyExchangeRepository {

	private String getCsvFilePath() throws IOException {
		InputStream file = getClass().getResourceAsStream("application.properties");
		Properties props = new Properties();
		props.load(file);

		String csvFilePath = props.getProperty("csv-file-path");
		return csvFilePath;
	};

	private CurrencyExchange loadFromCsvLine(String line) throws ParseException {
		String[] csvValues = line.split(";");
		CurrencyExchange currencyExchange = new CurrencyExchange();

		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(csvValues[0]);
		currencyExchange.setDate(date);

		Currency currency = new Currency();
		currencyExchange.setCurrency(currency);
		currencyExchange.getCurrency().setAcronym(csvValues[3]);
		currencyExchange.getCurrency().setCode(Long.parseLong(csvValues[1]));
		currencyExchange.getCurrency().setType(CurrencyType.valueOf(csvValues[2]));

		currencyExchange.setBuyingRate(new BigDecimal(csvValues[4].replace(",", ".")));
		currencyExchange.setSellingRate(new BigDecimal(csvValues[5].replace(",", ".")));
		currencyExchange.setBuyingParity(new BigDecimal(csvValues[6].replace(",", ".")));
		currencyExchange.setSellingParity(new BigDecimal(csvValues[7].replace(",", ".")));
		return currencyExchange;
	}
	
	private CurrencyExchange getLineByDateAndCurrencyAcronym(
			BufferedReader csvFile, Date date, String acronym)
			throws InvalidCsvFileException {
		String line;
		try {
			line = csvFile.readLine();
			if (line == null)
				return null;

			CurrencyExchange currencyExchange = loadFromCsvLine(line);
			if ((currencyExchange.getDate().equals(date)) && (currencyExchange.getCurrency().getAcronym().equals(acronym))) {
				return currencyExchange;
			} else {
				return getLineByDateAndCurrencyAcronym(csvFile, date, acronym);
			}
		} catch (IOException e) {
			throw new InvalidCsvFileException();
		} catch (ParseException e) {
			throw new InvalidCsvFileException();
		}
	}

	public CurrencyExchange findByDateAndCurrencyAcronym(Date date,
			String acronym) throws InvalidCsvFileException {
		BufferedReader csvFile;
		try {
			csvFile = new BufferedReader(new FileReader(getCsvFilePath()));
			CurrencyExchange currencyExchange = getLineByDateAndCurrencyAcronym(csvFile, date, acronym);
			csvFile.close();
			return currencyExchange;
		} catch (IOException e) {
			throw new InvalidCsvFileException();
		}
	}

	private CurrencyExchange getLineByDate(BufferedReader csvFile, Date date)
			throws InvalidCsvFileException {
		String line;
		try {
			line = csvFile.readLine();
			if (line == null)
				return null;

			CurrencyExchange currencyExchange = loadFromCsvLine(line);
			if (currencyExchange.getDate().equals(date)) {
				return currencyExchange;
			} else {
				return getLineByDate(csvFile, date);
			}
		} catch (IOException e) {
			throw new InvalidCsvFileException();
		} catch (ParseException e) {
			throw new InvalidCsvFileException();
		}
	}

	public CurrencyExchange findByDate(Date date)
			throws InvalidCsvFileException {
		BufferedReader csvFile;
		try {
			csvFile = new BufferedReader(new FileReader(getCsvFilePath()));
			CurrencyExchange currencyExchange = getLineByDate(csvFile, date);
			csvFile.close();
			return currencyExchange;
		} catch (IOException e) {
			throw new InvalidCsvFileException();
		}
	}
}
