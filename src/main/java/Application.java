
import com.ticketland.config.ApplicationConfig;
import com.ticketland.daos.UserDAO;
import com.ticketland.entities.User;
import com.ticketland.services.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user = new User("01", "Tanjiro Kamado", "tanjiro@kamado.com");

        userService.registerUser(user);
        userService.showUsers();
    }
}
