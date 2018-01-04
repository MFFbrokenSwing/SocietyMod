package societymod.client.bindings;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import societymod.client.gui.GuiWelcome;
import societymod.common.SocietyMod;

@EventBusSubscriber(modid = SocietyMod.MODID, value = Side.CLIENT)
public class Bindings {

    public static final KeyBinding MAIN_GUI_BINDING = new KeyBinding("keybinding.opengui.desc", KeyConflictContext.IN_GAME, KeyModifier.NONE, Keyboard.KEY_NONE, "SocietyMod");

    @SubscribeEvent
    public static void openSocietyModGui(final KeyInputEvent event) {
        if (MAIN_GUI_BINDING.isPressed())
            Minecraft.getMinecraft().displayGuiScreen(new GuiWelcome());
    }

}
