import com.ipl.model.Match;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IplTest {

    @Test
   public void matchesPlayedPerYear() throws IOException {
        List<Match> matches=Ipl.getMatchData();

        HashMap<String, Integer>  expected = new HashMap<String, Integer>();
        expected.put("2009", 57);
        expected.put("2008", 58);
        expected.put("2017", 59);
        expected.put("2016", 60);
        expected.put("2015", 59);
        expected.put("2014", 60);
        expected.put("2013", 76);
        expected.put("2012", 74);
        expected.put("2011", 73);
        expected.put("2010", 60);

        assertEquals(Ipl.matchesPlayedPerYear(matches), expected);
    }
}