package mjz.springframework.mjjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {

    public static final String MY_QUEUE = "my-hello-word-queue"; //  we need to set a queue name

    @Bean
    public MessageConverter messageConverter(){
        //when we send a message to JMS, spring convert it to a JMS text message,
        //and payload is going to be taking the Java Object and convert it to JSON payload
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type"); // we are going set a property name of Type so spring can decode this

        return converter;
    }
}
