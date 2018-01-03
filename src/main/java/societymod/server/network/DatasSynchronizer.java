package societymod.server.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.relauncher.Side;
import societymod.common.SocietyMod;
import societymod.common.capability.person.PersonProvider;
import societymod.common.network.ModNetwork;
import societymod.common.network.packet.SPacketMoney;
import societymod.common.network.packet.SPacketUpdatePerson;
import societymod.server.money.IMoneySystem;
import societymod.server.money.MoneySystemsManager;

@EventBusSubscriber(modid = SocietyMod.MODID, value = Side.SERVER)
public class DatasSynchronizer {

    @SubscribeEvent
    public static void playerConnectEvent(final PlayerLoggedInEvent event) {
        final EntityPlayerMP player = (EntityPlayerMP) event.player;
        updateMoney(player);
        sendPersonCapabilities(player);
    }

    public static void updateMoney(final EntityPlayerMP player) {
        final IMoneySystem moneySys = MoneySystemsManager.getSelectedMoneySystemOrDefault();
        ModNetwork.NETWORK.sendTo(new SPacketMoney(moneySys.getHolderFor(player), moneySys.getMoneyName()), player);
    }

    public static void sendPersonCapabilities(final EntityPlayerMP player) {
        ModNetwork.NETWORK.sendTo(new SPacketUpdatePerson(player.getCapability(PersonProvider.PERSON_CAPABILITY, null)), player);
    }

}
