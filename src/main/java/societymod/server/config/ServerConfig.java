package societymod.server.config;

import java.io.File;

import net.minecraftforge.common.config.Property;
import societymod.common.config.BaseConfig;
import societymod.server.money.MoneySystemsManager;

public class ServerConfig extends BaseConfig {

    private static final String CAT_PRE = "server";

    public ServerConfig(final File file) {
        super(file);
    }

    @Override
    protected void load() {
        super.load();
        final String category = CAT_PRE + ".money";

        Property p;

        // Money configuration
        config.setCategoryComment(category, "Settings about the money system");

        p = config.get(category, "money-system-to-use", "societymod", "The money system to use (default: societymod). Please refer to the wiki to know which system is supported.");
        MoneySystemsManager.setSelectedMoneySystem(p.getString());

        config.save();
    }

}
