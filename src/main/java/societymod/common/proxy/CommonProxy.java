package societymod.common.proxy;

import java.io.File;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import societymod.common.SocietyMod;
import societymod.common.capability.DefaultPerson;
import societymod.common.capability.IPerson;
import societymod.common.capability.PersonStorage;
import societymod.common.config.BaseConfig;
import societymod.common.network.ModNetwork;

public abstract class CommonProxy {

    @SidedProxy(modId = SocietyMod.MODID, clientSide = "societymod.client.proxy.ClientProxy", serverSide = "societymod.server.proxy.ServerProxy")
    public static CommonProxy proxy = null;

    private BaseConfig config = null;

    public void preInit(final FMLPreInitializationEvent event) {
        this.config = createConfig(event.getSuggestedConfigurationFile());
    }

    public void init(final FMLInitializationEvent event) {
        CapabilityManager.INSTANCE.register(IPerson.class, new PersonStorage(), DefaultPerson.class);
        ModNetwork.init();
    }

    /**
     * Creates a side specific configuration
     */
    protected abstract BaseConfig createConfig(File file);

    public BaseConfig getConfig() {
        return this.config;
    }

}
