package application;

public class Staff_Appointment {
  int appointment_id;
  int Staff_id ;
  String firstName;
  String appointment_Time;
public Staff_Appointment(int appointment_id, int staff_id, String firstName, String appointment_Time) {
	super();
	this.appointment_id = appointment_id;
	Staff_id = staff_id;
	this.firstName = firstName;
	this.appointment_Time = appointment_Time;
}
public int getAppointment_id() {
	return appointment_id;
}
public void setAppointment_id(int appointment_id) {
	this.appointment_id = appointment_id;
}
public int getStaff_id() {
	return Staff_id;
}
public void setStaff_id(int staff_id) {
	Staff_id = staff_id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getAppointment_Time() {
	return appointment_Time;
}
public void setAppointment_Time(String appointment_Time) {
	this.appointment_Time = appointment_Time;
}
  
}
