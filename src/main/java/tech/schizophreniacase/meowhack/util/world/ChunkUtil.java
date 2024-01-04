/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package tech.schizophreniacase.meowhack.util.world;

import net.minecraft.world.chunk.WorldChunk;

import java.util.ArrayList;
import java.util.List;

import static tech.schizophreniacase.meowhack.util.Wrapper.mc;

public class ChunkUtil {
    public static List<WorldChunk> getLoadedChunks() {
        List<WorldChunk> chunks = new ArrayList<>();
        int renderDistance = mc.options.getViewDistance().getValue();

        for(int x = -renderDistance; x <= renderDistance; x++) {
            for(int z = -renderDistance; z <= renderDistance; z++) {
                WorldChunk chunk = mc.world.getChunk(x, z);
                if(chunk != null) {
                    chunks.add(chunk);
                }
            }
        }

        return chunks;
    }
}
