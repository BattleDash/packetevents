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

package io.github.retrooper.packetevents.utils.netty.buffer;

import com.github.retrooper.packetevents.netty.buffer.ByteBufAbstract;
import com.github.retrooper.packetevents.netty.buffer.ByteBufAllocatorAbstract;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufAllocatorModern implements ByteBufAllocatorAbstract {
    private final ByteBufAllocator byteBufAllocator;

    public ByteBufAllocatorModern(Object rawByteBufAllocator) {
        this.byteBufAllocator = (ByteBufAllocator) rawByteBufAllocator;
    }

    @Override
    public Object rawByteBufAllocator() {
        return byteBufAllocator;
    }

    @Override
    public ByteBufAbstract buffer() {
        return new ByteBufModern(byteBufAllocator.buffer());
    }

    @Override
    public ByteBufAbstract buffer(int initialCapacity) {
        return new ByteBufModern(byteBufAllocator.buffer(initialCapacity));
    }

    @Override
    public ByteBufAbstract buffer(int initialCapacity, int maxCapacity) {
        return new ByteBufModern(byteBufAllocator.buffer(initialCapacity, maxCapacity));
    }

    @Override
    public ByteBufAbstract ioBuffer() {
        return new ByteBufModern(byteBufAllocator.ioBuffer());
    }

    @Override
    public ByteBufAbstract ioBuffer(int initialCapacity) {
        return new ByteBufModern(byteBufAllocator.ioBuffer(initialCapacity));
    }

    @Override
    public ByteBufAbstract ioBuffer(int initialCapacity, int maxCapacity) {
        return new ByteBufModern(byteBufAllocator.ioBuffer(initialCapacity, maxCapacity));
    }

    @Override
    public ByteBufAbstract heapBuffer() {
        return new ByteBufModern(byteBufAllocator.heapBuffer());
    }

    @Override
    public ByteBufAbstract heapBuffer(int initialCapacity) {
        return new ByteBufModern(byteBufAllocator.heapBuffer(initialCapacity));
    }

    @Override
    public ByteBufAbstract heapBuffer(int initialCapacity, int maxCapacity) {
        return new ByteBufModern(byteBufAllocator.heapBuffer(initialCapacity, maxCapacity));
    }

    @Override
    public ByteBufAbstract directBuffer() {
        return new ByteBufModern(byteBufAllocator.directBuffer());
    }

    @Override
    public ByteBufAbstract directBuffer(int initialCapacity) {
        return new ByteBufModern(byteBufAllocator.directBuffer(initialCapacity));
    }

    @Override
    public ByteBufAbstract directBuffer(int initialCapacity, int maxCapacity) {
        return new ByteBufModern(byteBufAllocator.directBuffer(initialCapacity, maxCapacity));
    }

    @Override
    public ByteBufAbstract compositeBuffer() {
        return new ByteBufModern(byteBufAllocator.compositeBuffer());
    }

    @Override
    public ByteBufAbstract compositeBuffer(int maxNumComponents) {
        return new ByteBufModern(byteBufAllocator.compositeBuffer(maxNumComponents));
    }

    @Override
    public ByteBufAbstract compositeHeapBuffer() {
        return new ByteBufModern(byteBufAllocator.compositeHeapBuffer());
    }

    @Override
    public ByteBufAbstract compositeHeapBuffer(int maxNumComponents) {
        return new ByteBufModern(byteBufAllocator.compositeHeapBuffer(maxNumComponents));
    }

    @Override
    public ByteBufAbstract compositeDirectBuffer() {
        return new ByteBufModern(byteBufAllocator.compositeDirectBuffer());
    }

    @Override
    public ByteBufAbstract compositeDirectBuffer(int maxNumComponents) {
        return new ByteBufModern(byteBufAllocator.compositeDirectBuffer(maxNumComponents));
    }

    @Override
    public boolean isDirectBufferPooled() {
        return byteBufAllocator.isDirectBufferPooled();
    }
}
