package dsw.gerumap.app.core.observer;

public interface Publisher { // model

    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void Notify(Object notification);

}
