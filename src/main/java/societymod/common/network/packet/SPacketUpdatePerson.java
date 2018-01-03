package societymod.common.network.packet;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import societymod.common.capability.person.IPerson;
import societymod.common.capability.person.PersonProvider;

public class SPacketUpdatePerson implements IMessage {

    private NBTTagCompound nbt;

    public SPacketUpdatePerson() {}

    public SPacketUpdatePerson(final IPerson person) {
        this.nbt = (NBTTagCompound) PersonProvider.PERSON_CAPABILITY.getStorage().writeNBT(PersonProvider.PERSON_CAPABILITY, person, null);
    }

    @Override
    public void fromBytes(final ByteBuf buf) {
        final PacketBuffer pck = new PacketBuffer(buf);
        try {
            this.nbt = pck.readCompoundTag();
        } catch (final IOException e) {
            e.printStackTrace();
            this.nbt = new NBTTagCompound();
        }
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        final PacketBuffer pck = new PacketBuffer(buf);
        pck.writeCompoundTag(this.nbt);
    }

    public static class Handler implements IMessageHandler<SPacketUpdatePerson, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(final SPacketUpdatePerson message, final MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                final Capability.IStorage<IPerson> storage = PersonProvider.PERSON_CAPABILITY.getStorage();
                final IPerson person = Minecraft.getMinecraft().player.getCapability(PersonProvider.PERSON_CAPABILITY, null);
                storage.readNBT(PersonProvider.PERSON_CAPABILITY, person, null, message.nbt);
            });
            return null;
        }

    }

}
