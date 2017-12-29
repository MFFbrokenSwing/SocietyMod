package societymod.client.gui.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import societymod.common.SocietyMod;

public class GuiButtonArrow extends Gui {

    private static final ResourceLocation RIGHT_ARROW = new ResourceLocation(SocietyMod.MODID, "textures/gui/right_arrow.png");

    protected final int       x;
    protected final int       y;
    protected final int       width;
    protected final int       height;
    protected final Minecraft mc;
    protected boolean         enabled   = true;
    protected ArrowDirection  direction = ArrowDirection.RIGHT;
    protected boolean         hovered   = false;
    protected final int       arrowSize;

    public GuiButtonArrow(final Minecraft mc, final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mc = mc;
        this.arrowSize = Math.min(this.width / 2, this.height / 2);
    }

    public void setEnable(final boolean enabled) {
        this.enabled = enabled;
    }

    public void setDirection(final ArrowDirection direction) {
        this.direction = direction;
    }

    public void draw(final int mouseX, final int mouseY) {
        this.mc.getTextureManager().bindTexture(RIGHT_ARROW);
        hovered = mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, hovered && enabled ? 0x88AAAAAA : enabled ? 0x88DDDDDD : 0x55DDDDDD);
        GlStateManager.color(0.0f, 0f, 0f);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.x + this.width / 2, this.y + this.height / 2, 0);
        GlStateManager.rotate(this.direction.angle, 0, 0, 1f);
        Gui.drawScaledCustomSizeModalRect(-this.arrowSize / 2, -this.arrowSize / 2, 0, 0, 256, 256, this.arrowSize, this.arrowSize, 256, 256);
        GlStateManager.popMatrix();
        GlStateManager.color(1f, 1f, 1f);
    }

    public boolean isHovered() {
        return hovered;
    }

    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        return enabled && hovered;
    }

    public static enum ArrowDirection {
        TOP(270f), BOTTOM(90f), LEFT(180f), RIGHT(0f);

        private final float angle;

        private ArrowDirection(final float angle) {
            this.angle = angle;
        }

    }

}
