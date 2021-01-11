package com.bank.iban;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bank.iban.service.IbanService;
import com.opencsv.exceptions.CsvValidationException;

@SpringBootTest
public class IbanServiceTest {
	
	@Autowired
	private IbanService ibanService;
	
	@Test
	public void ibanValidationTestIsTrue() {
		String actualResult = ibanService.ibanValidation("LT647044001231465456");
		String expectedResult = "LT647044001231465456;true;";
		assertEquals(expectedResult, actualResult);	
	}
	
	@Test
	public void ibanValidationTestIsFalse() {
		String actualResult = ibanService.ibanValidation("LT227044077788877777");
		String expectedResult = "LT227044077788877777;false;";
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void ibanFindedBankCode() {
		String actualResult = ibanService.recognizeBank("LT227044077788877777");
		String expectedResult = "LT227044077788877777;AB SEB bankas;";
		assertEquals(expectedResult, actualResult);
	}
	
	@Test
	public void ibanNotFindedBankCode() {
		String actualResult = ibanService.recognizeBank("CC051245445454552117989");
		String expectedResult = "CC051245445454552117989;;";
		assertEquals(expectedResult, actualResult);
	}
	
//	@Test
//	public void exportIbansFromCsv() throws CsvValidationException, IOException {
//		String testpath = "C:/Users/User/Desktop/ibanstest.csv";
//		List<String> actualList = ibanService.exportValidation(testpath);
//		String actualRecord = actualList.get(0);
//		String expectedResult = "AA051245445454552117989;false;";
//		assertEquals(expectedResult, actualRecord);
//	}

}
