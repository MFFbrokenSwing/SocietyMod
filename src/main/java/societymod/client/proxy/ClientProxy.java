package societymod.client.proxy;

import java.io.File;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import societymod.client.bindings.Bindings;
import societymod.client.config.ClientConfig;
import societymod.client.gui.style.GuiStyleDefault;
import societymod.client.gui.style.GuiStyleManager;
import societymod.common.config.BaseConfig;
import societymod.common.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
        // Register default GUI style
        GuiStyleManager.register("default", new GuiStyleDefault());
        // Register key binding
        ClientRegistry.registerKeyBinding(Bindings.MAIN_GUI_BINDING);
    }

    @Override
    protected BaseConfig createConfig(final File file) {
        return new ClientConfig(file);
    }

}
