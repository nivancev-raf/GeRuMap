package dsw.gerumap.app.core.observer;

public interface Publisher { // model

    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscribers(Object notification);

}
