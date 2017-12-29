package societymod.common.network.packet;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import societymod.common.capability.IPerson;
import societymod.common.capability.PersonProvider;

public class SPacketSocietiesIds implements IMessage {

    public List<String> ids;

    public SPacketSocietiesIds() {}

    public SPacketSocietiesIds(final IPerson person) {
        this.ids = person.getSocietiesIds();
    }

    @Override
    public void fromBytes(final ByteBuf buf) {
        this.ids = new ArrayList<>(buf.readInt());
        for (int i = 0; i < this.ids.size(); i++)
            this.ids.set(i, ByteBufUtils.readUTF8String(buf));
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        buf.writeInt(ids.size());
        for (int i = 0; i < ids.size(); i++)
            ByteBufUtils.writeUTF8String(buf, ids.get(i));
    }

    public static class Handler implements IMessageHandler<SPacketSocietiesIds, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(final SPacketSocietiesIds message, final MessageContext ctx) {
            Minecraft.getMinecraft().player.getCapability(PersonProvider.PERSON_CAPABILITY, null).setSocietiesIds(message.ids);
            return null;
        }

    }

}
