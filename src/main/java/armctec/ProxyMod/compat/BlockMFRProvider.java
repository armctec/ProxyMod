package armctec.ProxyMod.compat;

import armctec.ProxyMod.block.BlockBasic;
import armctec.ProxyMod.block.BlockTileBasic;
import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.utility.LogHelper;
import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet;
import powercrystals.minefactoryreloaded.api.rednet.RedNetConnectionType;

import java.util.Random;


public class BlockMFRProvider extends BlockTileBasic implements IConnectableRedNet
{
    public static int redstone_power [] = {0,0,0,0,0,0};

    public BlockMFRProvider(int id)
    {
        super(id, Material.rock);
        this.setBlockName(Names.Blocks.BUNDLEDCC);
        this.setBlockTextureName(Names.Blocks.BUNDLEDCC);
        this.setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        LogHelper.debug("createNewTileEntity");
        return new MFRProvider();
    }

    @Override
    public boolean canProvidePower() {
        return false;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        LogHelper.warn("Weak x:"+x+"y:"+y+"z:"+z+"side:"+side);
        //return redstone_power[side];
        return 0;//redstone_power[];
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        LogHelper.warn("Strong x:"+x+"y:"+y+"z:"+z+"side:"+side);
        //return redstone_power[side];
        return 0;//redstone_power;
    }

    @Override
    public int tickRate(World world) {
        return 10;

    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        LogHelper.debug("canConnectRedstone x:" + x + ",y:" + y + ",z:" + z + ",side:" + side);
        return true;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id)
    {
        super.onNeighborBlockChange(world, x, y, z, id);

        ForgeDirection direction;
        int xa, ya, za;

        for(int lado=0;lado<6;lado++) {
            direction = ForgeDirection.getOrientation(lado);
            xa = x + direction.offsetX;
            ya = y + direction.offsetY;
            za = z + direction.offsetZ;

            redstone_power[lado] = world.getIndirectPowerLevelTo(xa, ya, za, lado);
        }
        
        world.scheduleBlockUpdate(x,y,z,this.blockID,this.tickRate(world));
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rtick) {
        super.updateTick(world, x, y, z, rtick);
        notifyAllNeighbor(world, x, y, z);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        notifyAllNeighbor(world,x,y,z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int metadata) {
        super.breakBlock(world, x, y, z, id, metadata);
        notifyAllNeighbor(world,x,y,z);
    }

    private void notifyAllNeighbor(World world, int x, int y, int z)
    {
        ForgeDirection direction;
        int xa,ya,za,idblock;

        for(int lado=0;lado<6;lado++) {
            direction = ForgeDirection.getOrientation(lado);
            xa = x + direction.offsetX;
            ya = y + direction.offsetY;
            za = z + direction.offsetZ;

            if(world.isAirBlock(x,y,z)==true)
                continue;

            world.notifyBlockOfNeighborChange(xa, ya, za, blockID);

            idblock = world.getBlockId(x,y,z);
            if(idblock==Block.torchRedstoneIdle.blockID ||
                    idblock==Block.torchRedstoneActive.blockID ||
                    idblock==Block.redstoneComparatorActive.blockID ||
                    idblock==Block.redstoneComparatorIdle.blockID ||
                    idblock==Block.redstoneWire.blockID)
                continue;

            if(direction != ForgeDirection.UP)
                world.notifyBlockOfNeighborChange(xa, ya - 1, za, blockID);

            if(direction != ForgeDirection.DOWN)
                world.notifyBlockOfNeighborChange(xa, ya + 1, za, blockID);

            if(direction != ForgeDirection.EAST)
                world.notifyBlockOfNeighborChange(xa + 1, ya, za, blockID);

            if(direction != ForgeDirection.WEST)
                world.notifyBlockOfNeighborChange(xa - 1, ya, za, blockID);

            if(direction != ForgeDirection.NORTH)
                world.notifyBlockOfNeighborChange(xa, ya, za + 1, blockID);

            if(direction != ForgeDirection.SOUTH)
                world.notifyBlockOfNeighborChange(xa, ya, za - 1, blockID);
        }
    }

    @Override
    public boolean shouldCheckWeakPower(World world, int x, int y, int z, int side) {
        return false;
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }

    @Override
    public RedNetConnectionType getConnectionType(World world, int x, int y, int z, ForgeDirection side) {
        //LogHelper.debug("Block:getConnectionType x:"+x+",y:"+y+",z:"+z+",side:"+side);
        return RedNetConnectionType.CableAll;
    }

    @Override
    public int[] getOutputValues(World world, int x, int y, int z, ForgeDirection side)
    {
        int power[] = new int[16];
        LogHelper.debug("Block:getOutputValues x:"+x+",y:"+y+",z:"+z+",side:"+side);

        for(int lado=0;lado<6;lado++) {
            power[lado] = redstone_power[lado];
            LogHelper.debug("subnet:" + power[lado]);
        }

        return power;
    }

    @Override
    public int getOutputValue(World world, int x, int y, int z, ForgeDirection side, int subnet)
    {
        LogHelper.debug("Block:getOutputValue x:"+x+",y:"+y+",z:"+z+",side:"+side+",subnet:"+subnet);

        if(subnet>=0 && subnet<6) {
            LogHelper.debug("Block:getOutputValue:Subnet:" + redstone_power[subnet]);
            return redstone_power[subnet];
        }
        return 0;
    }

    @Override
    public void onInputsChanged(World world, int x, int y, int z, ForgeDirection side, int[] inputValues)
    {
        LogHelper.debug("Block:onInputsChanged x:"+x+",y:"+y+",z:"+z+",side:"+side);
        for(int i=0;i<16;i++)
            LogHelper.debug("Subnet:"+inputValues[i]);
    }

    @Override
    public void onInputChanged(World world, int x, int y, int z, ForgeDirection side, int inputValue)
    {
        LogHelper.debug("Block:onInputChanged x:"+x+",y:"+y+",z:"+z+",side:"+side+",value:"+inputValue);
    }
}
