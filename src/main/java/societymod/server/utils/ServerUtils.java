package societymod.server.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.server.FMLServerHandler;

public class ServerUtils {

    public static MinecraftServer getServer() {
        return FMLServerHandler.instance().getServer();
    }

}
