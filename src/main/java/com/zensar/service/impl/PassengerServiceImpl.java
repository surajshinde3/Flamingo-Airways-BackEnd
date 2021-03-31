package com.zensar.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.zensar.model.Passenger;
import com.zensar.repository.PassengerRepository;
import com.zensar.services.PassengerService;
@Service
public class PassengerServiceImpl implements PassengerService {

	@Autowired
    private PassengerRepository passengerRepository;

	  @Autowired
	  private JdbcTemplate template;
    
   
    @Override
    public Page<Passenger> getAllPassengersPaged(int pageNum) {
        return passengerRepository.findAll(PageRequest.of(pageNum,5, Sort.by("lastName")));
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElse(null);
    }

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public void deletePassengerById(Long passengerId) {
        passengerRepository.deleteById(passengerId);
    }

	@Override
	public List<Passenger> getPassengers(long flightId) {
		
		return template.query("select passenger_id,email,first_name,last_name,passport_number,phone_number from passenger where flight_flight_id='"+flightId+"';",new RowMapper<Passenger>() {

			@Override
			public Passenger mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Passenger passenger=new Passenger();
				passenger.setPassengerId(rs.getLong(1));
				//passenger.setAddress(rs.getString(2));
				passenger.setEmail(rs.getString(2));
				passenger.setFirstName(rs.getString(3));
				passenger.setLastName(rs.getString(4));
				passenger.setPassportNumber(rs.getString(5));
				passenger.setPhoneNumber(rs.getString(6));
				//passenger.setFlight(rs.getInt(8));
				System.out.println();
				return passenger;
				
				
			}

			
			
			
		});
	}
}
