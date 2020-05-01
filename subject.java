import java.util.ArrayList;
import java.util.List;

public abstract class subject {

    private List<observer> observers = new ArrayList<>();

    public void register(observer observer) {
        this.observers.add(observer);
    }

    public void unregister(observer observer) {
        if(this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    public void notifyobservers() {
        for(observer o : observers) {
            o.update();
        }
    }

}
