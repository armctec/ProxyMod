package armctec.ProxyMod.init;

import armctec.ProxyMod.handler.ConfigurationHandler;
import armctec.ProxyMod.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
    public static Block cc_blockCable;
    public static ItemStack cc_cable;
    public static ItemStack cc_wiredModem;
    public static Item mfr_machineblock;

    public static void init()
    {
        cc_blockCable = GameRegistry.findBlock("ComputerCraft", "CC-Cable");
        if(cc_blockCable != null) {
            cc_cable = new ItemStack(cc_blockCable, 1, 0);
            cc_wiredModem = new ItemStack(cc_blockCable, 1, 1);
        }

        mfr_machineblock = GameRegistry.findItem("MineFactoryReloaded", "item.mfr.machineblock");


        if(cc_cable != null && cc_wiredModem != null && mfr_machineblock!=null && ConfigurationHandler.vanillarecipe==false) {
            LogHelper.debug("Recipe:Normal");
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ProxyCCMFR, 1),
                    new Object[]{"iii", "nmc", "rwr", 'n', "cableRedNet", 'm', mfr_machineblock, 'w', cc_wiredModem, 'c', cc_cable, 'r', Item.redstone, 'i', Item.ingotIron }));
        }
        else
        {
            LogHelper.debug("Recipe:Vanilla");
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.ProxyCCMFR, 1),
                    new Object[]{"iii", "ibi", "iii", 'i', Item.ingotIron, 'b', Block.blockRedstone}));
        }
    }
}
