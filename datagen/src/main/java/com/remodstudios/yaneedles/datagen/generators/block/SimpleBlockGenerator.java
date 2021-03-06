package com.remodstudios.yaneedles.datagen.generators.block;

import com.remodstudios.yaneedles.datagen.ResourceGenerator;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.util.IdUtils;
import net.minecraft.util.Identifier;

public class SimpleBlockGenerator implements ResourceGenerator {

    protected void generateBlockStates(ArtificeResourcePack.ClientResourcePackBuilder pack, Identifier id) {
        pack.addBlockState(id, state -> state
            .variant("", variant -> variant
                .model(getBlockSubPath(id))
            )
        );
    }

    protected void generateModels(ArtificeResourcePack.ClientResourcePackBuilder pack, Identifier id) {
        pack.addBlockModel(id, model -> model
            .parent(new Identifier("block/cube_all"))
            .texture("all", getBlockSubPath(id))
        );
    }

    protected void generateLootTable(ArtificeResourcePack.ServerResourcePackBuilder pack, Identifier id) {
        pack.addLootTable(getBlocksSubPath(id), loot -> loot
            .type(new Identifier("block"))
            .pool(pool -> pool
                .rolls(1)
                .bonusRolls(0f)
                .entry(entry -> entry
                    .type(new Identifier("item"))
                    .name(id)
                )
                .condition(new Identifier("survives_explosion"), c -> {})
            )
        );
    }

    @Override
    public void generateAssets(ArtificeResourcePack.ClientResourcePackBuilder pack, Identifier id) {
        this.generateBlockStates(pack, id);
        this.generateModels(pack, id);
    }

    @Override
    public void generateData(ArtificeResourcePack.ServerResourcePackBuilder pack, Identifier id) {
        this.generateLootTable(pack, id);
    }

    protected static Identifier getBlockSubPath(Identifier blockId) {
        return IdUtils.wrapPath("block/", blockId);
    }
    protected static Identifier getBlockSubPath(Identifier blockId, String suffix) {
        return IdUtils.wrapPath("block/", blockId, suffix);
    }
    protected static Identifier getBlocksSubPath(Identifier blockId) {
        return IdUtils.wrapPath("blocks/", blockId);
    }
}
