package societymod.server.network;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import societymod.common.SocietyMod;

@EventBusSubscriber(modid = SocietyMod.MODID, value = Side.SERVER)
public class NotificationDispatcher {

    @SubscribeEvent
    public static void playerConnect(final PlayerLoggedInEvent event) {}

}
