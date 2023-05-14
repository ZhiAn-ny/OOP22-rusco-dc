package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.ChangeFloor;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.utils.Pair;

public class Stair extends InteractableAbs{

    private String name;
    private String path;
    private String info;

    public Stair(Pair<Integer, Integer> pos) {
        super(pos);
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public GameCommand interact() {
        return new ChangeFloor();
    }
}
