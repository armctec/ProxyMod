package armctec.ProxyMod.integration;

import armctec.ProxyMod.tileentity.BasicTileEntity;
import armctec.ProxyMod.utility.LogHelper;
import dan200.computercraft.api.redstone.IBundledRedstoneProvider;
import net.minecraft.world.World;

public class CCProvider extends BasicTileEntity implements IBundledRedstoneProvider
{
    static int redstonepower[]={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    @Override
    public int getBundledRedstoneOutput(World world, int x, int y, int z, int side)
    {
        int out = 0, i = 0;

        for(i=0;i<16;i++) {
            LogHelper.debug("CC:getBundledRedstoneOutput - subnet:" + i +",value:"+redstonepower[i]);

            if (redstonepower[i] != 0)
                out |= (1 << i);
        }

        LogHelper.debug("CC:getBundledRedstoneOutput - " + out);

        return out;
    }

    public void setRedstonepower(int subnet, int value)
    {
        if(subnet<0 || subnet>15)
            return;

        if(value>15)
            value=15;

        if(value<0)
            value=0;

        redstonepower[subnet]=value;

        LogHelper.debug("CC:setRedstonepower - subnet:" + subnet +",value:"+value);
    }
}
