package com.pjhu.account;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class IdGenerator {
    private static final int TOTAL_BITS = 64;
    private static final int EPOCH_BITS = 42;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;
    private static final int MAX_NODE_ID = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int MAX_SEQUENCE = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    private static final int NODE_ID = createNodeId();

    // 2019-05-06T08:40:14.946Z
    private static final long CUSTOM_EPOCH = 1557131988965L;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    private static IdGenerator snowFlake;

    static {
        snowFlake = new IdGenerator();
    }

    public static synchronized long nextIdentity() {
        return snowFlake.generate();
    }

    private long generate() {
        return createNextId(NODE_ID);
    }

    private synchronized long createNextId(int nodeId) {
        long currentTimestamp = timestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;
        long id = currentTimestamp << (TOTAL_BITS - EPOCH_BITS);
        id |= (nodeId << (TOTAL_BITS - EPOCH_BITS - NODE_ID_BITS));
        id |= sequence;
        return id;
    }

    private static int createNodeId() {
        int newNodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (byte macByte : mac) {
                        sb.append(String.format("%02X", macByte));
                    }
                }
            }
            newNodeId = sb.toString().hashCode();
        } catch (Exception exception) {
            newNodeId = (new SecureRandom().nextInt());
        }
        newNodeId = newNodeId & MAX_NODE_ID;
        return newNodeId;
    }

    private static long timestamp() {
        return Instant.now().toEpochMilli() - CUSTOM_EPOCH;
    }

    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }
        return currentTimestamp;
    }
}
