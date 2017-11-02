package org.dreambot.articron.util;


import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.script.listener.PaintListener;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.fw.ScriptContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Articron
 * Date:   18/10/2017.
 */
public class MTAPaint {

    private ScriptContext context;
    private List<BufferedImage> rewardSprites;
    private String status;

    private final Color HEADER_COLOR = new Color(46,204,113);

    public MTAPaint(ScriptContext context) {
        this.context = context;
        rewardSprites = new ArrayList<>();
    }

    public void loadRewards() {
        rewardSprites.clear();
        try {
            for (Reward r : context.getMTA().getRewardQueue().getProcessingRewards()) {
                rewardSprites.add(ImageIO.read(new URL(r.getItemSprite())));
            }
        } catch (IOException e) {
            System.out.println("failed to load reward sprite");
        }
    }

    public void onPaint(Graphics2D g) {
        try {
            g.setColor(new Color(0.2f, 0.2f, 0.2f, 0.5f));
            g.fillRect(5, 40, 160, 280);
            g.setColor(HEADER_COLOR);
            g.fillRect(7, 42, 156, 30);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 13));
            g.drawString(getScriptString(), 25, 60);
            g.setFont(new Font("Arial", Font.PLAIN, 11));
            g.drawString("Time running: " + context.getRuntime(), 10, 90);
            g.drawString("Status: " + status, 10, 110);
            g.drawString("Magic XP: " + context.getDB().getSkillTracker().getGainedExperience(Skill.MAGIC) +
                    "(+" + context.getDB().getSkillTracker().getGainedLevels(Skill.MAGIC) + ")", 10, 130);
            g.setColor(Color.WHITE);
            g.drawString("Reward Queue:", 10, 150);
            if (rewardSprites != null && rewardSprites.size() != 0) {
                for (int i = 0; i < ((rewardSprites.size() > 3) ? 3 : rewardSprites.size()); i++) {
                    int x = 15 + (i * 35);
                    g.drawImage(rewardSprites.get(i), x, 160, null);
                }
                g.setColor(new Color(0f, 0.7f, 0f, 0.3f));
                int width = rewardSprites.get(0).getWidth();
                g.fillRect(10, 158, width + 10, rewardSprites.get(0).getHeight() + 3);
            } else {
                g.drawString("None!", 15, 170);
            }

            g.setColor(Color.BLACK);
            int nameWidth = g.getFontMetrics().stringWidth(context.getDB().getClient().getLocalPlayer().getName());
            g.fillRect(7, 459, Math.round(nameWidth * 1.2f), 15);
            g.setColor(new Color(56, 56, 56));
            for (int i = 0; i < MTARoom.values().length; i++) {
                g.fillRect(10, 200 + (i * 30), 150, 20);
            }
            for (int i = 0; i < MTARoom.values().length; i++) {
                MTARoom room = MTARoom.values()[i];
                g.setColor(room.getPaintColor());
                int y = 200 + (i * 30);
                g.fillRect(10, y, getBarPercentageWidth(room), 20);
                g.setColor(Color.WHITE);
                y = 213 + (i * 30);
                g.drawString(room.name(), 15, 213 + (i * 30));
                String progression = getProgression(room);
                int strWidth = g.getFontMetrics().stringWidth(progression);
                g.drawString(progression, 155 - strWidth, y);
            }
            if (rewardSprites.size() > 3) {
                g.drawString("+" + (rewardSprites.size() - 3) + "...", 140, 180);
            }
        /*
        g.drawString("HP % = " + context.getMTA().getGraveyardHandler().getHPPercent(), 300,300);
         **/
        } catch (Exception e) {
            System.out.println("Paint error");
            e.printStackTrace();
        }
    }

    private String getProgression(MTARoom room) {
        return context.getMTA()
                .getPizzazPoints(room) + "/" +
                context.getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(room);
    }

    private String getScriptString() {
        return context.getManifest().name() + " v" + context.getManifest().version();
    }

    private int getBarPercentageWidth(MTARoom room) {
        return Math.round(ScriptMath.getPercentage(
                context.getMTA().getPizzazPoints(room),
                context.getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(room)) * 1.5f);
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
