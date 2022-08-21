package player.tmplayer;

public class playTime {

    public String displayTime(double t) {
        String time = "";

        int hr = (int) (t/3600);
        int min = (int) ((t%3600) / 60);
        int sec = (int) ((t%3600) % 60);

        if (t>=3600) {
            if (sec < 10) {
                time = time + Integer.toString(hr) + ":" + Integer.toString(min) + ":" + "0" + Integer.toString(sec);
            } else if (min < 10) {
                time = Integer.toString(hr) + ":" +"0" + Integer.toString(min) + ":" + Integer.toString(sec);
            } else {
                time = Integer.toString(hr) + ":" + Integer.toString(min) + ":" + Integer.toString(sec);
            }
        } else {
            if (sec < 10) {
                time = Integer.toString(min) + ":" + "0" + Integer.toString(sec);
            } else if (min < 10) {
                time = "0" + Integer.toString(min) + ":" + Integer.toString(sec);
            } else {
                time = Integer.toString(min) + ":" + Integer.toString(sec);
            }
        }

        return time;
    }
}
