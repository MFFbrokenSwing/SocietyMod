package societymod.client.gui.style;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import societymod.common.SocietyMod;

public class GuiStyleManager {

    private static final Logger                     LOGGER = LogManager.getLogger(SocietyMod.MODID);
    private static final HashMap<String, IGuiStyle> STYLES = new HashMap<>();
    private static String                           styleName;

    private static IGuiStyle cachedStyle = null;

    /**
     * Registers a style with the specified name
     *
     * @param name
     *            The name of the style
     * @param style
     *            The style to use
     */
    public static void register(final String name, final IGuiStyle style) {
        if (!STYLES.containsKey(name)) {
            STYLES.put(name, style);
            LOGGER.debug("Registered GUI style with name {}.", name);
        } else
            LOGGER.warn("Tried to register GUI style under name {} but this name wasn't available.", name);
    }

    /**
     * Sets the style to use to display GUIs.
     *
     * @param name
     *            The name of the style
     */
    public static void setStyle(final String name) {
        styleName = name;
        cachedStyle = null;
    }

    /**
     * Returns the style to use to display the components of the SocietyMod.
     *
     * @return the style to use
     */
    public static IGuiStyle getStyle() {
        if (cachedStyle == null) {
            if (!STYLES.containsKey(styleName)) {
                LOGGER.warn("GUI style was set to {} but no style exists with this name. Falling back to 'default'.", styleName);
                styleName = "default";
            }
            cachedStyle = STYLES.get(styleName);
        }
        return cachedStyle;
    }

}
