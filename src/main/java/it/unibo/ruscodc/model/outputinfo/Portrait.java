package it.unibo.ruscodc.model.outputinfo;

import java.util.List;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

public interface Portrait extends Entity {
    
    double getHPcoeff();

    double getAPcoeff();
    
    List<Pair<String, String>> getAttackInfo();

}
