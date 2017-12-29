package societymod.client.render;

import java.util.Map;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHelper {

    // From :
    // https://github.com/DarkStorm652/Minecraft-GUI-API/blob/master/src/org/darkstorm/minecraft/gui/util/RenderUtil.java
    public static void scissorBox(final int x, final int y, final int xend, final int yend) {
        final int width = xend - x;
        final int height = yend - y;
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        final int factor = sr.getScaleFactor();
        final int bottomY = Minecraft.getMinecraft().currentScreen.height - yend;
        GL11.glScissor(x * factor, bottomY * factor, width * factor, height * factor);
    }

    /**
     * Renders the player head from his skin
     *
     * @param x
     *            on the screen
     * @param y
     *            on the screen
     * @param width
     *            to draw the head
     * @param height
     *            to draw the head
     * @param skinTexture
     *            The player's skin texture
     */
    public static void renderPlayerHead(final int x, final int y, final int width, final int height, final ResourceLocation skinTexture) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(skinTexture);
        Gui.drawScaledCustomSizeModalRect(x, y, 8, 8, 8, 8, width, height, 64, 64);
    }

    /**
     * Returns player's skin from his name
     *
     * @param playerName
     *            The name of the player
     * @return player's skin's texture
     */
    @Nullable
    public static ResourceLocation getPlayerSkin(final String playerName) {
        final GameProfile profile = TileEntitySkull.updateGameprofile(new GameProfile(null, playerName));
        return getPlayerSkin(profile);
    }

    /**
     * Returns player's skin from his profile
     *
     * @param profile
     *            The profile of the player
     * @return player's skin's texture
     */
    @Nullable
    public static ResourceLocation getPlayerSkin(final GameProfile profile) {
        if (profile != null) {
            final Map<Type, MinecraftProfileTexture> texProfiles = Minecraft.getMinecraft().getSkinManager().loadSkinFromCache(profile);
            if (texProfiles.containsKey(Type.SKIN))
                return Minecraft.getMinecraft().getSkinManager().loadSkin(texProfiles.get(Type.SKIN), Type.SKIN);
        }
        return null;
    }

}
