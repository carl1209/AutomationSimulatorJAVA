package simulator.model;

public class ActuationCommand {
    private String cmd;
    public ActuationCommand(String cmd) { this.cmd = cmd; }
    @Override public String toString() { return cmd; }
}
