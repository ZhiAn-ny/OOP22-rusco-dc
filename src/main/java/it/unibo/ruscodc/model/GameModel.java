package it.unibo.ruscodc.model;

import it.unibo.ruscodc.model.actors.Actor;
import it.unibo.ruscodc.model.gamemap.Room;

import java.util.List;

public interface GameModel {

    List<Actor> getActorByInitative();

    List<Entity> getTile();

    List<Entity> getInfo(Entity toGet);

    Room getCurrentRoom();

}
