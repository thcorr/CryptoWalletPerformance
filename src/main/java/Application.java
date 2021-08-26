
import java.util.List;

import Controller.CryptoCoinEnricherController;
import Controller.ExcelCsvReaderController;
import Controller.OutputController;

import Model.CryptoCoinEnriched;
import Model.CryptoCoinExcelEntry;

public class Application {

	public Application() {
		execute();
	}

	public static void main(String[] args) {
		new Application();

	}

	private void execute() {
		final List<CryptoCoinExcelEntry> cryptoCoinExcelList = new ExcelCsvReaderController("crypto.csv").getCryptoCoinExcelList();

		final List<CryptoCoinEnriched> listOfEnrichedCryptoCoins = new CryptoCoinEnricherController().bulkEnrichCryptoCoinsFromInput(cryptoCoinExcelList);
		
		new OutputController().resultOutput(listOfEnrichedCryptoCoins);

	}
}
