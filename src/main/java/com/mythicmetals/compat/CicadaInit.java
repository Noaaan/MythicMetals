package com.mythicmetals.compat;

import nl.enjarai.cicada.api.conversation.ConversationManager;
import nl.enjarai.cicada.api.util.*;

public class CicadaInit implements CicadaEntrypoint {
    @Override
    public void registerConversations(ConversationManager conversationManager) {
        conversationManager.registerSource(
            JsonSource.fromResource("data/cicada/meme_metals/conversations.json"), ProperLogger.getLogger("MythicMetals")::info
        );
    }
}
