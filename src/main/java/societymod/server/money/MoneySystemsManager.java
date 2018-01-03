package societymod.server.money;

import java.util.HashMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import societymod.common.SocietyMod;

public class MoneySystemsManager {

    private static final Logger                        LOGGER        = LogManager.getLogger(SocietyMod.MODID);
    private static final HashMap<String, IMoneySystem> MONEY_SYSTEMS = new HashMap<>();

    private static IMoneySystem cachedMoneySystem = null;
    private static String       selectedMoneySystem;

    /**
     * Registers a money system with the specified name.
     *
     * @param name
     *            The name of the system
     * @param system
     *            The system
     */
    public static void registerSystem(final String name, final IMoneySystem system) {
        if (MONEY_SYSTEMS.containsKey(name))
            LOGGER.warn("Tried to register a money system under name {} but a money system already exists under this name.", name);
        else {
            MONEY_SYSTEMS.put(name, system);
            LOGGER.debug("Registered money system under name {}.", name);
        }
    }

    /**
     * Provides the money system with the specified name, or null if no money system
     * with this name is registered.
     *
     * @param name
     *            The name of the money system
     * @return the money system, or null
     */
    @Nullable
    public static IMoneySystem getMoneySystem(final String name) {
        return MONEY_SYSTEMS.get(name);
    }

    /**
     * Sets the money system to use in the mod
     *
     * @param name
     *            The name of the money system
     */
    public static void setSelectedMoneySystem(final String name) {
        selectedMoneySystem = name;
        LOGGER.info("Selected money system '{}'.", name);
    }

    /**
     * Returns the money system which is specified in the configuration file or the
     * default one if no money system exists with this name.
     *
     * @return the money system to use in the mod
     */
    @Nonnull
    public static IMoneySystem getSelectedMoneySystemOrDefault() {
        if (cachedMoneySystem == null) {
            cachedMoneySystem = getMoneySystem(selectedMoneySystem);
            if (cachedMoneySystem == null) {
                LOGGER.warn("Money system was set to '{}' but this system is not supported, falling back to 'societymod' system.", selectedMoneySystem);
                selectedMoneySystem = "societymod";
                cachedMoneySystem = getMoneySystem(selectedMoneySystem);
            }
        }
        return cachedMoneySystem;
    }

}
