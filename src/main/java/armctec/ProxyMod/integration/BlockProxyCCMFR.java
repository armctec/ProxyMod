package armctec.ProxyMod.integration;

import armctec.ProxyMod.block.BlockTileBasic;
import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.utility.LogHelper;
import dan200.computercraft.api.ComputerCraftAPI;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import powercrystals.minefactoryreloaded.api.rednet.IConnectableRedNet;
import powercrystals.minefactoryreloaded.api.rednet.RedNetConnectionType;

import java.util.Random;

public class BlockProxyCCMFR  extends BlockTileBasic implements IConnectableRedNet
{
    static int redstone_cc[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int redstone_mfr[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int redstone_bits[] = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768};
    /* Registra classe para computer craft */
    public static void init()
    {
        ComputerCraftAPI.registerBundledRedstoneProvider(new TileProviderCC());
    }

    public BlockProxyCCMFR(int id)
    {
        super(id, Material.rock);
        this.setBlockName(Names.Blocks.PROXYCCMFR);
        this.setBlockTextureName(Names.Blocks.PROXYCCMFR);
        this.setTickRandomly(true);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        //LogHelper.debug("createNewTileEntity");
        return new TileProviderCC();
    }

    @Override
    public int tickRate(World world) {
        return 10;

    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id)
    {
        //super.onNeighborBlockChange(world, x, y, z, id);

        ForgeDirection direction, directionop;
        TileEntity BlockEntity;
        TileProviderCC CCEntity = null;
        int xa,ya,za;
        int valor, sideop;
        int conta, i;

        BlockEntity = world.getBlockTileEntity(x,y,z);
        if(BlockEntity instanceof TileProviderCC) {
            CCEntity = (TileProviderCC) BlockEntity;
            for(i=0;i<16;i++)
                CCEntity.setRedstonepower(i,redstone_mfr[i]);
        }

        for(int lado=0;lado<6;lado++)
        {
            direction = ForgeDirection.getOrientation(lado);
            xa = x + direction.offsetX;
            ya = y + direction.offsetY;
            za = z + direction.offsetZ;

            if (world.blockHasTileEntity(xa, ya, za) == true) {
                //LogHelper.debug("Posicao x:" + xa + ",y:" + ya + ",z:" + za);

                directionop = direction.getOpposite();
                sideop = directionop.ordinal();
                valor = ComputerCraftAPI.getBundledRedstoneOutput(world, xa, ya, za, sideop);
                if (valor != -1) {
                    //LogHelper.debug("Valor do lado:" + sideop + "=" + valor);

                    for(i=0;i<16;i++){
                        conta = valor&redstone_bits[i];
                        if(conta!=0)
                            redstone_cc[i]=15;
                        else
                            redstone_cc[i]=0;
                    }
                }
            }
            world.notifyBlockOfNeighborChange(xa, ya, za, lado);
        }

        world.scheduleBlockUpdate(x,y,z,this.blockID,this.tickRate(world));
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        notifyAllNeighbor(world, x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int metadata) {
        super.breakBlock(world, x, y, z, id, metadata);
        notifyAllNeighbor(world,x,y,z);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rtick) {
        super.updateTick(world, x, y, z, rtick);

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
            if(idblock== Block.torchRedstoneIdle.blockID ||
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
    public RedNetConnectionType getConnectionType(World world, int x, int y, int z, ForgeDirection side) {
        //LogHelper.debug("Block:getConnectionType x:"+x+",y:"+y+",z:"+z+",side:"+side);
        return RedNetConnectionType.CableAll;
    }

    @Override
    public int[] getOutputValues(World world, int x, int y, int z, ForgeDirection side)
    {
        int power[] = new int[16];

        //LogHelper.debug("Block:getOutputValues x:"+x+",y:"+y+",z:"+z+",side:"+side);

        for(int subnet=0;subnet<16;subnet++) {
            power[subnet] = redstone_cc[subnet];
            //LogHelper.debug("subnet:" + subnet + ",valor:" + power[subnet]);
        }

        return power;
    }

    @Override
    public int getOutputValue(World world, int x, int y, int z, ForgeDirection side, int subnet)
    {
        //LogHelper.debug("Block:getOutputValue x:"+x+",y:"+y+",z:"+z+",side:"+side+",subnet:"+subnet);

        if(subnet<0 || subnet>15)
            return 0;

        //LogHelper.debug("Block:getOutputValue:Subnet:" + subnet + ",Valor:" +redstone_cc[subnet]);
        return redstone_cc[subnet];
    }

    @Override
    public void onInputsChanged(World world, int x, int y, int z, ForgeDirection side, int[] inputValues)
    {
        //LogHelper.debug("Block:onInputsChanged x:"+x+",y:"+y+",z:"+z+",side:"+side);

        for(int i=0;i<16;i++)
        {
            //LogHelper.debug("Subnet:" + i + ",Valor:"+ inputValues[i]);
            redstone_mfr[i] = inputValues[i];
        }
    }

    @Override
    public void onInputChanged(World world, int x, int y, int z, ForgeDirection side, int inputValue)
    {
        //LogHelper.debug("Block:onInputChanged x:"+x+",y:"+y+",z:"+z+",side:"+side+",value:"+inputValue);
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
}
