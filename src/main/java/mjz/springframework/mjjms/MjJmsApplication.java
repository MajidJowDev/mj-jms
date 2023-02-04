package mjz.springframework.mjjms;

import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
import org.apache.activemq.artemis.core.server.ActiveMQServer;
import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MjJmsApplication {

    public static void main(String[] args) throws Exception {

        //just for test purpose, most of the times the ActiveMQ started outside of spring
        //setting up a minimal ActiveMQ Server
        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://0"));// will give us error "Acceptor with id 0 already registered", but ignore it

        server.start();

        SpringApplication.run(MjJmsApplication.class, args);
    }

}
