package societymod.client.gui.component;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import societymod.client.render.RenderHelper;

public class GuiPlayerSelector extends Gui {

    protected int              x, y;
    protected int              width, height;
    protected GuiTextField     nameField;
    protected long             lastTypedTime = -1L;
    protected final Minecraft  mc;
    protected ResourceLocation playerHead    = null;

    public GuiPlayerSelector(final Minecraft mc, final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        nameField = new GuiTextField(0, mc.fontRenderer, x, y, width, height);
        this.mc = mc;
    }

    public void draw() {
        nameField.drawTextBox();
        Gui.drawRect(this.x + this.width, this.y - 1, this.x + this.width + this.height + 2, this.y + this.height + 1, -6250336);
        if (playerHead != null) {
            GlStateManager.color(1f, 1f, 1f);
            RenderHelper.renderPlayerHead(this.x + this.width + 1, this.y, this.height, this.height, playerHead);
        } else
            Gui.drawRect(this.x + this.width + 1, this.y, this.x + this.height + this.width + 1, this.y + this.height, 0xFF000000);
    }

    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        boolean flag = this.nameField.mouseClicked(mouseX, mouseY, mouseButton);
        flag |= mouseX >= this.x + this.width && mouseX <= this.x + this.width + this.height + 2 && mouseY >= this.y - 1 && mouseY <= this.y + this.height + 1;
        this.nameField.setFocused(flag);
        return flag;
    }

    public void update() {
        this.nameField.updateCursorCounter();
        if (lastTypedTime > 0L && System.currentTimeMillis() - lastTypedTime > 1000L)
            if (!this.nameField.getText().isEmpty()) {
                lastTypedTime = -1L;
                this.playerHead = RenderHelper.getPlayerSkin(this.nameField.getText());
            }
    }

    public void keyTyped(final char typedChar, final int keyCode) {
        lastTypedTime = System.currentTimeMillis(); // Used to wait 2 secs
                                                    // before displaying the new
                                                    // head
        final String text = this.nameField.getText();
        this.nameField.textboxKeyTyped(typedChar, keyCode);
        if (!text.equals(this.nameField.getText()))
            this.playerHead = null; // Name certainly changed, so we reset head
        // display
    }

}
