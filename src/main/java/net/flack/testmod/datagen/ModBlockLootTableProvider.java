package net.flack.testmod.datagen;

import net.flack.testmod.block.ModBlocks;
import net.flack.testmod.block.custom.RadishCropBlock;
import net.flack.testmod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());

        add(ModBlocks.BISMUTH_ORE.get(),
                block -> createOreDrop(ModBlocks.BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get()));

        dropSelf(ModBlocks.BISMUTH_STAIRS.get());
        dropSelf(ModBlocks.BISMUTH_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BISMUTH_BUTTON.get());
        dropSelf(ModBlocks.BISMUTH_FENCE.get());
        dropSelf(ModBlocks.BISMUTH_FENCE_GATE.get());
        dropSelf(ModBlocks.BISMUTH_WALL.get());
        dropSelf(ModBlocks.BISMUTH_TRAPDOOR.get());

        add(ModBlocks.BISMUTH_SLAB.get(), block -> createSlabItemTable(ModBlocks.BISMUTH_SLAB.get()));
        add(ModBlocks.BISMUTH_DOOR.get(), block -> createDoorTable(ModBlocks.BISMUTH_DOOR.get()));

        dropSelf(ModBlocks.BISMUTH_LAMP.get());

        LootItemCondition.Builder lootItemConditionBuilder = LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RADISH_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RadishCropBlock.AGE, 3));
        this.add(ModBlocks.RADISH_CROP.get(), this.createCropDrops(ModBlocks.RADISH_CROP.get(),
                ModItems.RADISH.get(), ModItems.RADISH_SEEDS.get(), lootItemConditionBuilder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
