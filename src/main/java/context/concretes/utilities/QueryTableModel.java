package context.concretes.utilities;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.Map;
import java.util.TreeMap;

public class QueryTableModel implements TableModel {
    private final Map<String,String> map = new TreeMap<>();

    @Override
    public int getRowCount() {
        return 20;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        if(columnIndex==0)return "Key";
        return "Value";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
