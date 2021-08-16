import com.ipl.model.Match;
import com.ipl.model.Delivery;
import org.w3c.dom.ls.LSOutput;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;


public class Ipl {
  public  static int matchId=0;
  public  static int inning=1;
   public static int  battingTeam=2;
  public  static int bowlingTeam=3;
  public  static int over=4;
   public static int ball=5;
 public   static int batsman=6;
 public static int non_striker=7;
   public static int bowler=8;
 public static int is_super_over=9;
  public  static int wide_runs=10;
  public static int bye_runs=11;
  public  static int legbye_runs=12;
  public  static int noball_runs=13;
  public  static int penalty_runs=14;
    public static int batsman_runs=15;
  public  static int extra_runs=16;
   public static int total_runs=17;
   public static int player_dismissed=18;
   public static int dismissal_kind=19;
    public static int fielder=20;


public static int id=0;
    public  static int season=1;
   public static int city=2;
   public static int date=3;
   public static int team1=4;
   public static int team2=5;
    public static int tossWinner=6;
   public static int tossDecision=7;
  public  static int result=8;
   public static int dlApplied=9;
   public static int winner=10;
   public static int winByRuns=11;
   public static int winByWicket=12;
  public  static int playerOfMatch=13;
   public static int venue=14;
  public  static int umpire1=15;
  public  static int umpire2=16;
  public  static int umpire3=17;

static String pathDeliveries="deliveries.csv";
static String matchesCsv="matches.csv";

    public static List<Match> getMatchData() throws IOException {
        List<Match> matches = new ArrayList<>();
        String line = "";

        int skip = 0;
        BufferedReader bf = new BufferedReader(new FileReader(matchesCsv));
        while ((line = bf.readLine()) != null) {
            Match match = new Match();
            if (skip == 0) {
                skip++;
//                System.out.println(line);
                continue;
            }
//         System.out.println(line);
            String[] matchData = line.split(",");
            match.setSeason(matchData[season]);
            match.setWinner(matchData[winner]);
            match.setMatchId(matchData[matchId]);
            matches.add(match);
        }
//        System.out.println(matches.size());
//        for (Match m: matches) {
//            System.out.println(m.getSeason());
//        }
        return matches;
    }

    public static List<Delivery> getDeliveryData() throws IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String line = "";

        int skip = 0;
        BufferedReader bf = new BufferedReader(new FileReader(pathDeliveries));
        while ((line = bf.readLine()) != null) {
            Delivery delivery = new Delivery();
            if (skip == 0) {
                skip++;
//                System.out.println(line);
                continue;
            }
//         System.out.println(line);
            String[] deliveryData = line.split(",");
//            delivery.set(deliveryData[1]);
            delivery.setExtraRuns(deliveryData[extra_runs]);
            delivery.setMatchId(deliveryData[matchId]);
            delivery.setBowlingTeam(deliveryData[bowlingTeam]);
            delivery.setBowler(deliveryData[bowler]);
            delivery.setBall(deliveryData[ball]);
            delivery.setTotalRuns((deliveryData[total_runs]));
            deliveries.add(delivery);
        }
//        System.out.println(deliveries.size());
//        for (Delivery m: deliveries) {
//            System.out.println(m.getExtraRuns());
//        }
        return deliveries;
    }

    public static  HashMap<String, Integer> matchesPlayedPerYear(List<Match> matches) {
        HashMap<String, Integer> matchCountMap = new HashMap<String, Integer>();
        System.out.println("matches played per year");

        for (Match matchData : matches) {
            if (matchCountMap.containsKey(matchData.getSeason())) {
                matchCountMap.put(matchData.getSeason(), matchCountMap.get(matchData.getSeason()) + 1);
            } else {
                matchCountMap.put(matchData.getSeason(), 1);
            }
        }
        return matchCountMap;
    }

    public static void matchesWonPerTeamPerYear(List<Match> matches) {
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

    public static void extraRunConceded(List<Match> matches, List<Delivery> deliveries) {
        List<String> id = new ArrayList<String>();
        HashMap<String, Integer> extraRunsByBowlingTeam = new HashMap<>();
        for (Match matchData : matches) {
//           System.out.println(matchData.getSeason());
            if (matchData.getSeason().equals("2016")) {
//             System.out.println(matchData.getMatchId());
                id.add(matchData.getMatchId());
            }
        }
//       System.out.println(id);
//       System.out.println(years);
        for (Delivery delivery : deliveries) {
            if (id.contains(delivery.getMatchId())) {
                if (extraRunsByBowlingTeam.containsKey(delivery.getBowlingTeam())) {
//                  System.out.println(id);
//                  System.out.println(extraRunsByBowlingTeam.get(delivery.getBowlingTeam()));
                    extraRunsByBowlingTeam.put(delivery.getBowlingTeam(), extraRunsByBowlingTeam.get(delivery.getBowlingTeam()) + Integer.parseInt(delivery.getExtraRuns()));
                } else {
                    extraRunsByBowlingTeam.put(delivery.getBowlingTeam(), Integer.parseInt(delivery.getExtraRuns()));
                }
            }
        }
        System.out.println(extraRunsByBowlingTeam);

    }
    public static HashMap<String, Float> sortByValue(HashMap<String, Float> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Float> > list =
                new LinkedList<Map.Entry<String, Float> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Float> >() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Float> temp = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }




    public static void topTenEconomicalBowler(List<Match> matches, List<Delivery> deliveries) {
        List<String> id = new ArrayList<String>();
        HashMap<String, Float> topEcomnomicalBowler = new HashMap<>();
        ArrayList<Float> EcoRate = new ArrayList<Float>();

        HashMap<String, Integer> totalRuns = new HashMap<String, Integer>();
        HashMap<String, Integer> totalBalls = new HashMap<String, Integer>();
        for (Match matchData : matches) {
            if (matchData.getSeason().equals("2015")) {
                id.add(matchData.getMatchId());
            }
        }
        for (Delivery delivery : deliveries) {
            if (id.contains(delivery.getMatchId())) {
                if (totalRuns.containsKey(delivery.getBowler())) {
                    totalBalls.put(delivery.getBowler(), totalRuns.get(delivery.getBowler()) + Integer.parseInt(delivery.getTotalRuns()));
                    totalRuns.put(delivery.getBowler(), totalBalls.get(delivery.getBowler()) + Integer.parseInt(delivery.getTotalRuns()));
                } else {
                    totalRuns.put(delivery.getBowler(), Integer.parseInt(delivery.getTotalRuns()));
                    totalBalls.put(delivery.getBowler(), Integer.parseInt(delivery.getBall()));
                }
            }
        }
        for (String key : totalRuns.keySet()) {
            topEcomnomicalBowler.put(key, totalRuns.get(key).floatValue() / (Float) totalBalls.get(key).floatValue() / 6);
            EcoRate.add(totalRuns.get(key).floatValue() / (Float) totalBalls.get(key).floatValue() / 6);
        }
        HashMap<String, Float> topTen = sortByValue(topEcomnomicalBowler);
//        System.out.println(topTen);

        Set<Map.Entry<String,Float>> x=topTen.entrySet();
        int i=0;
        for(Map.Entry<String,Float> y:x){
            if(i<10){
                System.out.println(y.getKey()+" "+y.getValue());
                i++;
            }else{
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        List<Match> matchData = getMatchData();
        List<Delivery> deliveryData = getDeliveryData();
        matchesPlayedPerYear(matchData);
        matchesWonPerTeamPerYear(matchData);
        extraRunConceded(matchData,deliveryData);
        topTenEconomicalBowler(matchData, deliveryData);
    }
}


