package nourl.mythicmetals.misc;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class BanglumNukeSource extends DamageSource {
    public BanglumNukeSource(RegistryEntry<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
        super(type, source, attacker);
    }

    @Override
    public Text getDeathMessage(LivingEntity killed) {
        if (this.getAttacker() != null) {
            return Text.translatable("death.attack.banglum_nuke.player", killed.getDisplayName(), this.getAttacker().getDisplayName());
        }
        return super.getDeathMessage(killed);
    }
}
