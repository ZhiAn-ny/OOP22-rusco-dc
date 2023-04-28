package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.actors.DummyHero;
import it.unibo.ruscodc.model.actors.Hero;
import it.unibo.ruscodc.model.gamemap.Floor;
import it.unibo.ruscodc.model.gamemap.FloorImpl;
import it.unibo.ruscodc.model.gamemap.Room;
import it.unibo.ruscodc.utils.Pair;
import java.util.List;

public class GameModelImpl implements GameModel{
    private Floor floor;
    private final Hero hero;

    public GameModelImpl(){
        this.floor = new FloorImpl();
        this.hero = new DummyHero(new Pair<>(3,3), "Rusco");

    }

    private List<Actor> getParty() {
        return List.of(hero);
    }

    @Override
    public List<Actor> getActorByInitative() {
        return List.of(hero);
    }

    @Override
    public List<Entity> getTile() {
        return this.floor.getCurrentRoom().getTilesAsEntity();
    }

    @Override
    public List<Entity> getInfo(Entity toGet) {
        if(toGet instanceof Hero){
            return getParty().stream().map(A -> (Entity)A).toList();
        }
        return List.of();
    }

    @Override
    public Room getCurrentRoom() {
        return this.floor.getCurrentRoom();
    }
}
