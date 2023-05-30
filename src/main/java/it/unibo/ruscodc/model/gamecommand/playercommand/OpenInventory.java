package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.Item;
import it.unibo.ruscodc.model.item.consumable.Consumable;
import it.unibo.ruscodc.model.item.equipement.Equipement;
import it.unibo.ruscodc.model.outputinfo.InfoPayload;
import it.unibo.ruscodc.model.outputinfo.InfoPayloadImpl;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.Pairs;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.utils.exception.Undo;

public class OpenInventory extends NoIACommand {

    private final static int COLS = 7;

    private final Hero hero;
    private final Inventory inventory;
    private Optional<InfoPayload> advise;
    private Pair<Integer, Integer> cursorPos;
    private boolean isReady = false;
    private boolean exit = false;
    
    public OpenInventory() {
        this.hero = (Hero)this.getActor();
        this.inventory = this.hero.getInventory();
    }

    private void resetCursor() {
        this.cursorPos = new Pair<Integer,Integer>(0, 0);
    }


    private int getIndexOfLastItemInARow(final int row) {
        final int amountItem = this.inventory.slotOccupied(); 
        final int nextItem = amountItem - row*COLS;
        return Math.min(COLS-1, nextItem);
    }

    private int getIndexOfLastItemInACol(final int col) {
        final int amountItem = this.inventory.slotOccupied();
        final int lastRow = amountItem / COLS;
        final int inc = amountItem % COLS <= col ? 1 : 0;
        return lastRow + inc;
    }

    private void moveCursor(Pair<Integer, Integer> newPos) {
        final int amountItem = this.inventory.slotOccupied();
        if (newPos.getY() == COLS) {
            newPos = new Pair<Integer,Integer>(cursorPos.getX(), 0); //inizio della i-esima riga
        }
        if (newPos.getY() == -1) {
            newPos = new Pair<Integer,Integer>(cursorPos.getX(), getIndexOfLastItemInARow(cursorPos.getX())); //bisogna vedere se la riga è piena di oggetti o termina prima
        } 
        if (newPos.getX() == -1) {
            newPos = new Pair<Integer,Integer>(getIndexOfLastItemInACol(cursorPos.getY()), cursorPos.getY()); //bisogna vedere se la colonna è l'ultima o la penultima
        }
        if ((newPos.getX() * COLS + newPos.getY()) > amountItem) {
            newPos = new Pair<Integer,Integer>(0, cursorPos.getY());
        }
        cursorPos = newPos;
    }

    private void manageConsumable(Consumable consumable) {
        consumable.consume();
        this.inventory.removeItem(this.cursorPos.getX() * COLS + this.cursorPos.getY());
    }

    private void manageEquipement(Equipement equipement) {
        equipement.equip(hero);
    }

    private void manageUse(){
        Item item = this.inventory.getItem(this.cursorPos.getX() * COLS + this.cursorPos.getY());
        if (item.isWearable()) {
            manageEquipement((Equipement) item);
        } else {
            manageConsumable((Consumable) item);
        }
    }

    @Override
    public boolean modify(GameControl input) {
        boolean mustUpdate = true;
        this.advise = Optional.empty();

        if (this.inventory.isEmpty()) {
            input = GameControl.CANCEL;
            advise = Optional.of(new InfoPayloadImpl("Errore apertura Inventario", "L'inventario è vuoto"));
        }

        switch (input) {
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
                    .getItem(this.cursorPos.getX() * COLS + this.cursorPos.getY())
                    .getInfo()
                );
                mustUpdate = false;
                break;

            case CONFIRM:
                this.manageUse();
                this.isReady = false;
                this.resetCursor();
                break;

            case DELETE:
                inventory.removeItem(this.cursorPos.getX() * COLS + this.cursorPos.getY());
                resetCursor();
                break;

            case CANCEL: 
                this.isReady = true; 
                this.exit = true;
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

    @Override
    public Iterator<Entity> getEntities() {
        Stream<Entity> items = this.inventory
            .getAllItems()
            .stream()
            .map(i -> fromItemToEntity(
                i,
                new Pair<>(
                    this.inventory.getAllItems().indexOf(i) / COLS,
                    this.inventory.getAllItems().indexOf(i) % COLS)
                )
        );

        return Stream.concat(items, Stream.of(fromCursorToEntity())).iterator();
    }

    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        if (exit) {
            throw new Undo("Close inventory");
        }
        this.isReady = false;
        return advise;
    }
    
    @Override
    public boolean isReady() {
        return this.isReady;
    }
}
