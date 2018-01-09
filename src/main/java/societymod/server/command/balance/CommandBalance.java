package societymod.server.command.balance;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;
import net.minecraftforge.server.permission.PermissionAPI;
import societymod.common.SocietyMod;

public class CommandBalance extends CommandTreeBase {

    public static final String PERM_NODE = SocietyMod.MODID + ":command.balance";

    public CommandBalance() {
        this.addSubcommand(new CommandBalanceSet());
        this.addSubcommand(new CommandBalanceGet());
        this.addSubcommand(new CommandBalanceAdd());
    }

    @Override
    public String getName() {
        return "balance";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/balance";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        if (sender instanceof EntityPlayer)
            return PermissionAPI.hasPermission((EntityPlayer) sender, PERM_NODE);
        return super.checkPermission(server, sender);
    }

}
