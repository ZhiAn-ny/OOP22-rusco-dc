package it.unibo.ruscodc.model.outputinfo;

import it.unibo.ruscodc.utils.Pair;

public class InfoPayloadImpl implements InfoPayload {

    private final static String ERR_IMG_PATH = null;

    private final String title; 
    private final String text;
    private final String path;

    public InfoPayloadImpl(String title, String text, String path) {
        this.text = text;
        this.title = title;
        this.path = path;
    }

    public InfoPayloadImpl(String title, String text) { //TODO - fare un enum di immagini-utility? per ora 1 costante: quella di errore
        this(title, text, ERR_IMG_PATH);
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public String text() {
        return this.text;
    }

    @Override
    public String getPath() {
        return this.path;
    }

}