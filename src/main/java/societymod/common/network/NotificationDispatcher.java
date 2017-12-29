package societymod.common.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import societymod.common.SocietyMod;
import societymod.common.capability.PersonProvider;
import societymod.common.network.packet.SPacketToast;

@EventBusSubscriber(modid = SocietyMod.MODID)
public class NotificationDispatcher {

    @SubscribeEvent
    public static void playerConnect(final PlayerLoggedInEvent event) {
        if (!event.player.world.isRemote && event.player.getCapability(PersonProvider.PERSON_CAPABILITY, null).getSocietiesIds().isEmpty())
            ModNetwork.NETWORK.sendTo(new SPacketToast(new TextComponentTranslation("notification.welcome")), (EntityPlayerMP) event.player);
    }

}
