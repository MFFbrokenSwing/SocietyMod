package societymod.common.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import societymod.common.SocietyMod;

@EventBusSubscriber(modid = SocietyMod.MODID)
public class PersonProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IPerson.class)
    public static final Capability<IPerson> PERSON_CAPABILITY = null;

    private final IPerson person;

    public PersonProvider(final EntityPlayer player, final Side side) {
        this.person = new PlayerPerson(player, side);
    }

    @Override
    public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
        return capability == PERSON_CAPABILITY;
    }

    @Override
    public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
        return capability == PERSON_CAPABILITY ? PERSON_CAPABILITY.cast(this.person) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return PERSON_CAPABILITY.getStorage().writeNBT(PERSON_CAPABILITY, this.person, null);
    }

    @Override
    public void deserializeNBT(final NBTBase nbt) {
        PERSON_CAPABILITY.getStorage().readNBT(PERSON_CAPABILITY, this.person, null, nbt);
    }

    private static final ResourceLocation CAPA_LOC = new ResourceLocation(SocietyMod.MODID, "person");

    @SubscribeEvent
    public static void attachCapability(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            System.out.println("Attaching capability");
            event.addCapability(CAPA_LOC, new PersonProvider((EntityPlayer) event.getObject(), event.getObject().world.isRemote ? Side.SERVER : Side.CLIENT));
        }
    }

}
