package societymod.common.capability.money;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import societymod.common.money.IMoneyHolder;

public class MoneyStorage implements Capability.IStorage<IMoneyHolder> {

    @Override
    public NBTBase writeNBT(final Capability<IMoneyHolder> capability, final IMoneyHolder instance, final EnumFacing side) {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setFloat("balance", instance.getBalance());
        return nbt;
    }

    @Override
    public void readNBT(final Capability<IMoneyHolder> capability, final IMoneyHolder instance, final EnumFacing side, final NBTBase base) {
        if (!(base instanceof NBTTagCompound))
            return;
        final NBTTagCompound nbt = new NBTTagCompound();
        instance.setBalance(nbt.getInteger("balance"));
    }

}
