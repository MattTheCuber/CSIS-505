import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TooltipRenderer extends DefaultTableCellRenderer {
    // https://stackoverflow.com/a/9467372/9725459
    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                column);
        if (value != null) {
            setText(value.toString());
            setToolTipText(value.toString());
        }
        return this;
    }
}
