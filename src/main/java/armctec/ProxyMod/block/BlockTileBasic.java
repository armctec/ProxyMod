package armctec.ProxyMod.block;

import armctec.ProxyMod.creativetab.CreativeTabProxyMod;
import armctec.ProxyMod.reference.Reference;
import armctec.ProxyMod.utility.LogHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTileBasic extends BlockContainer
{
    public BlockTileBasic(int id, Material material) {
        super(id, material);
        this.setCreativeTab(CreativeTabProxyMod.PROXYMOD_TAB);
    }

    public BlockTileBasic(int id)
    {
        super(id, Material.rock);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return null;
    }

    public void setBlockName(String name)
    {
        setUnlocalizedName(name);
    }

    public void setBlockTextureName(String name)
    {
        setTextureName(name);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        world.setBlockTileEntity(x, y, z, this.createNewTileEntity(world));
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int metadata) {
        super.breakBlock(world, x, y, z, id, metadata);
        world.removeBlockTileEntity(x,y,z);
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int id, int event)
    {
        super.onBlockEventReceived(world, x, y, z, id, event);

        LogHelper.debug("onBlockEventReceived");

        TileEntity tileentity = world.getBlockTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(id, event) : false;
    }
}
