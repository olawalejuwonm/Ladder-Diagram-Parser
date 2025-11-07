public final class DataTypes {

    private DataTypes() {
        /* utility holder */ }

    /** Marker interface for all ladder elements. */
    public interface DT {
    }

    /** A coil with an associated variable name. */
    public static final class Coil implements DT {
        private final String var;

        public Coil(String var) {
            this.var = var;
        }


        @Override
        public String toString() {
            return var + " := ";
        }
    }

    /** A normally open contact with an associated variable name. */
    public static class Contact implements DT {
        public final String var;

        public Contact(String var) {
            this.var = var;
        }


        @Override
        public String toString() {
            return var + " /\\ ";
        }

    }

    /** A negated contact (inherits Contact). */
    public static final class NegatedContact extends Contact {
        public NegatedContact(String var) {
            super(var);
        }

        @Override
        public String toString() {
            return "¬" + this.var + " /\\ ";
        }
    }

    /** A vertical link in the ladder diagram */
    public static final class VerticalLink implements DT {
        public static final VerticalLink INSTANCE = new VerticalLink();

        private VerticalLink() {
        }

        @Override
        public String toString() {
            return "(";
        }
    }

    /** The end marker of a vertical link. */
    public static final class EndOfVerticalLink implements DT {
        public static final EndOfVerticalLink INSTANCE = new EndOfVerticalLink();

        private EndOfVerticalLink() {
        }

        @Override
        public String toString() {
            return ") \\/ ";
        }
    }

    /** A horizontal connection/link. */
    public static final class HorizontalLink implements DT {
        public static final HorizontalLink INSTANCE = new HorizontalLink();

        private HorizontalLink() {
        }

        @Override
        public String toString() {
            return "";
        }
    }

    /** The left-power rail of the ladder. */
    public static final class LeftPowerRail implements DT {
        public static final LeftPowerRail INSTANCE = new LeftPowerRail();

        private LeftPowerRail() {
        }

        @Override
        public String toString() {
            return "True";
        }
    }

    /** Represents an empty/no-op cell.  */
    public static final class Empty implements DT {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
