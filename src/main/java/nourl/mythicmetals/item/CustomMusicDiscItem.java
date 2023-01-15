package nourl.mythicmetals.item;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

public class CustomMusicDiscItem extends MusicDiscItem {
    public CustomMusicDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings, 162);
    }

    @Override
    public MutableText getDescription() {
        return super.getDescription().formatted(Formatting.ITALIC);
    }
}
