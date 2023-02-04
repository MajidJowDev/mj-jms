package mjz.springframework.mjjms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//if we are going to doing on JMS messages if we want to send this as a java object we have to implement serializable
public class HelloWorldMessage implements Serializable {

    static final long serialVersionUID = -5083010098850947039L; //a best practice for when we implement serializable (if we do not add it java create one automatically)

    private UUID id;
    private String message;
}
