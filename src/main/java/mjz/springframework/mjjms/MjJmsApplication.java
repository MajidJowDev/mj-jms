package mjz.springframework.mjjms;

//import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
//import org.apache.activemq.artemis.core.server.ActiveMQServer;
//import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MjJmsApplication {

    public static void main(String[] args) throws Exception {

        // if we have the server on class path, spring boot is going to automatically bring up the configuration for us
        /*
        //just for test purpose, most of the times the ActiveMQ started outside of spring
        //setting up a minimal ActiveMQ Server
        ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
                .setPersistenceEnabled(false)
                .setJournalDirectory("target/data/journal")
                .setSecurityEnabled(false)
                .addAcceptorConfiguration("invm", "vm://0"));// will give us error "Acceptor with id 0 already registered", but ignore it

        server.start();
         */

        //Here we want to use the ActiveMQ server we created on Docker
        //We can get and runt the ActiveMQ server by using following address and commands
        //https://github.com/vromero/activemq-artemis-docker
        //docker pull vromero/activemq-artemis
        //docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis
        // set user name and password to "artemis" by following command
        // docker run -it --rm    -e ARTEMIS_USERNAME=myuser -e ARTEMIS_PASSWORD=otherpassword vromero/activemq-artemis


        SpringApplication.run(MjJmsApplication.class, args);
    }

}
