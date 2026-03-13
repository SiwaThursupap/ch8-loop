public class ForEach {

    public static void main(String[] args) {
        double[] score = {85.5, 92.0, 78.5};
        for (int i = 0; i< score.length; i++){
            IO.println(score[i]);
        }

        for(int i = 0 ;i < score.length;i++);{
            IO.println(score);
        }

    }
}
