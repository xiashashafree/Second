package AVL;

import java.util.*;

//public class Main {
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNext()){
//
//            int count = sc.nextInt() ;
//            int buy = sc.nextInt();
//            List<Integer> list = new ArrayList<>();
//            for(int i=0;i<count;i++){
//                list.add(sc.nextInt()) ;
//            }
//            Collections.sort(list);
//            HashMap<String,Integer> map = new HashMap<>();
//            for(int i=0;i<buy;i++){
//                String name = sc.next() ;
//                map.put(name,map.getOrDefault(name,0)+1);
//
//
//            }
//            List<Map.Entry<String,Integer>> goods= new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
//            Collections.sort(goods,new Comparator<Map.Entry<String,Integer>> (){
//
//                @Override
//                public int compare(Map.Entry<String,Integer > o1,Map.Entry<String,Integer> o2){
//                    return o2.getValue()-o1.getValue();
//                }
//
//
//            });
//
//            int min = 0;
//            int max = 0;
//            for( int i=0;i<buy;i++){
//                min +=goods.get(i).getValue() *list.get(i);
//                max +=goods.get(i).getValue()*list.get(count-1-i);
//
//            }
//            System.out.println(min+" "+max);
//        }
//
//    }
//}
import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int num = sc.nextInt();
            TreeMap<Integer,Integer> map = new TreeMap<>(new Comparator<Map.Entry<Integer,Integer>>(){
                @Override
                public int compare(Map.Entry<Integer,Integer> o1,Map.Entry<Integer,Integer> o2){
                    return o2.getValue()-o1.getValue();

                }
            });
            for(int i=0;i<num;i++){
                int key = sc.nextInt();
                map.put(key,map.getOrDefault(key,0)+1);
            }
            List<Integer> list = new ArrayList<>();
            for(int key:map.keySet()){
                if(map.get(key)%2 == 0){
                    for(int i=0;i<map.get(key);i++){
                        list.add(key);
                    }
                }
            }
            if(list.size() == 0){
                System.out.println(-1);
            }else{
                System.out.println( list.get(0)*list.get(2));
            }


        }
    }
}