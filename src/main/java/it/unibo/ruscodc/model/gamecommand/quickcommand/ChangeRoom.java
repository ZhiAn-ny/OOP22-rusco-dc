package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ChangeRoomException;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Command that alter the flow of the game due to the changing of a room.
 */
public class ChangeRoom extends ChangeSituation {

    private final Pair<Integer, Integer> door;

    /**
     * Create this type of exception of the normal flow of the game.
     * @param doorPos the position of the travelled door, useful during the mechanism of changing room
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
