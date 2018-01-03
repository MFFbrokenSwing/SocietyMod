package societymod.common.capability.person;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import societymod.common.network.ModNetwork;
import societymod.common.network.packet.SPacketUpdatePerson;

public class PlayerPerson extends DefaultPerson {

    private final EntityPlayer player;
    private final Side         side;

    public PlayerPerson(final EntityPlayer player, final Side side) {
        this.player = player;
        this.side = side;
    }

    @Override
    public void setSocietiesIds(final List<String> ids) {
        super.setSocietiesIds(ids);
        if (this.side.isServer())
            ModNetwork.NETWORK.sendTo(new SPacketUpdatePerson(this), (EntityPlayerMP) player);
    }

}
