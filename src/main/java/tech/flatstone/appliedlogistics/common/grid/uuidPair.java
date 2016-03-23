package tech.flatstone.appliedlogistics.common.grid;

import java.util.UUID;

public class uuidPair {
    public uuidPair(UUID uuid1, UUID uuid2) {
        this.uuid1 = uuid1;
        this.uuid2 = uuid2;
    }

    public UUID getUuid1() {
        return uuid1;
    }

    public UUID getUuid2() {
        return uuid2;
    }

    private UUID uuid1;
    private UUID uuid2;

}
