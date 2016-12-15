package tech.flatstone.appliedlogistics.api.Rotation.impl;

import com.google.common.base.Optional;
import net.minecraft.block.properties.IProperty;
import net.minecraft.util.EnumFacing;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotationState;

import java.util.ArrayList;
import java.util.Collection;

public class RotationProperty implements IProperty<String> {
    String Name;
    public final static ArrayList<String> ALLOWEDVALUES = new ArrayList();

    static {
        for (int i = 0; i < EnumFacing.values().length; i++) {
            for (int j = 0; j < EnumFacing.values().length; j++) {
                if (j == i) continue;
                else if (EnumFacing.getFront(i).getOpposite().equals(EnumFacing.getFront(j))) ;
                else
                    ALLOWEDVALUES.add(String.format("%s-%s", EnumFacing.getFront(i).getName(), EnumFacing.getFront(j).getName()));
            }
        }
    }

    public RotationProperty(String Name) {
        this.Name = Name;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public Collection<String> getAllowedValues() {
        return ALLOWEDVALUES;
    }

    @Override
    public Class<String> getValueClass() {
        return String.class;
    }

    @Override
    public Optional<String> parseValue(String value) {
        return Optional.fromNullable(value);
    }

    @Override
    public String getName(String value) {
        return value.replace("-", "_");
    }

    public static String parseValue(IRotationState state) {
        return String.format("%s%s%s", state.getbyside(Face.Front).getName(), "-", state.getbyside(Face.Top).getName());
    }
}
