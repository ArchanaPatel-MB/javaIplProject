import com.ipl.model.Match;
import com.ipl.model.Delivery;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Ipl {
    public static int matchId = 0;
    public static int inning = 1;
    public static int battingTeam = 2;
    public static int bowlingTeam = 3;
    public static int over = 4;
    public static int ball = 5;
    public static int batsman = 6;
    public static int non_striker = 7;
    public static int bowler = 8;
    public static int is_super_over = 9;
    public static int wide_runs = 10;
    public static int bye_runs = 11;
    public static int legbye_runs = 12;
    public static int noball_runs = 13;
    public static int penalty_runs = 14;
    public static int batsman_runs = 15;
    public static int extra_runs = 16;
    public static int total_runs = 17;
    public static int player_dismissed = 18;
    public static int dismissal_kind = 19;
    public static int fielder = 20;


    public static int id = 0;
    public static int season = 1;
    public static int city = 2;
    public static int date = 3;
    public static int team1 = 4;
    public static int team2 = 5;
    public static int tossWinner = 6;
    public static int tossDecision = 7;
    public static int result = 8;
    public static int dlApplied = 9;
    public static int winner = 10;
    public static int winByRuns = 11;
    public static int winByWicket = 12;
    public static int playerOfMatch = 13;
    public static int venue = 14;
    public static int umpire1 = 15;
    public static int umpire2 = 16;
    public static int umpire3 = 17;

    static String pathDeliveries = "deliveries.csv";
    static String matchesCsv = "matches.csv";

    public List<Match> getMatchData() throws IOException {
        List<Match> matches = new ArrayList<>();
        String line = "";

        int skip = 0;
        BufferedReader bf = new BufferedReader(new FileReader(matchesCsv));
        while ((line = bf.readLine()) != null) {
            Match match = new Match();
            if (skip == 0) {
                skip++;
                continue;
            }
            String[] matchData = line.split(",");
            match.setSeason(matchData[season]);
            match.setWinner(matchData[winner]);
            match.setMatchId(matchData[matchId]);
            matches.add(match);
        }
//
        return matches;
    }

    public  List<Delivery> getDeliveryData() throws IOException {
        List<Delivery> deliveries = new ArrayList<>();
        String line = "";

        int skip = 0;
        BufferedReader bf = new BufferedReader(new FileReader(pathDeliveries));
        while ((line = bf.readLine()) != null) {
            Delivery delivery = new Delivery();
            if (skip == 0) {
                skip++;
                continue;
            }
            String[] deliveryData = line.split(",");
            delivery.setExtraRuns(deliveryData[extra_runs]);
            delivery.setMatchId(deliveryData[matchId]);
            delivery.setBowlingTeam(deliveryData[bowlingTeam]);
            delivery.setBowler(deliveryData[bowler]);
            delivery.setBall(deliveryData[ball]);
            delivery.setTotalRuns((deliveryData[total_runs]));
            deliveries.add(delivery);
        }
        return deliveries;
    }

    public  HashMap<String, Integer> matchesPlayedPerYear(List<Match> matches) {
        if(matches.equals(null))
        {
//            throw new NullPointerException();
            return null;
        }
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

    public Map<String, HashMap<String, Integer>> matchesWonPerTeamPerYear(List<Match> matches) {
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
        return matchesWonPerTeam;
    }

    public  HashMap<String, Integer> extraRunConceded(List<Match> matches, List<Delivery> deliveries) {
        List<String> id = new ArrayList<String>();
        HashMap<String, Integer> extraRunsByBowlingTeam = new HashMap<>();
        for (Match matchData : matches) {
            if (matchData.getSeason().equals("2016")) {
                id.add(matchData.getMatchId());
            }
        }
        for (Delivery delivery : deliveries) {
            if (id.contains(delivery.getMatchId())) {
                if (extraRunsByBowlingTeam.containsKey(delivery.getBowlingTeam())) {
                    extraRunsByBowlingTeam.put(delivery.getBowlingTeam(), extraRunsByBowlingTeam.get(delivery.getBowlingTeam()) + Integer.parseInt(delivery.getExtraRuns()));
                } else {
                    extraRunsByBowlingTeam.put(delivery.getBowlingTeam(), Integer.parseInt(delivery.getExtraRuns()));
                }
            }
        }
        return extraRunsByBowlingTeam;

    }

    public static HashMap<String, Float> sortByValue(HashMap<String, Float> hm) {
        List<Map.Entry<String, Float>> list =
                new LinkedList<Map.Entry<String, Float>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Float>>() {
            public int compare(Map.Entry<String, Float> o1,
                               Map.Entry<String, Float> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<String, Float> temp = new LinkedHashMap<String, Float>();
        for (Map.Entry<String, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    public Map<String, Float> topTenEconomicalBowler(List<Match> matches, List<Delivery> deliveries) {
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
        for (String key : totalRuns.keySet())
        {
            topEcomnomicalBowler.put(key, totalRuns.get(key).floatValue() / (Float) totalBalls.get(key).floatValue() / 6);
            EcoRate.add(totalRuns.get(key).floatValue() / (Float) totalBalls.get(key).floatValue() / 6);
        }
//        System.out.println(topEcomnomicalBowler );
//
//        List<Map.Entry<String, Float>> topTen = new ArrayList<>(topEcomnomicalBowler.entrySet());
//        System.out.println(topTen);
//        Collections.sort(topTen, new Comparator<Map.Entry<String, Float>>() {
//            public int compare(Map.Entry<String, Float> c2,Map.Entry<String, Float> c1)
//            {
//
//                return c1.getValue().compareTo(c2.getValue());
//            }
//        });



        HashMap<String, Float> topTen = sortByValue(topEcomnomicalBowler);
//        System.out.println(topTen);
        Map<String, Float> finalBowlers = new HashMap<>();
        Set<Map.Entry<String, Float>> x = topTen.entrySet();
        int i = 0;
        for (Map.Entry<String, Float> y : x) {
            if (i < 10) {
                finalBowlers.put(y.getKey(), y.getValue());
                i++;
            } else {
                break;
            }
        }
        return finalBowlers;
    }

    public static void main(String[] args) throws IOException {
        Ipl ipl = new Ipl();
        List<Match> matchData = ipl.getMatchData();
        List<Delivery> deliveryData = ipl.getDeliveryData();
//        System.out.println(ipl.matchesPlayedPerYear(matchData));
//        System.out.println(ipl.matchesWonPerTeamPerYear(matchData));
//        System.out.println(ipl.extraRunConceded(matchData, deliveryData));
        System.out.println(ipl.topTenEconomicalBowler(matchData, deliveryData));
    }
}


