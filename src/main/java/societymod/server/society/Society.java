package societymod.server.society;

import java.util.UUID;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants.NBT;

public class Society {

    private final String id;
    private final String name;
    private final UUID[] owners;

    public Society(final String id, final String name, final UUID... owners) {
        this.id = id;
        this.name = name;
        this.owners = owners;
    }

    public Society(final NBTTagCompound nbt) {
        this.id = nbt.getString("id");
        this.name = nbt.getString("name");

        final NBTTagList owners = nbt.getTagList("owners", NBT.TAG_COMPOUND);
        this.owners = new UUID[owners.tagCount()];
        for (int i = 0; i < owners.tagCount(); i++)
            this.owners[i] = owners.getCompoundTagAt(i).getUniqueId("uuid");
    }

    public NBTTagCompound toNBT() {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("id", this.id);
        nbt.setString("name", this.name);

        final NBTTagList owners = new NBTTagList();
        for (final UUID owner : this.owners) {
            final NBTTagCompound uuid = new NBTTagCompound();
            uuid.setUniqueId("uuid", owner);
            owners.appendTag(uuid);
        }
        nbt.setTag("owners", owners);
        return nbt;
    }

}
