package mjz.springframework.mjjms.listener;

import mjz.springframework.mjjms.config.JmsConfig;
import mjz.springframework.mjjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class HelloMessageListener {
    /*
    Summary:
    this part tells Spring to listen to this QUEUE NAME and when there is a message
    in that queue, send the message to the method below
     */
    //@PayLoad annotation tells Spring to deserialize component payload of the JMS message
    //@Headers tells spring to get headers and in our case it's equivalent to JMS message properties
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message){ // we could actually omit the headers and message if we do not need their custom info

        System.out.println("I got a Message!!!!");

        System.out.println(helloWorldMessage);

        // if there be error on sending messages, the ActiveMQ will try to redeliver the message
        //so it's going to redeliver the message until it gets a proper confirmation from the client (this the Transactional feature of JMS)
       // throw new RuntimeException("Error receving message"); // uncomment for tossing the exception if you want to check the redelivery feature

    }
}
