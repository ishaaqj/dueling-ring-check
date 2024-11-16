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
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.GameTick;
import net.runelite.client.ui.overlay.OverlayManager;


@Slf4j
@PluginDescriptor(
	name = "Ring of Dueling Reminder"
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
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Ring of Dueling Plugin Started!");
		overlayManager.add(ringOfDuelingOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Ring of Dueling Plugin Ended");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Subscribe
	public void onChatMessage(ChatMessage chatMessage){
		if (chatMessage.getMessage().equals("Hi!")){
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "lolsquid" + chatMessage.getMessage(), null);
			if (isDuelingRingisWorn()){
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Dueling ring is worn", null);
			}
		}
	}

	@Provides
	RingOfDuelingConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RingOfDuelingConfig.class);
	}

	public boolean isDuelingRingisWorn() {
		return client.getItemContainer(InventoryID.EQUIPMENT).contains(2552);
	}

	@Subscribe
	public void onGameTick(GameTick event) {
		if (isDuelingRingisWorn()){
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Dueling ring is worn", null);
		}
	}
}
