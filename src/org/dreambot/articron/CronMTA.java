package org.dreambot.articron;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.MessageListener;
import org.dreambot.api.wrappers.widgets.message.Message;
import org.dreambot.articron.behaviour.EnableRunning;
import org.dreambot.articron.behaviour.SwitchStave;
import org.dreambot.articron.data.AlchemyDrop;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.fw.Manager;
import org.dreambot.articron.fw.ScriptContext;
import org.dreambot.articron.loader.HImageLoader;
import org.dreambot.articron.ui.MainUI;

/**
 * Author: Articron
 * Date:   14/10/2017.
 */
@ScriptManifest(
        category = Category.MINIGAME,
        name = "ArtiMTA PRO",
        author = "Articron",
        version = 1.23D,
        description = "Does the MTA minigame to obtain infinity items. 250-400K/hr, profitable magic xp! " +
        "See the script thread for user instructions!"
)
public class CronMTA extends AbstractScript implements MessageListener{

    private ScriptContext context;

    @Override
    public void onStart() {
        context = new ScriptContext(this, getManifest());
        getWalking().setRunThreshold(Calculations.random(30,50));
        Manager.init(context);
        Manager.commit(

                new EnableRunning().when(
                        () -> !getWalking().isRunEnabled() && getWalking().getRunEnergy() >= getWalking().getRunThreshold()
                ),

                new SwitchStave().when(
                        () -> !context.getMTA().hasValidStaff()
                )
        );
        new MainUI(
                "ArtiMTA-Pro V" + getManifest().version(),
                HImageLoader.loadImage("https://i.imgur.com/SGA9et4.png"),
                context
        );
    }



    @Override
    public int onLoop() {
        return Manager.cycleNodes();
    }

    @Override
    public void onPaint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_LCD_CONTRAST, 100);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,RenderingHints.VALUE_STROKE_PURE);
        context.getPaint().onPaint(g2);
    }

    @Override
    public void onGameMessage(Message message) {
        if (context.getMTA().getCurrentRoom() == MTARoom.ALCHEMY) {
            context.getMTA().getAlchemyHandler().setFoundItem(
                    AlchemyDrop.forMessage(message.getMessage()), getGameObjects().closest("Cupboard").getTile());
        }
    }

    @Override
    public void onPlayerMessage(Message message) {

    }

    @Override
    public void onTradeMessage(Message message) {

    }

    @Override
    public void onPrivateInMessage(Message message) {

    }

    @Override
    public void onPrivateOutMessage(Message message) {

    }
}
