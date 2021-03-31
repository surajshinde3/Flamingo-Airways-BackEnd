package com.zensar.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zensar.model.Aircraft;
import com.zensar.services.AircraftService;

@Controller
public class AircraftController {

	@Autowired
	AircraftService aircraftService;

	@PostMapping("/aircraftnew")
	public void saveAircraft(@RequestBody Aircraft aircraft) {

		aircraftService.saveAircraft(aircraft);

	}

	@GetMapping("/getAllAircraftsP")
	public Page<Aircraft> getAllAircraftsP() {
		return aircraftService.getAllAircraftsPaged(0);
	}

	@GetMapping("/getAllAircraftsP1")
	public Page<Aircraft> getAllAircraftsP1(@PathParam("pageNo") int pageNo) {
		return aircraftService.getAllAircraftsPaged(pageNo);
	}

	@DeleteMapping("/aircraftdelete/{aircraftId}")
	public void deleteAircraft(@PathVariable("aircraftId") Long aircraftId, Model model) {
		aircraftService.deleteAircraftById(aircraftId);
	}
}
