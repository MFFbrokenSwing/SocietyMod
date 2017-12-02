package societymod.common.proxy;

import net.minecraftforge.fml.common.SidedProxy;
import societymod.common.SocietyMod;

public abstract class CommonProxy {

    @SidedProxy(modId = SocietyMod.MODID, clientSide = "societymod.client.proxy.ClientProxy", serverSide = "societymod.server.proxy.ServerProxy")
    public static CommonProxy proxy = null;

}
