package view;

@FunctionalInterface
public interface ClickListener {
    void onClick(String value, int row, int column);
}
