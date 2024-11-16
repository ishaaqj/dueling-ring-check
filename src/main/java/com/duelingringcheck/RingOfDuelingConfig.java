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
			keyName = "countOverHeads",
			name = "Count Enemy Overheads",
			description = "Counts the number of each protection prayer attackable targets not in your CC are currently using",
			position = 1
	)
	default boolean countOverHeads()
	{
		return true;
	}
}


