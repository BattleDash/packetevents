package io.github.retrooper.packetevents.wrapper.status.client;

import io.github.retrooper.packetevents.utils.netty.bytebuf.ByteBufAbstract;
import io.github.retrooper.packetevents.utils.player.ClientVersion;
import io.github.retrooper.packetevents.wrapper.PacketWrapper;

public class WrapperStatusClientPing extends PacketWrapper {
    private final long time;

    public WrapperStatusClientPing(ByteBufAbstract byteBuf) {
        super(byteBuf);

        this.time = readLong();
    }

    public long getTime(){
        return this.time;
    }
}