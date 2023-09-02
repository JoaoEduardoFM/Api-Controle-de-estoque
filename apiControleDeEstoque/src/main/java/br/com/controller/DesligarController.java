package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.service.DesligarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/desligar")
@Api(value = "Desligar serviço", tags = "Desligar", description = " Desligar serviço.")
public class DesligarController {

	@Autowired
	DesligarService service;

	@ApiOperation(
			value = "Desligar aplicação.", 
			notes = "Serviço que desliga a aplicação.")
	@GetMapping()
	public ResponseEntity<?> shutdown() {
		return service.shutdown();
	}
}
