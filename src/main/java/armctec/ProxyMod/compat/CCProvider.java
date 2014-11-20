package armctec.ProxyMod.compat;

import dan200.computercraft.api.redstone.IBundledRedstoneProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet;

public class CCProvider implements IBundledRedstoneProvider
{
    @Override
    public int getBundledRedstoneOutput(World world, int x, int y, int z, int side) {
        TileEntity inputTE = world.getBlockTileEntity(x, y, z);
        if(inputTE instanceof IConnectableRedNet)
        {
            int out = 0, value = 0;
            IConnectableRedNet inputWire = (IConnectableRedNet)inputTE;
            for(int i = 0; i < 6; i++)
            {
                out = 0;
                for(int j = 0; j < 16; j++)
                {
                    value = inputWire.getOutputValue(world,x,y,z,ForgeDirection.getOrientation(i),j);
                    if (value != 0)
                        out |= (1 << j);
                }
            }
            return out;
        } else return -1;
    }
}
