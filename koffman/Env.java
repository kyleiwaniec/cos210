import java.util.*;

public class Env {
    public static void main (String[] args) {
        for (String env: args) {
            String value = System.getenv(env);
            if (value != null) {
                System.out.format("%s=%s%n",
                                  env, value);
            } else {
                System.out.format("%s is"
                    + " not assigned.%n", env);
            }
        }


        Map<Character, String> opts = new HashMap<Character, String>(); 
            for(int i = 0; i < args.length-1; i+=2) {
              opts.put(args[i].charAt(1), args[i+1]);
            }
        System.out.println(opts.get('b'));
    }

}