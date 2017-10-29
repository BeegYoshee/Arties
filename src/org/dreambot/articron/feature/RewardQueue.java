package org.dreambot.articron.feature;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import org.dreambot.articron.data.Reward;

/**
 * Author: Articron
 * Date:   16/10/2017.
 */
public class RewardQueue {

    private Queue<Reward> rewardQueue;
    private List<Reward> doneList;
    private int length;

    public RewardQueue() {
        this.rewardQueue = new ArrayDeque<>();
        this.doneList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return rewardQueue.isEmpty();
    }

    public void add(Reward... rewards) {
        rewardQueue.addAll(Arrays.asList(rewards));
    }

    public Reward getCurrentReward() {
        return rewardQueue.peek();
    }

    public void markCurrentDone() {
        doneList.add(rewardQueue.poll());
    }

    public List<Reward> getFinishedRewards() {
        return doneList;
    }

    public Queue<Reward> getProcessingRewards() {
        return rewardQueue;
    }

}
