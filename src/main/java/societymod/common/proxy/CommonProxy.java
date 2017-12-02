package societymod.common.proxy;

import net.minecraftforge.fml.common.SidedProxy;

public abstract class CommonProxy {

    @SidedProxy(clientSide = "societymod.client.proxy.ClientProxy", serverSide = "societymod.server.proxy.ServerProxy")
    public static CommonProxy proxy = null;

}
