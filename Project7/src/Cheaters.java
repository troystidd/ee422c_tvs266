import java.io.File;
import java.util.*;

public class Cheaters {
    public static void main(String[] args) throws Exception{
        //System.out.println(System.getProperty("user.dir"));
        String filename = args[0];
        int n = Integer.parseInt(args[1]);
        File dir = new File(filename);
        Map<Integer, Set<String>> map = new HashMap<>();
        int fileCount = dir.listFiles().length;
        Map<String, Integer> hits = new HashMap<>();

        for (File file : dir.listFiles()) {
            Scanner sc = new Scanner(file);
            hits.put(file.getName(), 0);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < n && sc.hasNext(); i++) {
                StringBuilder sb = new StringBuilder(sc.next().toUpperCase());
                for (int j = 0; j < sb.length(); j++) {
                    if(sb.charAt(j)<'A' || sb.charAt(j) > 'Z'){
                        sb.deleteCharAt(j);
                        j--;
                    }

                }
                list.add(sb.toString());
            }
            String s = "";
            for(String e : list)
                s+=e;
            //System.out.println(s.toUpperCase());
            int hash = s.hashCode();
            if(map.containsKey(hash)){
                Set val = map.get(hash);
                val.add(file.getName());
            }
            else{
                Set<String> val = new HashSet<>();
                val.add(file.getName());
                map.put(hash, val);
            }
            while(sc.hasNext()){
                list.remove(0);
                StringBuilder sb = new StringBuilder(sc.next().toUpperCase());
                for (int j = 0; j < sb.length(); j++) {
                    if(sb.charAt(j)<'A' || sb.charAt(j) > 'Z'){
                        sb.deleteCharAt(j);
                        j--;
                    }

                }
                list.add(sb.toString());
                s = "";
                for(String e : list)
                    s+=e;
                //System.out.println(s.toUpperCase());
                hash = s.hashCode();
                if(map.containsKey(hash)){
                    Set val = map.get(hash);
                    val.add(file.getName());
                }
                else{
                    Set<String> val = new HashSet<>();
                    val.add(file.getName());
                    map.put(hash, val);
                }
            }
            sc.close();
        }

        Set<Integer> keys = map.keySet();
        for(int i : keys){
            Set<String> val = map.get(i);
            for(String s : val){
                if(hits.containsKey(s)){
                    int count = hits.get(s);
                    hits.put(s, count+1);
                }
                else{
                    hits.put(s, 1);
                }
            }
        }

        Set<String> hitKeys = hits.keySet();
        for(String s : hitKeys){
            System.out.println(s+": " + hits.get(s));
        }
        //Scanner sc = new Scanner(new File(filename));
        //System.out.println(filename);
        //System.out.println(n);
        //System.out.println(sc.hasNext());
        /*
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n && sc.hasNext(); i++) {
            StringBuilder sb = new StringBuilder(sc.next().toUpperCase());
            for (int j = 0; j < sb.length(); j++) {
                if(sb.charAt(j)<'A' || sb.charAt(j) > 'Z'){
                    sb.deleteCharAt(j);
                    j--;
                }

            }
            list.add(sb.toString());
        }
        String s = "";
        for(String e : list)
            s+=e;
        System.out.println(s.toUpperCase());
        while(sc.hasNext()){
            list.remove(0);
            StringBuilder sb = new StringBuilder(sc.next().toUpperCase());
            for (int j = 0; j < sb.length(); j++) {
                if(sb.charAt(j)<'A' || sb.charAt(j) > 'Z'){
                    sb.deleteCharAt(j);
                    j--;
                }

            }
            list.add(sb.toString());
            s = "";
            for(String e : list)
                s+=e;
            System.out.println(s.toUpperCase());
        }*/
        //System.out.println(sc.hasNext());

    }
}
