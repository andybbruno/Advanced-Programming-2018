package controllerveto;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Controller implements Serializable {

    public final static String ON_CHANNEL = "on";

    private final int HUM_UPPER_BOUND = 90;
    private final int HUM_LOWER_BOUND = 30;

    protected boolean on;
    private int locHumidity;

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    public Controller() {
        
    }

    public void setLocHumidity(int new_LocHumidity) {
        if ((new_LocHumidity >= 0) && (new_LocHumidity <= 100)) {
            this.locHumidity = new_LocHumidity;

            if (new_LocHumidity <= HUM_LOWER_BOUND) {
                setOn(true);
            } else if (new_LocHumidity >= HUM_UPPER_BOUND) {
                setOn(false);
            }
        }
    }

    public int getLocHumidity() {
        return this.locHumidity;
    }

    public void setOn(boolean new_on) {
        changes.firePropertyChange(ON_CHANNEL, this.on, new_on);
        this.on = new_on;
    }

    public boolean isOn() {
        return this.on;
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changes.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        changes.removePropertyChangeListener(propertyName, listener);
    }

}
