package armctec.ProxyMod.tileentity;

import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class BasicTileEntity extends TileEntity
{
    @Override
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
    }

    @Override
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);
        return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tagCompound);
    }

    @Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
    {
        this.readFromNBT(pkt.data);
    }

    @Override
    public void updateEntity()
    {
    }
}
