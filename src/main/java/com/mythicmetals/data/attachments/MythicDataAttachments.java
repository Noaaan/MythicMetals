package com.mythicmetals.data.attachments;

import com.mythicmetals.item.armor.CarmotShield;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.serialization.CodecUtils;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

public class MythicDataAttachments {
    private MythicDataAttachments() {
    }

    public static final AttachmentType<CarmotShield> CARMOT_SHIELD_ATTACHMENT = AttachmentRegistry
        .create(RegistryHelper.id("carmot_shield"),
            builder -> builder
                .persistent(CodecUtils.toCodec(CarmotShield.ENDEC))
                .syncWith(CodecUtils.toPacketCodec(CarmotShield.ENDEC), AttachmentSyncPredicate.all())
        );

    public static final AttachmentType<Integer> CARMOT_SHIELD_COOLDOWN_ATTACHMENT = AttachmentRegistry
        .createDefaulted(RegistryHelper.id("carmot_shield_cooldown"), () -> 0);

    public static final AttachmentType<Integer> COMBUSTION_COOLDOWN_ATTACHMENT = AttachmentRegistry
        .createDefaulted(RegistryHelper.id("combustion_cooldown"), () -> 0);
}
