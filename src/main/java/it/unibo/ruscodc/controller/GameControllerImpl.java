package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.GameInstant;
import it.unibo.ruscodc.model.Monster;
import it.unibo.ruscodc.model.Hero;
import it.unibo.ruscodc.model.gamecommand.BuilderGameCommand;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.exception.ModelException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameControllerImpl implements GameObserverController {

    List<Actor> actors = new ArrayList<Actor>();
    Optional<BuilderGameCommand> actualInstant = Optional.empty();

    Room actualRoom;

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
            if (actualInstant.isPresent()) {
                actualInstant.get().modify(input);
            }
            else {
                actualInstant = Optional.of(actors.get(0).act(input));
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

    }

    @Override
    public void start() {

    }

    @Override
    public void launch(){
        manageMonsterTurn();
    }

    private void initNewTurn(){
        if (actors.isEmpty()) {
            //TODO
            //actors.add(new Hero());
        }

    }
    private void manageMonsterTurn(){
        initNewTurn();
        while (actors.get(0).getClass().equals(Monster.class)) {
            actors.get(0).act(0);
            actors.remove(0);
            initNewTurn();
        }
    }
}
