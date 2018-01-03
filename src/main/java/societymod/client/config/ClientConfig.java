package societymod.client.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import societymod.client.gui.style.GuiStyleManager;
import societymod.common.config.BaseConfig;

public class ClientConfig extends BaseConfig {

    private static final String CAT_PRE = Configuration.CATEGORY_GENERAL;

    public ClientConfig(final File file) {
        super(file);
    }

    @Override
    protected void load() {
        super.load();
        final String category = CAT_PRE + ".graphic-user-interfaces";
        Property p;

        // Gui style configuration
        config.setCategoryComment(category, "Settings about graphic user interfaces");

        p = config.get(category, "use_style", "default", "The name of the style to use");
        GuiStyleManager.setStyle(p.getString());

        config.save();
    }

}
