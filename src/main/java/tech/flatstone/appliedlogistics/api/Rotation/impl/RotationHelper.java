package tech.flatstone.appliedlogistics.api.Rotation.impl;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotatable;

public class RotationHelper {

    public static final double ROTATION_EDGE = 0.6;

    public static boolean rotateBlock(IRotatable block, double hitX, double hitY, double hitZ, EnumFacing side, BlockPos pos) {

        double hitX0 = 1 - hitX;
        double hitY0 = 1 - hitY;
        double hitZ0 = 1 - hitZ;

        if (hitX == 0.0 || hitX == 1.0) {
            if (hitZ0 > hitY && hitZ0 > hitY0
                    || hitZ > hitY && hitZ > hitY0) {

                if (hitZ > ROTATION_EDGE) {
                    return block.handleRotation(block.getbyEF(side), EnumFacing.SOUTH,pos);
                } else if (hitZ0 > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.NORTH,pos);
                else
                    return block.handleSetFront(side,pos);
            } else {

                if (hitY > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.UP,pos);
                else if (hitY0 > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.DOWN,pos);
                else
                    return block.handleSetFront(side,pos);
            }

        } else if (hitY == 0.0 || hitY == 1.0) {

            if (hitX0 > hitZ && hitX0 > hitZ0
                    || hitZ > hitY && hitZ > hitY0) {
                if (hitX > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.EAST,pos);
                else if (hitX0 > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.WEST,pos);
                else
                    return block.handleSetFront(side,pos);
            } else {

                if (hitZ > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.SOUTH,pos);
                else if (hitZ0 > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.NORTH,pos);
                else
                    return block.handleSetFront(side,pos);
            }

        } else {

            if (hitX0 > hitY && hitX0 > hitY0
                    || hitX > hitY && hitX > hitY0) {
                if (hitX > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.EAST,pos);
                else if (hitX0 > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.WEST,pos);
                else
                    return block.handleSetFront(side,pos);

            } else {
                if (hitY > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.UP,pos);
                else if (hitY0 > ROTATION_EDGE)
                    return block.handleRotation(block.getbyEF(side), EnumFacing.DOWN,pos);
                else
                    return block.handleSetFront(side,pos);
            }
        }
    }

    public static final int coupleBefore(EnumFacing face, int max, int min) {
        if (face.getAxis().equals(EnumFacing.Axis.Y) || face.getAxis().equals(EnumFacing.Axis.X))
            face = face.getOpposite();
        if (face.ordinal() > min + 1 && face.ordinal() < max)
            return (face.ordinal() + (face.ordinal() % 2) * 2 - 1) % max;
        if (face.ordinal() == max) return min + 1;
        if (face.ordinal() > max) return max;
        return max - face.ordinal();
    }

    /**
     * Planes: Horizontal{
     * NORTH,UP,SOUTH,DOWN
     * },
     * Vertical {
     * EAST, NORTH, WEST, DOWN
     * }
     * return Whether both planes are the same.
     */
    public static boolean isOnSamePlane(EnumFacing facing, EnumFacing main) {
        if (main.getAxis().equals(EnumFacing.Axis.Z) ||
                facing.getAxis().equals(EnumFacing.Axis.Z) ||
                main.getAxis().equals(facing.getAxis()))
            return true;
        return false;
    }

    public static Face defaultFace(EnumFacing facing) {
        if (facing.getAxis().equals(EnumFacing.Axis.Y) || facing.getAxis().equals(EnumFacing.Axis.X))
            return Face.byId(facing.getOpposite().ordinal());
        return Face.byId(facing.ordinal());
    }

    public static EnumFacing defualtDirection(Face side) {
        EnumFacing defaultF = EnumFacing.getFront(side.ordinal());
        if (defaultF.getAxis().equals(EnumFacing.Axis.Y))
            return defaultF.getOpposite();
        return defaultF;
    }
}
