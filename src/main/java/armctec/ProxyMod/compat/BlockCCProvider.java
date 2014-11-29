package armctec.ProxyMod.compat;

import armctec.ProxyMod.block.BlockTileBasic;
import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.utility.LogHelper;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.redstone.IBundledRedstoneProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.Random;


public class BlockCCProvider extends BlockTileBasic
{
    //public static int redstone_power [] = {0,0,0,0,0,0};
    public static int redstone_power = 0;

    public BlockCCProvider(int id)
    {
        super(id, Material.rock);
        this.setBlockName(Names.Blocks.BUNDLEDCC);
        this.setBlockTextureName(Names.Blocks.BUNDLEDCC);
        this.setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        LogHelper.debug("createNewTileEntity");
        return new CCProvider();
    }

    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        LogHelper.warn("Weak x:"+x+"y:"+y+"z:"+z+"side:"+side);
        //return redstone_power[side];
        return redstone_power;
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        LogHelper.warn("Strong x:"+x+"y:"+y+"z:"+z+"side:"+side);
        //return redstone_power[side];
        return redstone_power;
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
        //super.onNeighborBlockChange(world, x, y, z, id);

        ForgeDirection direction, directionop;
        TileEntity BlockEntity;
        CCProvider CCEntity = null;
        int xa,ya,za;
        int valor, sideop;

        BlockEntity = world.getBlockTileEntity(x,y,z);
        if(BlockEntity instanceof CCProvider)
            CCEntity = (CCProvider) BlockEntity;

        for(int lado=0;lado<6;lado++)
        {
            direction = ForgeDirection.getOrientation(lado);
            xa = x + direction.offsetX;
            ya = y + direction.offsetY;
            za = z + direction.offsetZ;

            if(CCEntity!=null)
                CCEntity.setRedstonepower(lado,world.getIndirectPowerLevelTo(xa,ya,za,lado));

            if (world.blockHasTileEntity(xa, ya, za) == true) {
                LogHelper.debug("Posicao x:" + xa + ",y:" + ya + ",z:" + za);

                /*
                computer = world.getBlockTileEntity(xa,ya,za);
                LogHelper.debug("Nome:"+computer.getBlockType().getLocalizedName());

                if(computer instanceof IBundledRedstoneProvider)
                    LogHelper.debug("OK");
                */
                directionop = direction.getOpposite();
                sideop = directionop.ordinal();
                valor = ComputerCraftAPI.getBundledRedstoneOutput(world, xa, ya, za, sideop);
                if (valor != -1) {
                    LogHelper.debug("Valor do lado" + sideop + "=" + valor);
                    //redstone_power[sideop]=valor;
                    redstone_power = valor;
                    /*
                    int xop,yop,zop;
                    xop = x+directionop.offsetX;
                    yop = y+directionop.offsetY;
                    zop = z+directionop.offsetZ;
                    world.notifyBlocksOfNeighborChange(xop,yop,zop,this.blockID);

                    world.getIndirectPowerLevelTo()
                    */

                    world.notifyBlockOfNeighborChange(xa, ya, za, sideop);
                }
            }
        }

        world.scheduleBlockUpdate(x,y,z,this.blockID,this.tickRate(world));
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rtick) {
        super.updateTick(world, x, y, z, rtick);

        notifyAllNeighbor(world,x,y,z);
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
        return true;
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
}
