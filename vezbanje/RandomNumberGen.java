import java.util.Arrays;
class RandomNumberGen {
    public static void main(String[] args) {
        int[] brojevi = new int[6];
        for(int i = 0; i<6;i++){
            int n=0;
            brojevi[i] = (int) (Math.random()*48);
            while(n<i){
                if(brojevi[n] == brojevi[i]){
                    brojevi[i] = (int) (Math.random() * 48);
                }
                n++;
            }
            if(brojevi[i]==0){
                brojevi[i] = (int) (Math.random() * 48);
            }
        }
        Arrays.sort(brojevi);
        Svetovid.out.println(brojevi);
    }
}