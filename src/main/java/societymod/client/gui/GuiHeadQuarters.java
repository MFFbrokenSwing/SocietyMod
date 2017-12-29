package societymod.client.gui;

import java.io.IOException;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.tileentity.TileEntity;
import societymod.common.tile.TileHeadQuarters;

public class GuiHeadQuarters extends GuiScreen {

    private final TileHeadQuarters tile;

    public GuiHeadQuarters(final TileEntity tile) {
        if (!(tile instanceof TileHeadQuarters))
            throw new RuntimeException("Wrong TileEntity passed to " + this.getClass().getSimpleName());
        this.tile = (TileHeadQuarters) tile;
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public void updateScreen() {}

    @Override
    public void initGui() {}

}
