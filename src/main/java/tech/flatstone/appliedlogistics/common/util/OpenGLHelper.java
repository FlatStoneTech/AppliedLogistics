package tech.flatstone.appliedlogistics.common.util;

import org.lwjgl.opengl.GL11;

public class OpenGLHelper {
    public static int[][] saveGLState(int[] bitsToSave) {
        if (bitsToSave == null) {
            return null;
        }

        int[][] savedGLState = new int[bitsToSave.length][2];
        int count = 0;

        for (int glBit : bitsToSave) {
            savedGLState[count][0] = glBit;
            savedGLState[count++][1] = GL11.glIsEnabled(glBit) ? 1 : 0;
        }

        return savedGLState;
    }

    public static int[][] modifyGLState(int[] bitsToDisable, int[] bitsToEnable) {
        return modifyGLState(bitsToDisable, bitsToEnable, null);
    }

    public static int[][] modifyGLState(int[] bitsToDisable, int[] bitsToEnable, int[] bitsToSave) {
        if (bitsToDisable == null && bitsToEnable == null && bitsToSave == null) {
            return null;
        }

        int[][] savedGLState = new int[(bitsToDisable != null ? bitsToDisable.length : 0) + (bitsToEnable != null ? bitsToEnable.length : 0) + (bitsToSave != null ? bitsToSave.length : 0)][2];
        int count = 0;

        if (bitsToDisable != null) {
            for (int glBit : bitsToDisable) {
                savedGLState[count][0] = glBit;
                savedGLState[count++][1] = GL11.glIsEnabled(glBit) ? 1 : 0;
                GL11.glDisable(glBit);
            }
        }

        if (bitsToEnable != null) {
            for (int glBit : bitsToEnable) {
                savedGLState[count][0] = glBit;
                savedGLState[count++][1] = GL11.glIsEnabled(glBit) ? 1 : 0;
                GL11.glEnable(glBit);
            }
        }

        if (bitsToSave != null) {
            for (int glBit : bitsToSave) {
                savedGLState[count][0] = glBit;
                savedGLState[count++][1] = GL11.glIsEnabled(glBit) ? 1 : 0;
            }
        }

        return savedGLState;
    }

    public static void restoreGLState(int[][] savedGLState) {
        if (savedGLState == null) {
            return;
        }

        for (int[] glBit : savedGLState) {
            if (glBit[1] == 1)
                GL11.glEnable(glBit[0]);
            else
                GL11.glDisable(glBit[0]);
        }
    }
}
