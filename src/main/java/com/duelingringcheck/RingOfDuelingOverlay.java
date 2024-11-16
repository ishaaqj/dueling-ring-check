package com.duelingringcheck;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.WorldType;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import org.apache.commons.lang3.ArrayUtils;

import com.duelingringcheck.RingOfDuelingPlugin;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.PluginInstantiationException;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
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
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPosition(OverlayPosition.TOP_RIGHT);
        setPreferredPosition(OverlayPosition.TOP_RIGHT);
        loadRecoilImage();
    }

    private static void loadRecoilImage() {
        ringOfDuelingIcon = ImageUtil.loadImageResource(RingOfDuelingPlugin.class, "/ring_of_dueling.png");
    }

    @Override
    public Dimension render(Graphics2D graphics){
        if (!plugin.isDuelingRingisWorn()) {
            return null;
        }
        ImageComponent imagePanelComponent = new ImageComponent(ringOfDuelingIcon);
        return imagePanelComponent.render(graphics);
    }
}
