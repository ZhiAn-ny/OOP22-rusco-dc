package it.unibo.ruscodc.model.gamecommand.quickcommand;

import java.util.Optional;

import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.utils.exception.ChangeFloorException;
import it.unibo.ruscodc.utils.exception.ModelException;

/**
 * Command that alter the flow of the game due to the changing of the floor.
 */
public class ChangeFloor extends ChangeSituation {

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        throw new ChangeFloorException();
    }
}
