
public class Parser {

    public static DataTypes.DT[][] buildExampleGrid1() {
        return new DataTypes.DT[][] {
                {
                        DataTypes.LeftPowerRail.INSTANCE,
                        new DataTypes.Contact("x"),
                        DataTypes.HorizontalLink.INSTANCE,
                        new DataTypes.NegatedContact("y"),
                        DataTypes.VerticalLink.INSTANCE,
                        new DataTypes.Coil("r")
                },
                {
                        DataTypes.LeftPowerRail.INSTANCE,
                        DataTypes.HorizontalLink.INSTANCE,
                        new DataTypes.Contact("z"),
                        DataTypes.HorizontalLink.INSTANCE,
                        DataTypes.EndOfVerticalLink.INSTANCE,
                        DataTypes.Empty.INSTANCE
                }
        };
    }

    // P6.SG = (P6.QS /\ True) V P6.SG /\ ¬P6.QX /\ True
    public static DataTypes.DT[][] buildExampleGrid2() {
        return new DataTypes.DT[][] {
                {
                        DataTypes.LeftPowerRail.INSTANCE,
                        new DataTypes.Contact("P6.QS"),
                        DataTypes.HorizontalLink.INSTANCE,
                        DataTypes.HorizontalLink.INSTANCE,
                        DataTypes.VerticalLink.INSTANCE,
                        new DataTypes.Coil("P6.SG")
                },
                {
                        DataTypes.LeftPowerRail.INSTANCE,
                        new DataTypes.NegatedContact("P6.QX"),
                        DataTypes.HorizontalLink.INSTANCE,
                        new DataTypes.Contact("P6.SG"),
                        DataTypes.EndOfVerticalLink.INSTANCE,
                        DataTypes.Empty.INSTANCE
                }
        };
    }

    public static void main(String[] args) {
        DataTypes.DT[][] grid = buildExampleGrid2();

        for (int r = 0; r < grid.length; r++) {
            // System.out.print("Row " + r + ": ");
            for (int c = grid[r].length - 1; c >= 0; c--) {
                System.out.print(grid[r][c]);
            }
            // System.out.println();
        }
    }

}