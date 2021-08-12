import com.ipl.model.Match;
import com.ipl.model.Delivery;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Ipl {

    public  static List<Match>  getMatchData() throws IOException {
  List<Match>  matches=new ArrayList<>();
    String line="";

    int skip=0;
        BufferedReader bf= new BufferedReader(new FileReader("matches.csv"));
        while((line=bf.readLine())!=null) {
            Match match=new Match();
            if (skip == 0) {
                skip++;
//                System.out.println(line);
                continue;
            }
//         System.out.println(line);
            String[] matchData = line.split(",");
             match.setSeason(matchData[1]);
             match.setWinner(matchData[10]);
             match.setMatchId(matchData[0]);
             matches.add(match);
        }
//        System.out.println(matches.size());
//        for (Match m: matches) {
//            System.out.println(m.getSeason());
//        }
        return matches;
    }

    public  static List<Delivery>  getDeliveryData() throws IOException {
        List<Delivery>  deliveries=new ArrayList<>();
        String line="";

        int skip=0;
        BufferedReader bf= new BufferedReader(new FileReader("deliveries.csv"));
        while((line=bf.readLine())!=null) {
            Delivery delivery=new Delivery();
            if (skip == 0) {
                skip++;
//                System.out.println(line);
                continue;
            }
//         System.out.println(line);
            String[] deliveryData = line.split(",");
//            delivery.set(deliveryData[1]);
            delivery.setExtraRuns(deliveryData[16]);
            delivery.setMatchId(deliveryData[0]);
            delivery.setBowlingTeam(deliveryData[3]);
            deliveries.add(delivery);
        }
//        System.out.println(deliveries.size());
//        for (Delivery m: deliveries) {
//            System.out.println(m.getExtraRuns());
//        }
        return deliveries;
    }

    public static void  matchesPlayedPerYear(List<Match> matches){
        HashMap<String, Integer>   matchCountMap = new HashMap<String, Integer>();
        System.out.println("matches played per year");

        for (Match matchData:matches) {
            if (matchCountMap.containsKey(matchData.getSeason())) {
                matchCountMap.put(matchData.getSeason(), matchCountMap.get(matchData.getSeason()) + 1);
            } else {
                matchCountMap.put(matchData.getSeason(), 1);
            }
        }
        System.out.println(matchCountMap);
    }

    public static void matchesWonPerTeamPerYear( List<Match> matches) {
        Map<String, HashMap<String, Integer>> matchesWonPerTeam = new HashMap<String, HashMap<String, Integer>>();
        System.out.println("matches won per team per year");

        for (Match matchData : matches) {

            if (matchesWonPerTeam.containsKey(matchData.getSeason())) {
                if (matchesWonPerTeam.get(matchData.getSeason()).containsKey(matchData.getWinner())) {
                    matchesWonPerTeam.get(matchData.getSeason()).put(matchData.getWinner(), matchesWonPerTeam.get(matchData.getSeason()).get(matchData.getWinner()) + 1);

                } else {
                    matchesWonPerTeam.get(matchData.getSeason()).put(matchData.getWinner(), 1);
                }
            } else {
                matchesWonPerTeam.put(matchData.getSeason(), new HashMap<String, Integer>());
                matchesWonPerTeam.get(matchData.getSeason()).put(matchData.getWinner(), 1);
            }
        }
        System.out.println(matchesWonPerTeam);
    }

   public static void extraRunConceded(List<Match> matches, List<Delivery> deliveries){
       List<String> id= new ArrayList<String>();
       HashMap<String, Integer> extraRunsByBowlingTeam = new  HashMap<String, Integer>();

      int i=0;
       for (Match matchData : matches) {
//           System.out.println(matchData.getSeason());
         if(matchData.getSeason().equals("2016")){

//             System.out.println(matchData.getMatchId());
             id.add(matchData.getMatchId());
         }
       }
//       System.out.println(id);
//       System.out.println(years);
       for (Delivery delivery: deliveries) {
           if(id.contains(delivery.getMatchId())){
              if( extraRunsByBowlingTeam.containsKey(delivery.getBowlingTeam())){
//                  System.out.println(id);
//                  System.out.println(extraRunsByBowlingTeam.get(delivery.getBowlingTeam()));
                  extraRunsByBowlingTeam.put(delivery.getBowlingTeam(),extraRunsByBowlingTeam.get(delivery.getBowlingTeam()) + Integer.parseInt(delivery.getExtraRuns()));
                }
                else{
                    extraRunsByBowlingTeam.put(delivery.getBowlingTeam(),Integer.parseInt(delivery.getExtraRuns()));
                }
            }
        }
       System.out.println(extraRunsByBowlingTeam);

   }
    public static void main(String[] args) throws IOException {
        List<Match>  matchData=getMatchData();
        List<Delivery> deliveryData=getDeliveryData();
        matchesPlayedPerYear(matchData);
        matchesWonPerTeamPerYear(matchData);
        extraRunConceded(matchData,deliveryData);
    }
}


