package com.feed_the_beast.ftbu.cmd;

import com.feed_the_beast.ftbl.lib.cmd.CommandLM;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CmdKickme extends CommandLM
{
    @Override
    public String getCommandName()
    {
        return "kickme";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.kick.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if(server.isDedicatedServer())
        {
            getCommandSenderAsPlayer(sender).connection.kickPlayerFromServer("You kicked yourself!");
        }
        else
        {
            server.initiateShutdown();
        }
    }
}