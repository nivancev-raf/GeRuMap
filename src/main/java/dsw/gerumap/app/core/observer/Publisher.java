package dsw.gerumap.app.core.observer;

public interface Publisher {

    void addSubscriber();
    void removeSubscriber();
    void Notify();

}
