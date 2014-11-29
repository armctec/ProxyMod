package armctec.ProxyMod.init;

import armctec.ProxyMod.utility.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
    public static void init()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.ProxyCCMFR, 1),
                    new Object[]{"iii", "ibi", "iii", 'i', Item.ingotIron, 'b', Block.blockRedstone});
    }
}
