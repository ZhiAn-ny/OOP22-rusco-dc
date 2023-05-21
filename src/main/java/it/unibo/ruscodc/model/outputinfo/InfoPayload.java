package it.unibo.ruscodc.model.outputinfo;

/**
 * An utility class to prensent to the view a set of information that don't follow a matrix logic.
 * For now, these informations can rappresent a simple info-page with title, description and one image
 * coded with their path.
 */
public interface InfoPayload {

    /**
     * Get the title of info-page.
     * @return the title.
     */
    String title();

    /**
     * Get the description of info-page.
     * @return some informations.
     */
    String text();

    /**
     * Get the image, coded as path.
     * @return the path of the image
     */
    String getPath();

}
