package com.rs.game.player.quests.handlers.merlinscrystal.knightsroundtable;

import com.rs.game.player.Player;
import com.rs.game.player.content.dialogue.Conversation;
import com.rs.game.player.content.dialogue.Dialogue;
import com.rs.game.player.content.dialogue.HeadE;
import com.rs.game.player.content.dialogue.Options;
import com.rs.game.player.quests.Quest;
import com.rs.plugin.annotations.PluginEventHandler;
import com.rs.plugin.events.NPCClickEvent;
import com.rs.plugin.handlers.NPCClickHandler;

import static com.rs.game.player.quests.handlers.merlinscrystal.MerlinsCrystal.*;

@PluginEventHandler
public class SirLancelotMerlinsCrystalD extends Conversation {
    private final static int NPC = 239;
    public SirLancelotMerlinsCrystalD(Player p) {
        super(p);
        switch(p.getQuestManager().getStage(Quest.MERLINS_CRYSTAL)) {
            case NOT_STARTED -> {
                addNPC(NPC, HeadE.CALM_TALK, "Greetings! I am Sir Lancelot, the greatest Knight in the land! What do you want?");
                addOptions("Choose an option:", new Options() {
                    @Override
                    public void create() {
                        option("You're a little full of yourself aren't you?", new Dialogue()
                                .addPlayer(HeadE.HAPPY_TALKING, "You're a little full of yourself aren't you?")
                                .addNPC(NPC, HeadE.CALM_TALK, "I have every right to be proud of myself. My prowess in battle in world renowned!")
                        );
                        option("I seek a quest!", new Dialogue()
                                .addPlayer(HeadE.HAPPY_TALKING, "I seek a quest!")
                                .addNPC(NPC, HeadE.CALM_TALK, "Leave questing to the professionals. Such as myself.")
                        );
                    }
                });
            }
            case TALK_TO_KNIGHTS, CONFRONT_KEEP_LA_FAYE -> {
                addNPC(NPC, HeadE.CALM_TALK, "Greetings! I am Sir Lancelot, the greatest Knight in the land! What do you want?");
                addOptions("Choose an option:", new Options() {
                    @Override
                    public void create() {
                        option("I want to get Merlin out of the crystal", new Dialogue()
                                .addPlayer(HeadE.HAPPY_TALKING, "I want to get Merlin out of the crystal")
                                .addNPC(NPC, HeadE.CALM_TALK, "Well, if the Knights of the Round Table can't manage it, I can't see how a commoner " +
                                        "like you could succeed where we have failed.")
                        );
                        option("You're a little full of yourself aren't you?", new Dialogue()
                                .addPlayer(HeadE.HAPPY_TALKING, "You're a little full of yourself aren't you?")
                                .addNPC(NPC, HeadE.CALM_TALK, "I have every right to be proud of myself. My prowess in battle in world renowned!")
                        );
                        option("Any ideas on how to get into Morgan Le Faye's stronghold?", new Dialogue()
                                .addPlayer(HeadE.HAPPY_TALKING, "Any ideas on how to get into Morgan Le Faye's stronghold?")
                                .addNPC(NPC, HeadE.CALM_TALK, "That stronghold is built in a strong defensive position. It's on a big rock out into the sea. ")
                                .addNPC(NPC, HeadE.CALM_TALK, "There are two ways in that I know of, the large heavy front doors, and the sea entrance, " +
                                        "only penetrable by boat. They take all their deliveries by boat.", ()->{
                                    p.getQuestManager().setStage(Quest.MERLINS_CRYSTAL, CONFRONT_KEEP_LA_FAYE);
                                })
                        );
                    }
                });
            }
            case THE_BLACK_CANDLE, OBTAINING_EXCALIBUR, PERFORM_RITUAL, BREAK_MERLIN_CRYSTAL -> {
                addNPC(NPC, HeadE.CALM_TALK, "I guess going by boat was the way to go?");
                addPlayer(HeadE.HAPPY_TALKING, "Yes it was...");
            }
            case TALK_TO_ARTHUR, QUEST_COMPLETE -> {
                addNPC(NPC, HeadE.CALM_TALK, "Merlin is out of the crystal I hear...");
                addPlayer(HeadE.HAPPY_TALKING, "Yes he is...");
            }
        }
    }

    public static NPCClickHandler handleDialogue = new NPCClickHandler(NPC) {
        @Override
        public void handle(NPCClickEvent e) {
            e.getPlayer().startConversation(new SirLancelotMerlinsCrystalD(e.getPlayer()).getStart());
        }
    };
}