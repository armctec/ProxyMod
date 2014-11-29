package armctec.ProxyMod.compat;

import armctec.ProxyMod.tileentity.BasicTileEntity;
import armctec.ProxyMod.utility.LogHelper;
import dan200.computercraft.api.redstone.IBundledRedstoneProvider;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet;

public class CCProvider extends BasicTileEntity implements IBundledRedstoneProvider
{
    static int redstonepower[]={0,0,0,0,0,0};

    @Override
    public int getBundledRedstoneOutput(World world, int x, int y, int z, int side)
    {
        int out = 0, i = 0;


        for(i=0;i<6;i++)
            if (redstonepower[i] != 0)
                out |= (1 << i);

        LogHelper.debug("CC:getBundledRedstoneOutput - " + out);

        return out;
        /*
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
        } else return -1
        */
    }

    public void setRedstonepower(int side, int value)
    {
        if(side<0 || side>5)
            return;

        if(value>15)
            value=0;

        if(value<0)
            value=0;

        redstonepower[side]=value;

        LogHelper.debug("CC:setRedstonepower - side:" + side +",value:"+value);
    }
}
