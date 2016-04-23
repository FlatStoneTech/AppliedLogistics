package tech.flatstone.appliedlogistics.common.grid;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class WhitelistData {
    private boolean isWhitelist;
    private UUID parent;
    private UUID end;
    private List<String> list;

    public WhitelistData(boolean isWhitelist, UUID parent, UUID end, List<String> list) {
        setWhitelist(isWhitelist);
        setParent(parent);
        setEnd(end);
        setList(list);
    }

    public UUID getEnd() {
        return end;
    }

    public void setEnd(UUID end) {
        this.end = end;
    }

    public boolean isWhitelist() {
        return isWhitelist;
    }

    public void setWhitelist(boolean whitelist) {
        isWhitelist = whitelist;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = Collections.synchronizedList(list);
    }

    public UUID getParent() {
        return parent;
    }

    public void setParent(UUID parent) {
        this.parent = parent;
    }
}
