package mff.brokenswing.societymod.common.block;

import mff.brokenswing.societymod.common.SocietyMod;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(SocietyMod.MODID)
@EventBusSubscriber(modid = SocietyMod.MODID)
public class ModBlocks {
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        
    }

}
