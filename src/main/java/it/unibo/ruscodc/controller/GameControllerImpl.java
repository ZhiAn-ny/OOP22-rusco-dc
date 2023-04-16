package it.unibo.ruscodc.controller;

import it.unibo.ruscodc.model.Actor;
import it.unibo.ruscodc.model.GameInstant;
import it.unibo.ruscodc.model.Monster;
import it.unibo.ruscodc.model.Hero;
import it.unibo.ruscodc.model.gamemap.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameControllerImpl implements GameObserverController {

    List<Actor> actors = new ArrayList<Actor>();
    Optional<GameInstant> actualInstant = Optional.empty();

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
                //TODO
            }
            else {
                actors.get(0).act();
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
        while (actors.get(0) instanceof Monster) {
            actors.get(0).act();
            actors.remove(0);
            initNewTurn();
        }
    }
}
