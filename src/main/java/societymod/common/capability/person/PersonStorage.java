package societymod.common.capability.person;

import java.util.ArrayList;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants.NBT;

public class PersonStorage implements Capability.IStorage<IPerson> {

    @Override
    public NBTBase writeNBT(final Capability<IPerson> capability, final IPerson instance, final EnumFacing side) {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("openedGuiMenu", instance.hasOpenedGuiMenu());
        final NBTTagList societies = new NBTTagList();
        for (final String id : instance.getSocietiesIds())
            societies.appendTag(new NBTTagString(id));
        nbt.setTag("societies", societies);
        return nbt;
    }

    @Override
    public void readNBT(final Capability<IPerson> capability, final IPerson instance, final EnumFacing side, final NBTBase nbtBase) {
        if (!(nbtBase instanceof NBTTagCompound))
            return;
        final NBTTagCompound nbt = (NBTTagCompound) nbtBase;
        instance.setOpenedGuiMenu(nbt.getBoolean("openedGuiMenu"));
        final NBTTagList societies = nbt.getTagList("societies", NBT.TAG_STRING);
        final ArrayList<String> societiesIds = new ArrayList<>();
        societies.forEach(tag -> societiesIds.add(((NBTTagString) tag).getString()));
    }

}
