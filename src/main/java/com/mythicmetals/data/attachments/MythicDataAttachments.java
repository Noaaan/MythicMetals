package com.mythicmetals.data.attachments;

import com.mojang.serialization.Codec;
import com.mythicmetals.item.armor.CarmotShield;
import com.mythicmetals.misc.RegistryHelper;
import io.wispforest.owo.serialization.CodecUtils;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

@SuppressWarnings("UnstableApiUsage")
public class MythicDataAttachments {
    private MythicDataAttachments() {
    }

    public static final AttachmentType<CarmotShield> CARMOT_SHIELD_ATTACHMENT = AttachmentRegistry
        .createPersistent(RegistryHelper.id("carmot_shield"), CodecUtils.toCodec(CarmotShield.ENDEC));

    public static final AttachmentType<Integer> COMBUSTION_COOLDOWN_ATTACHMENT = AttachmentRegistry
        .createPersistent(RegistryHelper.id("combustion_cooldown"), Codec.INT);
}
