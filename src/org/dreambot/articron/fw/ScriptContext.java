package org.dreambot.articron.fw;

import org.dreambot.api.methods.MethodContext;
import org.dreambot.api.randoms.RandomManager;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.articron.behaviour.*;
import org.dreambot.articron.behaviour.mta.*;
import org.dreambot.articron.behaviour.mta.alchemy.AlchemyGroup;
import org.dreambot.articron.behaviour.mta.alchemy.children.AlchItem;
import org.dreambot.articron.behaviour.mta.alchemy.children.DepositGold;
import org.dreambot.articron.behaviour.mta.alchemy.children.LootItem;
import org.dreambot.articron.behaviour.mta.enchanting.EnchantingGroup;
import org.dreambot.articron.behaviour.mta.enchanting.children.EnchantStones;
import org.dreambot.articron.behaviour.mta.enchanting.children.LootStones;
import org.dreambot.articron.behaviour.mta.enchanting.children.WorldHop;
import org.dreambot.articron.behaviour.mta.graveyard.GraveyardGroup;
import org.dreambot.articron.behaviour.mta.graveyard.children.ConvertBones;
import org.dreambot.articron.behaviour.mta.graveyard.children.DepositFruit;
import org.dreambot.articron.behaviour.mta.graveyard.children.EatFruit;
import org.dreambot.articron.behaviour.mta.graveyard.children.LootBones;
import org.dreambot.articron.behaviour.mta.mule_interactions.GiveLoot;
import org.dreambot.articron.behaviour.mta.mule_interactions.RequestMule;
import org.dreambot.articron.behaviour.mta.mule_interactions.TradeMule;
import org.dreambot.articron.behaviour.mta.outside.OutsideGroup;
import org.dreambot.articron.behaviour.mta.outside.children.BuyReward;
import org.dreambot.articron.behaviour.mta.outside.children.OpenShop;
import org.dreambot.articron.behaviour.mta.outside.children.WalkToPortals;
import org.dreambot.articron.behaviour.mta.outside.children.WalkToShop;
import org.dreambot.articron.behaviour.mta.telekinetic.ApproachGroup;
import org.dreambot.articron.behaviour.mta.telekinetic.MazeGroup;
import org.dreambot.articron.behaviour.mta.telekinetic.SolutionGroup;
import org.dreambot.articron.behaviour.mta.telekinetic.SolveGroup;
import org.dreambot.articron.behaviour.mta.telekinetic.children.*;
import org.dreambot.articron.behaviour.muling.*;
import org.dreambot.articron.data.MTARoom;
import org.dreambot.articron.data.MuleLocation;
import org.dreambot.articron.data.Reward;
import org.dreambot.articron.data.ScriptMode;
import org.dreambot.articron.fw.handlers.MTAHandler;
import org.dreambot.articron.fw.handlers.MuleHandler;
import org.dreambot.articron.net.MuleClient;
import org.dreambot.articron.net.MuleServer;
import org.dreambot.articron.paint.Graphics2DPaint;
import org.dreambot.articron.paint.impl.MTAPaint;
import org.dreambot.articron.paint.impl.MulePaint;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Author: Articron
 * Date:   14/10/2017.
 */
public class ScriptContext {

    private final MethodContext CONTEXT;
    private final MTAHandler mtaHandler;
    private Graphics2DPaint MTAPaint;
    private MuleServer muleServer;
    private MuleClient muleClient;
    private MuleLocation muleLocation;
    private MuleHandler muleHandler;
    private final ScriptManifest manifest;
    private final Timer timer;
    private ScriptMode mode;
    private RandomManager randomManager;

    private boolean shouldMule = false;
    private boolean shouldPaint = false;

    public ScriptContext(MethodContext methodContext, ScriptManifest manifest, RandomManager randomManager) {
        CONTEXT = methodContext;
        this.manifest = manifest;
        this.randomManager = randomManager;
        mtaHandler = new MTAHandler(this);
        muleHandler = new MuleHandler(this);
        MTAPaint = new MTAPaint(this);
        this.timer = new Timer();
        CONTEXT.getSkillTracker().start();

    }

    public MethodContext getDB() {
        return CONTEXT;
    }

    public MTAHandler getMTA() {
        return mtaHandler;
    }


    public Graphics2DPaint getPaint() {
        return MTAPaint;
    }

    public String getRuntime() {
        return timer.formatTime();
    }

    public ScriptManifest getManifest() {
        return manifest;
    }


    public void shutdown() {
        Manager.cleanRoot();
        Manager.commit(
                new LeaveRoom().when(
                        () -> !getMTA().isOutside()
                ),
                new ShutdownScript().when(
                        () -> getMTA().isOutside()
                )
        );
    }

    public void loadMode(ScriptMode mode) {
        Manager.cleanRoot();
        this.mode = mode;
        shouldPaint = true;
        if (mode == ScriptMode.MULE) {
            MTAPaint = new MulePaint(this);
            System.out.println("Loading mule nodes");
            Manager.commit(

                    new WalkToMulingSpot().when(
                            () -> getDB().getClient().isLoggedIn() && !getMuleHandler().isInMulingSpot()
                    ),
                    new LogoutMule().when(
                            () -> !getMTA().getMuleQueue().hasPendingRequests() && getDB().getClient().isLoggedIn()
                            && getMuleHandler().isInMulingSpot()
                    ),
                    new Login().when(
                            () -> getMTA().getMuleQueue().hasPendingRequests() && !getDB().getClient().isLoggedIn()
                    ),

                    new MuleGroup("MuleGroup", () -> getMTA().getMuleQueue().hasPendingRequests() && getDB().getClient().isLoggedIn()
                    ).addToGroup(
                          new HopWorld().when(
                                  () -> getMuleHandler().shouldHop()
                          ),
                          new TradeWorker().when(
                                  () -> getMuleHandler().isWorkerHere() && !getDB().getTrade().isOpen()
                          ),
                            new GiveSupplies().when(
                                    () -> getDB().getTrade().isOpen(1) && !getMuleHandler().getTrading().offerComplete()
                            ),
                            new AcceptTrade().when(
                                    () -> getDB().getTrade().isOpen() && getMuleHandler().getTrading().offerComplete()
                                    && getMuleHandler().getTrading().isOfferingMTAReward()
                                    && getMTA().getMuleQueue().getCurrentRequest().getBotName().equals(getDB().getTrade().getTradingWith())
                            )
            ));
        }

        if (mode == ScriptMode.LOOKING_FOR_MULE) {
            Manager.commit(
                    new WalkToMulingSpot().when(
                        () -> !getMuleHandler().isInMulingSpot() && hasToMule()
                    ),
                    new RequestMule().when(
                            () -> getMuleHandler().isInMulingSpot() && hasToMule() && !getMuleHandler().isMuleHere()
                    ),
                    new TradeMule().when(
                            () -> getMuleHandler().isInMulingSpot() && hasToMule() && getMuleHandler().isMuleHere()
                            && !getDB().getTrade().isOpen()
                    ),
                    new GiveLoot().when(
                            () -> getDB().getTrade().isOpen(1) && getMTA().getRewardsInInventory().length != 0
                    ),
                    new AcceptTrade().when(
                            () -> getDB().getTrade().isOpen() && getMTA().getRewardsInInventory().length == 0
                    )
            );
        }
        if (mode == ScriptMode.WORKER) {
            if (shouldMule) {
                Manager.addNodeLoader(this::hasToMule, ScriptMode.LOOKING_FOR_MULE);
                Manager.addNodeLoader(() -> !hasToMule() &&
                        !getDB().getTrade().isOpen(), ScriptMode.WORKER);
            }
            getPaint().loadRewards();
            Manager.commit(

                    new SwitchStave().when(
                            () -> !getMTA().hasValidStaff()
                    ),
                    new DialogueHandler().when(
                            () -> getDB().getDialogues().inDialogue()
                    ),

                    new OutsideGroup("Outside", () -> getMTA().isOutside()).addToGroup(
                            new OutsideMTAGroup("Outside MTA", () -> !getMTA().isInsideMTABuilding()).addToGroup(
                                    new EnterBuilding().when(
                                            () -> !getMTA().isInsideMTABuilding()
                                    )
                            ),
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
                                    () ->  getMTA().getAlchemyHandler().shouldAlch()
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

    public void setMuleClient(int port) {
        if (this.muleClient == null) {
            this.muleClient = new MuleClient(null,port,this);
        }
    }

    public void setMuleClient(String ip, int port) {
        if (this.muleClient == null) {
            this.muleClient = new MuleClient(ip, port,this);
        }
    }

    public void setMuleServer(int port, String key) {
        if (this.muleServer == null) {
            this.muleServer = new MuleServer(this, port, key, getDB().getLocalPlayer().getName());
        }
    }

    public MuleClient getMuleClient() {
        return muleClient;
    }

    public MuleServer getMuleServer() {
        return muleServer;
    }

    public ScriptMode getMode() {
        return mode;
    }

    public void setMode(ScriptMode mode) {
        this.mode = mode;
    }

    public MuleHandler getMuleHandler() {
        return muleHandler;
    }

    public RandomManager getRandomManager() {
        return randomManager;
    }

    public MuleLocation getMuleLocation() {
        return muleLocation;
    }

    public void setMuleLocation(MuleLocation muleLocation) {
        this.muleLocation = muleLocation;
    }

    public boolean hasToMule() {
        if (!shouldMule) {
            return false;
        }
        for (Reward r : Arrays.stream(Reward.values()).filter(Reward::shouldMule).collect(Collectors.toList())) {
            if (getDB().getInventory().contains(r.getID())) {
                return true;
            }
        }
        return false;
    }

    public void shouldMule(boolean bool) {
        this.shouldMule = bool;
    }

    public boolean isShouldPaint() {
        return shouldPaint;
    }

    public void setShouldPaint(boolean shouldPaint) {
        this.shouldPaint = shouldPaint;
    }
}
