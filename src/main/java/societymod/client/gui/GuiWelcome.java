package societymod.client.gui;

import java.io.IOException;

import net.minecraft.client.resources.I18n;
import societymod.client.gui.component.GuiMessagePanel;
import societymod.client.gui.style.MeaningType;
import societymod.common.capability.person.PersonProvider;
import societymod.common.network.ModNetwork;
import societymod.common.network.packet.CPacketOpenedGuiMenu;

public class GuiWelcome extends GuiSocietyMod {

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (mouseButton == 0) {
            this.mc.player.getCapability(PersonProvider.PERSON_CAPABILITY, null).setOpenedGuiMenu(true);
            ModNetwork.NETWORK.sendToServer(new CPacketOpenedGuiMenu());
            this.initGui();
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        if (this.mc.player.getCapability(PersonProvider.PERSON_CAPABILITY, null).hasOpenedGuiMenu()) {
            this.mc.displayGuiScreen(new GuiMenu());
        } else {
            GuiMessagePanel panel = new GuiMessagePanel(this.mc, I18n.format("message.welcome.title"), I18n.format("message.welcome.text"), this.width / 4, this.width / 4, this.width / 2);
            final int height = panel.getHeight();
            panel = new GuiMessagePanel(this.mc, I18n.format("message.welcome.title"), I18n.format("message.welcome.text"), this.width / 4, (this.height - height) / 2, this.width / 2);
            panel.setType(MeaningType.INFO);
            this.components.add(panel);
        }
    }

}
