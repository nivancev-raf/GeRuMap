package dsw.gerumap.app.logger;

import dsw.gerumap.app.core.observer.Publisher;
import dsw.gerumap.app.core.observer.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MessageGeneratorImplementation implements MessageGenerator, Publisher {

    List<Subscriber> subscribers;
    Message message;



    public MessageGeneratorImplementation(){
        subscribers = new ArrayList<>();
    }

    @Override
    public void generate(EventType type) {
        if (type.equals(EventType.ADD_AUTHOR_ERROR)) {
            message = new Message("You can't add author here", type);
            notifySubscribers(message);
        } else if (type.equals(EventType.ADDING_ELEMENT)) {
            message = new Message("You can't add elements manually", type);
            notifySubscribers(message);
        } else if (type.equals(EventType.NON_SELECTED)) {
            message = new Message("Action can't be perfrormed because selected is null", type);
            notifySubscribers(message);
        } else if (type.equals(EventType.REMOVE_PROJECT_EXPLORER)) {
            message = new Message("You can't remove project explorer", type);
            notifySubscribers(message);
        } else if (type.equals(EventType.FIELD_CANNOT_BE_EMPTY)) {
            message = new Message("Field cannot be empty", type);
            notifySubscribers(message);
        }
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Subscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
//        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
//            return;

        // problem : addSubscriber se nigde ne poziva, ulazi u gornji (zakomentarisani) if

        for(Subscriber listener : subscribers){
            listener.update(notification);
        }

    }
}
