package models.entites;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import models.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
		super();
	}

	public Reservation(Integer roomNumber, Date checkin, Date checkout){
		if(!checkout.after(checkin)) {
			throw new DomainException("Error check-in date cannot be after check-out") ;
		}
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		//System.out.println(diff);
		//System.out.println(TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS));
		return TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
		
	}
	
	public void updateDate(Date checkIn, Date checkOut){
		
		Date now = new Date();
		if(checkIn.before(now)|| checkOut.before(now)) {
			throw new DomainException("Reservation dates for updates must be future dates");
		}
		if(!checkOut.after(checkIn)) {
			throw new DomainException("Error check-in date cannot be after check-out") ;
		}
		
		checkin = checkIn;
		checkout = checkOut;
		
	}

	@Override
	public String toString() {
		return "Room " 
				+ roomNumber
				+", check-In: "
				+ sdf.format(checkin)
				+", check-Out: "
				+sdf.format(checkout)
				+", "
				+ duration()
				+" Nights";
	}
	
	
}