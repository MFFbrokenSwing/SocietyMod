package societymod.client.gui.component;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import societymod.client.gui.style.GuiStyleManager;
import societymod.client.gui.style.IGuiStyle;
import societymod.client.gui.style.MeaningType;

public class GuiMessagePanel extends Gui implements IGuiComponent {

    protected final String       title;
    protected final List<String> lines;
    protected final int          x, y;
    protected final int          width, height;
    protected final Minecraft    mc;
    protected MeaningType        type;
    protected final int          headerHeight;

    protected static final int MARGIN     = 4;
    protected static final int INTER_LINE = 3;

    public GuiMessagePanel(final Minecraft mc, final String title, final String message, final int x, final int y, final int width) {
        String trimmedTitle = mc.fontRenderer.trimStringToWidth(title, width - GuiStyleManager.getStyle().getMargin() * 2);
        if (!trimmedTitle.equals(title) && trimmedTitle.length() >= 7)
            trimmedTitle = StringUtils.abbreviate(trimmedTitle, trimmedTitle.length() - 3);
        this.title = trimmedTitle;
        this.x = x;
        this.y = y;
        this.width = width;
        this.mc = mc;
        this.lines = this.mc.fontRenderer.listFormattedStringToWidth(message, this.width - 2 * MARGIN);
        this.height = this.lines.size() * (this.mc.fontRenderer.FONT_HEIGHT + INTER_LINE) - INTER_LINE + MARGIN * 2;
        this.headerHeight = this.mc.fontRenderer.FONT_HEIGHT + 2 * INTER_LINE;
        this.type = MeaningType.INFO;
    }

    public void setType(final MeaningType type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(final int mouseX, final int mouseY) {
        final IGuiStyle style = GuiStyleManager.getStyle();

        // Header
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + headerHeight, style.getColorFor(this.type).getPrimaryColor());
        this.mc.fontRenderer.drawString(this.title, this.x + MARGIN, this.y + INTER_LINE, 0xFFFFFF);

        // Body
        Gui.drawRect(this.x, this.y + headerHeight, this.x + this.width, this.y + headerHeight + this.height, style.getColorFor(this.type).getSecondaryColor());
        int i = 0;
        for (final String line : this.lines) {
            this.mc.fontRenderer.drawString(line, this.x + MARGIN, this.y + headerHeight + MARGIN + i * (this.mc.fontRenderer.FONT_HEIGHT + INTER_LINE), 0);
            i++;
        }

        // Reset color for other components
        GlStateManager.color(1f, 1f, 1f);
    }

}
