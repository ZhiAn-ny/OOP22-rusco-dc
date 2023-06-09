package it.unibo.ruscodc.utils.outputinfo;

import java.util.Objects;

/**
 * Implement InfoPayload interface. 
 */
public class InfoPayloadImpl implements InfoPayload {

    private static final String ERR_IMG_PATH = "it/unibo/ruscodc/output_res/error";

    private final String title; 
    private final String text;
    private final String path;

    /**
     * Create a generic info-page.
     * @param title the title of info-page
     * @param text their informations
     * @param path the image to match with the info-page
     */
    public InfoPayloadImpl(final String title, final String text, final String path) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(text);
        Objects.requireNonNull(path);
        this.text = text;
        this.title = title;
        this.path = path;
    }

    /**
     * Create a info-page with a standard image.
     * (for now, this image is only a warning-image)
     * @param title the title of info-page
     * @param text their informations
     */
    public InfoPayloadImpl(final String title, final String text) { 
        this(title, text, ERR_IMG_PATH);
    }

    /**
     * 
     */
    @Override
    public String title() {
        return this.title;
    }

    /**
     * 
     */
    @Override
    public String text() {
        return this.text;
    }

    /**
     * 
     */
    @Override
    public String getPath() {
        return this.path;
    }

}
