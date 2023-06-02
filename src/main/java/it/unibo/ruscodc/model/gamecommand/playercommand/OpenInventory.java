package it.unibo.ruscodc.model.gamecommand.playercommand;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.model.actors.hero.Hero;
import it.unibo.ruscodc.model.item.Inventory;
import it.unibo.ruscodc.model.item.InventoryImpl;
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

    private Hero hero;
    private Inventory inventory;
    private Optional<InfoPayload> advise;
    private Pair<Integer, Integer> cursorPos;
    private boolean isReady = false;
    private boolean exit = false;
    private boolean isInit = false;
    private boolean mustClose = false;
    
    public OpenInventory() {
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
        if (newPos.getX() == COLS) {
            newPos = new Pair<Integer,Integer>(0, cursorPos.getY()); //inizio della i-esima riga
        }
        if (newPos.getX() == -1) {
            newPos = new Pair<Integer,Integer>(getIndexOfLastItemInARow(cursorPos.getY()), cursorPos.getY()); //bisogna vedere se la riga è piena di oggetti o termina prima
        } 
        if (newPos.getY() == -1) {
            newPos = new Pair<Integer,Integer>(cursorPos.getX(), getIndexOfLastItemInACol(cursorPos.getX())); //bisogna vedere se la colonna è l'ultima o la penultima
        }
        if ((newPos.getY() * COLS + newPos.getY()) > amountItem) {
            newPos = new Pair<Integer,Integer>(cursorPos.getX(), 0);
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

    private void manageUse() {
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

        if (mustClose) {
            mustClose = false;
            input = GameControl.CANCEL;
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
                    .getItem(this.cursorPos.getY() * COLS + this.cursorPos.getX())
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
                inventory.removeItem(this.cursorPos.getY() * COLS + this.cursorPos.getX());
                resetCursor();
                break;

            case CANCEL: 
                this.isReady = true; 
                this.exit = true;
                break;

            default:
                mustUpdate = false;
        }
        System.out.println("AM: " + cursorPos + " / " + this.inventory.slotOccupied());
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
    public Set<Entity> getEntities() {
        if (!isInit) {
            this.hero = (Hero)this.getActor();
            this.inventory = hero.getInventory();
            isInit = true;
            resetCursor();
        }
        //this.advise = Optional.empty();
        Set<Entity> items = this.inventory
            .getAllItems()
            .stream()
            .map(i -> fromItemToEntity(
                i,
                new Pair<>(
                    this.inventory.getAllItems().indexOf(i) % COLS,
                    this.inventory.getAllItems().indexOf(i) / COLS)
                )
        ).collect(Collectors.toSet());

        if (items.isEmpty()) {
            advise = Optional.of(new InfoPayloadImpl(
                    "Errore apertura Inventario", 
                    "L'inventario è vuoto: verrà chiuso al prossimo tasto della tasiera"));
            mustClose = true;
            isReady = true;
        }

        items.add(fromCursorToEntity());

        return items;
    }

    @Override
    public Optional<InfoPayload> execute() throws ModelException {
        if (exit) {
            this.isReady = false;
            resetCursor();
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
