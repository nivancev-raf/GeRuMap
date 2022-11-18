package dsw.gerumap.app.logger;

import dsw.gerumap.app.core.observer.Publisher;

public interface MessageGenerator extends Publisher{

    void generate(EventType type);

}
