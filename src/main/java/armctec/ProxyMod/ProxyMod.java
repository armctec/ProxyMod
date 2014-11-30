package armctec.ProxyMod;

import armctec.ProxyMod.handler.ConfigurationHandler;
import armctec.ProxyMod.init.ModBlocks;
import armctec.ProxyMod.init.ModItems;
import armctec.ProxyMod.init.ModTileEntities;
import armctec.ProxyMod.init.Recipes;
import armctec.ProxyMod.integration.BlockProxyCCMFR;
import armctec.ProxyMod.proxy.ClientProxy;
import armctec.ProxyMod.proxy.CommonProxy;
import armctec.ProxyMod.proxy.IProxy;
import armctec.ProxyMod.reference.Reference;
import armctec.ProxyMod.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class ProxyMod
{
    @Mod.Instance(Reference.MOD_ID)
    public static ProxyMod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());

        //ModItems.init();

        ModBlocks.init();

        LogHelper.info("Pre Initialization Complete!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModTileEntities.init();
        BlockProxyCCMFR.init();
        LogHelper.info("Initialization Complete!");
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        Recipes.init();
        LogHelper.info("Post Initialization Complete!");
    }
}
