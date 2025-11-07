public class Parser {

    /**
     * Build the example 2D ladder grid using LadderElements types.
     *
     * Grid layout (2 rows x 6 columns):
     * [ left power rail , contact "x" , horizontal link , negated contact "y" , vertical link, coil "r" ],
     * [ left power rail, horizontal link , contact "z" , horizontal link , End of Vertical Link, empty ]
     */
    public static LadderElements.LadderElement[][] buildExampleGrid() {
        return new LadderElements.LadderElement[][]{
            {
                LadderElements.LeftPowerRail.INSTANCE,
                new LadderElements.Contact("x"),
                LadderElements.HorizontalLink.INSTANCE,
                new LadderElements.NegatedContact("y"),
                LadderElements.VerticalLink.INSTANCE,
                new LadderElements.Coil("r")
            },
            {
                LadderElements.LeftPowerRail.INSTANCE,
                LadderElements.HorizontalLink.INSTANCE,
                new LadderElements.Contact("z"),
                LadderElements.HorizontalLink.INSTANCE,
                LadderElements.EndOfVerticalLink.INSTANCE,
                LadderElements.Empty.INSTANCE
            }
        };
    }

    /** Simple demo runner to print the grid. */
    public static void main(String[] args) {
        LadderElements.LadderElement[][] grid = buildExampleGrid();

        System.out.println("Example ladder grid:");
        for (int r = 0; r < grid.length; r++) {
            System.out.print("Row " + r + ": [ ");
            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c]);
                if (c < grid[r].length - 1) System.out.print(" , ");
            }
            System.out.println(" ]");
        }
    }

}