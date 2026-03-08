    package net.daplow.mob_revamp.worldgen.structures;

    import com.mojang.serialization.Codec;
    import com.mojang.serialization.codecs.RecordCodecBuilder;
    import net.daplow.mob_revamp.worldgen.ModStructures;
    import net.minecraft.block.BlockState;
    import net.minecraft.block.Blocks;
    import net.minecraft.structure.*;
    import net.minecraft.structure.pool.StructurePool;
    import net.minecraft.structure.pool.StructurePoolBasedGenerator;
    import net.minecraft.tag.BiomeTags;
    import net.minecraft.util.BlockRotation;
    import net.minecraft.util.Identifier;
    import net.minecraft.util.math.BlockBox;
    import net.minecraft.util.math.BlockPos;
    import net.minecraft.util.math.ChunkPos;
    import net.minecraft.util.registry.DynamicRegistryManager;
    import net.minecraft.util.registry.RegistryEntry;
    import net.minecraft.world.Heightmap;
    import net.minecraft.world.biome.Biome;
    import net.minecraft.world.gen.HeightContext;
    import net.minecraft.world.gen.chunk.ChunkGenerator;
    import net.minecraft.world.gen.chunk.VerticalBlockSample;
    import net.minecraft.world.gen.feature.DefaultFeatureConfig;
    import net.minecraft.world.gen.heightprovider.HeightProvider;
    import net.minecraft.world.gen.structure.Structure;
    import net.minecraft.world.gen.structure.StructureType;

    import java.util.Optional;

    import static net.daplow.mob_revamp.DaplowMain.MOD_ID;


    public class PiglinMineStructure extends Structure {

        public static final Codec<PiglinMineStructure> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Config.CODEC.forGetter(s -> s.config),
                StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter(s -> s.startPool),
                Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(s -> s.startJigsawName),
                Codec.intRange(0, 7).fieldOf("size").forGetter(s -> s.size),
                HeightProvider.CODEC.fieldOf("start_height").forGetter(s -> s.startHeight),
                Codec.BOOL.fieldOf("use_expansion_hack").forGetter(s -> s.useExpansionHack),
                Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(s -> s.projectStartToHeightmap),
                Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(s -> s.maxDistanceFromCenter)
        ).apply(instance, PiglinMineStructure::new));

        private final RegistryEntry<StructurePool> startPool;
        private final Optional<Identifier> startJigsawName;
        private final int size;
        private final HeightProvider startHeight;
        private final boolean useExpansionHack;
        private final Optional<Heightmap.Type> projectStartToHeightmap;
        private final int maxDistanceFromCenter;

        public PiglinMineStructure(Config config,
                                   RegistryEntry<StructurePool> startPool,
                                   Optional<Identifier> startJigsawName,
                                   int size,
                                   HeightProvider startHeight,
                                   boolean useExpansionHack,
                                   Optional<Heightmap.Type> projectStartToHeightmap,
                                   int maxDistanceFromCenter) {
            super(config);
            this.startPool = startPool;
            this.startJigsawName = startJigsawName;
            this.size = size;
            this.startHeight = startHeight;
            this.useExpansionHack = useExpansionHack;
            this.projectStartToHeightmap = projectStartToHeightmap;
            this.maxDistanceFromCenter = maxDistanceFromCenter;
        }


        @Override
        public Optional<StructurePosition> getStructurePosition(Context context) {

            ChunkPos chunkPos = context.chunkPos();
            int x = chunkPos.getCenterX();
            int z = chunkPos.getCenterZ();

            VerticalBlockSample column = context.chunkGenerator().getColumnSample(
                    x,
                    z,
                    context.world(),
                    context.noiseConfig()
            );
            // Finding best Y height.
            int bottomY = context.world().getBottomY();
            int flatRadius = 4;
            int minY = Integer.MAX_VALUE;
            int maxY = Integer.MIN_VALUE;

            for (int dx = -flatRadius; dx <= flatRadius; dx++) {
                for (int dz = -flatRadius; dz <= flatRadius; dz++) {
                    int checkX = x + dx;
                    int checkZ = z + dz;

                    int checkY = 100;
                    while (checkY > bottomY + 10) {
                        BlockState state = column.getState(checkY);
                        if (!state.isAir() && !state.isOf(Blocks.BEDROCK) && state.getFluidState().isEmpty()) {
                            break;
                        }
                        checkY--;
                    }

                    minY = Math.min(minY, checkY);
                    maxY = Math.max(maxY, checkY);
                }
            }

            if (maxY - minY > 3) {
                //System.out.println("PiglinMineStructure SKIPPED in chunk " + chunkPos + " (terrain too uneven)");
                return Optional.empty();
            }

            int surfaceY = (minY + maxY) / 2;
            BlockPos pos = new BlockPos(x, surfaceY + 1, z);

            StructureTemplateManager manager = context.structureTemplateManager();
            StructureTemplate template = manager.getTemplateOrBlank(new Identifier(MOD_ID, "piglin_mine"));
            if (template == null) {
                //System.out.println("PiglinMineStructure SKIPPED in chunk " + chunkPos + " (template missing)");
                return Optional.empty();
            }

            //System.out.println("PiglinMineStructure WILL spawn in chunk " + chunkPos + " at " + pos);

            return Optional.of(new StructurePosition(pos, collector -> {
                collector.addPiece(
                        new PiglinMinePiece(
                                manager,
                                new Identifier(MOD_ID, "piglin_mine"),
                                pos,
                                BlockRotation.NONE,
                                new StructurePlacementData()
                        )
                );
            }));
        }


        /*

        @Override
        public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context) {
            ChunkPos chunkPos = context.chunkPos();
            int y = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));
            BlockPos pos = new BlockPos(chunkPos.getStartX(), y, chunkPos.getStartZ());
            return StructurePoolBasedGenerator.generate(
                    context,
                    this.startPool,
                    this.startJigsawName,
                    this.size,
                    pos,
                    this.useExpansionHack,
                    this.projectStartToHeightmap,
                    this.maxDistanceFromCenter
            );
        }

         */







        @Override
        public StructureType<?> getType() {
            return ModStructures.PIGLIN_MINE; // your custom type
        }
    }
