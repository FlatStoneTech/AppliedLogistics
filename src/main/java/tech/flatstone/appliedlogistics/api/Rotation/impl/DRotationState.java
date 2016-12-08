package tech.flatstone.appliedlogistics.api.Rotation.impl;

import net.minecraft.util.EnumFacing;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotationState;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.ISide;

import java.util.Arrays;
import java.util.List;

/**
 * Used for only one-directional Rotation: Front
 */
public class DRotationState implements IRotationState {
    private ISide front;
    private List<EnumFacing> filter = null;
    private int[] defaults = new int[]{2, 1};

    public int[] getDefaults() {
        return defaults;
    }

    public DRotationState(EnumFacing Front) {
        front = new basicSide(Face.Front, Front);
    }

    public DRotationState() {
        this(EnumFacing.NORTH);
    }

    @Override
    public EnumFacing getbyside(Face side) {
        if (side.equals(Face.Front)) return front.getFacing();
        else if (side.equals(Face.Back))
            return front.getFacing().getOpposite();
        if (RotationHelper.isOnSamePlane(RotationHelper.defualtDirection(side), front.getFacing()))
            switch (side) {
                case Top:
                    return EnumFacing.getFront(RotationHelper.coupleBefore(front.getFacing(), 3, 0));
                case Bottom:
                    return EnumFacing.getFront(RotationHelper.coupleBefore(front.getFacing(), 3, 0)).getOpposite();
                case Right:
                    return EnumFacing.getFront(RotationHelper.coupleBefore(front.getFacing(), 5, 1));
                case Left:
                    return EnumFacing.getFront(RotationHelper.coupleBefore(front.getFacing(), 5, 1)).getOpposite();

            }
        return RotationHelper.defualtDirection(side.Opposite());
    }

    @Override
    public Face getbyEF(EnumFacing facing) {
        if (facing.equals(front.getFacing())) return Face.Front;
        else if (facing.equals(front.getFacing().getOpposite())) return Face.Back;
        else if (RotationHelper.isOnSamePlane(facing, front.getFacing())) {
            return RotationHelper.defaultFace(EnumFacing.getFront(front.getFacing().ordinal() - facing.getOpposite().ordinal() % 2));
        }
        return RotationHelper.defaultFace(facing);
    }

    @Override
    public int[] Save() {
        return new int[]{front.getFacing().ordinal()};
    }

    @Override
    public IRotationState Load(int[] sides) {
        return applyRT(front.getSide(), EnumFacing.getFront(sides[0]));
    }

    @Override
    public IRotationState copy() {
        return new DRotationState(front.getFacing());
    }

    public void Setfilter(List<EnumFacing> filter) {
        this.filter = filter;
    }

    @Override
    public IRotationState applyRT(Face side, EnumFacing face) {
        if (filter != null && filter.size() > 0)
            if (!filter.contains(face))
                return null;
        front.setFacing(face);
        return this;
    }

    @Override
    public boolean validate() {
        if (filter != null && filter.size() > 0)
            if (filter.contains(getbyside(Face.Front)))
                return true;
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IRotationState &&
                Arrays.equals(((IRotationState)obj).Save(),Save());
    }
}
