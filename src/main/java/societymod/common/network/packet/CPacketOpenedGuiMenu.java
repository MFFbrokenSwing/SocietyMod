package societymod.common.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import societymod.common.capability.person.PersonProvider;

public class CPacketOpenedGuiMenu implements IMessage {

    @Override
    public void fromBytes(final ByteBuf buf) {}

    @Override
    public void toBytes(final ByteBuf buf) {}

    public static class Handler implements IMessageHandler<CPacketOpenedGuiMenu, IMessage> {

        @Override
        public IMessage onMessage(final CPacketOpenedGuiMenu message, final MessageContext ctx) {
            ctx.getServerHandler().player.getCapability(PersonProvider.PERSON_CAPABILITY, null).setOpenedGuiMenu(true);
            return null;
        }

    }

}
