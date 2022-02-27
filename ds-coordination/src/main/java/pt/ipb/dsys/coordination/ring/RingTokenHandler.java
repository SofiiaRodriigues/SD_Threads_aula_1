package pt.ipb.dsys.coordination.ring;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RingTokenHandler implements Receiver {

    private static final Logger logger = LoggerFactory.getLogger(RingTokenHandler.class);

    private final JChannel channel;

    private View view;

    private boolean hasToken = false;

    private long counter = 0;

    public RingTokenHandler(JChannel channel) {
        this.channel = channel;
    }

    @Override
    public void viewAccepted(View view) {
        this.view = view;
        // check whether I'm the first one here
        if (view.getMembers().size() == 1) {
            logger.info("# {} got the token first", channel.address());
            setToken();
        }
    }

    @Override
    public void receive(Message msg) {
        if (msg.getObject() instanceof Long) {
            this.counter = msg.getObject();
            logger.info("\t\t\t ({}) counter reset to: {} (src: {})", channel.address(), counter, msg.src());
        }

        if (msg.getObject() instanceof String) {
            logger.info("\t ({}) token received from: {}", channel.address(), msg.src());
            setToken();
        }
    }

    public boolean hasToken() {
        return hasToken;
    }

    public Address getNext() {
        if (view == null)
            return channel.address(); // I'm alone (probably)

        // sort members
        List<Address> sortedMembers = view
                .getMembers()
                .stream()
                .sorted()
                .collect(Collectors.toList());

        // find next index, circular list using '%'
        int meIdx = sortedMembers.indexOf(channel.address());
        int size = sortedMembers.size();
        int nextIdx = (meIdx + 1) % size;

        return sortedMembers.get(nextIdx);
    }

    public void setToken() {
        this.hasToken = true;
    }

    public void clearToken() {
        this.hasToken = false;
    }

    public boolean alone() {
        return view == null || view.getMembers().size() == 1;
    }

    public void incrementCounter() {
        counter++;
    }

    public long getCounter() {
        return counter;
    }

}
