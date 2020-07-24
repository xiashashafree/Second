import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    private static void check(String str){
        int begin = 0;
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while(begin<len){
            int i = begin;
            char ch= str.charAt(begin);
            while(i+1<len &&str.charAt(i) == str.charAt(i+1)){
                i++;
            }
            if(i-begin<1){
                sb.append(ch);
            }else{
                int j = i+1;
                sb.append(ch);
                sb.append(ch);
                while(j+1<len && str.charAt(j) == str.charAt(j+1)){
                    j++;
                }
                if(j-i >= 1 && i+1<len ){
                    sb.append(str.charAt(i+1));
                }
                i = j;
            }
            if(i == begin){
                begin++;
            }else{
                begin = i+1;
            }
        }
        System.out.println(sb.toString());
        sb = null;

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            List<String> list = new ArrayList<>();
            for(int i=0; i<num; i++){
                list.add(sc.next());
            }
            for(String s:list){
                check(s);
            }
        }
    }
}
