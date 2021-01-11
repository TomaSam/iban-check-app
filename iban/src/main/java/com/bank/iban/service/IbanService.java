package com.bank.iban.service;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class IbanService {
	
	private String initialCharacters = "2129";
	
	public String ibanValidation(String iban) {
		if (iban.length() == 20 && computeReminder(iban) == 1 && checkTwoDigits(iban) == Integer.parseInt(iban.substring(2, 4))) {
			return iban + ";true;";
		}
		else {
			return iban + ";false;";
		}
	}
	
	private int computeReminder(String iban) {
		String convertedIban = iban.substring(4) + initialCharacters + iban.subSequence(2, 4);
		System.out.println("Converted string 1: " + convertedIban);
		BigInteger resultBig = new BigInteger(convertedIban).remainder(new BigInteger("97"));
		System.out.println("ResultBig1: " + resultBig);
		int result = resultBig.intValue();
		System.out.println("Result1: " + result);
		return result;
	}
	
	private int checkTwoDigits(String iban) {
		String convertedIban = iban.substring(4) + initialCharacters + "00";
		System.out.println("Converted string 2: " + convertedIban);
		System.out.println("parse: " + Integer.parseInt(iban.substring(2, 4)));
		BigInteger resultBig = new BigInteger(convertedIban).remainder(new BigInteger("97"));
		System.out.println("ResultBig2: " + resultBig);
		int result = 98 - resultBig.intValue();
		System.out.println("Result2: " + result);
		return result;
	}
	
	public String recognizeBank(String iban) {
		String bankCode = iban.substring(4, 9);
		System.out.println(bankCode);
		if (iban.length() != 20) {
			return iban + ";;";
		} else {
		switch (bankCode) {
		  case "40100":
		    return iban + ";Luminor Bank AS Lietuvos skyrius;";  
		  case "71800":
		    return iban + ";AB Siauliu bankas;";
		  case "73000":
		    return iban + ";Swedbank AB;";
		  case "72300":
		    return iban + ";UAB Medicinos bankas;";
		  case "74000":
		    return iban + ";Danske Bank A/S Lietuvos filialas;";
		  case "70440":
		    return iban + ";AB SEB bankas;";
		  default:
		    return iban + ";;"; 
		}
		}
	}
	
	public void exportValidation(String path) throws IOException, CsvValidationException  {
		Reader reader = Files.newBufferedReader(Paths.get(path));
		CSVReader csvReader = new CSVReader(reader);
		String[] ibanRecord;
		List<String[]> validatedList = new ArrayList<>();
		
		while ((ibanRecord = csvReader.readNext()) != null) {
			ibanRecord[0] = ibanValidation(ibanRecord[0]);
			validatedList.add(ibanRecord);
		}
		csvReader.close();
		for (String[] iban : validatedList) {
			System.out.println(iban[0]);
		}
		
		String updatedPath = path + "_valid.csv";
		File file = new File(updatedPath);
		try {
			FileWriter outputFile = new FileWriter(file);
			
			CSVWriter csvWriter = new CSVWriter(outputFile);
			for (String[] iban : validatedList) {
				csvWriter.writeNext(iban);
			}
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace(); 
		}	
	}
	
	public void exportBankName(String path) throws IOException, CsvValidationException {
		Reader reader = Files.newBufferedReader(Paths.get(path));
		CSVReader csvReader = new CSVReader(reader);
		String[] ibanRecord;
		List<String[]> recognizedBankList = new ArrayList<>();
		
		while ((ibanRecord = csvReader.readNext()) != null) {
			ibanRecord[0] = recognizeBank(ibanRecord[0]);
			recognizedBankList.add(ibanRecord);
		}
		csvReader.close();
		for (String[] iban : recognizedBankList) {
			System.out.println(iban[0]);
		}
		
		String updatedPath = path + "_bank.csv";
		File file = new File(updatedPath);
		try {
			FileWriter outputFile = new FileWriter(file);
			
			CSVWriter csvWriter = new CSVWriter(outputFile);
			for (String[] iban : recognizedBankList) {
				csvWriter.writeNext(iban);
			}
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace(); 
		}	
	}

}
