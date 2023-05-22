package it.unibo.ruscodc.model.item;

import it.unibo.ruscodc.model.outputinfo.InfoPayload;

public interface Item {
    String getName();
    String getPath();
    InfoPayload getInfo();
    boolean isWearable();
}
