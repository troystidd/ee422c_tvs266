import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Cheaters {
    public static void main(String[] args) throws Exception{
        //System.out.println(System.getProperty("user.dir"));
        String filename = args[0];
        int n = Integer.parseInt(args[1]);
        Scanner sc = new Scanner(new File(filename));
        System.out.println(filename);
        System.out.println(n);
        System.out.println(sc.hasNext());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n && sc.hasNext(); i++) {
            list.add(sc.next());
        }
        String s = "";
        for(String e : list)
            s+=e;
        System.out.println(s.toUpperCase());
        while(sc.hasNext()){
            list.remove(0);
            list.add(sc.next());
            s = "";
            for(String e : list)
                s+=e;
            System.out.println(s.toUpperCase());
        }

    }
}
