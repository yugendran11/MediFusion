public class AppointmentReminderThread extends Thread {

    private Appointment appointment;

    public AppointmentReminderThread(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public void run() {
        AppointmentReminder.checkReminder(appointment);
    }
}