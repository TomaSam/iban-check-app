package com.bank.iban.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.iban.service.IbanService;
import com.opencsv.exceptions.CsvValidationException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/check")
public class IbanController {
	
	@Autowired
	private IbanService ibanService;
	
	@PostMapping("/validation")
	public String ibanValidation(@RequestParam("iban") String iban) {
		return ibanService.ibanValidation(iban);
	}
	
	@PostMapping("/bankcode")
	public String ibanCheckBank(@RequestParam("iban") String iban) {
		return ibanService.recognizeBank(iban);
	}
	
	@GetMapping("/exportValidation")
	public void exportValidation(@RequestParam("path") String path) throws CsvValidationException, IOException {
		ibanService.exportValidation(path);
	}
	
	@GetMapping("/exportBankName")
	public void exportBanks(@RequestParam("path") String path) throws CsvValidationException, IOException {
		ibanService.exportBankName(path);
	}
}
