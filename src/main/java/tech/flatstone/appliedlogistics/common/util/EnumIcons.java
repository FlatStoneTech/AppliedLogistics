package tech.flatstone.appliedlogistics.common.util;

public enum EnumIcons {
    LOCK_OVERLAY("ov_lock.png", 16, 16),
    ERROR_OVERLAY("ov_error.png", 16, 16),
    ;

    private final String fileName;
    private final int width;
    private final int height;

    EnumIcons(String fileName, int width, int height) {
        this.fileName = fileName;
        this.width = width;
        this.height = height;
    }

    public String getFileName() {
        return fileName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}