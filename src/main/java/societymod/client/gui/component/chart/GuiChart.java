package societymod.client.gui.component.chart;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.Vec2f;
import societymod.client.gui.component.IGuiComponent;
import societymod.client.gui.component.IGuiComponent.IClickableComponent;
import societymod.client.gui.component.IGuiComponent.IUpdatableComponent;
import societymod.client.gui.style.GuiStyleManager;
import societymod.client.gui.style.IGuiStyle;
import societymod.client.gui.style.MeaningType;
import societymod.client.render.RenderHelper;

public class GuiChart extends Gui implements IGuiComponent, IClickableComponent, IUpdatableComponent {

    protected final Minecraft mc;
    protected final int       x, y;
    protected final int       width, height;

    protected int   clickedX     = -1;
    protected int   clickedY     = -1;
    protected float chartOffsetX = 0;
    protected float chartOffsetY = 0;
    protected float chartScaling = 1; // 1 pixel on screen <=> 'chartScaling' on axis

    protected IChart chart = null;

    protected final int chartWidth, chartHeight;

    protected int mouseX, mouseY;

    public GuiChart(final Minecraft mc, final int x, final int y, final int width, final int height) {
        this.mc = mc;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        final IGuiStyle style = GuiStyleManager.getStyle();
        this.chartWidth = this.width - 2 * style.getMargin();
        this.chartHeight = this.height - style.getMargin() - 2 * style.getInterLine() - this.mc.fontRenderer.FONT_HEIGHT;
    }

    public void setChart(final IChart chart) {
        this.chart = chart;
        this.chartOffsetX = this.chartOffsetY = 0;
    }

    @Override
    public boolean mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {

        // Checks that it's left button which is clicked and mouse is inside the chart
        if (mouseButton == 0 && isCoordInChart(mouseX, mouseY)) {
            clickedX = mouseX;
            clickedY = mouseY;
            return true;
        }
        clickedX = clickedY = -1;
        return false;
    }

    @Override
    public void mouseClickMove(final int mouseX, final int mouseY, final int clickedButton, final long timeSinceLastClick) {
        if (this.chart != null && isCoordInChart(mouseX, mouseY)) {

            // Moving on X
            this.chartOffsetX += (this.clickedX - mouseX) * this.chartScaling;
            this.clickedX = mouseX;

            // Moving on Y
            this.chartOffsetY += (mouseY - this.clickedY) * this.chartScaling;
            this.clickedY = mouseY;
        }
    }

    public boolean isCoordInChart(final int xPos, final int yPos) {
        final IGuiStyle style = GuiStyleManager.getStyle();

        // Checks X position is in the chart
        if (xPos >= this.x + style.getMargin() && xPos <= this.x + this.width - style.getMargin())
            // Checks Y position is in the chart
            if (yPos >= this.y + this.mc.fontRenderer.FONT_HEIGHT + 2 * style.getInterLine() && yPos <= this.y + this.height - style.getMargin())
                return true;

        return false;
    }

    @Override
    public void draw(final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        final IGuiStyle style = GuiStyleManager.getStyle();
        final int headerSize = this.mc.fontRenderer.FONT_HEIGHT + 2 * style.getInterLine();

        // Draw header + borders
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, style.getColorFor(MeaningType.CHART).getPrimaryColor());

        // Draw chart background
        Gui.drawRect(this.x + style.getMargin(), this.y + headerSize, this.x + this.width - style.getMargin(), this.y + this.height - style.getMargin(),
                style.getColorFor(MeaningType.CHART).getSecondaryColor());

        if (this.chart == null) {
            final String message = I18n.format("chart.unavailable");
            this.mc.fontRenderer.drawString(message, this.x + (this.width - this.mc.fontRenderer.getStringWidth(message)) / 2, this.y + (this.height - this.mc.fontRenderer.FONT_HEIGHT) / 2, 0xFF0000);
        } else {

            // Draw mouse position {
            final Vec2f mouseCoords = fromChartToScaledChart(fromScreenToChart(new Vec2f(mouseX, mouseY)));
            if (isCoordInChart(mouseX, mouseY)) {
                final int coordsWidth = this.mc.fontRenderer.getStringWidth(mouseCoords.x + "/" + mouseCoords.y);
                int preDefinedX = mouseX + 4;
                int preDefinedY = mouseY - 8;
                if (!isCoordInChart(preDefinedX + coordsWidth, mouseY))
                    preDefinedX = this.x + this.width - style.getMargin() - coordsWidth;
                if (!isCoordInChart(mouseX, preDefinedY))
                    preDefinedY = this.y + headerSize;
                this.mc.fontRenderer.drawString(mouseCoords.x + "/" + mouseCoords.y, preDefinedX, preDefinedY, 0);
            }
            // }

            // Draw axis {
            final Vec2f originOnScaledChart = new Vec2f(0, 0);
            final Vec2f originOnChart = fromScaledChartToChart(originOnScaledChart);
            final Vec2f originOnScreen = fromChartToScreen(originOnChart);

            if (originOnChart.y > 0 && originOnChart.y < this.chartHeight)
                Gui.drawRect(this.x + style.getMargin(), (int) originOnScreen.y, this.x + this.width - style.getMargin(), (int) originOnScreen.y + 1, 0xFF000000);

            if (originOnChart.x > 0 && originOnChart.x < this.chartWidth)
                Gui.drawRect((int) originOnScreen.x, this.y + headerSize, (int) originOnScreen.x + 1, this.y + headerSize + this.chartHeight, 0xFF000000);

            // Draw graph {
            for (int i = 0; i < this.chartWidth; i++) {

                Vec2f pointOnChart = new Vec2f(i, 0);
                Vec2f pointOnScaledChart = fromChartToScaledChart(pointOnChart);
                final float value = this.chart.getValueFor(pointOnScaledChart.x);
                if (value < Float.MAX_VALUE) {
                    pointOnScaledChart = new Vec2f(pointOnScaledChart.x, value);
                    pointOnChart = fromScaledChartToChart(pointOnScaledChart);
                    final Vec2f pointOnScreen = fromChartToScreen(pointOnChart);

                    if (pointOnChart.y > 0 && pointOnChart.y < this.chartHeight)
                        Gui.drawRect((int) pointOnScreen.x, (int) pointOnScreen.y, (int) pointOnScreen.x + 1, (int) pointOnScreen.y + 1, 0xFF000000);
                }
            }
            // }

            // Draw graduations {
            GL11.glEnable(GL11.GL_SCISSOR_TEST);
            RenderHelper.scissorBox(this.x + style.getMargin(), this.y + headerSize, this.x + style.getMargin() + this.chartWidth, this.y + this.height - style.getMargin());
            final int yNumPos = (int) Math.max(style.getMargin(), originOnChart.x + 2 * style.getMargin());
            final int xNumPos = (int) Math.min(Math.max(this.mc.fontRenderer.FONT_HEIGHT + style.getInterLine(), originOnChart.y - style.getInterLine()), this.chartHeight - style.getInterLine());
            final float scaledHeight = this.chartHeight * this.chartScaling;
            int powTen = (int) Math.pow(10, Math.round(Math.log10(scaledHeight)));
            float step = powTen / 10 * Math.max(1, Math.round(scaledHeight / powTen));
            for (int i = 0; i < scaledHeight; i++) {
                final int yValueToDisplay = Math.round(this.chartOffsetY + i);
                if (yValueToDisplay % step == 0 && yValueToDisplay != 0) {
                    final Vec2f chartPoint = fromScaledChartToChart(new Vec2f(0, this.chartOffsetY + i));
                    final Vec2f screenPoint = fromChartToScreen(new Vec2f(yNumPos, chartPoint.y));
                    int xScreen = (int) screenPoint.x;
                    final int valueWidth = this.mc.fontRenderer.getStringWidth("" + yValueToDisplay);
                    if (xScreen + valueWidth > this.x + this.width - 2 * style.getMargin())
                        xScreen = this.x + this.width - 2 * style.getMargin() - valueWidth;
                    this.mc.fontRenderer.drawString(yValueToDisplay + "", xScreen, (int) screenPoint.y - this.mc.fontRenderer.FONT_HEIGHT / 2, 0);
                }
            }

            final float scaledWidth = this.chartWidth * this.chartScaling;
            powTen = (int) Math.pow(10, Math.round(Math.log10(scaledWidth)));
            step = powTen / 10 * Math.max(1, Math.round(scaledWidth / powTen));
            for (int i = 0; i < scaledWidth; i++) {
                final int xValueToDisplay = Math.round(this.chartOffsetX + i);
                if (xValueToDisplay % step == 0 && xValueToDisplay != 0) {
                    final Vec2f chartPoint = fromScaledChartToChart(new Vec2f(this.chartOffsetX + i, 0));
                    final Vec2f screenPoint = fromChartToScreen(new Vec2f(chartPoint.x, xNumPos));
                    this.mc.fontRenderer.drawString(xValueToDisplay + "", (int) screenPoint.x - this.mc.fontRenderer.getStringWidth("" + xValueToDisplay) / 2, (int) screenPoint.y, 0);
                }
            }
            GL11.glDisable(GL11.GL_SCISSOR_TEST);
            // }

            // Draw title
            this.mc.fontRenderer.drawString(chart.getTitle(), this.x + style.getMargin(), this.y + style.getInterLine(), 0xFFFFFF);
            // Draw x label
            this.mc.fontRenderer.drawString(chart.getXLabel(), this.x + 2 * style.getMargin(), this.y + headerSize + style.getMargin(), 0);
            // Draw y label
            this.mc.fontRenderer.drawString(chart.getYLabel(), this.x + this.width - 2 * style.getMargin() - this.mc.fontRenderer.getStringWidth(chart.getYLabel()),
                    this.y + this.height - style.getMargin() - style.getInterLine() - this.mc.fontRenderer.FONT_HEIGHT, 0);

        }
    }

    /**
     * Converts screen coordinates to chart coordinates.
     */
    protected Vec2f fromScreenToChart(final Vec2f coords) {
        final IGuiStyle style = GuiStyleManager.getStyle();
        return new Vec2f(coords.x - this.x - style.getMargin(), this.chartHeight - coords.y + this.y + this.mc.fontRenderer.FONT_HEIGHT + style.getInterLine() * 2);
    }

    /**
     * Converts chat coordinates to screen coordinates
     */
    protected Vec2f fromChartToScreen(final Vec2f coords) {
        final IGuiStyle style = GuiStyleManager.getStyle();
        return new Vec2f(coords.x + this.x + style.getMargin(), this.chartHeight - coords.y + this.y + this.mc.fontRenderer.FONT_HEIGHT + style.getInterLine() * 2);
    }

    /**
     * Converts chart coordinates to scaled chart coordinates.
     */
    protected Vec2f fromChartToScaledChart(final Vec2f coords) {
        return new Vec2f(this.chartOffsetX + coords.x * this.chartScaling, this.chartOffsetY + coords.y * this.chartScaling);
    }

    /**
     * Converts scaled chart coordinates to chart coordinates.
     */
    protected Vec2f fromScaledChartToChart(final Vec2f coords) {
        return new Vec2f((coords.x - this.chartOffsetX) / this.chartScaling, (coords.y - this.chartOffsetY) / this.chartScaling);
    }

    @Override
    public void update() {
        final int scroll = Mouse.getDWheel();
        if (scroll != 0) {
            final Vec2f pointed = fromChartToScaledChart(fromScreenToChart(new Vec2f(mouseX, mouseY)));
            this.chartScaling += -scroll / 120.0f;
            this.chartScaling = Math.max(this.chartScaling, 1);
            final Vec2f newMousePos = fromChartToScreen(fromScaledChartToChart(pointed));
            this.clickedX = (int) newMousePos.x;
            this.clickedY = (int) newMousePos.y;
            this.mouseClickMove(mouseX, mouseY, 0, 0);
            this.clickedX = -1;
            this.clickedY = -1;
        }
    }

}
