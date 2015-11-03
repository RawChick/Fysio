import businesslogic.AppointmentManager;
import domain.Appointment;
import domain.Employee;
import domain.Patient;
import junit.framework.TestCase;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by ids1 on 3-11-2015.
 */
public class AppoinmentTest extends TestCase {

    private AppointmentManager manager;
    private boolean result;
    private Appointment appointment;

    public AppoinmentTest(String testName){
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        manager = new AppointmentManager();
        appointment = new Appointment(3, LocalDate.now(), LocalTime.MIDNIGHT, LocalTime.now(), new Employee(
                "2", "Ids", "Fysio", "05948239", "Breda", "Nederland",
                "Resedastraat", "18", "02-09-1994", "4818GR", "0614168219", "idsvanderzee2@gamil.com"
        ),new Patient(
                234, "a","s","d","f","g","h",LocalDate.now(),"j","k","l"
        ));
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddAppointment(){
        result = manager.addApointment(appointment);
        assertTrue(result);

    }
}
