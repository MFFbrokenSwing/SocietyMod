package societymod.client.gui.style;

public interface IGuiStyle {

    /** @return the margin at edges of components */
    public int getMargin();

    /** @return the space between two lines */
    public int getInterLine();

    /**
     * Returns the set of colors to use for the specified meaning.
     *
     * @param meaning
     *            The meaning of the required colors
     * @return the colors to use
     */
    public ColorSet getColorFor(MeaningType meaning);

}
