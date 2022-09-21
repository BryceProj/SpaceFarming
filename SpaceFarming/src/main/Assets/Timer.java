package main.Assets;

public class Timer {
    public int[] maxTime;
    int counter = 0;
    public int current = 0;

    public Timer(int[] maxTime) {
        this.maxTime = maxTime;
    }

    public int update() {
        if(counter > maxTime[current]) {
            counter = 0;
            current++;
            if (current >= maxTime.length) {
                current = 0;
                return maxTime.length + 1;
            }
        } else {
            counter++;
        }
        return current;
    }

    public int size() {
        return maxTime.length;
    }
}
