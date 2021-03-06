package com.remodstudios.yaneedles.datagen.generators.block;

import com.google.gson.JsonObject;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.util.IdUtils;
import net.minecraft.util.Identifier;

import java.util.Map;

public class SlabBlockGenerator extends AbstractParentedBlockGenerator {

    public SlabBlockGenerator(Map<String, String> arguments) {
        super(arguments);
    }
    public SlabBlockGenerator(Identifier baseBlockId) {
        super(baseBlockId);
    }

    @Override
    protected void generateBlockStates(ArtificeResourcePack.ClientResourcePackBuilder rrp, Identifier id) {
        Identifier blockPath = getBlockSubPath(id);

        rrp.addBlockState(id, state -> state
            .variant("type=bottom", v -> v.model(blockPath))
            .variant("type=top", v -> v.model(IdUtils.wrapPath(blockPath, "_top")))
            .variant("type=double", v -> v.model(baseBlockId))
        );
    }

    @Override
    protected void generateModels(ArtificeResourcePack.ClientResourcePackBuilder pack, Identifier id) {
        pack.addBlockModel(id, model -> model
            .parent(new Identifier("block/slab"))
            .texture("bottom", baseBlockId)
            .texture("top", baseBlockId)
            .texture("side", baseBlockId)
        );
        pack.addBlockModel(IdUtils.wrapPath(id, "_top"), model -> model
            .parent(new Identifier("block/slab_top"))
            .texture("bottom", baseBlockId)
            .texture("top", baseBlockId)
            .texture("side", baseBlockId)
        );
    }

    @Override
    protected void generateLootTable(ArtificeResourcePack.ServerResourcePackBuilder pack, Identifier id) {
        JsonObject propertyObject = new JsonObject();
        propertyObject.addProperty("type", "double");

        pack.addLootTable(getBlocksSubPath(id), loot -> loot
            .pool(pool -> pool
                .rolls(1)
                .bonusRolls(0f)
                .entry(entry -> entry
                    .type(new Identifier("item"))
                    .name(id)
                    .function(new Identifier("explosion_decay"), f -> {})
                    .function(new Identifier("set_count"), f -> f
                        .condition(new Identifier("block_state_property"), cond -> cond
                            .add("block", id.toString())
                            .add("properties", propertyObject)
                        )
                        .add("count", 2)
                        .add("add", false)
                    )
                )
            )
        );
    }

}
