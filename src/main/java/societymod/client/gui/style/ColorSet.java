package societymod.client.gui.style;

public class ColorSet {

    protected final int primaryColor, secondaryColor;

    public ColorSet(final int primaryColor, final int secondaryColor) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
    }

    public int getPrimaryColor() {
        return this.primaryColor;
    }

    public int getSecondaryColor() {
        return this.secondaryColor;
    }

}
