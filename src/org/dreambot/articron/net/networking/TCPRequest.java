package org.dreambot.articron.net.networking;

public enum TCPRequest {

    IDENTIFY(0x7f),
    NEED_A_MULE(0x2f),
    MULE_IS_COMING(0x3f),
    DONE_MULING(0x1f);

    TCPRequest(int id) {
        this.id = id;
    }

    private int id;

    public int getOpcode() {
        return id;
    }


    public static TCPRequest forOpcode(int opcode) {
        for (TCPRequest request : TCPRequest.values()) {
            if (request.getOpcode() == opcode) {
                return request;
            }
        }
        return null;
    }
}
