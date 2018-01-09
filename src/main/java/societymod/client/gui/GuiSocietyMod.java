package societymod.client.gui;

import java.io.IOException;
import java.util.LinkedList;

import net.minecraft.client.gui.GuiScreen;
import societymod.client.gui.component.IGuiComponent;
import societymod.client.gui.component.IGuiComponent.IClickableComponent;
import societymod.client.gui.component.IGuiComponent.IKeyListenerComponent;
import societymod.client.gui.component.IGuiComponent.IUpdatableComponent;

public class GuiSocietyMod extends GuiScreen {

    protected final LinkedList<IGuiComponent> components = new LinkedList<>();

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        components.forEach(c -> c.draw(mouseX, mouseY));
    }

    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        components.stream().filter(c -> c instanceof IClickableComponent).forEach(c -> {
            final IClickableComponent component = (IClickableComponent) c;
            if (component.mouseClicked(mouseX, mouseY, mouseButton))
                componentClicked(components.indexOf(c));
        });
    }

    public void componentClicked(final int id) {}

    @Override
    protected void mouseClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        components.stream().filter(c -> c instanceof IClickableComponent).forEach(c -> ((IClickableComponent) c).mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick));
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        components.stream().filter(c -> c instanceof IUpdatableComponent).forEach(c -> ((IUpdatableComponent) c).update());
    }

    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        components.stream().filter(c -> c instanceof IKeyListenerComponent).forEach(c -> ((IKeyListenerComponent) c).keyTyped(typedChar, keyCode));
    }

    @Override
    public void initGui() {
        super.initGui();
        this.components.clear();
    }

}
