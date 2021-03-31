package com.zensar.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zensar.model.Airport;
import com.zensar.services.AirportService;

@Controller
public class AirportController {

	@Autowired
	AirportService airportService;

	@PostMapping("/airportnew")
	public void saveAirport(@RequestBody Airport airport) {

		airportService.saveAirport(airport);
	}

	@GetMapping("/getAlAirportP")
	public Page<Airport> getAlAirportP() {
		System.out.println("hi page" + airportService.getAllAirportsPaged(0));
		return airportService.getAllAirportsPaged(0);
	}

	@GetMapping("/getAlAirportP1")
	public Page<Airport> getAlAirportP1(@PathParam("pageNo") int pageNo) {
		return airportService.getAllAirportsPaged(pageNo);
	}

	@DeleteMapping("/airportdelete/{airportId}")
	public void deleteAirport(@PathVariable("airportId") int airportId) {
		System.out.println("back delete");
		airportService.deleteAirport(airportId);

	}
}
