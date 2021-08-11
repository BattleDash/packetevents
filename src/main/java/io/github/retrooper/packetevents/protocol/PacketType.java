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

package io.github.retrooper.packetevents.protocol;

import io.github.retrooper.packetevents.protocol.protocols.*;
import io.github.retrooper.packetevents.utils.player.ClientVersion;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;

public final class PacketType {
    public static PacketTypeAbstract getById(PacketSide side, PacketState state, ClientVersion version, int packetID) {
        if (state == null) {
            state = PacketState.HANDSHAKING;
        }
        switch (state) {
            case HANDSHAKING:
                return Handshaking.Client.getById(packetID);
            case STATUS:
                return null;
            case LOGIN:
                return null;
            case PLAY:
                if (side == PacketSide.CLIENT) {
                    return Play.Client.getById(version, packetID);
                }
                else {
                    return null;
                }
            default:
                return null;
        }
    }

    public static class Handshaking {
        public enum Client implements PacketTypeAbstract {
            HANDSHAKE;

            @Nullable
            public static PacketTypeAbstract getById(int packetID) {
                if (packetID == 0) {
                    return HANDSHAKE;
                }
                else {
                    return null;
                }
            }
        }
    }

    public static class Status {
        public enum Client implements PacketTypeAbstract {
            REQUEST,
            PING;

            @Nullable
            public static PacketTypeAbstract getById(int packetID) {
                return packetID == 0 ? REQUEST : packetID == 1 ? PING : null;
            }
        }

        public enum Server implements PacketTypeAbstract {
            RESPONSE,
            PONG;

            @Nullable
            public static PacketTypeAbstract getById(int packetID) {
                return packetID == 0 ? RESPONSE : packetID == 1 ? PONG : null;
            }
        }
    }

    public static class Play {
        public enum Client implements PacketTypeAbstract {
            TELEPORT_CONFIRM,
            QUERY_BLOCK_NBT,
            SET_DIFFICULTY,
            CHAT_MESSAGE,
            CLIENT_STATUS,
            CLIENT_SETTINGS,
            TAB_COMPLETE,
            CONFIRM_TRANSACTION,
            CLICK_WINDOW_BUTTON,
            CLICK_WINDOW,
            CLOSE_WINDOW,
            PLUGIN_MESSAGE,
            EDIT_BOOK,
            QUERY_ENTITY_NBT,
            INTERACT_ENTITY,
            GENERATE_STRUCTURE,
            KEEP_ALIVE,
            LOCK_DIFFICULTY,
            PLAYER_POSITION,
            PLAYER_POSITION_AND_ROTATION,
            PLAYER_ROTATION,
            PLAYER_MOVEMENT,
            VEHICLE_MOVE,
            STEER_BOAT,
            PICK_ITEM,
            CRAFT_RECIPE_REQUEST,
            PLAYER_ABILITIES,
            PLAYER_DIGGING,
            ENTITY_ACTION,
            STEER_VEHICLE,
            PONG,
            RECIPE_BOOK_DATA,
            SET_DISPLAYED_RECIPE,
            SET_RECIPE_BOOK_STATE,
            NAME_ITEM,
            RESOURCE_PACK_STATUS,
            ADVANCEMENT_TAB,
            SELECT_TRADE,
            SET_BEACON_EFFECT,
            HELD_ITEM_CHANGE,
            UPDATE_COMMAND_BLOCK,
            UPDATE_COMMAND_BLOCK_MINECART,
            CREATIVE_INVENTORY_ACTION,
            UPDATE_JIGSAW_BLOCK,
            UPDATE_STRUCTURE_BLOCK,
            UPDATE_SIGN,
            ANIMATION,
            SPECTATE,
            PLAYER_BLOCK_PLACEMENT,
            USE_ITEM;

            private int packetID = -1;
            private static final Map<ClientVersion, Map<Integer, Enum<?>>> PACKET_ID_CACHE = new IdentityHashMap<>();

            Client() {
            }

            public boolean isSupported() {
                return packetID != -1;
            }

            public int getPacketID() {
                return packetID;
            }

            @Nullable
            public static PacketTypeAbstract getById(ClientVersion version, int packetID) {
                Map<Integer, Enum<?>> innerMap = PACKET_ID_CACHE.get(version);
                if (innerMap != null) {
                    Object client = innerMap.get(packetID);
                    if (client instanceof PacketTypeAbstract) {
                        return (PacketTypeAbstract) client;
                    }
                }
                return null;
            }

            private static void loadPacketIDs(ClientVersion version, Enum<?>[] enumConstants) {
                Map<Integer, Enum<?>> innerMap = new IdentityHashMap<>();
                for (int i = 0; i < enumConstants.length; i++) {
                    Client.valueOf(enumConstants[i].name()).packetID = i;
                    innerMap.put(i, Client.valueOf(enumConstants[i].name()));
                }
                PACKET_ID_CACHE.put(version, innerMap);
            }

            public static void load() {
                loadPacketIDs(ClientVersion.v_1_7_10, PacketType_1_7_10.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_8, PacketType_1_8.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_9, PacketType_1_9.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_9_1, PacketType_1_9.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_9_2, PacketType_1_9.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_9_3, PacketType_1_9.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_10, PacketType_1_9.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_11, PacketType_1_9.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_11_1, PacketType_1_9.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_12, PacketType_1_12.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_12_1, PacketType_1_12_1.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_12_2, PacketType_1_12_1.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_13, PacketType_1_13.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_14, PacketType_1_14.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_14_1, PacketType_1_14.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_14_2, PacketType_1_14.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_14_3, PacketType_1_14.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_14_4, PacketType_1_14.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_15, PacketType_1_14.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_15_1, PacketType_1_14.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_15_2, PacketType_1_15_2.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_16, PacketType_1_16.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_16_1, PacketType_1_16.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_16_2, PacketType_1_16_2.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_16_3, PacketType_1_16_2.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_16_4, PacketType_1_16_2.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_17, PacketType_1_17.Play.Client.values());
                loadPacketIDs(ClientVersion.v_1_17_1, PacketType_1_17.Play.Client.values());
            }
        }
    }
}