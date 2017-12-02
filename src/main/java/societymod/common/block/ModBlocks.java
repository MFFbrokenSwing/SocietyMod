package societymod.common.block;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import societymod.common.SocietyMod;

@ObjectHolder(SocietyMod.MODID)
@EventBusSubscriber(modid = SocietyMod.MODID)
public class ModBlocks {
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        
    }

}
