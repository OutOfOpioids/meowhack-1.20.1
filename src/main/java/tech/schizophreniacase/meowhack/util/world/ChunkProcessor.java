/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package tech.schizophreniacase.meowhack.util.world;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import tech.schizophreniacase.meowhack.Meowhack;
import tech.schizophreniacase.meowhack.event.bus.Subscribe;
import tech.schizophreniacase.meowhack.event.events.ReceivePacketEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;
import static tech.schizophreniacase.meowhack.util.Wrapper.nullCheck;

public class ChunkProcessor {
    private ExecutorService executorService;

    private int threadCount;
    private BiConsumer<ChunkPos, WorldChunk> loadConsumer;
    private BiConsumer<ChunkPos, WorldChunk> unloadConsumer;
    private BiConsumer<BlockPos, BlockState> updateConsumer;

    public ChunkProcessor(
            int threadCount,
            BiConsumer<ChunkPos, WorldChunk> loadConsumer,
            BiConsumer<ChunkPos, WorldChunk> unloadConsumer,
            BiConsumer<BlockPos, BlockState> updateConsumer
    ) {
        this.threadCount = threadCount;
        this.loadConsumer = loadConsumer;
        this.unloadConsumer = unloadConsumer;
        this.updateConsumer = updateConsumer;
        Meowhack.INSTANCE.EVENT_BUS.subscribe(this);
    }

    public void start() {
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void stop() {
        executorService.shutdownNow();
        executorService = null;
    }

    public void restartExecutor() {
        stop();
        start();
    }

    public void submitAllLoadedChunks() {
        if (executorService == null) {
            return;
        }

        for (WorldChunk chunk : ChunkUtil.getLoadedChunks()) {
            executorService.submit(() -> loadConsumer.accept(chunk.getPos(), chunk));
        }
    }

    @Subscribe
    public void onReceivePacketEvent(ReceivePacketEvent event) {
        Packet<?> packet = event.getPacket();
        if(updateConsumer != null && packet instanceof BlockUpdateS2CPacket) {
            BlockUpdateS2CPacket blockUpdateS2CPacket = (BlockUpdateS2CPacket) packet;

            executorService.execute(() -> updateConsumer.accept(blockUpdateS2CPacket.getPos(), blockUpdateS2CPacket.getState()));
        }
        else if (updateConsumer != null && packet instanceof ExplosionS2CPacket) {
            ExplosionS2CPacket explosionS2CPacket = (ExplosionS2CPacket) packet;
            for(BlockPos blockPos : explosionS2CPacket.getAffectedBlocks()) {
                executorService.execute(() -> updateConsumer.accept(blockPos, Blocks.AIR.getDefaultState()));
            }
        }
        else if (updateConsumer != null && packet instanceof ChunkDeltaUpdateS2CPacket) {
            ChunkDeltaUpdateS2CPacket chunkDeltaUpdateS2CPacket = (ChunkDeltaUpdateS2CPacket) packet;
            chunkDeltaUpdateS2CPacket.visitUpdates((blockPos, blockState) -> {
                BlockPos immutableBlockPos = blockPos.toImmutable();

                executorService.execute(() -> updateConsumer.accept(immutableBlockPos, blockState));
            });
        }
        else if (loadConsumer != null && packet instanceof ChunkDataS2CPacket) {
            ChunkDataS2CPacket chunkDataS2CPacket = (ChunkDataS2CPacket) packet;

            ChunkPos chunkPos = new ChunkPos(chunkDataS2CPacket.getX(), chunkDataS2CPacket.getZ());
            WorldChunk worldChunk = new WorldChunk(mc.world, chunkPos);
            worldChunk.loadFromPacket(chunkDataS2CPacket.getChunkData().getSectionsDataBuf(), new NbtCompound(), chunkDataS2CPacket.getChunkData().getBlockEntities(chunkDataS2CPacket.getX(), chunkDataS2CPacket.getZ()));

            executorService.execute(() -> loadConsumer.accept(chunkPos, worldChunk));
        }
        else if(unloadConsumer != null && packet instanceof UnloadChunkS2CPacket) {
            UnloadChunkS2CPacket unloadChunkS2CPacket = (UnloadChunkS2CPacket) packet;
            ChunkPos chunkPos = new ChunkPos(unloadChunkS2CPacket.getX(), unloadChunkS2CPacket.getZ());
            WorldChunk worldChunk = mc.world.getChunk(unloadChunkS2CPacket.getX(), unloadChunkS2CPacket.getZ());

            executorService.execute(() -> unloadConsumer.accept(chunkPos, worldChunk));
           }

    }
}
