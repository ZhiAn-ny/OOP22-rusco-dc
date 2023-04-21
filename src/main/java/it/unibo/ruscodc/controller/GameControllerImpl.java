package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.*;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import it.unibo.ruscodc.utils.exception.ModelException;
import it.unibo.ruscodc.view.GameView;
import it.unibo.ruscodc.view.ViewJFX;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameControllerImpl implements GameObserverController {

    private List<ActorAbs> actors = new ArrayList<ActorAbs>();
    private Hero rusco;
    private Optional<BuilderGameCommand> actualInstant = Optional.empty();
    private Room actualRoom;
    private final GameView view;

    public GameControllerImpl(){
        this.view = new ViewJFX();
        Hero rusco = new DummyHero(new Pair<>(3,3), "Rusco");
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
    public void computeInput(int input) {
        
        if (actors.get(0) instanceof Hero) {
            Hero tmp = (Hero)actors.get(0);
            if (actualInstant.isPresent()) {
                actualInstant.get().modify(input);
            }
            else {
                actualInstant = Optional.of(tmp.act(input));
                actualInstant.get().setRoom(actualRoom);
            }

            if (actualInstant.get().isReady()) {
                try{
                    actualInstant.get().execute();
                    actors.remove(0);
                    manageMonsterTurn();
                }catch (ModelException m) {

                }
            }
        }
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
    }

    @Override
    public void launch(){
        manageMonsterTurn();
    }

    private void initNewTurn(){
        if (actors.isEmpty()) {
            actors.add(rusco);
        }

    }
    private void manageMonsterTurn(){
        Monster tmp;
        initNewTurn();
        while (actors.get(0).getClass().equals(Monster.class)) {
            tmp = (Monster)actors.get(0);
            //tmp.behave();
            actors.remove(0);
            initNewTurn();
        }
    }
}
