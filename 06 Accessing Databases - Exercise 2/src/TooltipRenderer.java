// Accessing Databases - Exercise 2
// Matthew Vine
// CSIS 505-B01 (Liberty University)
// June 21, 2024

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Custom cell renderer extending the DefaultTableCellRenderer class that
 * displays a tooltip with the cell value when the cell is hovered over.
 */
public class TooltipRenderer extends DefaultTableCellRenderer {
    // Override the getTableCellRendererComponent method to set the cell value as
    // the tooltip text.
    // Reference: https://stackoverflow.com/a/9467372/9725459
    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        // Call the superclass method to set the cell value.
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                column);
        // Set the tooltip text to the cell value if it is present.
        if (value != null) {
            setText(value.toString());
            setToolTipText(value.toString());
        }
        return this;
    }
}
