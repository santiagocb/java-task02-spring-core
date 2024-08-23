import com.ticketland.config.ApplicationConfig;
import com.ticketland.entities.*;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.Month;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        EventService eventService = context.getBean(EventService.class);
        UserService userService = context.getBean(UserService.class);
        TicketService ticketService = context.getBean(TicketService.class);
        
        userService.showUsers();
        eventService.showAllEvents();

        // Create a user
        User user = new User("06", "Tanjiro Kamado", "tanjiro@kamado.com");
        userService.registerUser(user);

        // Create an event
        Event event = new Event("001", "ComiCon2024", "Plaza Mayor", new Location(-124, 2), LocalDate.parse("2024-12-19"));
        eventService.registerEvent(event);

        // Get a ticket
        Ticket ticket = ticketService.generateTicket(TicketType.VIP, user, event);

        System.out.printf("Ticket %s purchased: [%s]%s has 1 entry to %s in %s on %s%n", ticket.id(), user.id(), user.name(), event.name(), event.place(), event.date().toString());


    }
}
