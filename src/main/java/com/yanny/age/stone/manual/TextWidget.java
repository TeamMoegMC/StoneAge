package com.yanny.age.stone.manual;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.Screen;

import java.util.ArrayList;
import java.util.List;

public class TextWidget extends Widget {
    public static final String TYPE = "text";

    protected final Widget parent;
    protected final String text;
    protected final List<CustomFontRenderer.Link> links = new ArrayList<>();
    protected final CustomFontRenderer customFontRenderer;

    protected final int color;
    protected final float scale;
    protected final int tmpWidth;
    protected final int tmpHeight;

    public TextWidget(Widget parent, JsonObject object) {
        this.parent = parent;

        text = Utils.getString(object, "text", "<UNSET>", false);
        scale = Utils.getReal(object, "scale", 1.0, true).floatValue();
        color = Utils.getInt(object, "color", -1, true);
        tmpWidth = Utils.getInt(object, "width", DYNAMIC, true);
        tmpHeight = Utils.getInt(object, "height", DYNAMIC, true);

        String key = Utils.getString(object, "key", null, true);
        if (key != null) {
            parent.addLink(key);
        }

        customFontRenderer = new CustomFontRenderer(mc.fontRenderer);
    }

    @Override
    public int getMinWidth(int height) {
        return Math.max(tmpWidth, DYNAMIC);
    }

    @Override
    public int getMinHeight(int width) {
        return Math.max(tmpHeight, Math.round(getTextHeight(width) * scale));
    }

    @Override
    public void setPos(int x, int y) {
        super.setPos(x, y);
        links.clear();
        links.addAll(customFontRenderer.analyseSplitStringLinks(text, x, y, width, scale));
    }

    @Override
    public void drawBackgroundLayer(Screen screen, int mx, int my) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(x, y, 0.0f);
        GlStateManager.scalef(scale, scale, 1.0f);
        customFontRenderer.drawSplitString(text, 0, 0, Math.round(width / scale), color);
        GlStateManager.popMatrix();

        links.forEach(link -> {
            if (link.inArea(x, y, mx, my, scale)) {
                link.rects.forEach(rect -> {
                    float x1 = x + (rect.x1 - x) * scale;
                    float y1 = y + (rect.y1 - y) * scale;
                    float x2 = x + (rect.x2 - x) * scale;
                    float y2 = y + (rect.y2 - y) * scale;

                    Screen.fill((int) x1, (int) y1, (int) x2, (int) y2, 0x66000000);
                });
            }
        });
    }

    @Override
    public void render(Screen screen, int mx, int my) {

    }

    @Override
    public boolean mouseClicked(int mx, int my, int key) {
        for (CustomFontRenderer.Link link : links) {
            if (link.inArea(x, y, mx, my, scale)) {
                parent.changePage(link.key);
                return true;
            }
        }

        return false;
    }

    private int getTextHeight(int width) {
        return mc.fontRenderer.getWordWrappedHeight(text, Math.round(width / scale));
    }
}
