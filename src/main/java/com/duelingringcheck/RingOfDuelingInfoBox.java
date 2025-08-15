package com.duelingringcheck;

import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.ColorUtil;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RingOfDuelingInfoBox extends InfoBox {
    private final RingOfDuelingPlugin plugin;
    public RingOfDuelingInfoBox(RingOfDuelingPlugin plugin, ItemManager itemManager){
        super(itemManager.getImage(2552),plugin);
        this.plugin = plugin;

    }

    @Override
    public String getText() {
        return "";
    }

    public Color getTextColor() {
        return Color.green;
    }

}
