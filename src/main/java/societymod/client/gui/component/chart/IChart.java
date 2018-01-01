package societymod.client.gui.component.chart;

public interface IChart {

    /**
     * Returns the Y value for the given X over or {@link Float#MAX_VALUE} if not
     * value exists.
     *
     * @param x
     *            The X value
     * @return the corresponding Y value
     */
    public float getValueFor(float x);

    /**
     * Returns the title of the chart.
     *
     * @return the title
     */
    public String getTitle();

    /**
     * Returns the label to display on the X axis.
     *
     * @return the label
     */
    public String getXLabel();

    /**
     * Returns the label to display on the Y axis.
     *
     * @return the label
     */
    public String getYLabel();

}
