package societymod.common.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

public class PersonStorage implements Capability.IStorage<IPerson> {

    @Override
    public NBTBase writeNBT(final Capability<IPerson> capability, final IPerson instance, final EnumFacing side) {
        return new NBTTagCompound();
    }

    @Override
    public void readNBT(final Capability<IPerson> capability, final IPerson instance, final EnumFacing side, final NBTBase nbt) {}

}
