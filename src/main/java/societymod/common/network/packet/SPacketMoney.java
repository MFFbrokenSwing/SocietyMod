package societymod.common.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import societymod.client.ClientInformations;
import societymod.common.capability.money.MoneyProvider;
import societymod.common.money.IMoneyHolder;

public class SPacketMoney implements IMessage {

    private String moneyName;
    private float  amount;

    public SPacketMoney() {}

    public SPacketMoney(final IMoneyHolder holder, final String moneyName) {
        this.amount = holder.getBalance();
        this.moneyName = moneyName;
    }

    @Override
    public void fromBytes(final ByteBuf buf) {
        this.amount = buf.readFloat();
        this.moneyName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(final ByteBuf buf) {
        buf.writeFloat(this.amount);
        ByteBufUtils.writeUTF8String(buf, this.moneyName);
    }

    public static class Handler implements IMessageHandler<SPacketMoney, IMessage> {

        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(final SPacketMoney message, final MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                Minecraft.getMinecraft().player
                        .getCapability(MoneyProvider.MONEY_HOLDER_CAPABILITY, null)
                        .setBalance(message.amount);
                ClientInformations.moneyName = message.moneyName;
            });
            return null;
        }

    }

}
