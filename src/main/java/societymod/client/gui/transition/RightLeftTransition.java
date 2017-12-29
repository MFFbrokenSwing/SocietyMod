package societymod.client.gui.transition;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class RightLeftTransition extends Transition {

    private final long                          duration;
    private final RightLeftTransition.Direction direction;

    public RightLeftTransition(final GuiScreen from, final GuiScreen to, final RightLeftTransition.Direction direction) {
        this(from, to, 1000L, direction);
    }

    public RightLeftTransition(final GuiScreen from, final GuiScreen to, final long duration, final RightLeftTransition.Direction direction) {
        super(from, to);
        this.duration = duration;
        this.direction = direction;
    }

    @Override
    protected void drawTransition(final int mouseX, final int mouseY, final long delta, final long timeSinceBeginning, final float partialTicks) {
        int x = this.width - (int) ((timeSinceBeginning * 1f / this.duration) * this.width);
        if (this.direction == Direction.RIGHT)
            x = -x;
        this.mc.fontRenderer.drawString("" + x, 100, 100, 0);
        if (timeSinceBeginning > duration)
            this.transitionFinished();

        // Draws outcoming GuiScreen

        GlStateManager.pushMatrix();
        GlStateManager.translate(x + (Direction.RIGHT == this.direction ? 1 : -1) * this.width, 0, 0);
        this.from.drawScreen(mouseX - x - this.width, mouseY, partialTicks);
        GlStateManager.popMatrix();

        // Draws incoming GuiScreen

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, 0, 0);
        this.to.drawScreen(mouseX - x, mouseY, partialTicks);
        GlStateManager.popMatrix();
    }

    public static enum Direction {
        RIGHT, LEFT;
    }

}
