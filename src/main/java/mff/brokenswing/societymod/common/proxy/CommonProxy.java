package mff.brokenswing.societymod.common.proxy;

import net.minecraftforge.fml.common.SidedProxy;

public abstract class CommonProxy {

    @SidedProxy(clientSide = "mff.brokenswing.societymod.client.proxy.ClientProxy", serverSide = "mff.brokenswing.societymod.server.proxy.ServerProxy")
    public static CommonProxy proxy = null;

}
