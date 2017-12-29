package societymod.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;

public class ContainerEmpty extends Container {

    private final BlockPos pos;

    public ContainerEmpty(final BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public boolean canInteractWith(final EntityPlayer player) {
        return player.getPosition().distanceSq(this.pos) < 16.0;
    }

}
