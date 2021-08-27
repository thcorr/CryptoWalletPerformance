package Controller;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import Model.CryptoCoinExcelEntry;


public class ExcelCsvReaderController {

	private final List<CryptoCoinExcelEntry> crypoCointListFromExcel;
	
	public ExcelCsvReaderController(String cryptoCoinExcelFile) {

		final var dataStream = ClassLoader.getSystemResourceAsStream(cryptoCoinExcelFile);

		if (dataStream == null ) {
			throw new IllegalStateException("File not found or is empty");
		}

		final var builder = new CsvToBeanBuilder<CryptoCoinExcelEntry>(new InputStreamReader(dataStream));

		crypoCointListFromExcel = builder
				.withType(CryptoCoinExcelEntry.class)
				.withSeparator(',')
				.build()
				.parse();
	}
	
	public List<CryptoCoinExcelEntry> getCryptoCoinExcelList(){
		
		return this.crypoCointListFromExcel;
	}
	
}
