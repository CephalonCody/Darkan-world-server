// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
//  Copyright © 2021 Trenton Kress
//  This file is part of project: Darkan
//
package com.rs.game.player.controllers;

import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.dialogue.Conversation;
import com.rs.game.player.content.dialogue.Dialogue;
import com.rs.game.player.content.dialogue.HeadE;
import com.rs.game.player.content.dialogue.Options;
import com.rs.game.player.quests.handlers.merlinscrystal.MorganMerlinsCrystalD;
import com.rs.game.player.quests.handlers.merlinscrystal.ThrantaxMerlinsCrystalD;
import com.rs.game.region.RegionBuilder.DynamicRegionReference;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.lib.game.Animation;
import com.rs.lib.game.SpotAnim;
import com.rs.lib.game.WorldTile;

public class MerlinsCrystalRitualScene extends Controller {
    NPC spirit;
    final int THRANTAX_SPIRIT = 238;

	@Override
	public void start() {
        player.lock();
		playCutscene();
	}

	private void playCutscene() {
        boolean hasSpirit = false;
        for(NPC npc : World.getNPCsInRegion(player.getRegionId()))
            if(npc.getId() == THRANTAX_SPIRIT)
                hasSpirit = true;
        if(hasSpirit)
            ;
        else {
            spirit = World.spawnNPC(THRANTAX_SPIRIT, new WorldTile(2780, 3516, 0), -1, false, true);
            player.getPackets().sendMusic(449, 100, 255);
            spirit.setNextSpotAnim(new SpotAnim(1605, 0, 0));
            spirit.setCantInteract(true);
            spirit.faceSouth();
            spirit.setRandomWalk(false);
            spirit.lingerForPlayer(player);
        }
        player.startConversation(new ThrantaxMerlinsCrystalD(player).getStart());
	}

	@Override
	public boolean login() {
		forceClose();
		return false;
	}

	@Override
	public boolean logout() {
		player.unlock();
		return false;
	}

	@Override
	public void forceClose() {
		player.unlock();
		removeController();
	}

}