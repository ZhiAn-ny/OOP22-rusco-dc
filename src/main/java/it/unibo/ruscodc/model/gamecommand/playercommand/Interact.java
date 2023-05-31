package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.gamecommand.GameCommand;
import it.unibo.ruscodc.model.gamemap.Tile;
import it.unibo.ruscodc.model.interactable.Interactable;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.model.range.Range;
import it.unibo.ruscodc.model.range.SingleRange;
import it.unibo.ruscodc.model.range.SquareRange;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;

public class Interact extends NoIACommand {

    private Range interactableRange = new SquareRange(1, new SingleRange());
    private Pair<Integer, Integer> cursorPos;
    private boolean isReady;
    private boolean undo;

    public Interact() {
        cursorPos = null;
    }

    private boolean moveCursor(final Pair<Integer, Integer> newPos) {
        System.out.println(newPos);
        if (this.getRoom().isInRoom(newPos)) {
            cursorPos = newPos;
            return true;
        } else {
            return false;
        }
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
                return cursorPos;
            }

            @Override
            public int getID() {
                return getCursorDepth();
            }

        };
    }
    
    @Override
    public boolean modify(GameControl input) {
        boolean mustUpdate = true;
        switch (input) {
            case MOVEUP: mustUpdate = moveCursor(Pairs.computeUpPair(cursorPos)); break;
            case MOVEDOWN: mustUpdate = moveCursor(Pairs.computeDownPair(cursorPos)); break;
            case MOVELEFT: mustUpdate = moveCursor(Pairs.computeLeftPair(cursorPos)); break;
            case MOVERIGHT: mustUpdate = moveCursor(Pairs.computeRightPair(cursorPos)); break;
            case CONFIRM: isReady = true; mustUpdate = false; break;
            case CANCEL: isReady = true; undo = true; mustUpdate = false; break;
            default: mustUpdate = false;
        }
        return mustUpdate;
    }

    @Override
    public Set<Entity> getEntities() {
        if (cursorPos == null) {
            cursorPos = this.getActor().getPos();
        }
        //Iterator<Entity> tmpp = interactableRange.getRange(this.getActor().getPos(), cursorPos, this.getRoom());
        //System.out.println("################");
        //System.out.println(tmpp.next().getID());
        //final Iterator<Entity> tmp = interactableRange.getRange(this.getActor().getPos(), cursorPos, this.getRoom());
        //Stream<Entity> rangeE = this.interactableRange.getRange(this.getActor().getPos(), cursorPos, getRoom()).stream();
        //return Stream.concat(rangeE, Stream.of(getCursorAsEntity())).collect(Collectors.toSet());
        Set<Entity> tmp = this.interactableRange.getRange(this.getActor().getPos(), cursorPos, this.getRoom());
        tmp.add(getCursorAsEntity());
        return tmp;
    }

    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        isReady = false;
        if (undo) {
            undo = false;
            cursorPos = null;
            throw new Undo("");
        }
        
        Optional<Tile> selected = this.getRoom().get(cursorPos);
        if (selected.isEmpty()) {
            return Optional.of(new InfoPayloadImpl("ERR", "ERR"));
        }

        Optional<Interactable> interac = selected.get().get();
        if (interac.isEmpty()) {
            return Optional.of(new InfoPayloadImpl("ERR", "ERR"));
        }
        GameCommand obtained = interac.get().interact();
        obtained.setActor(this.getActor());
        obtained.setRoom(this.getRoom());
        if (!obtained.isReady()) {
            throw new IllegalStateException("GameCommand behind interactable must not be complex");
        }
        cursorPos = null;
        return obtained.execute();
    }

    /**
     * 
     */
    @Override
    public boolean isReady() {
        return isReady;
    }
    
}
