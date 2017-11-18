import ServerClient.HealthServerHandler;
import ServerClient.HelloWorld;
import ServerClient.MainServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/18
 * @description
 */
@Configuration
@ComponentScan("ServerClient")
public class SpringConfiguation {

    @Bean
    public HelloWorld getHelloWorld() {
        return new HelloWorld();
    }

    @Bean
    public MainServer getMainServer() {
        return new MainServer();
    }

    @Bean
    public HealthServerHandler getHealthServerHandler() {
        return new HealthServerHandler();
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(SpringConfiguation.class);

        MainServer mainServer = ctx.getBean(MainServer.class);
        mainServer.init();
    }



}
