package org.dreambot.articron.paint;

import java.awt.*;

public abstract class Graphics2DPaint {

    private String status;


    public abstract void onPaint(Graphics2D g);

    public abstract void loadRewards();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
