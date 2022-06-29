package view;

public interface Listener {
    void exitTheGame();
    void clickNumberButton(String value, int row, int column);
    void passButtonListener();
    void recordsButton();
}
