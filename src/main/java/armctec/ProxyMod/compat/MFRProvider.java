package armctec.ProxyMod.compat;

import armctec.ProxyMod.tileentity.BasicTileEntity;
import armctec.ProxyMod.utility.LogHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet;
import powercrystals.minefactoryreloaded.api.rednet.IRedNetNetworkContainer;
import powercrystals.minefactoryreloaded.api.rednet.RedNetConnectionType;

public class MFRProvider extends BlockContainer implements IConnectableRedNet
{
    protected MFRProvider(int id, Material material) {
        super(id, material);
    }

    @Override
    public RedNetConnectionType getConnectionType(World world, int x, int y, int z, ForgeDirection side)
    {
        LogHelper.debug("getConnectionType");
        return RedNetConnectionType.CableAll;
    }

    @Override
    public int[] getOutputValues(World world, int x, int y, int z, ForgeDirection side)
    {
        //IRedNetNetworkContainer

        LogHelper.debug("getOutputValues");
        return new int[0];
    }

    @Override
    public int getOutputValue(World world, int x, int y, int z, ForgeDirection side, int subnet)
    {
        LogHelper.debug("getOutputValue");
        return 0;
    }

    @Override
    public void onInputsChanged(World world, int x, int y, int z, ForgeDirection side, int[] inputValues)
    {
        LogHelper.debug("onInputsChanged");
    }

    @Override
    public void onInputChanged(World world, int x, int y, int z, ForgeDirection side, int inputValue)
    {
        LogHelper.debug("onInputChanged");
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {
        LogHelper.debug("createNewTileEntity");
        return null; //new MFRProvider();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        LogHelper.debug("hasTileEntity:"+metadata);
        return true;
    }
}
