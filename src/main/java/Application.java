import com.ticketland.config.ApplicationConfig;
import com.ticketland.entities.*;
import com.ticketland.programs.BookingService;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        EventService eventService = context.getBean(EventService.class);
        UserService userService = context.getBean(UserService.class);
        TicketService ticketService = context.getBean(TicketService.class);
        BookingService bookingService = context.getBean(BookingService.class);

        userService.showUsers();
        eventService.showAllEvents();

        var user = userService.getUserById("02");
        var event = eventService.getEventById("001");
        var eventNextDay = eventService.getEventById("002");

        // Get a ticket
        var ticketId1 = bookingService.createBooking(event.id(), user.id(), TicketType.VIP);
        var ticketId2 = bookingService.createBooking(eventNextDay.id(), user.id(), TicketType.VIP);

        // Cancel first ticket because of a situation
        bookingService.cancelBooking(event.id(), ticketId2);

        bookingService.showTicketById(event.id(), ticketId1);
        bookingService.showAllTicketsByEvent(event.id());
    }
}
