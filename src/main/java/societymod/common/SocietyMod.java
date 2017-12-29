package societymod.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import societymod.common.proxy.CommonProxy;

@Mod(modid = SocietyMod.MODID, version = "unknown", useMetadata = true, name = "Society Mod")
public class SocietyMod {

    public static final String MODID = "societymod";

    @Instance(MODID)
    public static SocietyMod instance = null;

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        CommonProxy.proxy.preInit(event);
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        CommonProxy.proxy.init(event);
    }
}
