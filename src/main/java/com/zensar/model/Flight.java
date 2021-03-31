package com.zensar.model;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "flightId")
public class Flight implements Serializable{
	@Id
	@GeneratedValue
	private long flightId;
	private String flightNumber;
	@ManyToOne
	private Airport departureAirport;
	@ManyToOne
	private Airport destinationAirport;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate departureDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate arrivalDate;

	private String departureTime;

	private String arrivalTime;

	private double flightCharge;

	
	@ManyToOne
	Aircraft aircraft;
	
	
	@OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
	private List<Passenger> passengers = new ArrayList<>();

	public Flight() {
	}

	public Flight(String flightNumber, Airport departureAirport, Airport destinationAirport, double flightCharge,
			LocalDate departureDate, LocalDate arrivalDate) {

		this.flightNumber = flightNumber;
		this.departureAirport = departureAirport;
		this.destinationAirport = destinationAirport;
		this.flightCharge = flightCharge;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	public long getFlightId() {
		return flightId;
	}

	public void setFlightId(long flightId) {
		this.flightId = flightId;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(Airport departureAirport) {
		this.departureAirport = departureAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public double getFlightCharge() {
		return flightCharge;
	}

	public void setFlightCharge(double flightCharge) {
		this.flightCharge = flightCharge;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightNumber=" + flightNumber + ", departureAirport="
				+ departureAirport + ", destinationAirport=" + destinationAirport + ", departureDate=" + departureDate
				+ ", arrivalDate=" + arrivalDate + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", flightCharge=" + flightCharge + ", aircraft=" + aircraft + ", passengers=" + passengers + "]";
	}
	
	
}
