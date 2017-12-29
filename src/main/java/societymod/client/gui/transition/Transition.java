package societymod.client.gui.transition;

import javax.annotation.Nullable;

import net.minecraft.client.gui.GuiScreen;

/**
 * Base class for transitions between two {@link GuiScreen}
 *
 * @author BrokenSwing
 *
 */
public abstract class Transition extends GuiScreen {

    protected final GuiScreen from;
    /** The {@link GuiScreen} to display once the transition finished */
    protected final GuiScreen to;

    protected long transitionStartingTime = -1L;
    protected long delta                  = 0;
    protected long currentTime            = 0;
    protected long lastDraw               = 0L;

    /**
     * Creates a transition between two {@link GuiScreen}
     *
     * @param from
     *            The {@link GuiScreen} to stop displaying, can be null
     * @param to
     *            The {@link GuiScreen} to start displaying, can be null
     */
    public Transition(@Nullable final GuiScreen from, @Nullable final GuiScreen to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Call this when the transition is ended, it will display the {@link GuiScreen}
     * {@link Transition#to}.
     */
    protected final void transitionFinished() {
        this.mc.displayGuiScreen(this.to);
    }

    @Override
    public final void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.currentTime = System.currentTimeMillis();
        if (this.transitionStartingTime < 0L) {
            this.transitionStartingTime = this.currentTime;
            this.lastDraw = this.currentTime;
        }
        this.delta = this.currentTime - this.lastDraw;
        this.lastDraw = this.currentTime;
        this.drawTransition(mouseX, mouseY, this.delta, this.currentTime - this.transitionStartingTime, partialTicks);
    }

    /**
     * Draws the transitions
     *
     * @param mouseX
     *            The x position of the mouse in the {@link GuiScreen}
     * @param mouseY
     *            The y position of the mouse in the {@link GuiScreen}
     * @param delta
     *            The time passed between the last frame and this one
     * @param timeSinceBeginning
     *            The time passed since the beginning of the transition
     * @prama partialTicks The partial ticks
     */
    protected abstract void drawTransition(int mouseX, int mouseY, long delta, long timeSinceBeginning, float partialTicks);

    @Override
    public void initGui() {
        if (this.to != null) {
            this.to.setWorldAndResolution(this.mc, this.width, this.height);
            this.to.initGui();
        }
        if (this.from != null) {
            this.from.setWorldAndResolution(this.mc, this.width, this.height);
            this.from.initGui();
        }
    }

}
