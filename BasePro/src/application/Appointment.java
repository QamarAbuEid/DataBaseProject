package application;



import java.time.LocalDateTime;

public class Appointment {
    private int appointment_id;
    private String appointment_time;
   
	public Appointment(int appointment_id, String appointment_time) {
		super();
		this.appointment_id = appointment_id;
		this.appointment_time = appointment_time;
		
	}
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public String getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}
	

   

	

   
}