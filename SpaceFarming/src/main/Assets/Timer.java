package main.Assets;

public class Timer {
    public int[] maxTime;
    public int counter = 0;
    public int current = 0;

    public boolean repeating = false;

    public Timer(int[] maxTime, boolean repeating) {
        this.maxTime = maxTime;
        this.repeating = repeating;
    }

    public int update() {
        if(counter > maxTime[current]) {
            counter = 0;
            current++;
            if (current >= maxTime.length) {
                current = 0;
                return repeating ? 0 : maxTime.length + 1;
            }
        } else {
            counter++;
        }
        return current;
    }
    public void reset() {
        counter = 0;
        current = 0;
    }

    public int size() {
        return maxTime.length;
    }

}
