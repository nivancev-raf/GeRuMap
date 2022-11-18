package dsw.gerumap.app.logger;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {

    String text;
    EventType type;

    public Message(String text, EventType type) {
        this.text = text;
        this.type = type;
    }
}

