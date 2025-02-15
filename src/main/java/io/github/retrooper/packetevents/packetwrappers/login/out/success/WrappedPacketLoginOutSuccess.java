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

package io.github.retrooper.packetevents.packetwrappers.login.out.success;

import io.github.retrooper.packetevents.packettype.PacketTypeClasses;
import io.github.retrooper.packetevents.packetwrappers.NMSPacket;
import io.github.retrooper.packetevents.packetwrappers.WrappedPacket;
import io.github.retrooper.packetevents.packetwrappers.api.SendableWrapper;
import io.github.retrooper.packetevents.utils.gameprofile.GameProfileUtil;
import io.github.retrooper.packetevents.utils.gameprofile.WrappedGameProfile;
import io.github.retrooper.packetevents.utils.nms.NMSUtils;

import java.lang.reflect.Constructor;

public class WrappedPacketLoginOutSuccess extends WrappedPacket implements SendableWrapper {
    private static Constructor<?> packetConstructor;
    private WrappedGameProfile wrappedGameProfile;

    public WrappedPacketLoginOutSuccess(final NMSPacket packet) {
        super(packet);
    }

    public WrappedPacketLoginOutSuccess(final WrappedGameProfile wrappedGameProfile) {
        this.wrappedGameProfile = wrappedGameProfile;
    }

    @Override
    protected void load() {
        try {
            packetConstructor = PacketTypeClasses.Login.Server.SUCCESS.getConstructors()[1];
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    //TODO Support property reading in wrapped game profile
    public WrappedGameProfile getGameProfile() {
        if (packet != null) {
            return GameProfileUtil.getWrappedGameProfile(readObject(0, NMSUtils.gameProfileClass));
        } else {
            return this.wrappedGameProfile;
        }
    }

    //TODO Support writing property in wrapped game profile
    public void setGameProfile(final WrappedGameProfile wrappedGameProfile) {
        if (packet != null) {
            final Object gameProfile = GameProfileUtil.getGameProfile(wrappedGameProfile.getId(), wrappedGameProfile.getName());
            write(NMSUtils.gameProfileClass, 0, gameProfile);
        } else {
            this.wrappedGameProfile = wrappedGameProfile;
        }
    }

    @Override
    public Object asNMSPacket() throws Exception {
        WrappedGameProfile gp = getGameProfile();
        //TODO Support writing property in wrapped game profile
        return packetConstructor.newInstance(GameProfileUtil.getGameProfile(gp.getId(), gp.getName()));
    }

    @Override
    public boolean isSupported() {
        return PacketTypeClasses.Login.Server.SUCCESS != null;
    }
}
