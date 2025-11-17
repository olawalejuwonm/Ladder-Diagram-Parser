
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

        public static DataTypes.DT[][] buildExampleGrid3() {
                return new DataTypes.DT[][] {
                                {
                                                DataTypes.LeftPowerRail.INSTANCE,
                                                new DataTypes.Contact("x"),
                                                DataTypes.VerticalLink.INSTANCE,
                                                new DataTypes.Coil("r")
                                },
                                {
                                                DataTypes.LeftPowerRail.INSTANCE,
                                                new DataTypes.Contact("y"),
                                                DataTypes.EndOfVerticalLink.INSTANCE,
                                                DataTypes.Empty.INSTANCE
                                },
                                {
                                                DataTypes.LeftPowerRail.INSTANCE,
                                                new DataTypes.Contact("z"),
                                                DataTypes.EndOfVerticalLink.INSTANCE,
                                                DataTypes.Empty.INSTANCE
                                }
                };
        }

        public static DataTypes.DT[][] justHorizontal() {
                return new DataTypes.DT[][] {
                                {
                                                DataTypes.LeftPowerRail.INSTANCE,
                                                new DataTypes.Contact("a"),
                                                DataTypes.HorizontalLink.INSTANCE,
                                                new DataTypes.Contact("b"),
                                                DataTypes.HorizontalLink.INSTANCE,
                                                new DataTypes.Coil("c")
                                }
                };
        }

        public static void main(String[] args) {
                // DataTypes.DT[][] grid = justHorizontal();
                DataTypes.DT[][] grid = buildExampleGrid1();
                // DataTypes.DT[][] grid = buildExampleGrid2();
                // DataTypes.DT[][] grid = buildExampleGrid3();
                for (int r = 0; r < grid.length; r++) {
                        // System.out.print("Row " + r + ": ");
                        for (int c = grid[r].length - 1; c >= 0; c--) {
                                // set the nextLadderElement to the future one
                                // DataTypes.nextLadderElement = (c - 1 >= 0) ? grid[r][c - 1] : null;
                                // System.err.println("Next element at (" + r + "," + (c - 1) + "): " +
                                // DataTypes.nextLadderElement);
                                System.out.print(grid[r][c]);
                        }
                        // System.out.println();
                }
        }

}