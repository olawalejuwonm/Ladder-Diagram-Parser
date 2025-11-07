public final class DatTypes {


    /** Marker interface for all ladder elements. */
    public interface DatTypesElement {
    }

    /** A coil with an associated variable name. */
    public static final class Coil implements DatTypesElement {
        private final String var;

        public Coil(String var) {
            this.var = var;
        }

        // public String getVar() {
        //     return var;
        // }

        @Override
        public String toString() {
            return var + " := ";
        }
    }

    /** A normally open contact with an associated variable name. */
    public static class Contact implements DatTypesElement {
        private final String var;

        public Contact(String var) {
            this.var = var;
        }

        public String getVar() {
            return " " + var + " ";
        }

        @Override
        public String toString() {
            return  var + " /\\ ";
        }

    }

    /** A negated contact (inherits Contact). */
    public static final class NegatedContact extends Contact {
        public NegatedContact(String var) {
            super(var);
        }

        @Override
        public String toString() {
            return "¬" + getVar() + " /\\ ";
        }
    }

    /** A vertical link in the ladder diagram */
    public static final class VerticalLink implements DatTypesElement {
        public static final VerticalLink INSTANCE = new VerticalLink();

        private VerticalLink() {
        }

        @Override
        public String toString() {
            return "(";
        }
    }

    /** The end marker of a vertical link. */
    public static final class EndOfVerticalLink implements DatTypesElement {
        public static final EndOfVerticalLink INSTANCE = new EndOfVerticalLink();

        private EndOfVerticalLink() {
        }

        @Override
        public String toString() {
            return ") V ";
        }
    }

    /** A horizontal connection/link. */
    public static final class HorizontalLink implements DatTypesElement {
        public static final HorizontalLink INSTANCE = new HorizontalLink();

        private HorizontalLink() {
        }

        @Override
        public String toString() {
            return "";
        }
    }

    /** The left-power rail of the ladder. Singleton. */
    public static final class LeftPowerRail implements DatTypesElement {
        public static final LeftPowerRail INSTANCE = new LeftPowerRail();

        private LeftPowerRail() {
        }

        @Override
        public String toString() {
            return "True";
        }
    }

    /** Represents an empty/no-op cell. Singleton. */
    public static final class Empty implements DatTypesElement {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
