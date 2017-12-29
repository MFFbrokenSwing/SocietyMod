package societymod.server.society;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;

public class Society {

    public Society(final String id, final String name, final UUID[] owners) {}

    public Society(final NBTTagCompound nbt) {}

    public NBTTagCompound toNBT() {
        return new NBTTagCompound();
    }

}
