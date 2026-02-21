    package net.daplow.mob_revamp.worldgen.structures;


    import net.daplow.mob_revamp.worldgen.ModStructures;
    import net.minecraft.block.BlockState;
    import net.minecraft.block.Blocks;
    import net.minecraft.nbt.NbtCompound;
    import net.minecraft.structure.*;
    import net.minecraft.util.BlockRotation;
    import net.minecraft.util.Identifier;
    import net.minecraft.util.math.BlockBox;
    import net.minecraft.util.math.BlockPos;
    import net.minecraft.util.math.ChunkPos;
    import net.minecraft.util.math.random.Random;
    import net.minecraft.world.ServerWorldAccess;
    import net.minecraft.world.StructureWorldAccess;
    import net.minecraft.world.gen.StructureAccessor;
    import net.minecraft.world.gen.chunk.ChunkGenerator;
    import net.minecraft.world.gen.chunk.VerticalBlockSample;

    import java.util.concurrent.atomic.AtomicInteger;

    import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

    public class PiglinMinePiece extends SimpleStructurePiece {

        private static final Identifier DRILL = new Identifier(MOD_ID, "piglin_mine/piglin_mine_drill_1");
        private static final Identifier FURNACE = new Identifier(MOD_ID, "piglin_mine/piglin_mine_furnace_1");
        private static final Identifier LAMP = new Identifier(MOD_ID, "piglin_mine/piglin_mine_lamp");

        private static final Identifier[] EXTRAS = new Identifier[] {
                DRILL,
                FURNACE,
                LAMP
        };

        // NBT constructor (for loading from world)
        public PiglinMinePiece(StructureContext context, NbtCompound nbt) {
            super(
                    ModStructures.PIGLIN_MINE_PIECE,
                    nbt,
                    context.structureTemplateManager(),
                    id -> createPlacementData()
            );
        }


        // Template constructor (manual placement)

        public PiglinMinePiece(
                StructureTemplateManager manager,
                Identifier templateId,
                BlockPos pos,
                BlockRotation rotation,
                StructurePlacementData placementData
        ) {
            super(
                    ModStructures.PIGLIN_MINE_PIECE,
                    0,
                    manager,
                    templateId,
                    templateId.getPath(), // ✅ FIX
                    createPlacementData(rotation),
                    pos
            );
        }
        private static StructurePlacementData createPlacementData() {
            return new StructurePlacementData()
                    .setIgnoreEntities(false);
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation) {
            return new StructurePlacementData()
                    .setRotation(rotation)
                    .setIgnoreEntities(false);
        }


        @Override
        public void generate(
                StructureWorldAccess world,
                StructureAccessor structureAccessor,
                ChunkGenerator chunkGenerator,
                Random random,
                BlockBox boundingBox,
                ChunkPos chunkPos,
                BlockPos pivot
        ) {
            super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pivot);
            System.out.println("YO Piece RAN!!!");

            /*
            StructureTemplateManager manager = ((ServerWorldAccess) world).toServerWorld().getStructureTemplateManager();
            StructureTemplate mainTemplate = manager.getTemplateOrBlank(new Identifier(MOD_ID, "piglin_mine"));

            if (mainTemplate != null) {
                StructurePlacementData placementData = createPlacementData(BlockRotation.NONE);

                // Place template at pivot
                mainTemplate.place(world, pivot, pivot, placementData, random, 2);
            }
*/
            // Let the superclass place the structure first
            supportPlatform(world, random, pivot); // prevent floating

            convertSurroundingNetherrack(world, boundingBox, random, pivot);

            spawnExtras(world, random, pivot);

            System.out.println(chunkPos);
        }


        private void spawnExtras(StructureWorldAccess world, Random random, BlockPos pivot) {

            StructureTemplateManager manager =
                    ((ServerWorldAccess) world).toServerWorld().getStructureTemplateManager();

            java.util.List<BlockPos> usedPositions = new java.util.ArrayList<>();

            spawnSingle(world, manager, random, pivot, DRILL, usedPositions);
            spawnSingle(world, manager, random, pivot, DRILL, usedPositions);
            spawnSingle(world, manager, random, pivot, DRILL, usedPositions);

            spawnSingle(world, manager, random, pivot, FURNACE, usedPositions);

            spawnSingle(world, manager, random, pivot, LAMP, usedPositions);
            spawnSingle(world, manager, random, pivot, LAMP, usedPositions);

        }

        private void supportPlatform(StructureWorldAccess world, Random random, BlockPos pivot) {

            int radius = 4;     // 8x8 platform
            int maxDepth = 8;   // fill downward limit

            for (int x = -radius; x < radius; x++) {
                for (int z = -radius; z < radius; z++) {

                    BlockPos columnTop = pivot.add(x, -1, z);

                    for (int d = 0; d < maxDepth; d++) {

                        BlockPos pos = columnTop.down(d);

                        if (!world.isAir(pos)) break;

                        BlockState state =
                                random.nextFloat() < 0.25f
                                        ? Blocks.NETHER_GOLD_ORE.getDefaultState()
                                        : Blocks.NETHERRACK.getDefaultState();

                        world.setBlockState(pos, state, 2);
                    }
                }
            }
        }

        private void spawnSingle(
                StructureWorldAccess world,
                StructureTemplateManager manager,
                Random random,
                BlockPos pivot,
                Identifier templateId,
                java.util.List<BlockPos> usedPositions
        ) {

            BlockPos finalPos = null;

            // Try up to 10 times to find a valid position
            for (int attempt = 0; attempt < 10; attempt++) {

                int offsetX, offsetZ;

                // avoid center 8x8 platform
                do {
                    offsetX = random.nextBetween(-12, 12);
                    offsetZ = random.nextBetween(-12, 12);
                } while (Math.abs(offsetX) <= 4 && Math.abs(offsetZ) <= 4);

                BlockPos pos = pivot.add(offsetX, 5, offsetZ);
                pos = findGround(world, pos);

                // Check void: must have at least 1 solid block below
                if (!hasGroundBelow(world, pos, 8)) continue;

                // Check distance from other extras
                boolean tooClose = false;
                for (BlockPos used : usedPositions) {
                    if (used.getSquaredDistance(pos) < 36) { // 6-block radius
                        tooClose = true;
                        break;
                    }
                }
                if (tooClose) continue;

                finalPos = pos;
                break;
            }

            // If no valid position found, skip
            if (finalPos == null) {
                System.out.println("Skipped extra " + templateId + ": no valid position found");
                return;
            }

            usedPositions.add(finalPos);

            // Determine rotation
            BlockRotation rotation;
            if (templateId.equals(FURNACE)) {
                // Face the main structure pivot
                int dx = pivot.getX() - finalPos.getX();
                int dz = pivot.getZ() - finalPos.getZ();


                if (Math.abs(dx) > Math.abs(dz)) {
                    rotation = dx > 0 ? BlockRotation.NONE : BlockRotation.CLOCKWISE_180;
                } else {
                    rotation = dz > 0 ? BlockRotation.COUNTERCLOCKWISE_90 : BlockRotation.CLOCKWISE_90;
                }

            } else {
                rotation = BlockRotation.random(random);
            }

            StructurePlacementData data = createPlacementData(rotation);
            StructureTemplate template = manager.getTemplateOrBlank(templateId);

            template.place(world, finalPos, finalPos, data, random, 2);

            System.out.println("Placed extra: " + templateId + " at " + finalPos);

            // Special behavior for drill
            if (templateId.equals(DRILL)) {
                generateDrillGold(world, random, finalPos);
            }
        }



        private void generateDrillGold(StructureWorldAccess world, Random random, BlockPos center) {

            // Always gold directly under drill tip
            BlockPos tip = center.down();

            world.setBlockState(
                    tip,
                    Blocks.NETHER_GOLD_ORE.getDefaultState(),
                    2
            );

            int radius = 2;
            int depth = 2;

            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {

                    if (random.nextFloat() > 0.35f) continue;

                    BlockPos surface = center.add(x, 0, z);

                    for (int d = 0; d < depth; d++) {

                        BlockPos pos = surface.down(d);

                        if (world.getBlockState(pos).isOf(Blocks.NETHERRACK)) {

                            world.setBlockState(
                                    pos,
                                    Blocks.RAW_GOLD_BLOCK.getDefaultState(),
                                    2
                            );

                            break;
                        }

                    }
                }
            }
        }
        private BlockPos findGround(StructureWorldAccess world, BlockPos start) {

            BlockPos.Mutable mutable = start.mutableCopy();

            for (int i = 0; i < 20; i++) { // scan downward max 20 blocks
                if (!world.isAir(mutable.down()) && world.getBlockState(mutable.down()).getMaterial().isSolid()) {
                    return mutable;
                }
                mutable.move(0, -1, 0);
            }

            return start; // fallback
        }
        private boolean hasGroundBelow(StructureWorldAccess world, BlockPos pos, int depth) {
            for (int i = 0; i < depth; i++) {
                BlockPos check = pos.down(i);
                if (!world.isAir(check)) {
                    return true;
                }
            }
            return false; // all air below
        }

        private void convertSurroundingNetherrack(StructureWorldAccess world, BlockBox boundingBox, Random random, BlockPos pivot) {

            int horizontalRadius = 8; // around structure
            int verticalRadius = 5;   // only 5 blocks above/below pivot

            // Horizontal bounds
            int minX = pivot.getX() - horizontalRadius;
            int maxX = pivot.getX() + horizontalRadius;
            int minZ = pivot.getZ() - horizontalRadius;
            int maxZ = pivot.getZ() + horizontalRadius;

            // Vertical bounds: only around pivot Y
            int minY = Math.max(pivot.getY() - verticalRadius, world.getBottomY());
            int maxY = Math.min(pivot.getY() + verticalRadius, world.getTopY());

            for (int x = minX; x <= maxX; x++) {
                for (int z = minZ; z <= maxZ; z++) {

                    // Optional: circular area for natural look
                    double dx = x - (minX + maxX) / 2.0;
                    double dz = z - (minZ + maxZ) / 2.0;
                    if (dx * dx + dz * dz > horizontalRadius * horizontalRadius) continue;

                    for (int y = minY; y <= maxY; y++) {

                        BlockPos pos = new BlockPos(x, y, z);

                        // 50% chance to convert
                        //if (random.nextFloat() > 0.35f) continue;

                        // Only replace Netherrack
                        if ((world.getBlockState(pos).isOf(Blocks.NETHERRACK)) && (random.nextFloat() < 0.35f)) {
                            world.setBlockState(pos, Blocks.NETHER_GOLD_ORE.getDefaultState(), 2);
                            System.out.println("GOLD:" + pos);

                        }



                    }
                }
            }

            System.out.println("Piglin mine gold generation complete at " + boundingBox);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {

        }
    }