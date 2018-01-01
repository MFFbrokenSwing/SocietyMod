package societymod.common.config;

import java.io.File;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.Configuration;
import societymod.common.SocietyMod;

public class BaseConfig {

    private final static Logger LOGGER = LogManager.getLogger(SocietyMod.MODID);

    protected final Configuration            config;
    protected final HashMap<String, Integer> INTEGERS = new HashMap<>();
    protected final HashMap<String, String>  STRINGS  = new HashMap<>();
    protected final HashMap<String, Boolean> BOOLS    = new HashMap<>();

    public BaseConfig(final File file) {
        this.config = new Configuration(file);
        load();
    }

    protected void load() {

    }

    public final void reload() {
        this.config.load();
        load();
    }

    public final int getInt(final String key) {
        if (INTEGERS.containsKey(key))
            return INTEGERS.get(key).intValue();
        LOGGER.warn("Tried to get unknown int value from config : " + key);
        return 0;
    }

    public final String getString(final String key) {
        if (STRINGS.containsKey(key))
            return STRINGS.get(key);
        LOGGER.warn("Tried to get unknown string value from config : " + key);
        return "";
    }

    public final boolean getBool(final String key) {
        if (BOOLS.containsKey(key))
            return BOOLS.get(key).booleanValue();
        LOGGER.warn("Tried to get unknown boolean value from config : " + key);
        return false;
    }

}
