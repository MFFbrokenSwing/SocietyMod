package societymod.client.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import societymod.client.gui.style.GuiStyleManager;
import societymod.common.config.BaseConfig;

public class ClientConfig extends BaseConfig {

    public ClientConfig(final File file) {
        super(file);
    }

    @Override
    protected void load() {
        super.load();
        final String category = Configuration.CATEGORY_CLIENT;
        Property p;

        // Gui style configuration
        p = config.get(category + ".graphic-user-interfaces", "use_style", "default", "The name of the style to use");
        GuiStyleManager.setStyle(p.getName());
    }

}
