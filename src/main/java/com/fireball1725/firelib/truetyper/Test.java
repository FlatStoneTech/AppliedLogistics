package com.fireball1725.firelib.truetyper;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.ArrayList;

public class Test {
    public static ArrayList<TrueTypeFont> testFonts = new ArrayList<TrueTypeFont>();
    public static float[] white = {1f, 1f, 1f, 1f};
    public static boolean init = false;

    public static void initTest() {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font[] fonts = e.getAllFonts();
        {
            int max = 9;
            for (Font f : fonts) {
                boolean pass = true;
                for (TrueTypeFont ttf : testFonts) {
                    if (ttf.font.getFontName().startsWith(f.getFontName().substring(0, 1))) {
                        pass = false;
                        continue;
                    }
                }
                if (pass) {
                    max--;
                    if (max == 0) {
                        break;
                    }
                    testFonts.add(FontLoader.loadSystemFont(f.getFontName(), 24f, false));
                    testFonts.add(FontLoader.loadSystemFont(f.getFontName(), 24f, true));
                }
            }
        }
        init = true;

    }

    @SideOnly(Side.CLIENT)
    public static void doTest() {
        if (!init) {
            initTest();
        }
        for (int i = 0; i < testFonts.size(); i++) {
            int y = 10 + i * 12;
            int x = 10;
            FontHelper.drawString("He" + TextFormatting.GOLD + "llo " + TextFormatting.GREEN + "wor" + TextFormatting.YELLOW + "ld", x, y, testFonts.get(i), 0.64f, 0.64f, 1, 255, 255, 255, 255);
        }
    }
}
