package pt.ipb.dsys.coordination.ring;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.ObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ipb.dsys.coordination.common.jgroups.DefaultProtocols;
import pt.ipb.dsys.coordination.common.jgroups.PeerUtil;
import pt.ipb.dsys.coordination.common.util.Sleeper;

import java.net.UnknownHostException;

public class RingTokenMain {

    private static final Logger logger = LoggerFactory.getLogger(RingTokenMain.class);

    private static final String CLUSTER_NAME = "ring.token";

    public static void main(String[] args) throws UnknownHostException {
        String gossipHostname = "ring-gossip-router";
        PeerUtil.localhostFix(gossipHostname);

        try (JChannel channel = new JChannel(DefaultProtocols.gossipRouter(gossipHostname, 12001))) {
            RingTokenHandler handler = new RingTokenHandler(channel);
            channel.setReceiver(handler);
            channel.setDiscardOwnMessages(true);

            // very important: set receiver before connecting
            channel.connect(CLUSTER_NAME);

            // optional: let cluster stabilize to reduce ambiguity, although it works all the same
            Sleeper.sleep(5000);

            while (true) {
                if (handler.hasToken() && !handler.alone()) {
                    // "spend" the token
                    handler.clearToken();
                    // send counter to next
                    handler.incrementCounter();
                    // send counter to everyone
                    logger.info("\t\t ({}) sending new counter to all: {}", channel.address(), handler.getCounter());
                    channel.send(new ObjectMessage(null, handler.getCounter()));
                    // send token to next in list
                    Address nextInRing = handler.getNext();
                    logger.info("\t ({}) sending token to: {}", channel.address(), nextInRing);
                    channel.send(new ObjectMessage(nextInRing, "token"));
                }

                Sleeper.sleep(1000);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}
