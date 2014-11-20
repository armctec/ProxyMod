package armctec.ProxyMod.block;

import armctec.ProxyMod.creativetab.CreativeTabProxyMod;
import armctec.ProxyMod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockBasic extends Block
{
    public BlockBasic(int id, Material material)
    {
        super(id, material);
        this.setCreativeTab(CreativeTabProxyMod.PROXYMOD_TAB);
    }

    public BlockBasic(int id)
    {
        this(id, Material.rock);
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
    public void registerBlockIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
