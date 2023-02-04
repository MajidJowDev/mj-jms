package mjz.springframework.mjjms.sender;

import lombok.RequiredArgsConstructor;
import mjz.springframework.mjjms.config.JmsConfig;
import mjz.springframework.mjjms.model.HelloWorldMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor // Generates a constructor with required arguments. Required arguments are final fields (here JmsTemplate)
public class HelloSender {

    // for getting this to work, what we need to do is get a hold of JMS template (pre-configured to talk to ActiveMQ server)
    private final JmsTemplate jmsTemplate;

    //we want to have this scheduled periodically
    @Scheduled(fixedRate = 2000) // set it to 2000 ms
    public void sendMessage(){

        System.out.println("I'm sending a message");

        // setting up the message Object we want to send
        HelloWorldMessage message = HelloWorldMessage
                .builder().id(UUID.randomUUID())
                .message("Hello World!!!")
                .build();

        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message); // it uses the messageConverted we provided in JmsConfig

        System.out.println("Message Sent!");
    }
}
