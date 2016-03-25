package tech.flatstone.appliedlogistics.common.grid;

import java.util.UUID;

public class TransportContainer {
    private String unlocalizedName;
    private Object load;

    private UUID source;
    private UUID destination;

    public TransportContainer(UUID source, String unlocalizedName,Object load) {
        this.load = load;
        this.source = source;
        this.unlocalizedName = unlocalizedName;
    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public UUID getDestination() {
        return destination;
    }

    public UUID getSource() {
        return source;
    }
}
