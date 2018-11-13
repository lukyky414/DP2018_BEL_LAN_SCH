package factory;

import com.sun.prism.Texture;
import model.Bateau;

import java.util.List;

public abstract class SingletonEpoque {

    public SingletonEpoque instance;

    public SingletonEpoque getInstance() {
        return instance;
    }

    abstract Texture getTexture(Bateau b);
    abstract List<Bateau> getFleet();
}
