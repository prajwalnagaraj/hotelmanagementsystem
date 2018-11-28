package com.cg.hbms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.hbms.bean.BookingDetails;
import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.bean.User;
import com.cg.hbms.dao.HotelDaoImpl;
import com.cg.hbms.dao.IHotelDao;
import com.cg.hbms.exceptions.HMSExceptions;

public class HotelServiceImpl implements IHotelService {
	static Logger log = Logger.getLogger(HotelServiceImpl.class);
	IHotelDao dao = new HotelDaoImpl();

	/*
	 * methodName = getRole 
	 * arguments = String , String  
	 * return type = String 
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get role from users table
	 */
	
	@Override
	public String getRole(String userName, String password)
			throws HMSExceptions {
		return dao.getRole(userName, password);
	}

	/*
	 * methodName = addHotelDetails 
	 * arguments = hotel object 
	 * return type = String 
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get add hotel details into hotel table
	 */
	
	@Override
	public String addHotelDetails(Hotel hotel) throws HMSExceptions {
		return dao.addHotelDetails(hotel);
	}

	/*
	 * Validation of hotel Details
	 */
	public boolean hotelValidation(Hotel hotel) {
		log.info("Inside hotelValidation Method");
		boolean validationFlag = true;
		List<String> validation = validation(hotel);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				System.err.println(string);
				log.error("Validation of  Hotel is Unsuccessfull");
			}
			log.debug("Validation of  Hotel is Successfull");
			validationFlag = false;
		}
		return validationFlag;
	}

	private List<String> validation(Hotel hotel) {
		List<String> validationErrors = new ArrayList<String>();
		String nameRegex = "[A-Z]{1}[a-z]{2,}[a-z]";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(hotel.getHotelName());
		if (!nameMatcher.find()) {
			validationErrors
					.add("\n Hotel Name is in wrong Format"
							+ "\n"
							+ "It should start from capital and minimum of 5 characters");
		}

		String phoneRegex = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern = Pattern.compile(phoneRegex);
		Matcher phoneMatcher = phonePattern.matcher(hotel.getPhoneNumber1());
		if (!phoneMatcher.find()) {
			validationErrors.add("\n Phone number 1 is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		String phoneRegex1 = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern1 = Pattern.compile(phoneRegex1);
		Matcher phoneMatcher1 = phonePattern1.matcher(hotel.getPhoneNumber2());
		if (!phoneMatcher1.find()) {
			validationErrors.add("\n Phone number 2  is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		int rating = Integer.parseInt(hotel.getRating());
		if (!(rating > 0 && rating < 6)) {
			validationErrors.add("\n Rating should be between 1-5");
		}

		String faxRegex = "[1-9]{1}[0-9]{7}";
		Pattern faxPattern = Pattern.compile(faxRegex);
		Matcher faxMatcher = faxPattern.matcher(hotel.getFax());
		if (!faxMatcher.find()) {
			validationErrors.add("Fax number is in wrong Format" + "\n"
					+ "It should be 8 digits");
		}

		if (!(hotel.getRate() > 499)) {
			validationErrors.add("\n Rate should be greater than 500");
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(hotel.geteMail());
		if (!emailMatcher.find()) {
			validationErrors.add("\n E-mail id is in wrong format");
		}

		return validationErrors;
	}

	/*
	 * methodName = getHotelDetails 
	 * arguments = hotel object 
	 * return type = Hotel Object 
	 * Author = Capgemini creation
	 * Date = 17/11/2018 
	 * this method is used to get Hotel Details from hotel table
	 */
	
	@Override
	public Hotel getHotelDetails(String hotelId) throws HMSExceptions {
		return dao.getHotelDetails(hotelId);
	}

	/*
	 * methodName = deleteById 
	 * arguments = String 
	 * return type = Hotel Object
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to delete Hotel Details from hotel table
	 */
	
	@Override
	public int deleteById(String hotelId) throws HMSExceptions {
		return dao.deleteById(hotelId);
	}

	/*
	 * methodName = modifyById 
	 * arguments = String 
	 * return type = Hotel Object
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to modify Hotel Details from hotel table
	 */
	
	@Override
	public int modifyById(String hotelId1, String hotelDescription)
			throws HMSExceptions {
		return dao.modifyById(hotelId1, hotelDescription);
	}

	/*
	 * methodName = addRoomDetails 
	 * arguments = Room object 
	 * return type = String
	 * Id Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to add Room Details to room table
	 */
	
	@Override
	public String addRoomDetails(Room room) throws HMSExceptions {
		return dao.addRoomDetails(room);
	}

	@Override
	public boolean roomValidation(Room room) {
		log.info("Inside roomValidation Method");
		boolean validationFlag = true;
		List<String> validation = validationRoom(room);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				log.error("Validation of  Room is Unsuccessfull");
				System.err.println(string);
			}
			validationFlag = false;
		}
		log.debug("Validation of  Room is Successfull");
		return validationFlag;
	}

	private List<String> validationRoom(Room room) {
		List<String> validationErrors = new ArrayList<String>();
		String roomNoRegex = "[0-9]{3}";
		Pattern roomPattern = Pattern.compile(roomNoRegex);
		Matcher roomMatcher = roomPattern.matcher(room.getRoomNumber());
		if (!roomMatcher.find()) {
			validationErrors.add("\n Room number  is in wrong Format" + "\n"
					+ "It should be 3 digits");
		}

		if (!(room.getPerNightRate() > 499)) {
			validationErrors.add("\n Rate should be greater than 500");
		}

		if (!(room.getAvailability().equalsIgnoreCase("YES") || room
				.getAvailability().equalsIgnoreCase("NO"))) {
			validationErrors.add("\n Availablity should be YES or NO only");
		}

		return validationErrors;
	}

	/*
	 * methodName = getRoomDetials 
	 * arguments = String , String 
	 * return type = Room Object 
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get Room Details from room table
	 */
	
	@Override
	public Room getRoomDetails(String hotelId, String roomId)
			throws HMSExceptions {
		return dao.getRoomDetials(hotelId, roomId);
	}

	/*
	 * methodName = deleteRoomById 
	 * arguments = String 
	 * return type = Room Object
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to delete Room Details from room table
	 */
	
	@Override
	public int deleteRoomById(String roomId) throws HMSExceptions {
		return dao.deleteRoomById(roomId);
	}

	/*
	 * methodName = getAllHotels 
	 * arguments = none 
	 * return type = List 
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get list of hotels from hotel table
	 */
	
	@Override
	public List<Hotel> getAllHotels() throws HMSExceptions {
		return dao.getAllHotels();
	}

	/*
	 * methodName = getRoomDetials 
	 * arguments = String
	 * return type = List of Room Object
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to delete Room Details from room table
	 */
	
	@Override
	public List<Room> getRoomDetailsById(String hotelId) throws HMSExceptions {
		return dao.getRoomDetials(hotelId);
	}

	@Override
	public boolean validateBooking(BookingDetails bookingdetails) {
		log.info("Inside validateBooking Method");
		boolean validationFlag = true;
		List<String> validation = validationBooking(bookingdetails);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				System.err.println(string);
				log.error("Validation of  Booking Details is Unsuccessfull");
			}
			validationFlag = false;
			log.debug("Validation of  Booking Details is Successfull");
		}
		return validationFlag;

	}

	private List<String> validationBooking(BookingDetails bookingdetails) {
		List<String> validationErrors = new ArrayList<String>();
		String roomNoRegex = "[0-9]{3}";
		Pattern roomPattern = Pattern.compile(roomNoRegex);
		Matcher roomMatcher = roomPattern.matcher(bookingdetails.getRoomId());
		if (!roomMatcher.find()) {
			validationErrors.add("\n Room number  is in wrong Format" + "\n"
					+ "It should be 3 digits");
		}

		if (!(bookingdetails.getAmount() > 499)) {
			validationErrors.add("\n Rate should be greater than 200");
		}

		int adults = Integer.parseInt(bookingdetails.getNoOfAdults());
		int children = Integer.parseInt(bookingdetails.getNoOfChildren());
		if (!(adults > 0 || children > 0)) {
			validationErrors
					.add("\n Adults or children count must be greater than 0");
		}

		Date date = bookingdetails.getBookedFrom();
		long systemMS= System.currentTimeMillis();
		long enteredMS = date.getTime();
		long diff = enteredMS-systemMS;
		if(diff<0){
			validationErrors
			.add("\n Date must be greater than today's Date");
		}
		
		Date date1 = bookingdetails.getBookedTo();
		long enteredMS1= date.getTime();
		long enteredMS2 = date1.getTime();
		long diff1 = enteredMS2-enteredMS1;
		if(diff1<0){
			validationErrors
			.add("\n To Date must be greater than booking Date");
		}
		
		return validationErrors;
	}

	/*
	 * methodName = addBookingDetails 
	 * arguments = bookingdetails object 
	 * return type = String 
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to add booking details into booking details table 
	 */
	
	@Override
	public String addBookingDetails(BookingDetails bookingdetails)
			throws HMSExceptions {
		return dao.addBookingDetails(bookingdetails);
	}

	/*
	 * methodName = getAllHotelsbyCity 
	 * arguments = String
	 * return type = List of Hotel Object
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get list hotels from hotel table
	 * 	 */
	
	@Override
	public List<Hotel> getAllHotelsbyCity(String city) throws HMSExceptions {
		return dao.getAllHotelsbyCity(city);
	}

	/*
	 * methodName = getBookingStatus 
	 * arguments = String
	 * return type = String
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get  booking status from booking details table
	 */
	
	@Override
	public String getBookingStatus(String bookingId) throws HMSExceptions {
		return dao.getBookingStatus(bookingId);
	}

	/*
	 * methodName = getBookingDetails 
	 * arguments = String
	 * return type = List of Booking Detials object
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get List booking details from booking details table
	 */
	
	@Override
	public List<BookingDetails> getBookingDetails(String hotelId)
			throws HMSExceptions {
		return dao.getBookingDetails(hotelId);
	}

	/*
	 * methodName = getRegistered 
	 * arguments = user object 
	 * return type = String
	 * Author = Capgemini creation
	 * Date = 17/11/2018 
	 * this method is used to get user into users table
	 */
	
	@Override
	public String getRegistered(User user) throws HMSExceptions {
		return dao.getRegistered(user);
	}

	@Override
	public boolean validateRegister(User user) {
		log.info("Inside validateRegister Method");
		boolean validationFlag = true;
		List<String> validation = validationRegister(user);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				System.err.println(string);
				log.error("Validation of  Registration Details is Unsuccessfull");
			}
			log.debug("Validation of  Registration Details is Successfull");
			validationFlag = false;
		}
		return validationFlag;
	}

	private List<String> validationRegister(User user) {
		List<String> validationErrors = new ArrayList<String>();
		String nameRegex = "[A-Z]{1}[a-z]{2,}[a-z]";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(user.getUserName());
		if (!nameMatcher.find()) {
			validationErrors
					.add("\n User Name is in wrong Format"
							+ "\n"
							+ "It should start from capital and minimum of 5 characters");
		}

		String phoneRegex1 = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern1 = Pattern.compile(phoneRegex1);
		Matcher phoneMatcher1 = phonePattern1.matcher(user.getMobileNumber());
		if (!phoneMatcher1.find()) {
			validationErrors.add("\n Phone number  is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		String phoneRegex = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern = Pattern.compile(phoneRegex);
		Matcher phoneMatcher = phonePattern.matcher(user.getPhoneNumber());
		if (!phoneMatcher.find()) {
			validationErrors.add("\n Phone number  is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(user.geteMail());
		if (!emailMatcher.find()) {
			validationErrors.add("\n E-mail id is in wrong format");
		}

		return validationErrors;
	}

	/*
	 * methodName = getGuestList 
	 * arguments = Date
	 * return type = List of String
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get List guest names from booking details table
	 */
	
	@Override
	public List<String> getGuestList(String hotelId1) throws HMSExceptions {
		return dao.getGuestList(hotelId1);
	}

	/*
	 * methodName = getBookingList 
	 * arguments = Date
	 * return type = List of booking details object
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get List booking details from booking details table
	 */
	
	@Override
	public List<BookingDetails> getBookingList(Date bookingsDate)
			throws HMSExceptions {
		return dao.getBookingList(bookingsDate);
	}

	/*
	 * methodName = changeStatus 
	 * arguments = String
	 * return type = String
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to change Status from booking details table
	 */
	
	@Override
	public String changeStatus(String bookingId, String newStatus)
			throws HMSExceptions {
		return dao.changeStatus(bookingId, newStatus);
	}

	/*
	 * methodName = getUserId 
	 * arguments = String , String 
	 * return type = String 
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to get User Id from users table
	 */
	
	@Override
	public String getUserId(String userName, String password)
			throws HMSExceptions {
		return dao.getUserId(userName, password);
	}

	/*
	 * methodName = changeRoomAvailability 
	 * arguments = String
	 * return type = String
	 * Author = Capgemini 
	 * creationDate = 17/11/2018 
	 * this method is used to change Availability from room table
	 */
	
	@Override
	public String changeRoomAvailability(String hotelId1, String bookingId) throws HMSExceptions {
		return dao.changeRoomAvailability(hotelId1,bookingId);
	}
}
