package com.feed_the_beast.ftbu.cmd.chunks;

import com.feed_the_beast.ftbl.api.IForgePlayer;
import com.feed_the_beast.ftbl.lib.cmd.CommandLM;
import com.feed_the_beast.ftbl.lib.math.ChunkDimPos;
import com.feed_the_beast.ftbl.lib.math.EntityDimPos;
import com.feed_the_beast.ftbu.FTBLibIntegration;
import com.feed_the_beast.ftbu.FTBUNotifications;
import com.feed_the_beast.ftbu.world.FTBUUniverseData;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

/**
 * Created by LatvianModder on 27.06.2016.
 */
public class CmdUnload extends CommandLM
{
    @Override
    public String getCommandName()
    {
        return "unload";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        EntityPlayerMP player = getCommandSenderAsPlayer(sender);
        IForgePlayer p = getForgePlayer(player);
        ChunkDimPos pos = new EntityDimPos(player).toBlockDimPos().toChunkPos();

        if(FTBUUniverseData.setLoaded(p, pos, false))
        {
            FTBLibIntegration.API.sendNotification(player, FTBUNotifications.CHUNK_UNLOADED);
            CmdChunks.updateChunk(player, pos);
        }
        else
        {
            FTBLibIntegration.API.sendNotification(player, FTBUNotifications.CANT_MODIFY_CHUNK);
        }
    }
}