package net.elidhan.anim_guns.asm;

import com.chocohead.mm.api.ClassTinkerers;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;

public class EarlyRiser implements Runnable
{
    @Override
    public void run()
    {
        MappingResolver remapper = FabricLoader.getInstance().getMappingResolver();

        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
        {
            String armPoseClass = remapper.mapClassName("intermediary", "net.minecraft.class_572$class_573");
            ClassTinkerers.enumBuilder(armPoseClass, boolean.class)
                    .addEnum("HANDGUN_ONEHAND", false)
                    .addEnum("HANDGUN_TWOHAND", true)
                    .addEnum("REVOLVER_FANNING", true)
                    .addEnum("LONG_GUNS", true)
                    .addEnum("MINIGUN", true)
                    .build();
        }
    }
}
