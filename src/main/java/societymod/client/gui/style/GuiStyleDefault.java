package societymod.client.gui.style;

public class GuiStyleDefault implements IGuiStyle {

    private static final ColorSet INFO_COLORS   = new ColorSet(0xFF6AB0DE, 0xFFE7F2FA);
    private static final ColorSet HINT_COLORS   = new ColorSet(0xFF1ABC9C, 0xFFDBFAF4);
    private static final ColorSet WARN_COLORS   = new ColorSet(0xFFF0B37E, 0xFFFFEDCC);
    private static final ColorSet NORMAL_COLORS = new ColorSet(0xFFD8D8D8, 0xFFEEEEEE);
    private static final ColorSet CHART_COLORS  = new ColorSet(0xFF775B5B, 0xFFBAABAB);

    @Override
    public int getMargin() {
        return 4;
    }

    @Override
    public int getInterLine() {
        return 2;
    }

    @Override
    public ColorSet getColorFor(final MeaningType meaning) {
        switch (meaning) {
            case HINT:
                return HINT_COLORS;
            case INFO:
                return INFO_COLORS;
            case WARN:
                return WARN_COLORS;
            case NORMAL:
                return NORMAL_COLORS;
            case CHART:
                return CHART_COLORS;
        }
        throw new RuntimeException();
    }

}
