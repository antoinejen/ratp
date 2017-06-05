/**
 * Created by antoine on 05/06/17.
 */
public class Transfers {
    private int from;
    private int to;
    private int transfer_time;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) { this.to = to; }

    public int getTransfer_time() {
        return transfer_time;
    }

    public void setTransfer_time(int transfer_time) {
        this.transfer_time = transfer_time;
    }

    @Override
    public String toString() {
        return "\nFrom=" + getFrom() + "::To=" + getTo() + "::Duration=" + getTransfer_time();
    }
}
