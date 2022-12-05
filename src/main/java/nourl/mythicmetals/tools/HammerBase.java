package nourl.mythicmetals.tools;

import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class HammerBase extends PickaxeItem {

    private final int depth;

    public HammerBase(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, int depth) {
        super(material, attackDamage, attackSpeed, settings);
        this.depth = depth;
    }

    public boolean canBreak(BlockView view, BlockPos pos) {
        return super.isSuitableFor(view.getBlockState(pos));
    }

    public int getDepth() {
        return depth;
    }
}
