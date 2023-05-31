package it.unibo.ruscodc.model.interactable;

import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamecommand.quickcommand.ChangeFloor;
import it.unibo.ruscodc.utils.Pair;

/**
 *
 */
public class Stair extends InteractableAbs{

    private String name;
    private String path;
    private String info;

    /**
     *
     * @param pos
     */
    public Stair(Pair<Integer, Integer> pos) {
        super(pos);
    }


    /**
     *
     * @return
     */
    @Override
    public String getPath() {
        return "file:src/main/resources/it/unibo/ruscodc/map_res/StairTile";
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public GameCommand interact() {
        return new ChangeFloor();
    }

    @Override
    public boolean isTransitable() {
        return true;
    }
}
