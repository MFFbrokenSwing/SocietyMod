package societymod.client.gui;

import net.minecraft.tileentity.TileEntity;
import societymod.common.tile.TileHeadQuarters;

public class GuiHeadQuarters extends GuiSocietyMod {

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
    public void initGui() {
        super.initGui();
    }

}
