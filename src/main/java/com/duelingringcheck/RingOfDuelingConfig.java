package com.duelingringcheck;

import net.runelite.client.config.*;
import java.awt.*;

@ConfigGroup("example")
public interface RingOfDuelingConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login",
			position = 0
	)

	default String greeting()
	{
		return "Hello";
	}

	@ConfigItem(
			keyName = "checkInventory",
			name = "Check Inventory",
			description = "Uncheck if you do not want to check the inventory as well",
			position = 1
	)
	default boolean checkInventory()
	{
		return true;
	}
}


