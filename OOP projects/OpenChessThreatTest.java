package assignment1;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static assignment1.ChessThreat.CheckThreats;

public class ChessThreatTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void bishopThreat1_2P(){
        boolean threat = CheckThreats(1,0,0,2,2);
        Assert.assertTrue(threat);
    }

    @Test
    public void rookNoThreat1_2P(){
        boolean threat = CheckThreats(2,0,0,2,2);
        Assert.assertFalse(threat);
    }

    @Test
    public void knightThreat_2P(){
        boolean threat = CheckThreats(3,0,0,1,2);
        Assert.assertTrue(threat);
    }
}
