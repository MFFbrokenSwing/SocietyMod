package societymod.common.network;

import java.util.BitSet;
import java.util.HashMap;
import java.util.function.BiFunction;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import societymod.common.SocietyMod;
import societymod.common.network.packet.CPacketOpenedGuiMenu;
import societymod.common.network.packet.SPacketMoney;
import societymod.common.network.packet.SPacketToast;
import societymod.common.network.packet.SPacketUpdatePerson;

public class ModNetwork implements IGuiHandler {

    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(SocietyMod.MODID);

    public static void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(SocietyMod.instance, new ModNetwork());
        NETWORK.registerMessage(SPacketToast.Handler.class, SPacketToast.class, 0, Side.CLIENT);
        NETWORK.registerMessage(SPacketUpdatePerson.Handler.class, SPacketUpdatePerson.class, 1, Side.CLIENT);
        NETWORK.registerMessage(SPacketMoney.Handler.class, SPacketMoney.class, 2, Side.CLIENT);
        NETWORK.registerMessage(CPacketOpenedGuiMenu.Handler.class, CPacketOpenedGuiMenu.class, 3, Side.SERVER);
    }

    // #############################
    // ##### GUI HANDLER STUFF #####
    // #############################

    @Override
    public Object getServerGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        final TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        return CONTAINERS.containsKey(Integer.valueOf(ID)) ? CONTAINERS.get(Integer.valueOf(ID)).apply(player, tile) : null;
    }

    @Override
    public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int x, final int y, final int z) {
        final TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        return GUIS.containsKey(Integer.valueOf(ID)) ? GUIS.get(Integer.valueOf(ID)).apply(player, tile) : null;
    }

    private static final HashMap<Integer, BiFunction<EntityPlayer, TileEntity, Object>> CONTAINERS = new HashMap<>();
    private static final HashMap<Integer, BiFunction<EntityPlayer, TileEntity, Object>> GUIS       = new HashMap<>();
    private static final BitSet                                                         IDS        = new BitSet();

    public static int registerGuiId(final BiFunction<EntityPlayer, TileEntity, Object> guiProvider, final BiFunction<EntityPlayer, TileEntity, Object> containerProvider) {
        final int id = IDS.nextClearBit(0);
        IDS.set(id);
        CONTAINERS.put(Integer.valueOf(id), containerProvider);
        GUIS.put(Integer.valueOf(id), guiProvider);
        return id;
    }

}
