import java.util.*;

public class SinCos {
    // Test driver for the class SinCos
    // Boundries: 
    // Excactly on the boundry: x = 0, x = 45, x = 90, x = 180
    // Close to the Boundires: x = -1, x = 1, x = 44, x = 46, x = 89, x = 91, x = 179, x = 181
    // Mid boundries: x = -25, x = 25, x = 65, x = 125, x = 225
    public static void main(String[] args) {

        int[] testData = {0, 45, 90, 180, -1, 1, 44, 46, 89, 91, 179, 181, -25, 65, 125, 225};
        for(int i = 0; i < testData.length; i++){
            switch(testData[i]){
                case 0 : if(sin(testData[i]) == 1){
                                System.out.println("SUCCESS for SIN of: "+testData[i]);
                            }else{
                                System.out.println("FAIL for SIN of: "+testData[i]);
                            }
                            if(cos(testData[i]) == 2){
                                System.out.println("SUCCESS for COS of: "+testData[i]);
                            }else{
                                System.out.println("FAIL for COS of: "+testData[i]);
                            }
                break;
                case 45 : if(sin(testData[i]) == 1){
                                System.out.println("SUCCESS for SIN of: "+testData[i]);
                            }else{
                                System.out.println("FAIL for SIN of: "+testData[i]);
                            }
                            if(cos(testData[i]) == 1){
                                System.out.println("SUCCESS for COS of: "+testData[i]);
                            }else{
                                System.out.println("FAIL for COS of: "+testData[i]);
                            }
                break;
                case 90 : if(sin(testData[i]) == 2){
                                System.out.println("SUCCESS for SIN of: "+testData[i]);
                            }else{
                                System.out.println("FAIL for SIN of: "+testData[i]);
                            }
                            if(cos(testData[i]) == 1){
                                System.out.println("SUCCESS for COS of: "+testData[i]);
                            }else{
                                System.out.println("FAIL for COS of: "+testData[i]);
                            }
                break;


                // RINSE LATHER REPEAT:

                case 180 : System.out.println("180: "+testData[i]);
                break;
                case -1 : System.out.println("-1: "+testData[i]);
                break;
                case 1 : System.out.println("1: "+testData[i]);
                break;
                case 44 : System.out.println("44: "+testData[i]);
                break;
                case 46 : System.out.println("46: "+testData[i]);
                break;
                case 89 : System.out.println("89: "+testData[i]);
                break;
                case 91 : System.out.println("91: "+testData[i]);
                break;
                case 179 : System.out.println("179: "+testData[i]);
                break;
                case 181 : System.out.println("181: "+testData[i]);
                break;
                case -25 : System.out.println("-25: "+testData[i]);
                break;
                case 65 : System.out.println("65: "+testData[i]);
                break;
                case 125 : System.out.println("125: "+testData[i]);
                break;
                case 225 : System.out.println("225: "+testData[i]);
                break;
            }
        }
    }

    public static int sin(int x){
        if(x<0){
            x = -x;
        }
        x = x % 360;
        if(0 <= x && x <=45){
            return sin0to45(x);
        }else if(45 <=x && x <= 90){
            return sin45to90(x);
        }else if(90 <=x && x <= 180){
            return sin(180-x);
        }else{
            return -sin(x - 180);
        }
    }
    public static int cos(int x){
        return sin(x+90);
    }
    private static int sin0to45(int x){
        //STUB
        System.out.println("excecuting sin0to45()");
        return 1;
    }
    private static int sin45to90(int x){
        //STUB
        System.out.println("excecuting sin45to90()");
        return 2;
    }

    public static void parseArgs(String[] args){
         Map<Character, String> opts = new HashMap<Character, String>(); 
            for(int i = 0; i < args.length-1; i+=2) {
              opts.put(args[i].charAt(1), args[i+1]);
            }
        System.out.println(opts.get('b'));
    }
   
}