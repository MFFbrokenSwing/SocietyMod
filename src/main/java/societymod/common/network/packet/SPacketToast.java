package societymod.common.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.client.gui.toasts.SystemToast.Type;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SPacketToast implements IMessage {

    private ITextComponent title;
    private ITextComponent subtitle;

    public SPacketToast() {}

    public SPacketToast(final ITextComponent title) {
        this(title, new TextComponentString(""));
    }

    public SPacketToast(final ITextComponent title, final ITextComponent subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    @Override
    public void fromBytes(final ByteBuf buf) {
        title = ITextComponent.Serializer.jsonToComponent(ByteBufUtils.readUTF8String(buf));
        subtitle = ITextComponent.Serializer.jsonToComponent(ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, ITextComponent.Serializer.componentToJson(this.title));
        ByteBufUtils.writeUTF8String(buf, ITextComponent.Serializer.componentToJson(this.subtitle));
    }

    public static class Handler implements IMessageHandler<SPacketToast, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(final SPacketToast message, final MessageContext ctx) {
            Minecraft.getMinecraft().getToastGui().add(new SystemToast(Type.TUTORIAL_HINT, message.title, message.subtitle));
            return null;
        }

    }

}
