package mjz.springframework.mjjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import mjz.springframework.mjjms.config.JmsConfig;
import mjz.springframework.mjjms.model.HelloWorldMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@Component
@RequiredArgsConstructor // Generates a constructor with required arguments. Required arguments are final fields (here JmsTemplate)
public class HelloSender {

    // for getting this to work, what we need to do is get a hold of JMS template (pre-configured to talk to ActiveMQ server)
    private final JmsTemplate jmsTemplate;

    // spring boot is going to be creating a Jackson object mapper for us in configuring, we define it private final so lombok
    // will provide a constructor and spring injects that for us
    private final ObjectMapper objectMapper;

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


    /*
    One use case of JMS is to send a JMS message and expect reply so it breaks the asynchronicity of a typical JMS messaging but sometimes we have a scenario like this so what happens in this scenario:
    we send out toa queue and the message consumer then replies back on TEMPORARY QUEUE (TEMPORARY REPLY QUEUE)(managed transparently)
     */
    @Scheduled(fixedRate = 2000)
    public void sendAndReceiveMessage() throws JMSException {

        //System.out.println("I'm sending a message");

        HelloWorldMessage message = HelloWorldMessage
                .builder().id(UUID.randomUUID())
                .message("Hello")
                .build();

        Message receivedMessage = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message helloMessage = null; // because we are not doing conversion (not using messageConverter in the configs), we need to manage it ourselves

                try {
                    helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                    helloMessage.setStringProperty("_type", "mjz.springframework.mjjms.model.HelloWorldMessage");

                    System.out.println("Sending Hello");

                    return helloMessage;

                } catch (JsonProcessingException e) {
                    throw new  JMSException("Caboom");
                   // e.printStackTrace();
                }

            }
        });

        System.out.println(receivedMessage.getBody(String.class));
    }
}
