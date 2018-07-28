package hu.frontrider.arcana;

import hu.frontrider.arcana.capabilities.*;
import hu.frontrider.arcana.proxy.CommonProxy;
import hu.frontrider.arcana.research.ResearchRegistry;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ThaumicArcana.MODID, name = ThaumicArcana.NAME, version = ThaumicArcana.VERSION)
public class ThaumicArcana {
    public static final String MODID = "thaumic_arcana";
    public static final String NAME = "Thaumic Arcana";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @SidedProxy(clientSide = "hu.frontrider.arcana.proxy.ClientProxy", serverSide = "hu.frontrider.arcana.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        CapabilityManager.INSTANCE.register(ICreatureEnchant.class, new CreatureEnchantStorage(), CreatureEnchant::new);
        CapabilityManager.INSTANCE.register(IRelief.class, new ReliefStore(), Relief::new);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        ResearchRegistry.init();
    }
}