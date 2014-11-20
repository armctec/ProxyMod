package armctec.ProxyMod.block;

import armctec.ProxyMod.compat.MFRProvider;
import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.block.BlockBasic;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRednetProxy extends BlockBasic
{
    public BlockRednetProxy(int id)
    {
        super(id);
        this.setBlockName(Names.Blocks.REDNETPROXY);
        this.setBlockTextureName(Names.Blocks.REDNETPROXY);
        GameRegistry.registerTileEntity(MFRProvider.class,"BlockRednetProxy:TileEntityProxy");
    }

    public void breakBlock(World world, int x, int y, int z, int i, int j)
    {
        world.removeBlockTileEntity(x, y, z);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xOff, float yOff, float zOff)
    {
        MFRProvider teTut = (MFRProvider) world.getBlockTileEntity(x, y, z);
        return true;
    }

    public TileEntity createNewTileEntity(World world)
    {
        return new MFRProvider();
    }
}
