package tech.flatstone.appliedlogistics.api.Rotation.Rotation;

public enum Face {
    Top("top"), Bottom("bottom"),
    Front("front"), Back("back"),
    Right("right"), Left("left");
    String Name;

    public Face Opposite() {
        int Ordinal = ordinal();
        return Ordinal % 2 == 0 ? byId(Ordinal + 1): byId(Ordinal - 1) ;
    }

    public static Face byId(int id) {
        return values()[id];
    }

    Face(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name;
    }
}
