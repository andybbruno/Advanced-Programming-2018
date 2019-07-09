/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerveto;

import controllerveto.Controller;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 *
 * @author Andrea Bruno 585457
 */
public class ControllerVeto extends Controller {

    private VetoableChangeSupport vetos = new VetoableChangeSupport(this);

    public ControllerVeto() {

    }

    @Override
    public void setOn(boolean new_on) {
        try {
            vetos.fireVetoableChange(ON_CHANNEL, on, new_on);
            super.setOn(new_on);
        } catch (PropertyVetoException e) {
            System.out.println(e);
        }
    }

    public void addVetoableChangelistener(VetoableChangeListener l) {
        vetos.addVetoableChangeListener(l);
    }

    public void removeVetoableChangelistener(VetoableChangeListener l) {
        vetos.removeVetoableChangeListener(l);
    }
}
