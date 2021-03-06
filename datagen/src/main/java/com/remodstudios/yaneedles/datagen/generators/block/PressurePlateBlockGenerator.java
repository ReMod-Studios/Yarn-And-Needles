package com.remodstudios.yaneedles.datagen.generators.block;

import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.util.IdUtils;
import net.minecraft.util.Identifier;

import java.util.Map;

public class PressurePlateBlockGenerator extends AbstractParentedBlockGenerator {

    public PressurePlateBlockGenerator(Map<String, String> arguments) {
        super(arguments);
    }
    public PressurePlateBlockGenerator(Identifier baseBlockId) {
        super(baseBlockId);
    }

    @Override
    protected void generateBlockStates(ArtificeResourcePack.ClientResourcePackBuilder rrp, Identifier id) {
        Identifier blockPath = getBlockSubPath(id);

        rrp.addBlockState(id, state -> state
            .variant("powered=false", v -> v.model(blockPath))
            .variant("powered=true", v -> v.model(IdUtils.wrapPath(blockPath, "_down")))
        );
    }

    @Override
    protected void generateModels(ArtificeResourcePack.ClientResourcePackBuilder pack, Identifier id) {
        pack.addBlockModel(id, model -> model
            .parent(new Identifier("block/pressure_plate_up"))
            .texture("texture", baseBlockId)
        );
        pack.addBlockModel(IdUtils.wrapPath(id, "_down"), model -> model
            .parent(new Identifier("block/pressure_plate_down"))
                .texture("texture", baseBlockId)
        );
    }

}
