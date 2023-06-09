package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.item.consumable.Consumable;
import it.unibo.ruscodc.model.item.equipement.Equipement;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;
import it.unibo.ruscodc.utils.outputinfo.InfoPayload;

/**
 * Command that manage the interaction beetween player and the inventory of a hero.
 */
public class OpenInventory extends NoIACommand {

    private static final int COLS = 7;

    private Hero hero;
    private Inventory inventory;
    private Optional<InfoPayload> advise = Optional.empty();
    private Pair<Integer, Integer> cursorPos = new Pair<>(0, 0);
    private boolean isReady;
    private boolean exit;
    private boolean isInit;

    private int byPtoI(final Pair<Integer, Integer> toConvert) {
        return toConvert.getY() * COLS + toConvert.getX();
    }

    private void resetCursorForOpenInventory() {
        if (this.cursorPos == null || byPtoI(this.cursorPos) >= this.inventory.slotOccupied()) {
            this.cursorPos = new Pair<>(0, 0);
        }
    }

    private int getIndexOfLastItemInARow(final int row) {
        final int amountItem = this.inventory.slotOccupied(); 
        final int nextItem = amountItem - row * COLS;
        return Math.min(COLS - 1, nextItem - 1);
    }

    private int getIndexOfLastItemInACol(final int col) {
        final int amountItem = this.inventory.slotOccupied();
        final int lastRow = amountItem / COLS;
        final int inc = col < (amountItem % COLS) ? 0 : -1;
        return lastRow + inc;
    }

    private void moveCursor(final Pair<Integer, Integer> newPos) {
        Pair<Integer, Integer> tmpNewPos = newPos;
        final int amountItem = this.inventory.slotOccupied();
        if (tmpNewPos.getX() == COLS) {
            tmpNewPos = new Pair<>(0, cursorPos.getY());
        } else
        if (tmpNewPos.getX() == -1) {
            tmpNewPos = new Pair<>(getIndexOfLastItemInARow(cursorPos.getY()), cursorPos.getY());
        } else
        if (tmpNewPos.getY() == -1) {
            tmpNewPos = new Pair<>(cursorPos.getX(), getIndexOfLastItemInACol(cursorPos.getX()));
        } else 
        if (this.byPtoI(tmpNewPos) >= amountItem) {
            if (tmpNewPos.getY().equals(cursorPos.getY())) {
                tmpNewPos = new Pair<>(0, cursorPos.getY());
            } else {
                tmpNewPos = new Pair<>(cursorPos.getX(), 0);
            }
        }
        cursorPos = tmpNewPos;
    }

    private void manageConsumable(final Consumable consumable) {
        consumable.consume().applyEffect(hero);
        this.inventory.removeItem(this.cursorPos.getY() * COLS + this.cursorPos.getX());
    }

    private void manageEquipement(final Equipement equipement) {
        this.inventory.equip(equipement, hero);
    }

    private void manageUse() {
        final Item item = this.inventory.getItem(this.cursorPos.getY() * COLS + this.cursorPos.getX());
        if (item.isWearable()) {
            manageEquipement((Equipement) item);
        } else {
            manageConsumable((Consumable) item);
        }
    }

    /**
     * 
     */
    @Override
    public boolean modify(final GameControl input) {
        GameControl tmpInput = input;
        boolean mustUpdate = true;

        if (this.inventory.isEmpty()) {
            tmpInput = GameControl.CANCEL;
        }

        switch (tmpInput) {
            case MOVEUP: 
                this.moveCursor(Pairs.computeUpPair(cursorPos));
                break;

            case MOVEDOWN:
                this.moveCursor(Pairs.computeDownPair(cursorPos));
                break;

            case MOVELEFT:
                this.moveCursor(Pairs.computeLeftPair(cursorPos));
                break;

            case MOVERIGHT:
                this.moveCursor(Pairs.computeRightPair(cursorPos));
                break;

            case INVENTORY:
                this.isReady = true; 
                this.advise = Optional.of(
                    this.inventory
                    .getItem(this.cursorPos.getY() * COLS + this.cursorPos.getX())
                    .getInfo()
                );
                mustUpdate = false;
                break;

            case CONFIRM:
                this.manageUse();
                this.isReady = false;
                this.resetCursorForOpenInventory();
                break;

            case DELETE:
                inventory.removeItem(this.cursorPos.getY() * COLS + this.cursorPos.getX());
                resetCursorForOpenInventory();
                break;

            case CANCEL: 
                this.isReady = true; 
                this.exit = true;
                mustUpdate = false;
                break;

            default:
                mustUpdate = false;
        }
        return mustUpdate;
    }

    private Entity fromItemToEntity(final Item item, final Pair<Integer, Integer> pos) {
        return new Entity() {

            @Override
            public int getID() {
                return 1;
            }

            @Override
            public String getPath() {
                return item.getPath();
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return pos;
            }
        };
    }

    private Entity fromCursorToEntity() {
        return new Entity() {

            @Override
            public int getID() {
                return 2;
            }

            @Override
            public String getPath() {
                return getCursorPath();
            }

            @Override
            public Pair<Integer, Integer> getPos() {
                return cursorPos;
            }
        };
    }

    /**
     * 
     */
    @Override
    public List<Entity> getEntities() {
        final List<Entity> items = this.inventory
            .getAllItems()
            .stream()
            .map(i -> fromItemToEntity(
                i,
                new Pair<>(
                    this.inventory.getAllItems().indexOf(i) % COLS,
                    this.inventory.getAllItems().indexOf(i) / COLS)
                )
        ).collect(Collectors.toList());

        if (!items.isEmpty()) {
            items.add(fromCursorToEntity());
        }

        return items;
    }

    /**
     * 
     */
    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        if (exit) {
            this.isReady = false;
            this.exit = false;
            //this.mustClose = false;
            //this.needAbort = false;
            this.advise = Optional.empty();
            resetCursorForOpenInventory();
            throw new Undo();
        }
        this.isReady = false;
        return advise;
    }

    /**
     * 
     */
    @Override
    public boolean isReady() {
        if (!isInit) {
            this.hero = (Hero) this.getActor();
            this.inventory = hero.getInventory();
            isInit = true;
            resetCursorForOpenInventory();
        }
        return isReady;
    }
}
