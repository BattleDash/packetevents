/*
 * This file is part of packetevents - https://github.com/retrooper/packetevents
 * Copyright (C) 2021 retrooper and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.retrooper.packetevents.handlers.legacy.early;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.netty.channel.ChannelAbstract;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.protocol.player.UserProfile;
import io.github.retrooper.packetevents.handlers.legacy.PacketDecoderLegacy;
import io.github.retrooper.packetevents.handlers.legacy.PacketEncoderLegacy;
import net.minecraft.util.io.netty.channel.Channel;

public class ServerConnectionInitializerLegacy {
    public static void postInitChannel(Object ch, ConnectionState connectionState) {
        Channel channel = (Channel) ch;
        ChannelAbstract channelAbstract = PacketEvents.getAPI().getNettyManager().wrapChannel(channel);
        User user = new User(channelAbstract, connectionState, null, new UserProfile(null, null));
        PacketEvents.getAPI().getPlayerManager().USERS.put(channelAbstract, user);
        channel.pipeline().addAfter("splitter", PacketEvents.DECODER_NAME, new PacketDecoderLegacy(user));
        //No need to account for ViaVersion, as they don't support 1.7.10
        channel.pipeline().addBefore("encoder", PacketEvents.ENCODER_NAME, new PacketEncoderLegacy(user));
    }

    public static void postDestroyChannel(Object ch) {
        Channel channel = (Channel) ch;
        channel.pipeline().remove(PacketEvents.DECODER_NAME);
        channel.pipeline().remove(PacketEvents.ENCODER_NAME);
    }
}
