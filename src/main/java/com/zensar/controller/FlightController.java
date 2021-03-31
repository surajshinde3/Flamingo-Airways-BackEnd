package com.zensar.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.model.Aircraft;
import com.zensar.model.Airport;
import com.zensar.model.Flight;
import com.zensar.model.Passenger;
import com.zensar.services.AircraftService;
import com.zensar.services.AirportService;
import com.zensar.services.FlightService;
import com.zensar.services.PassengerService;

@RestController
public class FlightController {

	@Autowired
	AirportService airportService;
	@Autowired
	AircraftService aircraftService;
	@Autowired
	FlightService flightService;
	@Autowired
	PassengerService passengerService;

//	@GetMapping("/")
//	public String showHomePage() {
//		return "index";
//	}

//	@GetMapping("/airport/new")
//	public String showAddAirportPage(Model model) {
//		model.addAttribute("airport", new Airport());
//		return "newAirport";
//	}

//	@GetMapping("/airports")
//	public String showAirportsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {
//		model.addAttribute("airports", airportService.getAllAirportsPaged(pageNo));
//		model.addAttribute("currentPage", pageNo);
//		return "airports";
//	}

//	@GetMapping("/aircraft/new")
//	public String showAddAircraftPage(Model model) {
//		model.addAttribute("aircraft", new Aircraft());
//		return "newAircraft";
//	}

//	@GetMapping("/aircrafts")
//	public String showAircraftsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {
//		model.addAttribute("aircrafts", aircraftService.getAllAircraftsPaged(pageNo));
//		model.addAttribute("currentPage", pageNo);
//		return "aircrafts";
//	}

	@GetMapping("/flightnew")
	public List<Aircraft> showNewFlightPage() {
		List<Aircraft> a1 = new ArrayList<Aircraft>();
		a1 = aircraftService.getAllAircrafts();
		return a1;
	}

	@GetMapping("/flightnew1")
	public List<Airport> showNewFlightPage1() {
		System.out.println("oooooooooooooooooo");
		List<Airport> a2 = new ArrayList<Airport>();
		return a2 = airportService.getAllAirports();

	}

	@GetMapping("/flightnew2/{aircraftId}")
	public Aircraft saveFlight(@PathVariable("aircraftId") Long aircraftId) {

		return aircraftService.getAircraftById(aircraftId);

	}

	@GetMapping("/flightnew3/{departureAirport}")
	public Airport saveFlight3(@PathVariable("departureAirport") int departureAirport) {

		return airportService.getAirportById(departureAirport);

	}

	@GetMapping("/flightnew4/{departureAirport}")
	public Airport saveFlight4(@PathVariable("departureAirport") int destinationAirport) {

		return airportService.getAirportById(destinationAirport);

	}

	@PostMapping("/flightnew5")
	public void saveFlight5(@RequestBody Flight flight) {

		flightService.saveFlight(flight);

	}

	@GetMapping("/getAllFlightsPage")
	public Page<Flight> getAllFlightsPage() {
		return flightService.getAllFlightsPaged(0);
	}

	@GetMapping("/getAllFlightsPage2")
	public Page<Flight> getAllFlightsPage2(@PathParam("pageNo") int pageNo) {
		return flightService.getAllFlightsPaged(pageNo);
	}

	@GetMapping("/getallflightsss")
	public List<Flight> getAllFlight() {

		return flightService.getAllFlights();

	}

	@PostMapping("/settingFlight/{aircraftId}/{departureAirport}/{destinationAirport}/{departureTime}/{arrivalTime}")
	public void settingFlight(@PathVariable("aircraftId") Long aircraftId,
			@PathVariable("departureAirport") int departureAirport,
			@PathVariable("destinationAirport") int destinationAirport,
			@PathVariable("departureTime") String departureTime, @PathVariable("arrivalTime") String arrivalTime,
			@RequestBody Flight flight) {

		System.out.println("inside back settingFlight");

		flight.setAircraft(aircraftService.getAircraftById(aircraftId));
		flight.setDepartureAirport(airportService.getAirportById(departureAirport));
		flight.setDestinationAirport(airportService.getAirportById(destinationAirport));
		flight.setDepartureTime(departureTime);
		flight.setArrivalTime(arrivalTime);
		flightService.saveFlight(flight);
		System.out.println(flight);
	}

	@DeleteMapping("/flightdelete/{flightId}")
	public void deleteFlight(@PathVariable("flightId") long flightId) {
		flightService.deleteFlightById(flightId);

	}

	@PostMapping("/setSearchFlight/{departureAirport}/{destinationAirport}/{departureTime}")
	public List<Flight> setSearchFlight(@PathVariable("departureAirport") int departureAirport,
			@PathVariable("destinationAirport") int destinationAirport,
			@PathVariable("departureTime") String departureTime) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate deptTime = LocalDate.parse(departureTime, dtf);
		Airport depAirport = airportService.getAirportById(departureAirport);
		Airport destAirport = airportService.getAirportById(destinationAirport);
		List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(depAirport, destAirport, deptTime);
		return flights;
	}

	@PostMapping("/setBookFlight/{departureAirport}/{destinationAirport}/{departureTime}")
	public List<Flight> setBookFlight(@PathVariable("departureAirport") int departureAirport,
			@PathVariable("destinationAirport") int destinationAirport,
			@PathVariable("departureTime") String departureTime) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate deptTime = LocalDate.parse(departureTime, dtf);
		Airport depAirport = airportService.getAirportById(departureAirport);
		Airport destAirport = airportService.getAirportById(destinationAirport);
		List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(depAirport, destAirport, deptTime);
		return flights;
	}

//	@GetMapping("/flights")
//	public String showFlightsList(@RequestParam(defaultValue = "0") int pageNo, Model model) {
//		model.addAttribute("flights", flightService.getAllFlightsPaged(pageNo));
//		model.addAttribute("currentPage", pageNo);
//		return "flights";
//	}

//	@GetMapping("/flight/search")
//	public String showSearchFlightPage(Model model) {
//		model.addAttribute("airports", airportService.getAllAirports());
//		model.addAttribute("flights", null);
//		return "searchFlight";
//	}

//	@GetMapping("/flight/book")
//	public String showBookFlightPage(Model model) {
//		model.addAttribute("airports", airportService.getAllAirports());
//		return "bookFlight";
//	}

//	@PostMapping("/flight/book")
//	public String searchFlightToBook(@RequestParam("departureAirport") int departureAirport,
//			@RequestParam("destinationAirport") int destinationAirport,
//			@RequestParam("departureTime") String departureTime, Model model) {
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		LocalDate deptTime = LocalDate.parse(departureTime, dtf);
//		Airport depAirport = airportService.getAirportById(departureAirport);
//		Airport destAirport = airportService.getAirportById(destinationAirport);
//
//		if (departureAirport == destinationAirport) {
//			model.addAttribute("AirportError", "Departure and destination airport cant be same!");
//			model.addAttribute("airports", airportService.getAllAirports());
//			return "bookFlight";
//		}
//		List<Flight> flights = flightService.getAllFlightsByAirportAndDepartureTime(depAirport, destAirport, deptTime);
//		if (flights.isEmpty()) {
//			model.addAttribute("notFound", "No Record Found!");
//		} else {
//			model.addAttribute("flights", flights);
//		}
//		model.addAttribute("airports", airportService.getAllAirports());
//		return "bookFlight";
//	}

//	@GetMapping("/flight/book/new")
//	public String showCustomerInfoPage(@RequestParam long flightId, Model model) {
//		model.addAttribute("flightId", flightId);
//		model.addAttribute("passenger", new Passenger());
//		return "newPassenger";
//	}

	@GetMapping("/flightbooknew1/{flightId}")
	public Flight saveFlight3(@PathVariable("flightId") long flightId) {

		return flightService.getFlightById(flightId);

	}

	@PostMapping("/flightbooknew2")
	public Passenger saveFlight3(@RequestBody Passenger p1) {

		return passengerService.savePassenger(p1);

	}

	@GetMapping("/getPassengers1/{flightId}/{passengerId}")
	public Passenger getPassengers1(@PathVariable("flightId") long flightId,
			@PathVariable("passengerId") long passengerId) {

		Flight flight1 = flightService.getFlightById(flightId);

		System.out.println("inside getPassengers1" + flight1.getPassengers());

		// return flight1.getPassengers();
		List<Passenger> passengers = flight1.getPassengers();
		Passenger passenger = null;
		for (Passenger p : passengers) {
			if (p.getPassengerId() == passengerId) {
				passenger = passengerService.getPassengerById(passengerId);
				return passenger;
			}
		}
		return null;

	}

	@GetMapping("/getPassengers2")
	public List<Passenger> getPassengers2() {

		return passengerService.getAllPassengers();
	}

	@PostMapping("/setFlightflight/{flightId}")
	public Passenger setFlightflight(@RequestBody Passenger passenger, @PathVariable("flightId") long flightId) {

		Flight flight = flightService.getFlightById(flightId);
		Passenger passenger1 = passenger;
		passenger1.setFlight(flight);
		passengerService.savePassenger(passenger1);
		System.out.println("inside setFlightflight**********************8" + passenger.getFlight());
		return passenger1;

	}

	@PostMapping("/flightbooknew/{flightId}")
	public Passenger flightbooknew(@RequestBody Passenger passenger, @PathVariable("flightId") long flightId) {
		Flight flight = flightService.getFlightById(flightId);
		Passenger passenger1 = passenger;
		passenger1.setFlight(flight);
		return passengerService.savePassenger(passenger1);

	}

//	@GetMapping("/flight/book/verify")
//	public String showVerifyBookingPage() {
//		return "verifyBooking";
//	}

//	@PostMapping("/flightbookverify1")
//	public List<Passenger> showVerifyBookingPageResult(@RequestBody Flight flight) {
//
//		System.out.println("inside showVerifyBookingPageResult"+flight);
//		System.out.println("--->"+flight.getPassengers());
//		return flight.getPassengers();
//	}

	@GetMapping("/flightbookverify/{passengerId}")
	public Passenger flightbookverify(@PathVariable("passengerId") long passengerId) {

		return passengerService.getPassengerById(passengerId);

	}

	@GetMapping("/passengerEnter/{flightId}")
	public List<Passenger> passengerEnter(@PathVariable("flightId") long flightId) {

		List<Passenger> passengers = flightService.getFlightById(flightId).getPassengers();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& " + flightService.getFlightById(flightId).getPassengers());
		return passengers;

	}

	@DeleteMapping("/flightbookcancel/{passengerId}")
	public void flightbookcancel(@PathVariable("passengerId") long passengerId) {

		passengerService.deletePassengerById(passengerId);

	}

}
