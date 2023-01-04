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
        } else if (type.equals(EventType.NON_SELECTED_STATE)){
            message = new Message("First select the item with 'select button' in pallete", type);
            notifySubscribers(message);
        } else if(type.equals(EventType.CANNOT_BE_USED)){
            message = new Message("Settings can be used when there is selected element",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.NUMBERS_ONLY)){
            message = new Message("Type only numbers",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.OUT_OF_BOUNDS)){
            message = new Message("Insert under 15 characters",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.NON_CONNECTION)){
            message = new Message("No connections between elements OR non selected main element (must be just one)",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.ALREADY_SAVED)){
            message = new Message("Project has already been saved!",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.NON_SELECTED_PROJECT)){
            message = new Message("Project must be selected to use this action!",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.NO_MAPVIEW)){
            message = new Message("Mind Map doesn't exist!",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.EMPTY_MINDMAP)){
            message = new Message("Mind Map is empty, add some graphics elements!",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.ONLY_ONE_SELECTED)){
            message = new Message("Only one element can be main element",type);
            notifySubscribers(message);
        } else if(type.equals(EventType.SAVE_AS_PROJECT)){
            message = new Message("You must save project on the saveAs button!",type);
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
        for(Subscriber listener : subscribers){
            listener.update(notification);
        }

    }
}
