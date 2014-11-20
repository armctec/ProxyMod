package armctec.ProxyMod.creativetab;

import armctec.ProxyMod.init.ModItems;
import armctec.ProxyMod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabProxyMod
{
    public static final CreativeTabs PROXYMOD_TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return null;//ModItems.mapleLeaf;
        }
    };
}
