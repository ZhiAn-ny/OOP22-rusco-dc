package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.ChangeFloor;
import it.unibo.ruscodc.utils.Pair;

/**
 * This class represents the stairs of the room.
 * It is used to move from one room to another.
 */
public class Stair extends InteractableAbs {

    private final String name;

   /**
     * The constructor of this class.
     * @param pos where spawn stairs.
     */
    public Stair(final Pair<Integer, Integer> pos) {
        super(pos);
        this.name = "stair";
    }


    /**
     *
     * @return
     */
    @Override
    public String getPath() {
        return "it/unibo/ruscodc/map_res/StairTile";
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    @Override
    public GameCommand interact() {
        return new ChangeFloor();
    }

    /**
     * 
     */
    @Override
    public boolean isTransitable() {
        return true;
    }
}
