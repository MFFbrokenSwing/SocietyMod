package societymod.client.gui.component;

public interface IGuiComponent {

    public void draw(int mouseX, int mouseY);

    public static interface IClickableComponent {

        public boolean mouseClicked(int mouseX, int mouseY, int mouseButton);

        public void mouseClickMove(int mouseX, int mouseY, int clickedButton, long timeSinceLastClick);

    }

    public static interface IUpdatableComponent {

        public void update();

    }

    public static interface IKeyListenerComponent {

        public void keyTyped(char typedChar, int keyCode);

    }

}
