package org.dreambot.articron.paint.impl;

import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.paint.Graphics2DPaint;

import java.awt.*;

public class MulePaint extends Graphics2DPaint {

    private ScriptContext ctx;

    public MulePaint(ScriptContext ctx) {
       this.ctx = ctx;
    }

    @Override
    public void onPaint(Graphics2D g) {
        g.drawString("Status: " + getStatus(),20,50);
    }

    @Override
    public void loadRewards() { }
}
