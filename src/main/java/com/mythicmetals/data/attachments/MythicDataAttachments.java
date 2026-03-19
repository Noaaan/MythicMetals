package com.mythicmetals.data.attachments;

import com.mojang.serialization.Codec;
import com.mythicmetals.misc.RegistryHelper;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

@SuppressWarnings("UnstableApiUsage")
public class MythicDataAttachments {
    private MythicDataAttachments() {
    }

    public static final AttachmentType<Integer> COMBUSTION_COOLDOWN_ATTACHMENT = AttachmentRegistry
        .createPersistent(RegistryHelper.id("combustion_cooldown"), Codec.INT);
}
