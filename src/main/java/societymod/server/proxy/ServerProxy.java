package societymod.server.proxy;

import java.io.File;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;
import societymod.common.config.BaseConfig;
import societymod.common.proxy.CommonProxy;
import societymod.server.command.balance.CommandBalance;
import societymod.server.config.ServerConfig;
import societymod.server.money.MoneySystemSocietyMod;
import societymod.server.money.MoneySystemsManager;

public class ServerProxy extends CommonProxy {

    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        super.preInit(event);
        // Register default money system
        MoneySystemsManager.registerSystem("societymod", new MoneySystemSocietyMod());
    }

    @Override
    protected BaseConfig createConfig(final File file) {
        return new ServerConfig(file);
    }

    @Override
    public void serverStarting(FMLServerStartingEvent event) {
        super.serverStarting(event);
        event.registerServerCommand(new CommandBalance());
    }

    @Override
    public void registerPermissions() {
        super.registerPermissions();
        PermissionAPI.registerNode(CommandBalance.PERM_NODE, DefaultPermissionLevel.OP, "Allows player to use balance command.");
    }

}
