package societymod.common.block;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import societymod.common.SocietyMod;
import societymod.common.item.ModItems;
import societymod.common.tile.TileHeadQuarters;

@ObjectHolder(SocietyMod.MODID)
@EventBusSubscriber(modid = SocietyMod.MODID)
public class ModBlocks {

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        Block block;

        block = new BlockHeadQuarters().setRegistryName(SocietyMod.MODID, "head_quarters");
        registry.register(block);
        GameRegistry.registerTileEntity(TileHeadQuarters.class, "head_quarters");
        ModItems.registerItemBlock(block);
    }

}
