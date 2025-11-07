public class LadderElementsDemo {
    public static void main(String[] args) {
        // Create variable-bearing elements
        LadderElements.Coil coil = new LadderElements.Coil("M1");
        LadderElements.Contact contact = new LadderElements.Contact("X0");
        LadderElements.NegatedContact negContact = new LadderElements.NegatedContact("X1");

        // Use singletons for structural elements
        LadderElements.VerticalLink vlink = LadderElements.VerticalLink.INSTANCE;
        LadderElements.EndOfVerticalLink endV = LadderElements.EndOfVerticalLink.INSTANCE;
        LadderElements.HorizontalLink hlink = LadderElements.HorizontalLink.INSTANCE;
        LadderElements.LeftPowerRail leftRail = LadderElements.LeftPowerRail.INSTANCE;
        LadderElements.Empty empty = LadderElements.Empty.INSTANCE;

        // Print examples
        System.out.println("--- Ladder Elements Demo ---");
        System.out.println(coil);
        System.out.println(contact);
        System.out.println(negContact);
        System.out.println(vlink);
        System.out.println(endV);
        System.out.println(hlink);
        System.out.println(leftRail);
        System.out.println(empty);

        // Equality/hash demonstration
        LadderElements.Coil coil2 = new LadderElements.Coil("M1");
        System.out.println("coil equals coil2: " + coil.equals(coil2) + ", hashCodes: " + coil.hashCode() + ", " + coil2.hashCode());
        LadderElements.Contact contact2 = new LadderElements.Contact("X0");
        System.out.println("contact equals contact2: " + contact.equals(contact2));

        // Negated contact still compares by var (type differs but equality from parent applies)
        LadderElements.Contact negAsContact = new LadderElements.NegatedContact("X1");
        System.out.println("negContact (subclass) equals negAsContact: " + negContact.equals(negAsContact));
    }
}
