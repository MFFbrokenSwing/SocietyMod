package societymod.client.gui.component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.GameProfile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;
import societymod.client.render.RenderHelper;

public class GuiPlayerList extends Gui {

    protected int             x, y;
    protected int             width, height;
    protected int             slotHeight;
    protected final Minecraft mc;

    protected int ySlotsOffset  = 0;
    protected int scrollBarY    = 0;
    protected int scrollBarSize = 0;
    protected int clickedY      = -1;

    protected GameProfile selectedGameProfile = null;

    protected HashMap<GameProfile, ResourceLocation> players = new HashMap<>();

    public GuiPlayerList(final Minecraft mc, final int x, final int y, final int width, final int height, final int slotHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.slotHeight = slotHeight;
        this.mc = mc;
    }

    /**
     * Returns the selected {@link GameProfile} or null if no profile is selected.
     *
     * @return the selected {@link GameProfile}
     */
    @Nullable
    public GameProfile getSelectedProfile() {
        return this.selectedGameProfile;
    }

    /**
     * Removes a player from the list.
     *
     * @param player
     *            The name of the player
     */
    public void removeSlot(final String player) {
        final Iterator<Entry<GameProfile, ResourceLocation>> it = players.entrySet().iterator();
        while (it.hasNext())
            if (it.next().getKey().getName().equalsIgnoreCase(player)) {
                it.remove();
                return;
            }
    }

    public void setPositionAndSize(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {

        // Scroll bar stuff
        if (this.scrollBarSize > 0 && mouseButton == 0)
            // Checks if mouse is over the scroll bar
            if (mouseX >= this.x + this.width - 6 && mouseX <= this.x + this.width - 1 && mouseY >= this.y + 1 + this.scrollBarY && mouseY <= this.y + this.scrollBarY + this.scrollBarSize - 1) {
                this.clickedY = mouseY;
                return true;
            }
        clickedY = -1;

        // Slot selection stuff
        if (mouseButton == 0 && mouseX >= this.x + 1 && mouseX < this.x + this.width - (this.scrollBarSize > 0 ? -1 : -6) && mouseY >= this.y + 1 && mouseY <= this.y + this.height - 1) {
            final int selectedId = (int) ((mouseY + this.ySlotsOffset - this.y - 1) * 1f / this.slotHeight);
            if (selectedId >= 0 && selectedId < this.players.size()) {
                int i = 0;
                final Iterator<Entry<GameProfile, ResourceLocation>> it = players.entrySet().iterator();
                while (it.hasNext()) {
                    if (i++ == selectedId) {
                        this.selectedGameProfile = it.next().getKey();
                        return true;
                    }
                    it.next();
                }
            } else
                this.selectedGameProfile = null;
        }
        return false;
    }

    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        if (this.scrollBarSize > 0 && clickedY >= 0 && clickedMouseButton == 0)
            if (mouseY != this.clickedY) {
                this.scrollBarY += mouseY - this.clickedY;
                this.scrollBarY = Math.max(0, this.scrollBarY);
                this.scrollBarY = Math.min(this.scrollBarY, this.height - this.scrollBarSize);
                this.clickedY = mouseY;
            }
    }

    public void draw(final int mouseX, final int mouseY) {
        // Border
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -6250336);
        // Background
        Gui.drawRect(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height - 1, 0xFF000000);

        // Scissor to avoid drawing out of the list box
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        RenderHelper.scissorBox(this.x + 1, this.y + 1, this.x + this.width - 1, this.y + this.height);

        GlStateManager.color(1f, 1f, 1f); // Gui.drawRect changes the color, so we reset it, it fixes weird color on
                                          // players' heads

        int i = 0;
        this.ySlotsOffset = (int) (this.scrollBarY * 1f / this.height * this.players.size() * this.slotHeight);
        final Iterator<Entry<GameProfile, ResourceLocation>> it = this.players.entrySet().iterator();

        while (it.hasNext()) {
            final Entry<GameProfile, ResourceLocation> entry = it.next();

            final int ySlot = -this.ySlotsOffset + this.y + 1 + (this.slotHeight * (i++));
            this.drawSlot(ySlot, entry.getKey().getName(), entry.getValue(), entry.getKey() == this.selectedGameProfile);
        }

        GL11.glDisable(GL11.GL_SCISSOR_TEST);

        // Check if we need a scroll bar
        if (this.height - 2 < this.slotHeight * this.players.size()) {
            this.scrollBarSize = (int) (((this.height - 2) * 1.0f / this.slotHeight) / this.players.size() * (this.height - 2));

            // Draw scroll bar
            Gui.drawRect(this.x + this.width - 6, this.y + this.scrollBarY + 1, this.x + this.width - 1, this.y + this.scrollBarY + this.scrollBarSize - 1, 0xFF777777);
        } else {
            this.scrollBarSize = 0;
            this.scrollBarY = 0;
        }
    }

    /**
     * Draws the slot at the specified y location;
     *
     * @param y
     *            The y coords
     * @param name
     *            The name of the player
     * @param headTexture
     *            The skin's texture of the player
     * @param selected
     *            Is the slot selected
     */
    protected void drawSlot(final int y, final String name, final ResourceLocation headTexture, final boolean selected) {
        if (selected)
            Gui.drawRect(this.x + 1, y, this.x + this.width - (this.scrollBarSize > 0 ? -1 : -6), y + this.slotHeight, 0xFFFFFFFF);
        GlStateManager.color(1f, 1f, 1f);
        RenderHelper.renderPlayerHead(this.x + 1, y, this.slotHeight, this.slotHeight, headTexture);
        this.mc.fontRenderer.drawString(name, this.x + this.slotHeight + 3, (int) (y + this.slotHeight / 2f - this.mc.fontRenderer.FONT_HEIGHT / 2f), selected ? 0 : 0xFFFFFF);
    }

    /**
     * Adds a player to the list
     *
     * @param name
     *            The name of the player to add
     */
    public void addPlayer(final String name) {
        final GameProfile profile = TileEntitySkull.updateGameprofile(new GameProfile(null, name));
        if (profile != null && profile.isComplete()) {
            final ResourceLocation rl = RenderHelper.getPlayerSkin(profile);
            players.put(profile, rl != null ? rl : DefaultPlayerSkin.getDefaultSkin(profile.getId()));
        }
    }

}
