package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.*;
import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.Hero;
import it.unibo.ruscodc.model.actors.Monster;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamecommand.MoveBuilder;
import it.unibo.ruscodc.utils.GameControl;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.view.GameView;
import it.unibo.ruscodc.view.ViewJFX;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameControllerImpl implements GameObserverController {

    private List<Actor> initiative = new ArrayList<>();
    private Optional<BuilderGameCommand> actualInstant = Optional.empty();
    private final GameView view;
    private final GameModel model;

    public GameControllerImpl(){
        this.view = new ViewJFX();
        this.model = new GameModelImpl();

    }

    @Override
    public void save() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void changeAutomaticSave() {

    }

    @Override
    public void computeInput(GameControl input) {
        if (initiative.get(0) instanceof Hero) {
            Hero tmp = (Hero)initiative.get(0);
            if (actualInstant.isPresent()) {
               // actualInstant.get().modify(input); TODO
            }
            else {
                actualInstant = Optional.of(tmp.act(input));
                actualInstant.get().setRoom(this.model.getCurrentRoom());
            }

            if (actualInstant.get().isReady()) {
                try{
                    actualInstant.get().execute();
                    actualInstant = Optional.empty();

                    view.setEntityToDraw(entityToUpload());
                    initiative.remove(0);
                    manageMonsterTurn();
                }catch (ModelException m) {
                    if(actualInstant.get() instanceof MoveBuilder) {
                        actualInstant = Optional.empty();
                    }
                }
            }
        }

    }

    private List<Entity> entityToUpload(){
        List<Entity> tmp = new ArrayList<>(this.model.getCurrentRoom().getTilesAsEntity());
        tmp.add((Entity) initiative.get(0));
        return tmp;
    }

    @Override
    public void quit() {

    }

    @Override
    public void init() {
        this.view.init(this);
    }

    @Override
    public void start() {
        this.view.startView();
        initNewTurn();
        while (!this.view.isReady()) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.view.setEntityToDraw(entityToUpload());
        manageMonsterTurn();
    }

    @Override
    public void launch(){
        manageMonsterTurn();
    }

    private void initNewTurn(){
        if (initiative.isEmpty()) {
            initiative.addAll(model.getActorByInitative());
        }

    }
    private void manageMonsterTurn(){
        Monster tmp;
        initNewTurn();
        while (initiative.get(0) instanceof Monster) {
            tmp = (Monster) initiative.get(0);
            //tmp.behave();
            initiative.remove(0);
            initNewTurn();
        }
    }

}
