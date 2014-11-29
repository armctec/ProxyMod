package armctec.ProxyMod.block;

import armctec.ProxyMod.compat.MFRProvider;
import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.block.BlockBasic;
import armctec.ProxyMod.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
/*
public class BlockRednetProxy extends BlockBasic implements ITileEntityProvider
{
    public BlockRednetProxy(int id)
    {
        super(id);
        this.setBlockName(Names.Blocks.REDNETPROXY);
        this.setBlockTextureName(Names.Blocks.REDNETPROXY);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int i, int j)
    {
        LogHelper.debug("breakBlock");
        world.removeBlockTileEntity(x, y, z);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOff, float yOff, float zOff)
    {
        LogHelper.debug("onBlockActivated");
        MFRProvider teTut = (MFRProvider) world.getBlockTileEntity(x, y, z);
        return true;
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        LogHelper.debug("onNeighborBlockChange");
    }
}
*/