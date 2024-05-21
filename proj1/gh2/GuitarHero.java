package gh2;
import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        ArrayDeque<GuitarString> keyArray = new ArrayDeque<>();
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for (int i = 0; i < 37; i++) {
            GuitarString string = new GuitarString(440 * Math.pow(2, (i - 24)/12));
            keyArray.addLast(string);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) {
                    keyArray.get(keyboard.indexOf(key)).pluck();
                }
            }
            double sample = 0;
            for (int j = 0; j < 37; j++) {
                sample += keyArray.get(j).sample();
            }
            StdAudio.play(sample);
            for (int k = 0; k < 37; k++) {
                keyArray.get(k).tic();
            }
        }
    }
}
