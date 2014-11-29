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

public class BlockProxyCCMFR  extends BlockTileBasic implements IConnectableRedNet
{
    static int redstone_power[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int redstone_bits[] = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096,8192,16384,32768};
    /* Registra classe para computer craft */
    public static void init()
    {
        ComputerCraftAPI.registerBundledRedstoneProvider(new CCProvider());
    }

    public BlockProxyCCMFR(int id)
    {
        super(id, Material.rock);
        this.setBlockName(Names.Blocks.PROXYCCMFR);
        this.setBlockTextureName(Names.Blocks.PROXYCCMFR);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        LogHelper.debug("createNewTileEntity");
        return new CCProvider();
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, int id)
    {
        super.onNeighborBlockChange(world, x, y, z, id);

        ForgeDirection direction, directionop;
        TileEntity BlockEntity;
        CCProvider CCEntity = null;
        int xa,ya,za;
        int valor, sideop;
        int conta;

        for(int lado=0;lado<6;lado++)
        {
            direction = ForgeDirection.getOrientation(lado);
            xa = x + direction.offsetX;
            ya = y + direction.offsetY;
            za = z + direction.offsetZ;

            if (world.blockHasTileEntity(xa, ya, za) == true) {
                LogHelper.debug("Posicao x:" + xa + ",y:" + ya + ",z:" + za);

                directionop = direction.getOpposite();
                sideop = directionop.ordinal();
                valor = ComputerCraftAPI.getBundledRedstoneOutput(world, xa, ya, za, sideop);
                if (valor != -1) {
                    LogHelper.debug("Valor do lado:" + sideop + "=" + valor);

                    for(int i=0;i<16;i++){
                        conta = valor&redstone_bits[i];
                        if(conta!=0)
                            redstone_power[i]=15;
                        else
                            redstone_power[i]=0;
                    }
                }
            }
        }

        world.scheduleBlockUpdate(x,y,z,this.blockID,10);
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

    private void notifyAllNeighbor(World world, int x, int y, int z)
    {
        ForgeDirection direction;
        int xa,ya,za;

        for(int lado=0;lado<6;lado++) {
            direction = ForgeDirection.getOrientation(lado);
            xa = x + direction.offsetX;
            ya = y + direction.offsetY;
            za = z + direction.offsetZ;

            if(world.isAirBlock(x,y,z)==true)
                continue;

            world.notifyBlockOfNeighborChange(xa, ya, za, blockID);
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

        LogHelper.debug("Block:getOutputValues x:"+x+",y:"+y+",z:"+z+",side:"+side);

        for(int subnet=0;subnet<16;subnet++) {
            power[subnet] = redstone_power[subnet];
            LogHelper.debug("subnet:" + subnet + ",valor:" + power[subnet]);
        }

        return power;
    }

    @Override
    public int getOutputValue(World world, int x, int y, int z, ForgeDirection side, int subnet)
    {
        LogHelper.debug("Block:getOutputValue x:"+x+",y:"+y+",z:"+z+",side:"+side+",subnet:"+subnet);

        if(subnet<0 || subnet>15)
            return 0;

        LogHelper.debug("Block:getOutputValue:Subnet:" + subnet + ",Valor:" +redstone_power[subnet]);
        return redstone_power[subnet];
    }

    @Override
    public void onInputsChanged(World world, int x, int y, int z, ForgeDirection side, int[] inputValues)
    {
        TileEntity BlockEntity;
        CCProvider CCEntity = null;

        LogHelper.debug("Block:onInputsChanged x:"+x+",y:"+y+",z:"+z+",side:"+side);

        BlockEntity = world.getBlockTileEntity(x,y,z);
        if(BlockEntity instanceof CCProvider)
        {
            CCEntity = (CCProvider) BlockEntity;

            for(int i=0;i<16;i++)
            {
                LogHelper.debug("Subnet:" + i + ",Valor:"+ inputValues[i]);
                CCEntity.setRedstonepower(i,inputValues[i]);
            }
        }
    }

    @Override
    public void onInputChanged(World world, int x, int y, int z, ForgeDirection side, int inputValue)
    {
        LogHelper.debug("Block:onInputChanged x:"+x+",y:"+y+",z:"+z+",side:"+side+",value:"+inputValue);
    }
}
