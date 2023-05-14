package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.ChangeRoom;
import it.unibo.ruscodc.utils.Pair;

public class Door extends InteractableAbs {

    private String name;
    private String path;
    private String info;

    public Door(final Pair<Integer, Integer> pos){
        super(pos);
        this.name = "Door";
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/DoorTile";
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public GameCommand interact() {
        return new ChangeRoom(getPos());
    }
}
