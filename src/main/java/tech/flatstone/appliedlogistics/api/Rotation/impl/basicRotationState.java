package tech.flatstone.appliedlogistics.api.Rotation.impl;

import com.sun.istack.internal.NotNull;
import net.minecraft.util.EnumFacing;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotationState;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.ISide;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * used like {@link DRotationState} but bi directional:
 * may rotate Top and Front.
 */
public class basicRotationState implements IRotationState {
    List<ISide> Sides;
    public static final int[] Default = new int[]{2, 1};

    @Override
    public int[] getDefaults() {
        return Default;
    }

    public basicRotationState() {
        Sides = new ArrayList<>();

        Sides.add(new basicSide(Face.Top, EnumFacing.UP));
        Sides.add(new basicSide(Face.Bottom, EnumFacing.DOWN));
        Sides.add(new basicSide(Face.Front, EnumFacing.NORTH));
        Sides.add(new basicSide(Face.Back, EnumFacing.SOUTH));
        Sides.add(new basicSide(Face.Right, EnumFacing.EAST));
        Sides.add(new basicSide(Face.Left, EnumFacing.WEST));
    }

    public basicRotationState(@NotNull List<ISide> sides) {
        this.Sides = sides;
    }

    public basicRotationState(EnumFacing Front) {
        this();
        applyRT(Face.Front, Front);
    }

    public basicRotationState(int[] sides) {
        this();
        Load(sides);
    }


    @Override
    public EnumFacing getbyside(Face side) {
        return Sides.get(side.ordinal()).getFacing();
    }

    @Override
    public Face getbyEF(EnumFacing facing) {
        for (int i = 0; i < Sides.size(); i++) {
            if (Sides.get(i).getFacing().equals(facing))
                return Face.byId(i);
        }

        return null;
    }

    public int[] Save() {
        int sides[] = new int[2];
        sides[0] = getbyside(Face.Front).ordinal();
        sides[1] = getbyside(Face.Top).ordinal();
        return sides;
    }


    @Override
    public basicRotationState Load(int[] sides) {
        if (sides.length < 2)
            throw new IllegalStateException("Couldn't load from array since it's too small");
        applyRT(Face.Front, EnumFacing.getFront(sides[0]));
        applyRT(Face.Top, EnumFacing.getFront(sides[1]));
        return this;
    }

    @Override
    public basicRotationState copy() {
        return new basicRotationState().Load(Save().clone());
    }

    @Override
    public IRotationState applyRT(Face side, EnumFacing face) {
        Sides.get(getbyEF(face).Opposite().ordinal()).setFacing(getbyside(side));
        Sides.get(getbyEF(face).ordinal()).setFacing(getbyside(side).getOpposite());

        Sides.get(side.Opposite().ordinal()).setFacing(face.getOpposite());
        Sides.get(side.ordinal()).setFacing(face);

        return this;
    }

    public void dumpsides() {
        LogHelper.info("TFacing:" + Sides.get(0).getFacing());
        LogHelper.info("FFacing:" + Sides.get(2).getFacing());
    }

    @Override
    public boolean validate() {
        List sideValidated = new ArrayList<>();
        for (ISide side : Sides) {
            if (getbyside(side.getSide().Opposite()).equals(null)) return false;
            sideValidated.add(side.getFacing().ordinal());
        }
        if (!sideValidated.contains(EnumFacing.UP.ordinal()) ||
                !sideValidated.contains(EnumFacing.NORTH.ordinal()) ||
                !sideValidated.contains(EnumFacing.EAST.ordinal()))
            return false;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IRotationState)
            return Arrays.equals(Save(), ((IRotationState) obj).Save());
        else return false;
    }
}
