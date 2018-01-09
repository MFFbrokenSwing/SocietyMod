package societymod.client.gui;

import java.io.IOException;

import societymod.client.gui.component.GuiStyledButton;

public class GuiMenu extends GuiSocietyMod {

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void componentClicked(final int id) {}

    @Override
    public void initGui() {
        super.initGui();
        this.components.add(new GuiStyledButton(this.mc, 10, 10, (this.width - 30) / 2, (this.height - 30) / 2, "My societies"));
        this.components.add(new GuiStyledButton(this.mc, 10 + (this.width - 30) / 2 + 10, 10, (this.width - 30) / 2, (this.height - 30) / 2, "News"));
        this.components.add(new GuiStyledButton(this.mc, 10, 10 + (this.height - 30) / 2 + 10, (this.width - 30) / 2, (this.height - 30) / 2, "Rates"));
        this.components.add(new GuiStyledButton(this.mc, 10 + (this.width - 30) / 2 + 10, 10 + (this.height - 30) / 2 + 10, (this.width - 30) / 2, (this.height - 30) / 2, "Exchanges"));
    }

}
