package armctec.ProxyMod.compat;

import armctec.ProxyMod.tileentity.BasicTileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet;
import powercrystals.minefactoryreloaded.api.rednet.RedNetConnectionType;

public class MFRProvider extends BasicTileEntity implements IConnectableRedNet {
    @Override
    public RedNetConnectionType getConnectionType(World world, int x, int y, int z, ForgeDirection side)
    {
        return RedNetConnectionType.CableAll;
    }

    @Override
    public int[] getOutputValues(World world, int x, int y, int z, ForgeDirection side)
    {
        return new int[0];
    }

    @Override
    public int getOutputValue(World world, int x, int y, int z, ForgeDirection side, int subnet) {
        return 0;
    }

    @Override
    public void onInputsChanged(World world, int x, int y, int z, ForgeDirection side, int[] inputValues) {

    }

    @Override
    public void onInputChanged(World world, int x, int y, int z, ForgeDirection side, int inputValue) {

    }
}
