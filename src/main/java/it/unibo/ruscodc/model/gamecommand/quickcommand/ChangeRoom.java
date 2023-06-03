package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * TODO documentazione!.
 */
public class ChangeRoom extends ChangeSituation {

    private final Pair<Integer, Integer> door;

    /**
     * TODO documentazione!.
     * @param doorPos TODO documentazione!.
     */
    public ChangeRoom(final Pair<Integer, Integer> doorPos) {
        this.door = doorPos;
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        throw new ChangeRoomException(door);
    }
}
