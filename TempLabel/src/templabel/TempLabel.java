/**
 * @author Andrea Bruno
 */
package templabel;

import javax.swing.JLabel;

public class TempLabel extends JLabel {

    @Override
    public void setText(String txt) {
        if (!txt.isEmpty()){
            Double val = Double.valueOf(txt); 
            Double far = val * 9 / 5 + 32;
            super.setText(far.toString());
        }
    }
}
