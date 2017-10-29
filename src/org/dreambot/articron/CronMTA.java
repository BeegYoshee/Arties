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
import org.dreambot.articron.behaviour.DialogueHandler;
import org.dreambot.articron.behaviour.LeaveRoom;
import org.dreambot.articron.behaviour.PortalEnter;
import org.dreambot.articron.behaviour.alchemy.AlchemyGroup;
import org.dreambot.articron.behaviour.alchemy.children.AlchItem;
import org.dreambot.articron.behaviour.alchemy.children.DepositGold;
import org.dreambot.articron.behaviour.alchemy.children.LootItem;
import org.dreambot.articron.behaviour.enchanting.EnchantingGroup;
import org.dreambot.articron.behaviour.enchanting.children.EnchantStones;
import org.dreambot.articron.behaviour.enchanting.children.LootStones;
import org.dreambot.articron.behaviour.enchanting.children.WorldHop;
import org.dreambot.articron.behaviour.graveyard.GraveyardGroup;
import org.dreambot.articron.behaviour.graveyard.children.ConvertBones;
import org.dreambot.articron.behaviour.graveyard.children.DepositFruit;
import org.dreambot.articron.behaviour.graveyard.children.EatFruit;
import org.dreambot.articron.behaviour.graveyard.children.LootBones;
import org.dreambot.articron.behaviour.outside.OutsideGroup;
import org.dreambot.articron.behaviour.outside.children.BuyReward;
import org.dreambot.articron.behaviour.outside.children.OpenShop;
import org.dreambot.articron.behaviour.outside.children.WalkToPortals;
import org.dreambot.articron.behaviour.outside.children.WalkToShop;
import org.dreambot.articron.behaviour.telekinetic.ApproachGroup;
import org.dreambot.articron.behaviour.telekinetic.MazeGroup;
import org.dreambot.articron.behaviour.telekinetic.SolutionGroup;
import org.dreambot.articron.behaviour.telekinetic.SolveGroup;
import org.dreambot.articron.behaviour.telekinetic.children.ApproachMaze;
import org.dreambot.articron.behaviour.telekinetic.children.CastTelegrab;
import org.dreambot.articron.behaviour.telekinetic.children.EndMaze;
import org.dreambot.articron.behaviour.telekinetic.children.ObserveMaze;
import org.dreambot.articron.behaviour.telekinetic.children.RunToCastTile;
import org.dreambot.articron.data.AlchemyDrop;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.fw.Manager;
import org.dreambot.articron.fw.ScriptContext;

/**
 * Author: Articron
 * Date:   14/10/2017.
 */
@ScriptManifest(
        category = Category.MINIGAME,
        name = "ArtiMTA PRO",
        author = "Articron",
        version = 1.17D,
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
        context.getMTA().getRewardQueue().add(Reward.INFINITY_BOOTS);
        context.getMTA().getRewardQueue().add(Reward.MASTER_WAND);
        context.getMTA().getRewardQueue().add(Reward.INFINITY_HAT);
        context.getMTA().getRewardQueue().add(Reward.INFINITY_HAT);
        context.getMTA().getRewardQueue().add(Reward.INFINITY_HAT);
        context.getPaint().loadRewards();
        Manager.commit(

                /*
                 * General event-nodes
                 */
                new DialogueHandler().when(
                        () -> context.getDB().getDialogues().inDialogue()
                ),

                /*
                 * Outside
                 */
                new OutsideGroup("Outside", () -> context.getMTA().isOutside()).addToGroup(

                      new WalkToPortals().when(
                              () -> !context.getMTA().canBuyReward() && context.getDB().getLocalPlayer().getTile().getZ() == 1
                      ),
                      new PortalEnter().when(
                              () -> !context.getMTA().canBuyReward()
                      ),
                      new WalkToShop().when(
                              () -> context.getMTA().canBuyReward() && context.getDB().getLocalPlayer().getTile().getZ() != 1
                      ),
                      new OpenShop().when(
                              () -> context.getMTA().canBuyReward() && context.getDB().getLocalPlayer().getTile().getZ() == 1
                                      && !context.getMTA().getMTAShop().isOpen()
                        ),
                      new BuyReward().when(
                              () -> context.getMTA().canBuyReward() && context.getMTA().getMTAShop().isOpen()
                      )
                ),

                /*
                 * Telekinetic
                 */
                new MazeGroup("Telekinetic room", () -> context.getMTA().getCurrentRoom() == MTARoom.TELEKINETIC).addToGroup(

                        new LeaveRoom().when(
                                () -> context.getMTA().getPizzazPoints(MTARoom.TELEKINETIC) >=
                                        context.getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(MTARoom.TELEKINETIC)
                        ),

                       new ApproachGroup("Approach", () -> !context.getMTA().getTelekineticHandler().isObserving()
                       && !context.getMTA().getTelekineticHandler().solvedMaze()).addToGroup(

                               new ApproachMaze().when(
                                       () -> context.getDB().getGameObjects().closest(10755).distance() >= 1
                               ),
                               new ObserveMaze().when(
                                       () -> context.getDB().getGameObjects().closest(10755).distance() <= 0
                               )
                       ),

                new SolveGroup("Solve", () ->
                                   context.getMTA().getTelekineticHandler().isObserving() &&
                                   context.getMTA().getTelekineticHandler().getSolver().isRead() &&
                                   !context.getMTA().getTelekineticHandler().solvedMaze())
                               .addToGroup(

                       new RunToCastTile().when(
                               () -> {
                                   try {
                                       return context.getMTA().getTelekineticHandler().getSolver().getCastLocation() != null &&
                                               (context.getMTA().getTelekineticHandler().getSolver().getCastLocation().distance() > 1 ||
                                                       !context.getMTA().getTelekineticHandler().getSolver().isInCorrectRow())
                                               ;
                                   } catch (NullPointerException e) {
                                       return false;
                                   }
                               }
                       ),
                       new CastTelegrab().when(
                               () -> {
                                   try {
                                       return context.getMTA().getTelekineticHandler().getSolver().isInCorrectRow();
                                   } catch (NullPointerException e) {
                                       return false;
                                   }
                               }
                       )
                ),

                new SolutionGroup("Solution", () -> context.getMTA().getTelekineticHandler().solvedMaze()).addToGroup(
                        new EndMaze().when(
                                () -> !context.getDB().getDialogues().inDialogue()
                        )
                )
          )
        );

        /*
         * Enchanting
         */
        Manager.commit(
                new EnchantingGroup("Enchantment room", () -> context.getMTA().getCurrentRoom() == MTARoom.ENCHANTING).addToGroup(
                        new LootStones().when(
                                () -> context.getMTA().getEnchantingHandler().roomContainsStones() &&
                                        context.getMTA().getEnchantingHandler().getViableStone() != null
                        ),
                        new EnchantStones().when(
                                () -> context.getMTA().getEnchantingHandler().hasStones()
                        ),
                        new WorldHop().when(
                                () -> !context.getMTA().getEnchantingHandler().roomContainsStones() &&
                                        !context.getMTA().getEnchantingHandler().hasStones()
                        )
                )
        );

        /*
         * Alchemy
         */
        Manager.commit(

                new AlchemyGroup("Alchemy room", () -> context.getMTA().getCurrentRoom() == MTARoom.ALCHEMY).addToGroup(
                        new LeaveRoom().when(
                                () -> context.getMTA().getPizzazPoints(MTARoom.ALCHEMY) >=
                                        context.getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(MTARoom.ALCHEMY)
                        ),
                        new DepositGold().when(
                                () -> context.getMTA().getAlchemyHandler().shouldDeposit()
                        ),
                        new LootItem().when(
                                () -> !context.getMTA().getAlchemyHandler().shouldAlch()
                        ),

                        new AlchItem().when(
                                () -> context.getMTA().getAlchemyHandler().canAlch() && context.getMTA().getAlchemyHandler().shouldAlch()
                        )
                )
        );

        /*
         * Graveyard
         */
        Manager.commit(
                new GraveyardGroup("Graveyard room", () -> context.getMTA().getCurrentRoom() == MTARoom.GRAVEYARD).addToGroup(
                        new LeaveRoom().when(
                                () -> context.getMTA().getPizzazPoints(MTARoom.GRAVEYARD) >=
                                        context.getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(MTARoom.GRAVEYARD)
                        ),
                        new EatFruit().when(
                                () -> context.getMTA().getGraveyardHandler().shouldEat() && context.getMTA().getGraveyardHandler().canEat()
                        ),
                        new LootBones().when(() -> context.getMTA().getGraveyardHandler().shouldLoot()
                        ),
                        new ConvertBones().when(
                                () -> context.getMTA().getGraveyardHandler().shouldCastB2B()
                        ),
                        new DepositFruit().when(
                                () -> context.getMTA().getGraveyardHandler().shouldDeposit() && !context.getMTA().getGraveyardHandler().shouldEat()
                        )
                )
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
