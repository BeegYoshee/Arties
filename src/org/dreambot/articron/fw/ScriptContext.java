package org.dreambot.articron.fw;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.articron.behaviour.*;
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
import org.dreambot.articron.behaviour.telekinetic.children.*;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.fw.handlers.MTAHandler;
import org.dreambot.articron.util.PaintDrawer;

/**
 * Author: Articron
 * Date:   14/10/2017.
 */
public class ScriptContext {

    private final MethodContext CONTEXT;
    private final MTAHandler mtaHandler;
    private final PaintDrawer paintDrawer;
    private final ScriptManifest manifest;
    private final Timer timer;

    public ScriptContext(MethodContext methodContext, ScriptManifest manifest) {
        CONTEXT = methodContext;
        this.manifest = manifest;
        mtaHandler = new MTAHandler(this);
        paintDrawer = new PaintDrawer(this);
        this.timer = new Timer();
        CONTEXT.getSkillTracker().start();
    }

    public MethodContext getDB() {
        return CONTEXT;
    }

    public MTAHandler getMTA() {
        return mtaHandler;
    }


    public PaintDrawer getPaint() {
        return paintDrawer;
    }

    public String getRuntime() {
        return timer.formatTime();
    }

    public ScriptManifest getManifest() {
        return manifest;
    }


    public void loadScriptNodes() {
        getPaint().loadRewards();

        Manager.commit(
                new SwitchStave().when(
                        () -> !getMTA().hasValidStaff()
                )
        );
        Manager.commit(


                /*
                 * General event-nodes
                 */
                new DialogueHandler().when(
                        () -> getDB().getDialogues().inDialogue()
                ),



                /*
                 * Outside
                 */
                new OutsideGroup("Outside", () -> getMTA().isOutside()).addToGroup(

                        new TalkToHatter().when(
                                () -> !getMTA().hasProgressHat()
                        ),

                        new WearHat().when(
                                () -> getDB().getInventory().contains("Progress hat")
                        ),

                        new WalkToPortals().when(
                                () -> !getMTA().canBuyReward() && getDB().getLocalPlayer().getTile().getZ() == 1
                        ),
                        new PortalEnter().when(
                                () -> !getMTA().canBuyReward()
                        ),
                        new WalkToShop().when(
                                () -> getMTA().canBuyReward() && getDB().getLocalPlayer().getTile().getZ() != 1
                        ),
                        new OpenShop().when(
                                () -> getMTA().canBuyReward() && getDB().getLocalPlayer().getTile().getZ() == 1
                                        && !getMTA().getMTAShop().isOpen()
                        ),
                        new BuyReward().when(
                                () -> getMTA().canBuyReward() && getMTA().getMTAShop().isOpen()
                        )
                ),

                /*
                 * Telekinetic
                 */
                new MazeGroup("Telekinetic room", () -> getMTA().getCurrentRoom() == MTARoom.TELEKINETIC).addToGroup(

                        new LeaveRoom().when(
                                () -> getMTA().getPizzazPoints(MTARoom.TELEKINETIC) >=
                                        getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(MTARoom.TELEKINETIC)
                        ),

                        new ApproachGroup("Approach", () -> !getMTA().getTelekineticHandler().isObserving()
                                && !getMTA().getTelekineticHandler().solvedMaze()).addToGroup(

                                new ApproachMaze().when(
                                        () -> getDB().getGameObjects().closest(10755).distance() >= 1
                                ),
                                new ObserveMaze().when(
                                        () -> getDB().getGameObjects().closest(10755).distance() <= 0
                                )
                        ),

                        new SolveGroup("Solve", () ->
                                getMTA().getTelekineticHandler().isObserving() &&
                                        getMTA().getTelekineticHandler().getSolver().isRead() &&
                                        !getMTA().getTelekineticHandler().solvedMaze())
                                .addToGroup(

                                        new CastTelegrab().when(
                                                () -> getMTA().getTelekineticHandler().getSolver().isInCorrectRow()
                                        ),

                                        new RunToCastTile().when(
                                                () -> !getMTA().getTelekineticHandler().getSolver().isInCorrectRow() &&
                                                        getMTA().getTelekineticHandler().getSolver().getCastTile() != null
                                        )

                                ),

                        new SolutionGroup("Solution", () -> getMTA().getTelekineticHandler().solvedMaze()).addToGroup(
                                new EndMaze().when(
                                        () -> !getDB().getDialogues().inDialogue()
                                )
                        )
                )
        );

        /*
         * Enchanting
         */
        Manager.commit(
                new EnchantingGroup("Enchantment room", () -> getMTA().getCurrentRoom() == MTARoom.ENCHANTING).addToGroup(
                        new LootStones().when(
                                () -> getMTA().getEnchantingHandler().roomContainsStones() &&
                                        getMTA().getEnchantingHandler().getViableStone() != null
                        ),
                        new EnchantStones().when(
                                () -> getMTA().getEnchantingHandler().hasStones()
                        ),
                        new WorldHop().when(
                                () -> !getMTA().getEnchantingHandler().roomContainsStones() &&
                                        !getMTA().getEnchantingHandler().hasStones()
                        )
                )
        );

        /*
         * Alchemy
         */
        Manager.commit(

                new AlchemyGroup("Alchemy room", () -> getMTA().getCurrentRoom() == MTARoom.ALCHEMY).addToGroup(
                        new LeaveRoom().when(
                                () -> getMTA().getPizzazPoints(MTARoom.ALCHEMY) >=
                                        getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(MTARoom.ALCHEMY)
                        ),
                        new DepositGold().when(
                                () -> getMTA().getAlchemyHandler().shouldDeposit()
                        ),
                        new LootItem().when(
                                () -> !getMTA().getAlchemyHandler().shouldAlch()
                        ),

                        new AlchItem().when(
                                () -> getMTA().getAlchemyHandler().canAlch() && getMTA().getAlchemyHandler().shouldAlch()
                        )
                )
        );

        /*
         * Graveyard
         */
        Manager.commit(
                new GraveyardGroup("Graveyard room", () -> getMTA().getCurrentRoom() == MTARoom.GRAVEYARD).addToGroup(
                        new LeaveRoom().when(
                                () -> getMTA().getPizzazPoints(MTARoom.GRAVEYARD) >=
                                        getMTA().getRewardQueue().getCurrentReward().getRequiredPoints(MTARoom.GRAVEYARD)
                        ),
                        new EatFruit().when(
                                () -> getMTA().getGraveyardHandler().shouldEat() && getMTA().getGraveyardHandler().canEat()
                        ),
                        new LootBones().when(() -> getMTA().getGraveyardHandler().shouldLoot()
                        ),
                        new ConvertBones().when(
                                () -> getMTA().getGraveyardHandler().shouldCastB2B()
                        ),
                        new DepositFruit().when(
                                () -> getMTA().getGraveyardHandler().shouldDeposit() && !getMTA().getGraveyardHandler().shouldEat()
                        )
                )
        );
    }
}
