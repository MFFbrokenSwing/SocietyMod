package societymod.server.money;

import net.minecraft.entity.player.EntityPlayerMP;
import societymod.common.capability.money.MoneyProvider;
import societymod.common.money.IMoneyHolder;

public class MoneySystemSocietyMod implements IMoneySystem {

    @Override
    public IMoneyHolder getHolderFor(final EntityPlayerMP player) {
        return player.getCapability(MoneyProvider.MONEY_HOLDER_CAPABILITY, null);
    }

    @Override
    public String getMoneyName() {
        return "Roblyn";
    }

}
