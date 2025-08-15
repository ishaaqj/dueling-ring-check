package com.duelingringcheck;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.GameTick;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;


@Slf4j
@PluginDescriptor(
	name = "Ring of Dueling Reminder",
		description = "Reminds you to get / equip your ring of dueling!"
)
public class RingOfDuelingPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RingOfDuelingConfig config;

	@Inject
	RingOfDuelingOverlay ringOfDuelingOverlay;

	@Inject
	private ItemManager itemManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private InfoBoxManager infoBoxManager;

	private RingOfDuelingInfoBox infoBox = null;

	private boolean checkInventory = false;

	@Override
	protected void startUp() throws Exception{

		overlayManager.add(ringOfDuelingOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Ring of Dueling Plugin Ended");
	}
	@Subscribe
	public void onChatMessage(ChatMessage chatMessage){
		if (chatMessage.getMessage().equals("Hi!")){
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "lolsquid" + Boolean.toString(config.checkInventory()), null);
		}
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged configChanged) {
		if (configChanged.getGroup().equals(RingOfDuelingConfig.GROUP)) {
			var isShowing = infoBox != null;
			if (config.checkInventory()){
				if (isShowing && (isDuelingRingInEquipment() || isDuelingRingInInventory())){
					infoBoxManager.removeInfoBox(infoBox);
				}
			}
			else if (isShowing && isDuelingRingInEquipment()) {
				infoBoxManager.removeInfoBox(infoBox);
			}
			if(!isShowing) {
				handleInfoBox();
			}
		}
	}
	@Subscribe
	public void onGameTick(GameTick event) {
		var isShowing = infoBox != null;
		if (config.checkInventory()){
			if (isShowing && (isDuelingRingInEquipment() || isDuelingRingInInventory())){
				infoBoxManager.removeInfoBox(infoBox);
			}
		}
		else if (isShowing && isDuelingRingInEquipment()) {
			infoBoxManager.removeInfoBox(infoBox);
		}
		if(!isShowing) {
			handleInfoBox();
		}
	}
	@Provides
	RingOfDuelingConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RingOfDuelingConfig.class);
	}

	public boolean isDuelingRingInEquipment() {
		ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
		return (equipment.contains(2552) || equipment.contains(2554) || equipment.contains(2556) || equipment.contains(2558) || equipment.contains(2560) || equipment.contains(2562) || equipment.contains(2564) || equipment.contains(2566));
	}

	public boolean isDuelingRingInInventory() {
		ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
		return (inventory.contains(2552) || inventory.contains(2554) || inventory.contains(2556) || inventory.contains(2558) || inventory.contains(2560) || inventory.contains(2562) || inventory.contains(2564) || inventory.contains(2566));
	}

	private void handleInfoBox(){
		infoBox = new RingOfDuelingInfoBox(this, itemManager);
		infoBoxManager.addInfoBox(infoBox);
	}


}
