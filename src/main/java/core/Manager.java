package core;

public abstract class Manager {
    public Manager() {}
    protected boolean isInputLocked = false;
    public boolean isInputLocked() { return this.isInputLocked; }
    public abstract int takeAction(String command);
}
