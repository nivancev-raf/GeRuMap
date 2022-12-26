package dsw.gerumap.app.gui.swing.state;

import dsw.gerumap.app.gui.swing.state.model.*;

public class StateManager {
    private State currentState;
    private PojamState pojamState;
    private BrisanjeState brisanjeState;
    private PomeranjeState pomeranjeState;
    private SelekcijaState selekcijaState;
    private VezaState vezaState;
    private ZoomInState zoomInState;
    private ZoomOutState zoomOutState;

    public StateManager() {
        initStates();
    }

    private void initStates() {
        pojamState = new PojamState();
        brisanjeState = new BrisanjeState();
        pomeranjeState = new PomeranjeState();
        selekcijaState = new SelekcijaState();
        vezaState = new VezaState();
        zoomInState = new ZoomInState();
        zoomOutState = new ZoomOutState();

        currentState = vezaState; // defaultno stanje
    }

    public State getCurrent(){
        return currentState;
    }

    public void setPojamState() {
        currentState = pojamState;
    }

    public void setBrisanjeState() {
        currentState = brisanjeState;
    }

    public void setPomeranjeState() {
        currentState = pomeranjeState;
    }

    public void setSelekcijaState() {
        currentState = selekcijaState;
    }

    public void setVezaState() {
        currentState = vezaState;
    }

    public void setZoomInState() {
        currentState = zoomInState;
    }

    public void setZoomOutState() {
        currentState = zoomOutState;
    }
}
