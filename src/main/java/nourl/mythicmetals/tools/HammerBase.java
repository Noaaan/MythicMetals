package nourl.mythicmetals.tools;

import io.wispforest.owo.ops.WorldOps;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import nourl.mythicmetals.utils.BlockBreaker;

public class HammerBase extends PickaxeBase {

    private int depth;

    public HammerBase(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, int depth) {
        super(material, attackDamage, attackSpeed, settings);
        this.depth = depth;
    }

    @Override
    public boolean postMine(ItemStack hammer, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        if (!miner.isPlayer()) return false;

        var reach = BlockBreaker.getReachDistance((PlayerEntity) miner);

        Vec3d cameraPos = miner.getCameraPosVec(1);
        Vec3d rotation = miner.getRotationVec(1);

        Vec3d combined = cameraPos.add(rotation.x * reach, rotation.y * reach, rotation.z * reach);

        BlockHitResult blockHitResult = world.raycast(new RaycastContext(cameraPos, combined, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, miner));

        var facing = blockHitResult.getSide().getOpposite();
        var blocks = BlockBreaker.findBlocks(facing, pos, depth);
        for (BlockPos blockPos : blocks) {
            if (canBreak(world, blockPos)) {
                WorldOps.breakBlockWithItem(world, blockPos, hammer);
            }
        }
        return super.postMine(hammer, world, state, pos, miner);
    }

    public boolean canBreak(BlockView view, BlockPos pos) {
        return super.isSuitableFor(view.getBlockState(pos));
    }
}
