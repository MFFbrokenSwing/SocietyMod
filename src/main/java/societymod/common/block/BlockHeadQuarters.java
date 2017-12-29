package societymod.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import societymod.client.gui.GuiHeadQuarters;
import societymod.common.SocietyMod;
import societymod.common.container.ContainerEmpty;
import societymod.common.network.ModNetwork;
import societymod.common.tile.TileHeadQuarters;
import societymod.server.society.Society;

/**
 * This {@link Block} is used to create a {@link Society}.
 *
 * @author BrokenSwing
 *
 */
public class BlockHeadQuarters extends BlockContainer {

    public final int GUI_ID;

    public BlockHeadQuarters() {
        super(Material.WOOD);
        GUI_ID = ModNetwork.registerGuiId((player, tile) -> new GuiHeadQuarters(tile), (player, tile) -> new ContainerEmpty(tile.getPos()));
        this.setUnlocalizedName("head_quarters");
        this.setHarvestLevel("axe", 0);
        this.setHardness(4.0f);
        this.setResistance(5.0f);
    }

    @Override
    public boolean onBlockActivated(final World world, final BlockPos pos, final IBlockState state, final EntityPlayer player, final EnumHand hand, final EnumFacing facing, final float hitX,
            final float hitY, final float hitZ) {
        if (!world.isRemote) {
            player.openGui(SocietyMod.instance, this.GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
            return true;
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(final World worldIn, final int meta) {
        return new TileHeadQuarters();
    }

    @Override
    public EnumBlockRenderType getRenderType(final IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

}
