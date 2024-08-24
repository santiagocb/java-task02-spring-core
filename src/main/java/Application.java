import com.ticketland.config.ApplicationConfig;
import com.ticketland.entities.*;
import com.ticketland.programs.BookingService;
import com.ticketland.services.EventService;
import com.ticketland.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        EventService eventService = context.getBean(EventService.class);
        UserService userService = context.getBean(UserService.class);
        BookingService bookingService = context.getBean(BookingService.class);

        userService.showUsers();
        eventService.showAllEvents();

        // Get a ticket
        var ticketId1 = bookingService.createBooking("001", "02", TicketType.VIP);
        var ticketId2 = bookingService.createBooking("001", "02", TicketType.VIP);
        var ticketId3 = bookingService.createBooking("002", "02", TicketType.VIP);
        var ticketId4 = bookingService.createBooking("002", "03", TicketType.GENERAL);
        var ticketId5 = bookingService.createBooking("002", "05", TicketType.GENERAL);
        var ticketId6 = bookingService.createBooking("003", "01", TicketType.GENERAL);

        // Cancel first ticket because of a situation
        bookingService.cancelBooking("001", ticketId1);

        bookingService.showTicketById("002", ticketId3);
        bookingService.showAllTicketsByEvent("001");
        bookingService.showAllTicketsByEvent("002");
    }
}
