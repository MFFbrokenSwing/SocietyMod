package societymod.common.capability.money;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import societymod.common.SocietyMod;
import societymod.common.money.IMoneyHolder;

@EventBusSubscriber(modid = SocietyMod.MODID)
public class MoneyProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IMoneyHolder.class)
    public static final Capability<IMoneyHolder> MONEY_HOLDER_CAPABILITY = null;

    private final IMoneyHolder holder;

    public MoneyProvider() {
        this.holder = new DefaultMoneyHolder();
    }

    @Override
    public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
        return capability == MONEY_HOLDER_CAPABILITY;
    }

    @Override
    public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
        return capability == MONEY_HOLDER_CAPABILITY ? MONEY_HOLDER_CAPABILITY.cast(this.holder) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return MONEY_HOLDER_CAPABILITY.getStorage().writeNBT(MONEY_HOLDER_CAPABILITY, this.holder, null);
    }

    @Override
    public void deserializeNBT(final NBTBase nbt) {
        MONEY_HOLDER_CAPABILITY.getStorage().readNBT(MONEY_HOLDER_CAPABILITY, this.holder, null, nbt);
    }

    private static final ResourceLocation CAPA_LOC = new ResourceLocation(SocietyMod.MODID, "money");

    @SubscribeEvent
    public static void attachCapability(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer)
            event.addCapability(CAPA_LOC, new MoneyProvider());
    }

    @SubscribeEvent
    public static void copyOnDeath(final PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            final Capability.IStorage<IMoneyHolder> storage = MONEY_HOLDER_CAPABILITY.getStorage();
            final NBTBase nbt = storage.writeNBT(MONEY_HOLDER_CAPABILITY, event.getOriginal().getCapability(MONEY_HOLDER_CAPABILITY, null), null);
            storage.readNBT(MONEY_HOLDER_CAPABILITY, event.getEntityPlayer().getCapability(MONEY_HOLDER_CAPABILITY, null), null, nbt);;
        }
    }

}
