import com.ipl.model.Delivery;
import com.ipl.model.Match;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class IplTest {

    @Test
    public void testGetMatchData() throws IOException {
        Ipl ipl = new Ipl();
        assertNotNull(ipl.getMatchData());
    }

    @Test
    public void testGetDeliveriesData() throws IOException {
        Ipl ipl = new Ipl();
        assertNotNull(ipl.getDeliveryData());
    }

    @Test
    public void testMatchesPlayedNotNull() throws IOException {
        Ipl ipl = new Ipl();
        List<Match> helper=new ArrayList<>();
        List<Match> matches = ipl.getMatchData();
        for(int i=0;i<10;i++){
            helper.add(matches.get(i));
        }
        assertNotNull(ipl.matchesPlayedPerYear(helper));
    }

    @Test
    public void testMatchesPlayedPerYear() throws IOException {
        Ipl ipl = new Ipl();
        List<Match> helper=new ArrayList<>();
        List<Match> matches = ipl.getMatchData();
        for(int i=0;i<10;i++){
            helper.add(matches.get(i));
        }
        HashMap<String, Integer> expected = new HashMap<String, Integer>();
        expected.put("2017", 10);
        assertEquals(expected,ipl.matchesPlayedPerYear(helper));
    }

    @Test
    public void testForNullParam() throws IOException {
        Ipl ipl = new Ipl();
       // List<Match> m=null;
        List<Match> matches = ipl.getMatchData();
        assertThrows(Exception.class, () -> ipl.matchesPlayedPerYear(null));
//        assertEquals(matches, exception.getMessage());
    }

    @Test
    public void testForMatchesWon() throws IOException {
        Ipl ipl = new Ipl();
        List<Match> helper=new ArrayList<>();
        List<Match> matches = ipl.getMatchData();
        for(int i=0;i<10;i++){
            helper.add(matches.get(i));
        }
        Map<String, HashMap<String, Integer>> expected = new HashMap<String, HashMap<String, Integer>>();
        expected.put("2017", new HashMap<String, Integer>());
        expected.get("2017").put("Mumbai Indians",2);
        expected.get("2017").put("Rising Pune Supergiant",1);
        expected.get("2017").put("Sunrisers Hyderabad",2);
        expected.get("2017").put("Kings XI Punjab",2);
        expected.get("2017").put("Delhi Daredevils",1);
        expected.get("2017").put("Kolkata Knight Riders",1);
        expected.get("2017").put("Royal Challengers Bangalore",1);
        assertEquals(expected,ipl.matchesWonPerTeamPerYear(helper));

    }

    @Test
    public void testFOrMatchesWonReturningNullValue() throws IOException {
        Ipl ipl = new Ipl();
        List<Match> helper=new ArrayList<>();
        List<Match> matches = ipl.getMatchData();
        for(int i=0;i<10;i++){
            helper.add(matches.get(i));
        }
        assertNotNull(ipl.matchesWonPerTeamPerYear(helper));
    }


        @Test
    public void extraRunConceded() throws IOException {
            Ipl ipl = new Ipl();
            List<Match> matchesHelper=new ArrayList<>();
            List<Delivery> deliveryHelper=new ArrayList<>();
            List<Match> matches = ipl.getMatchData();
            List<Delivery> delivery = ipl.getDeliveryData();
            System.out.println(matches.size());
            for(int i=577;i<600;i++){
                matchesHelper.add(matches.get(i));
            }
            for(int i=136366;i<136700;i++){
                deliveryHelper.add(delivery.get(i));
            }

            HashMap<String, Integer> expected=new HashMap<>();
            expected.put("Delhi Daredevils",1);
            expected.put("Kolkata Knight Riders",12);
            System.out.println(ipl.extraRunConceded(matchesHelper,deliveryHelper));
            assertEquals(expected,ipl.extraRunConceded(matchesHelper,deliveryHelper));

        }



    @Test
    public void testTopTenEconomicalBowler() throws IOException {
        Ipl ipl = new Ipl();
        List<Match> matchesHelper=new ArrayList<>();
        List<Delivery> deliveryHelper=new ArrayList<>();
        List<Match> matches = ipl.getMatchData();
        List<Delivery> delivery = ipl.getDeliveryData();
        System.out.println(matches.size());
        for(int i=518;i<560;i++){
            matchesHelper.add(matches.get(i));
        }
        for(int i=122714;i<126700;i++){
            deliveryHelper.add(delivery.get(i));
        }
        System.out.println(ipl.topTenEconomicalBowler(matches,delivery));

    }

}
