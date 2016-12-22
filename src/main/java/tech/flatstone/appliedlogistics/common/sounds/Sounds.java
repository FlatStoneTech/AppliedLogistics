package tech.flatstone.appliedlogistics.common.sounds;

import net.minecraft.util.SoundEvent;
import tech.flatstone.appliedlogistics.common.sounds.misc.SoundCauldronHandle;
import tech.flatstone.appliedlogistics.common.util.RegistrationHelper;

public enum Sounds {
    CAULDRON_HANDLE(SoundCauldronHandle.class),
    ;

    private final Class<? extends SoundBase> soundClass;
    private SoundEvent sound;

    Sounds(Class<? extends SoundBase> soundClass) {
        this.soundClass = soundClass;
    }

    public static void registerSounds() {
        for (Sounds i : Sounds.values()) {
            i.registerSound();
        }
    }

    private void registerSound() {
        this.sound = RegistrationHelper.RegisterSound(soundClass);
    }

    public SoundEvent getSound() {
        return this.sound;
    }
}
