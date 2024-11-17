package com.duelingringcheck;

import net.runelite.client.config.*;
import java.awt.*;

@ConfigGroup("RingOfDuelingReminder")
public interface RingOfDuelingConfig extends Config
{
	@ConfigItem(
			keyName = "checkInventory",
			name = "Check Inventory",
			description = "Uncheck if you do not want to check the inventory as well",
			position = 0
	)
	default boolean checkInventory()
	{
		return false;
	}
}


