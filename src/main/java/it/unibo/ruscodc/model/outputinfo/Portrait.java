package it.unibo.ruscodc.model.outputinfo;

import java.util.List;

import it.unibo.ruscodc.model.Entity;
import it.unibo.ruscodc.utils.Pair;

/**
 * TODO documetazione!.
 */
public interface Portrait extends Entity {

    /**
     * TODO documetazione!.
     * @return TODO documetazione!.
     */
    double getHPcoeff();

    /**
     * TODO documetazione!.
     * @return TODO documetazione!.
     */
    double getAPcoeff();

    /**
     * TODO documetazione!.
     * @return TODO documetazione!.
     */
    List<Pair<String, String>> getAttackInfo();
}
