package societymod.server.command.balance;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import societymod.server.money.IMoneySystem;
import societymod.server.money.MoneySystemsManager;

public class CommandBalanceAdd extends CommandBase {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getUsage(final ICommandSender sender) {
        return "add <player> <amount>";
    }

    @Override
    public void execute(final MinecraftServer server, final ICommandSender sender, final String[] args) throws CommandException {
        if (args.length < 2)
            throw new WrongUsageException(getUsage(sender));
        float amount = (float) parseDouble(args[1]);
        final EntityPlayerMP player = getPlayer(server, sender, args[0]);
        final IMoneySystem sys = MoneySystemsManager.getSelectedMoneySystemOrDefault();
        amount = sys.getHolderFor(player).pushToBalance(amount, false);
        final TextComponentTranslation component = new TextComponentTranslation("command.balance.set.success", player.getName(), String.valueOf(amount), sys.getMoneyName());
        component.setStyle(new Style().setColor(TextFormatting.GREEN));
        sender.sendMessage(component);
    }

    @Override
    public boolean checkPermission(final MinecraftServer server, final ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(final MinecraftServer server, final ICommandSender sender, final String[] args, final BlockPos targetPos) {
        if (args.length == 1)
            return server.getPlayerList().getPlayers().stream().map(player -> player.getName()).filter(name -> name.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        return Lists.newArrayList();
    }

}

