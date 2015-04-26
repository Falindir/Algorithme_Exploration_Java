package tp.tools.visualisation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jimmy on 26/04/15.
 */
public abstract class Controller {

    private LinkedList<View> _view = new LinkedList<View>();

    public LinkedList<View> getView() {
        return _view;
    }

    public void addView(View view) {
        _view.add(view);
    }

    public void repaintView() {
        for(View v : _view) {
            v.repaint();
        }
    }
}
