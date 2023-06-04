package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Optional;
import java.util.Set;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.monster.Monster;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Tile;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SquareInteraction;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;

/**
 * Command that mange some type of interaction beetween player and things in a room
 * With this command, it's possible collect some chest and drop, of maybe know the status of mobs
 */
public class Interact extends NoIACommand {

    private static final String T_ERR = "Error during interaction";
    private static final String ERR_NOT_EX = "The tile which you try to interact doesn't exist!";
    private static final String ERR_NOT_R = "Cursor is out of interaction range";
    private static final String NOTHING_TO_INT = "You are tring to interact with an empty tile!";

    private final Range interactableRange = new SquareInteraction(new SingleRange());
    private boolean justOpen = true;

    /**
     * 
     */
    @Override
    public boolean modify(final GameControl input) {
        return super.commonModify(input);
    }

    /**
     * Compute the {@code}Entity{@code} that wrap for the view the cursor position.
     * @return the cursor position, abstracted into an Entity
     */
    private Entity getCursorAsEntity() {
        return new Entity() {

            @Override
            public String getPath() {
                return getCursorPath();
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return getCursorPos();
            }

            @Override
            public int getID() {
                return getCursorDepth();
            }
        };
    }

    /**
     * 
     */
    @Override
    public Set<Entity> getEntities() {
        if (justOpen) {
            reset();
            justOpen = false;
        }
        final Set<Entity> tmp = this.interactableRange.getRange(this.getActor().getPos(), super.getCursorPos(), this.getRoom());
        tmp.add(getCursorAsEntity());
        return tmp;
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        super.attempCommand();

        //cursorPos = null;
        if (super.mustAbortCommand()) {
            justOpen = true;
            throw new Undo();
        }

        final Pair<Integer, Integer> tmpCursor = super.getCursorPos();

        if (!interactableRange.isInRange(this.getActor().getPos(), tmpCursor, tmpCursor, getRoom())) {
            return Optional.of(new InfoPayloadImpl(T_ERR, ERR_NOT_R));
        }

        final Optional<Monster> selectedM = this.getRoom().getMonsters().stream()
            .filter(a -> a.getPos().equals(tmpCursor))
            .findFirst();

        if (selectedM.isPresent()) {
            final Monster m = selectedM.get();
            final String text = m.getName();
            final String descr = m.toString();
            final String path = m.getPath();
            return Optional.of(new InfoPayloadImpl(text, descr, path));
        }

        final Optional<Tile> selected = this.getRoom().get(tmpCursor);
        if (selected.isEmpty()) {
            //super.reset();
            return Optional.of(new InfoPayloadImpl(T_ERR, ERR_NOT_EX));
        }

        final Optional<Interactable> interac = selected.get().get();
        if (interac.isEmpty()) {
            //super.reset();
            return Optional.of(new InfoPayloadImpl(T_ERR, NOTHING_TO_INT));
        }

        final GameCommand obtained = interac.get().interact();
        obtained.setActor(this.getActor());
        obtained.setRoom(this.getRoom());
        if (!obtained.isReady()) {
            throw new IllegalStateException("GameCommand behind interactable must not be complex");
        }

        super.reset();
        justOpen = true;
        final Optional<InfoPayload> res = obtained.execute();
        if (res.isEmpty()) {
            this.getRoom().get(tmpCursor).get().empty();
        }
        return res;
    }
}
