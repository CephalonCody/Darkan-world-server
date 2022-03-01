package com.rs.game.player.quests.handlers.merlinscrystal.knightsroundtable;

import com.rs.game.player.Player;
import com.rs.game.player.content.dialogue.Conversation;
import com.rs.game.player.content.dialogue.HeadE;
import com.rs.game.player.quests.Quest;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

import static com.rs.game.player.quests.handlers.merlinscrystal.MerlinsCrystal.*;

@PluginEventHandler
public class SirBedivereMerlinsCrystalD extends Conversation {
	private final static int NPC = 242;
	public SirBedivereMerlinsCrystalD(Player p) {
		super(p);
		switch(p.getQuestManager().getStage(Quest.MERLINS_CRYSTAL)) {
		case NOT_STARTED -> {
			addNPC(NPC, HeadE.CALM_TALK, "May I help you?");
			addPlayer(HeadE.HAPPY_TALKING, "I'm really just looking for a quest...");
			addNPC(NPC, HeadE.CALM_TALK, "Fortune favours us both then adventurer. I suggest you go and speak to King Arthur.");
		}
		case TALK_TO_KNIGHTS, CONFRONT_KEEP_LA_FAYE -> {
			addPlayer(HeadE.HAPPY_TALKING, "I don't suppose you have any idea how to break into Mordred's fort do you?");
			addNPC(NPC, HeadE.CALM_TALK, "I am afraid not. Would that we could! Mordred and his cronies have been thorns in our side for far too long already!");
			addPlayer(HeadE.HAPPY_TALKING, "Ok. Thanks. See you later!");
			addNPC(NPC, HeadE.CALM_TALK, "Take care adventurer, Mordred is an evil and powerful foe.");
		}
		case THE_BLACK_CANDLE, OBTAINING_EXCALIBUR, PERFORM_RITUAL, BREAK_MERLIN_CRYSTAL -> {
			addNPC(NPC, HeadE.CALM_TALK, "Good luck breaking Merlin free adventurer.");
			addPlayer(HeadE.HAPPY_TALKING, "Thank you.");

		}
		case TALK_TO_ARTHUR, QUEST_COMPLETE -> {
			addNPC(NPC, HeadE.CALM_TALK, "You are an excellent knight indeed to have freed Merlin");
			addPlayer(HeadE.HAPPY_TALKING, "Thanks.");
		}
		}
	}

	public static NPCClickHandler handleDialogue = new NPCClickHandler(new Object[] { NPC }) {
		@Override
		public void handle(NPCClickEvent e) {
			e.getPlayer().startConversation(new SirBedivereMerlinsCrystalD(e.getPlayer()).getStart());
		}
	};
}
