package Controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExcelCsvReaderControllerTest {

	
	@Test
	@DisplayName("Ensure proper handling of incorrect csv file name")
	public void testInvalidExcelFileName() {

		String incorrectFileName = "BadFileName";
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			new ExcelCsvReaderController(incorrectFileName);
	    });

		String expectedMessage = "File not found or is empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	@DisplayName("Ensure proper handling of valid csv file")
	public void testValidFileImport() {
		
		String correctFileName = "crypto_t.csv";
		assertDoesNotThrow(() -> {new ExcelCsvReaderController(correctFileName);});
	}
	

}
