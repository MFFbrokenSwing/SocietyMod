package societymod.client.gui.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import societymod.client.gui.component.IGuiComponent.IClickableComponent;
import societymod.client.gui.style.GuiStyleManager;
import societymod.client.gui.style.IGuiStyle;
import societymod.client.gui.style.MeaningType;

public class GuiStyledButton extends Gui implements IGuiComponent, IClickableComponent {

    protected final int x, y;
    protected final int width, height;
    protected boolean   visible = true;
    protected boolean   enabled = true;
    protected String    text;

    protected final Minecraft mc;

    public GuiStyledButton(final Minecraft mc, final int x, final int y, final int width, final int height, final String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mc = mc;
        this.text = text;
    }

    @Override
    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height && enabled && visible;
    }

    @Override
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedButton, final long timeSinceLastClick) {}

    @Override
    public void draw(final int mouseX, final int mouseY) {
        if (this.visible) {
            final boolean hovered = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
            final int modifier = hovered && enabled ? 0x111111 : enabled ? 0 : -0x111111;
            final IGuiStyle style = GuiStyleManager.getStyle();
            Gui.drawRect(x, y, x + width, y + height, style.getColorFor(MeaningType.NORMAL).getPrimaryColor() + modifier);
            Gui.drawRect(x + 2, y + 2, x + width - 2, y + height - 2, style.getColorFor(MeaningType.NORMAL).getSecondaryColor() + modifier);
            this.mc.fontRenderer.drawString(this.text, this.x + this.width / 2 - this.mc.fontRenderer.getStringWidth(this.text) / 2, this.y + this.height / 2 - this.mc.fontRenderer.FONT_HEIGHT / 2,
                    0);
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(final boolean visible) {
        this.visible = visible;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

}
