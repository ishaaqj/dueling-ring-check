package com.duelingringcheck;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import java.awt.image.BufferedImage;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.util.ImageUtil;

@Slf4j
public class RingOfDuelingOverlay extends Overlay {
    private final RingOfDuelingPlugin plugin;
    private final RingOfDuelingPlugin config;
    private static BufferedImage ringOfDuelingIcon;
    @Inject
    public RingOfDuelingOverlay(final RingOfDuelingPlugin plugin, final RingOfDuelingPlugin config)
    {
        this.plugin = plugin;
        this.config = config;
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(PRIORITY_LOW);
        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        loadRingOfDuelingImage();
    }

    private static void loadRingOfDuelingImage() {
        ringOfDuelingIcon = ImageUtil.loadImageResource(RingOfDuelingPlugin.class, "/ring_of_dueling.png");
    }

    @Override
    public Dimension render(Graphics2D graphics){
        if (plugin.isDuelingRingInEquipment()) {
            return null;
        }
        ImageComponent imagePanelComponent = new ImageComponent(ringOfDuelingIcon);
        return imagePanelComponent.render(graphics);
    }
}
