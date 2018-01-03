package societymod.server.proxy;

import java.io.File;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import societymod.common.config.BaseConfig;
import societymod.common.proxy.CommonProxy;
import societymod.server.config.ServerConfig;
import societymod.server.money.MoneySystemSocietyMod;
import societymod.server.money.MoneySystemsManager;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
        // Register default money system
        MoneySystemsManager.registerSystem("societymod", new MoneySystemSocietyMod());
    }

    @Override
    protected BaseConfig createConfig(final File file) {
        return new ServerConfig(file);
    }

}
