package armctec.ProxyMod.init;

import armctec.ProxyMod.block.BlockBasic;
import armctec.ProxyMod.block.BlockRednetProxy;
import armctec.ProxyMod.reference.Names;
import armctec.ProxyMod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ModBlocks
{
    public static BlockBasic rednetproxy = null;
    public static int rednetproxy_id  = 1500;

    public static void init()
    {
        rednetproxy = new BlockRednetProxy(rednetproxy_id);

        GameRegistry.registerBlock(rednetproxy, Names.Blocks.REDNETPROXY);
    }
}
