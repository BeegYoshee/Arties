package org.dreambot.articron.feature;

import java.util.ArrayDeque;
import java.util.Queue;

public class MuleQueue {

    private Queue<MuleRequest> requestQueue;

    public MuleQueue() {
        requestQueue = new ArrayDeque<>();
    }

    public void addMuleRequest(MuleRequest request) {
        if (request != null) {
            requestQueue.add(request);
        }
    }

    public boolean hasPendingRequests() {
        return requestQueue.size() > 0;
    }

    public MuleRequest getCurrentRequest() {
        return requestQueue.peek();
    }

    public void finishedCurrentRequest() {
        requestQueue.poll();
    }
}
