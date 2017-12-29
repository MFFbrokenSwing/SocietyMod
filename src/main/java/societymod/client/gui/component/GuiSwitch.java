package societymod.client.gui.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import societymod.common.SocietyMod;

public class GuiSwitch extends Gui {

    private static final ResourceLocation SWITCH = new ResourceLocation(SocietyMod.MODID, "textures/gui/switch.png");

    private final Minecraft mc;
    private final int       x, y;
    private final int       width, height;
    private boolean         activated;
    private long            lastClickTime = -1L;
    private final long      animDuration  = 200;
    private final int       cacheWidth, cacheHeight;
    private boolean         visible       = true;

    public GuiSwitch(final Minecraft mc, final int x, final int y, final int width, final int height, final boolean activated) {
        this.mc = mc;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.activated = activated;
        this.cacheWidth = (int) (44.0 / 90.0 * this.width);
        this.cacheHeight = (int) (41.0 / 45.0 * this.height);
    }

    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    public void draw() {
        if (this.visible) {
            this.mc.getTextureManager().bindTexture(SWITCH);
            Gui.drawScaledCustomSizeModalRect(this.x, this.y, 0, 0, 90, 45, this.width, this.height, 90, 90);
            long timePassed = System.currentTimeMillis() - lastClickTime;
            timePassed = clamp(0, this.animDuration, timePassed);
            int offset = (int) (42.0 / 90.0 * this.width * (timePassed * 1.0 / animDuration));
            if (this.activated)
                offset = (int) (42.0 / 90.0 * this.width - offset);
            Gui.drawScaledCustomSizeModalRect(this.x + offset + (int) (2.0 / 90.0 * this.width), this.y + (int) (Math.ceil(2.0 / 45.0 * this.height)), 0, 45, 44, 41, this.cacheWidth, this.cacheHeight,
                    90, 90);
            if (timePassed >= this.animDuration)
                this.lastClickTime = -1L;
        }
    }

    private long clamp(final long min, final long max, final long value) {
        return value < min ? min : value > max ? max : value;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(final boolean activated) {
        this.activated = activated;
    }

    public void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (visible && mouseButton == 0 && lastClickTime <= 0L && mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height) {
            lastClickTime = System.currentTimeMillis();
            activated = !activated;
        }
    }

}
